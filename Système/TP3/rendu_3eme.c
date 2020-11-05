#include <stdio.h>
#include <string.h>
#include <sys/wait.h>
#include <unistd.h>
#include <sys/types.h>
#include <stdlib.h>

#define BUF_SIZE 256
#define READ_END 0
#define WRITE_END 1
#define LSH_TOK_BUFSIZE 64
#define LSH_TOK_DELIM " \t\n :"

char *read_line(FILE *f){
  char *line = NULL;
  ssize_t bufsize = 0; // donc getline realise l'allocation
  getline(&line, &bufsize, f);
  return line;
}

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

int main(int argc, char *argv[]){
    char *line;
    char **args;
    int status;
    
    do{
        printf("Entrez une line que le fils va écrire et qu'il va transmettre au père : ");
        line = read_line(stdin);
        args = sh_split_line(line);

        // On crée le pipe à deux extrémités (lecture/écriture)
        int fd[2];
        if(pipe(fd) == -1)
        {
            fprintf(stderr, "Pipe failed");
            return 1;
        }

        // Fork pour arriver dans le fils
        pid_t pid = fork();

        // Cas où le fils est mal crée
        if(pid<0)
        {
            fprintf(stderr, "Fork failed");
            return 1;
        }
        if(pid>0)
        {
            int status;
            char cmd[BUF_SIZE];
            strcpy (cmd, line);


            printf("Le fils envoie les données\n");

            // On ferme la fin du pipe car pas utilisée côte lecture
            close(fd[WRITE_END]);

            // On écrit au pipe
            dup2(fd[READ_END], 1);
            fflush(stdout);

            // On ferme l'écriture du pipe
            close(fd[READ_END]);

            // Non zombie !
            wait(&status);
        }
        
        else{
            // Processus fils lecture
            char msg[BUF_SIZE];
            char execResultFils[BUF_SIZE];

            // On ferme la fin du pipe cote lecture
            close(fd[READ_END]);

            // On écrit au père
            dup2(fd[WRITE_END], 0);
            printf("On envoie le résultat au père.\n");
            fflush(stdout);

            // On ferme l'écriture du pipe
            close(fd[WRITE_END]);

            execvp(args[0], args);

            // Non zombie !
            wait(&status);
        }

    return 0;
    }   while(1);
}