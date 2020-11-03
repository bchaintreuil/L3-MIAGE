/** Fichier : inc.c (Communication Sockets/UDP)
 *   Les deux processus distants s'envoient un nombre qu'ils 
 *   incrementent successivement : L'un compte en pair, l'autre en impair ...  */
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

int main(int argc, char *argv[]){ 
  int looptime = 0;
  socklen_t ls = sizeof(struct sockaddr_in); /* Taille des adresses */

  /*---- Caracterisation de la socket d'�mission ----------*/
	int sd_local;       /* Descripteur  de la socket*/
	int ps_local = 5001;    /* Port devient eph�m�re */
	struct sockaddr_in adr_local, *padr_local = &adr_local; /* Adresse  */


  /*---- Caracterisation de la socket distante ------*/
  struct sockaddr_in adr_dist,*padr_dist = &adr_dist;  /* Adresse du destinataire */
  struct hostent *hp1;       /* Adresse IP de la machine distante */

  /*---- Buffers pour Messages -------------------------------*/ 
  char msg_in[3] = "0";     /* Message recu de "0" a "99" */
  char msg_out[3] = "0";    /* Message a envoyer "0" a "99" */

  /* 0) Verifications de base : Syntaxe d'appel =====*/
  if (argc != 1){
    fprintf(stderr,"Syntaxe d'appel : tp4_1_server \n");
    exit(-1);
  }
  /* 1) Preparation de  la socket d'�mission ================*/
  /* a) Creation : Domaine AF_INET, type DGRAM, proto. par defaut*/
  if ((sd_local=socket(AF_INET, SOCK_DGRAM, 0)) == -1)
    perror("[SOCK_DGRAM, AF_INET, 0]");
  else
    printf("socket [SOCK_DGRAM, AF_INET, 0] creee\n");
  /* b) Preparation de l'adresse d'attachement */
  adr_local.sin_family      = AF_INET;
  adr_local.sin_addr.s_addr = htonl(INADDR_ANY);  /* Format reseau */
  adr_local.sin_port        = htons(ps_local);  /* Format reseau */
  /* c) Demande d'attachement de la socket */
  if(bind(sd_local,(struct sockaddr *)(padr_local),ls) == -1) {
    perror("Attachement de la socket impossible");
    close(sd_local);  /* Fermeture de la socket               */
    exit(-1);       /* Le processus se termine anormalement.*/
  }
  /* d) Recuperation de l'adresse effective d'attachement. */
  getsockname(sd_local,(struct sockaddr *)padr_local,&ls);

  /* 2) Concernant l'adresse de la  socket de destination ======*/
  /* a) A partir du nom du destinataire */
  hp1=gethostbyname(argv[1]);
  if(hp1 == NULL){ 
    fprintf(stderr,"machine %s inconnue\n",argv[1]); 
    exit(-1); 
  }else{
	/* Recuperation de l'adresse IP depuis la struct hostent */
    memcpy(&adr_dist.sin_addr.s_addr, hp1->h_addr, hp1->h_length);
    adr_dist.sin_family = AF_INET;
    adr_dist.sin_port   = htons(ps_local); /* Meme port que sd_local : why not ? */
    fprintf(stdout,"machine %s --> %s \n",hp1->h_name, inet_ntoa(adr_dist.sin_addr)); 
  }

  /* 3) Boucle emission-reception : A PARTICULARISER selon que l'on 
     est le serveur ou le client ... */
  for(;;) { 
    int i;
    struct sockaddr_in adr2, *padr2 = &adr2; /* Inutilise pour l'instant */
	
    /* b) Reception */
    printf("Attente de reception ... ");

    if (recvfrom(sd_local,msg_in, sizeof(msg_in), 0, (struct sockaddr *)NULL, NULL) == -1)  
      printf("inachevee : %s !\n",msg_in);
    else  {
      printf("terminee : valeur = %s !\n",msg_in);
      /* c) Traitement : La reception est bonne, on fait evoluer i */
      i = atoi(msg_in); 
      i = (i+1)%100; 
      sprintf(msg_out,"%d",i);
    }
	    /* a) Emission */
    printf("\n------------------\n\n\nEnvoi(%d) ... ", looptime);
    if (sendto(sd_local,msg_out, sizeof(msg_out), 0, (struct sockaddr *)padr_dist, ls) >0)
      printf("termine : valeur = %s !\n",msg_out);
    else
      printf("inacheve : %s !\n",msg_out);
    sleep(1); looptime++;
  }
}
