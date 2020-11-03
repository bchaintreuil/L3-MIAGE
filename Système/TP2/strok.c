/* 
   Fichier : strtok.c

   Ce programme illustre l'utilisation de la fonction
   strtok pour décomposer une chaine (qui pourrait
   etre une commande) et obtenir une liste des différents 
   tokens qui la compose (séparés par un separateur).
*/
#include<string.h>
#include<stdio.h>
#include<stdlib.h>

#define MNB 50   /* Nombre max de tokens dans une ligne */
/*----------------------------------*/

int analyse_cmd(char *l[]){
  /* Affichage des tokens */
  int i = 0;
  while(1){
    if(l[i]==NULL)
      break;
    printf("Item #%d is %s.\n",i,l[i]);
    i++;
  }
  return 0;
}

/*----------------------------------*/

char **find_tokens_list(char input_string[], char sep[]){
  int i;
  char **tl; /* Liste de tokens */
  tl = calloc(sizeof(char *),MNB);
  
  /* Y a t'il un debut d'une sequence de tokens ? */
  tl[0]=strtok(input_string, sep);
  if(tl[0]==NULL){
    printf("string is empty or contains only delimiters ?\n");
    exit(0);
   }

  /* Stockage des tokens cf NULL param*/
  for(i=1;i<MNB;i++){
    tl[i]=strtok(NULL, sep); /* get next */
    
    if(tl[i]==NULL) /* Fin de liste */
      break; /* rien de plus a extraire */
   }

  return tl;
}

/*----------------------------------*/

int main(int argc, char *argv[]){
  char s[]="pierre || jean paul || anna";
  //char s[]="Woody";

  char sep[] = "||"; /* Separateur de tokens */
  char **l;
  l = find_tokens_list(s,sep);
  analyse_cmd(l);
}

