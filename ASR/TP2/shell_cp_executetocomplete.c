/*
  Fichier : shell_cp_executetocomplete.c
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

/*---------------------------------------------*/

char *sh_read_line(FILE *f){
  char *line = NULL;
  ssize_t bufsize = 0; // donc getline realise l'allocation
  getline(&line, &bufsize, f);
  return line;
}

/*---------------------------------------------*/

#define LSH_TOK_BUFSIZE 64
#define LSH_TOK_DELIM " \t\n"

char ** sh_split_line( char *line){
  int bufsize = LSH_TOK_BUFSIZE, position = 0;
  char **tokens = malloc(bufsize * sizeof(char*));
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
      tokens = realloc(tokens, bufsize * sizeof(char*));
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

/*---------------------------------------------*/

int sh_execute(char **args){
  /* To complete !! */  
}

/*---------------------------------------------*/

void sh_loop(void){
  char *prompt = "l3miage shell > ";
  char *line;
  char **args;
  int status;
  
  do {  
    printf("%s",prompt);
    fflush(stdout);
    
    line = sh_read_line(stdin);
    args = sh_split_line(line); 
    status = sh_execute(args);
    
    /*sh_free(line); */
    /*sh_free(args); */
  } while(status);  
}

/*==============================================*/

int main(int argc, char * argv[]){

  // Init : Load config files, if any

  // Interp : Run Command loop
  sh_loop();
  
  // Termin : Perform any shutdown / cleanup
  
  return EXIT_SUCCESS;
}

