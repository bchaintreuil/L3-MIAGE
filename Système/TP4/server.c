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

int main(int argc, char *argv[]) { 
    /*
    * Vars
    */
	int n, i;
	socklen_t len = sizeof(struct sockaddr_in); // Taille des adresses
    // Buffer message
    char msg_in[3] = "0";
    char msg_out[3] = "0";

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

    while(1) {
        /* Reception */
        printf("Attente de réception de la part d'un client...\n");
        if ((n = recvfrom(sockfd, msg_in, sizeof(msg_in), 0, (struct sockaddr *) &cliaddr, &len)) == -1) {
            printf("Réception inachevé : %s !\n", msg_in);
        } else {
            printf("Réception de %s:%d !, Valeur = %s\n", inet_ntoa(cliaddr.sin_addr), cliaddr.sin_port, msg_in);
            /* Traitement : La reception est bonne, on fait evoluer i */
            i = atoi(msg_in);
            i = (i + 1) % 100;
            sprintf(msg_out, "%d", i);
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
