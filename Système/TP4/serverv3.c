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
#define BUFFER 256
#define LSH_TOK_DELIM " "

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
	for(int i = 0; tokens[i] != NULL; i++) {
		printf("Token : %s\n", tokens[i]);
	}
    return tokens; // 1 + 1 = [1, +, 1]
}

double calcul(char* msg_in, char** operands) {
    operands = sh_split_line(msg_in);
    double c = strtod(operands[0], NULL);
    if (strlen(operands[1]) == 1 && (char)operands[1][0] < 48) {
        switch(operands[1][0]) {
            case 43: // +
                c += strtod(operands[2], NULL);
                break;
            case 47: // /
                c /= strtod(operands[2], NULL);
                break;
            case 45: // -
                c -= strtod(operands[2], NULL);
                break;
            case 42: // *
                c *= strtod(operands[2], NULL);
                break;
        }
    }
	return c;
}

int main(int argc, char *argv[]) { 
    /*
    * Vars
    */
	int n, i;
	socklen_t len = sizeof(struct sockaddr_in); // Taille des adresses
    // Buffer message
    char msg_in[BUFFER] = "0";
    char msg_out[BUFFER] = "0";

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

    pid_t pid = -1;
	char** operands;
    while(1) {
        pid = -1;
    
        /* Reception */
        printf("Attente de réception de la part d'un client...\n");
        if ((n = recvfrom(sockfd, msg_in, sizeof(msg_in), 0, (struct sockaddr *) &cliaddr, &len)) == -1) {
            printf("Réception inachevé : %s !\n", msg_in);
        } else {
            printf("Réception de %s:%d !, Valeur = %s\n", inet_ntoa(cliaddr.sin_addr), cliaddr.sin_port, msg_in);
            if (strcmp(msg_in, "(") == 0) { // Le client ouvre une session
                printf("Le client ouvre une session\n");
                pid = fork();
            } else { // Calcul traditionnel, on continue
                /* Traitement : La reception est bonne, on fait evoluer i */
            	sprintf(msg_out, "%lf", calcul(msg_in, operands));
            }
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
