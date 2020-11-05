// Server side implementation of UDP client-server model 
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>

#define PORT 5001

#define LSH_TOK_BUFSIZE 64
#define LSH_TOK_DELIM " \n\t"

char **sh_split_line(char *line) { // Fonction qui split les arguments en token
    int bufsize = LSH_TOK_BUFSIZE, position = 0;
    char **tokens = malloc(bufsize * sizeof(char *));
    char *token;

    if (!tokens) {
        fprintf(stderr, "lsh: allocation error\n");
        exit(EXIT_FAILURE);
    }

    token = strtok(line, LSH_TOK_DELIM);

    while (token != NULL) {
        tokens[position] = token;
        position++;

        if (position >= bufsize) {
            bufsize += LSH_TOK_BUFSIZE;
            tokens = realloc(tokens, bufsize * sizeof(char *));
            if (!tokens) {
                fprintf(stderr, "lsh: allocation error\n");
                exit(EXIT_FAILURE);
            }
        }
        token = strtok(NULL, LSH_TOK_DELIM);
    }
    tokens[position] = NULL;
    return tokens;
}

int main(int argc, char *argv[]) { 
    /*
    * Vars
    */
	int n, i;
	socklen_t len = sizeof(struct sockaddr_in); // Taille des adresses
    // Buffer message
    char msg_in[32] = "0";
    char msg_out[32] = "0";

    // Caractérisation du socket serveur
    int sockfd; // Descripteur
    int sport = PORT; // Port
    struct sockaddr_in servaddr, *pservaddr = &servaddr; // Adresse

    // Création du socket
    if((sockfd = socket(AF_INET, SOCK_DGRAM, 0)) == -1) {
        perror("[SOCK_DGRAM, AF_INET, 0]");
        exit(2);
    } else {
        printf("Socket [SOCK_DGRAM, AF_INET, 0] créée\n");
    }

    servaddr.sin_family = AF_INET;
    servaddr.sin_addr.s_addr = htonl(INADDR_ANY);
    servaddr.sin_port = htons(sport);

    // Attachement du socket
    if (bind(sockfd, (struct sockaddr *)(pservaddr), len) == -1) {
        perror("Attachement de la socket impossible");
        close(sockfd);
        exit(2);
    }

    // Récupération de l'adresse effective d'attachement
    getsockname(sockfd, (struct sockaddr *) pservaddr, &len);

    // Définition de l'adresse client
	struct sockaddr_in cliaddr;

    char** operands;
    double calcul = 0;
    while(1) {
        /* Reception */
        printf("Attente de réception de la part d'un client...\n");
        if ((n = recvfrom(sockfd, msg_in, sizeof(msg_in), 0, (struct sockaddr *) &cliaddr, &len)) == -1) {
            printf("Réception inachevé : %s !\n", msg_in);
        } else {
            printf("Réception de %s:%d !, Valeur = %s\n", inet_ntoa(cliaddr.sin_addr), cliaddr.sin_port, msg_in);
            if (strcmp(msg_in, "(") == 0) {
                printf("DONE !!");
                switch(fork()) {
                    case -1:
                        printf("Error forking !\n"); 
                        exit(1);
                    case 0:
                    {
                        int var;
                        sprintf(msg_out, "%s", "Session ouverte");
                        sendto(sockfd, msg_out, sizeof(msg_out), 0, (struct sockaddr *) &cliaddr, len);
                        break;
                    }
                    default:
                        break;
                }
            } else {
                /* Traitement : La reception est bonne, on fait evoluer i */
                operands = sh_split_line(msg_in);
                calcul = strtod(operands[0], NULL);
                for(int j = 0; operands[j] != NULL; j++) {
                    if (strlen(operands[j]) == 1 && ((char)operands[j][0] < 48 || (char)operands[j][0] == 61)) {
                        // C'est un opérande
                        switch(operands[j][0]) {
                            case 43: // +
                                calcul += strtod(operands[j + 1], NULL);
                                break;
                            case 47: // /
                                calcul /= strtod(operands[j + 1], NULL);
                                break;
                            case 45: // -
                                calcul -= strtod(operands[j + 1], NULL);
                                break;
                            case 42: // *
                                calcul *= strtod(operands[j + 1], NULL);
                                break;
                        }
                    }
                }
            }
            sprintf(msg_out, "%lf", calcul);
        }

        /* Émission de la réponse */
        printf("Envoi de la réponse au client\n");
        if (sendto(sockfd, msg_out, sizeof(msg_out), 0, (struct sockaddr *) &cliaddr, len) > 0) {
            printf("Réponse envoyée !\n");
        }

        sleep(1);
    }
	return 0;
} 
