/*
  Fichier shell_cp_shloop.c
 */
#include <stdio.h>
#include <stdlib.h>

/*----------------------------------*/

void sh_loop(void){
  char *prompt = "> ";
  char *line;
  char **args;
  int status;
  
  do {  // Boucle de base
    /* Affichage du prompt ----------------------------- */
    printf("%s",prompt); 
    fflush(stdout);

    /* Lecture de la ligne de commande ----------------- */
    line = sh_read_line(stdin); 
    args = sh_split_line(line); // Analyse =>  on extrait les args  

    /* Execution de la commande ------------------------- */
    status = sh_execute(args);
    
    sh_free(line); // La lecture a du faire une allocation !
    sh_free(args); // idem pour le split .. donc on nettoie.
  } while(status);  
}

/*----------------------------------*/

int main(int argc, char * argv[]){

  // Init : Load config files, if any

  // Interp : Run Command loop
  sh_loop();
  
  // Termin : Perform any shutdown / cleanup
  
  return EXIT_SUCCESS;
}
