// Client side implementation of UDP client-server model 
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
	int n, i = 0;
	socklen_t len = sizeof(struct sockaddr_in); // Taille des adresses
    // Buffer message
    char msg_in[3] = "0";
    char msg_out[3] = "0";

    /* Verifications de base : Syntaxe d'appel */
    if (argc != 2){
        fprintf(stderr,"Syntaxe d'appel : a.out nom_du_host_peer\n");
        exit(2);
    }

    // Caractérisation du socket client
    int sockfd; // Descripteur
    int cport = PORT; // Port
    struct sockaddr_in serveraddr, *pserveraddr = &serveraddr; // Adresse serveur
    struct sockaddr_in clientaddr, *pclientaddr = &clientaddr;
    struct hostent *hp1;

    // Création du socket
    if((sockfd = socket(AF_INET, SOCK_DGRAM, 0)) == -1) {
        perror("[SOCK_DGRAM, AF_INET, 0]");
        exit(2);
    } else {
        printf("Socket [SOCK_DGRAM, AF_INET, 0] créée\n");
    }

    clientaddr.sin_family = AF_INET;
    clientaddr.sin_addr.s_addr = htonl(INADDR_ANY);
    clientaddr.sin_port = htons(0);

    // Attachement du socket
    if (bind(sockfd, (struct sockaddr *)(pclientaddr), len) == -1) {
        perror("Attachement de la socket impossible");
        close(sockfd);
        exit(2);
    }
    
    // Récupération de l'adresse effective d'attachement
    getsockname(sockfd, (struct sockaddr *) pclientaddr, &len);

    // Adresse serveur
    hp1 = gethostbyname(argv[1]);
    if (hp1 == NULL){
        fprintf(stderr, "Machine %s inconnue\n", argv[1]);
        exit(2);
    } else { /* Recuperation de l'adresse IP depuis la struct hostent */
        memcpy(&serveraddr.sin_addr.s_addr, hp1->h_addr, hp1->h_length);
        serveraddr.sin_family = AF_INET;
        serveraddr.sin_port   = htons(cport);
        fprintf(stdout,"Machine %s --> %s \n", hp1->h_name, inet_ntoa(serveraddr.sin_addr));
    }
	
    while(1) {
		/* Émission */
		sprintf(msg_out, "%d", i);
        

        if (sendto(sockfd, msg_out, sizeof(msg_out), 0, (const struct sockaddr *) &serveraddr, len) > 0) {
            printf("Client %s:%d envoi : valeur = %s !\n", inet_ntoa(clientaddr.sin_addr), clientaddr.sin_port, &msg_out); 
        }

		/* Réception de la réponse */
        n = recvfrom(sockfd, msg_in, sizeof(msg_in), 0, (struct sockaddr *) &serveraddr, &len);
        printf("Client %s:%d reçoit : valeur = %s !\n", inet_ntoa(clientaddr.sin_addr), clientaddr.sin_port, &msg_in);
        i = atoi(msg_in);
        msg_in[0] = '\0';

        sleep(1);
    }
	return 0; 
} 
