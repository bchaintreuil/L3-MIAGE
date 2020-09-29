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
    getline(&line, &bufsize, f); // Extracts characters from is and stores them into str until the delimitation character delim is found (or the newline character, '\n', for (2)).
    return line;
}

/*---------------------------------------------*/

#define LSH_TOK_BUFSIZE 64
#define LSH_TOK_DELIM " \t\n"

char ** sh_split_line( char *line, char mode){ // Fonction qui split les arguments en token
    int bufsize = LSH_TOK_BUFSIZE, position = 0;
    char **tokens = malloc(bufsize * sizeof(char*));
    char *token;

    if (!tokens) {
        fprintf(stderr, "lsh: allocation error\n");
        exit(EXIT_FAILURE);
    }

    if (mode == 0) {
        token = strtok(line, LSH_TOK_DELIM);
    } else {
        token = strtok(line, ":");
    }

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
        if (mode == 0) {
            token = strtok(NULL, LSH_TOK_DELIM);
        } else {
            token = strtok(NULL, ":");
        }
    }
    tokens[position] = NULL;
    return tokens;
}

/*---------------------------------------------*/

int sh_execute(char **args){ // TODO: Traitement des erreurs
    pid_t pid = fork();
    if(pid == 0) {
        // Child
        if (execvp(args[0], args) == -1) {
            // Erreur d'exécution
        }
    } else if (pid > 0) {
        //Parent
        int status = 1;

        wait(&status);
        WEXITSTATUS(status);

        return status;
    } else {
        //fork error
    }
}

/*---------------------------------------------*/

char is_forbidden(char** args, char** forbidden) {
    for(int i = 0; args[i] != NULL; i++) {
        for(int j = 0; forbidden[j] != NULL; j++) {
            if (strstr(args[i], forbidden[j])) {
                return 1;
            }
        }
    }
    return 0;
}

/*---------------------------------------------*/

void sh_loop(char** forbidden){
    char *prompt = "l3miage shell > ";
    char *line;
    char **args;
    int status;


    do {
        printf("%s",prompt);
        fflush(stdout);

        line = sh_read_line(stdin);
        args = sh_split_line(line, 0);

        if(is_forbidden(args, forbidden)) {
            printf("Travaille au lieu de jouer !\n");
        } else {
            if (strcmp(args[0], "exit\n")) {
                exit(0); //TODO : end instructions
            } else if (args[0] == "rmf\n") {
                
            } else if (args[0] == "newf\n"){
                ;
            } else {
                status = sh_execute(args);
            }
        }

        /*sh_free(line); */
        /*sh_free(args); */
    } while(1); //TODO: modif to status
}

/*==============================================*/

int main(int argc, char * argv[]){

    // Init : Load config files, if any
    char* f_env = getenv("FORBIDDEN");
    char** forbidden = sh_split_line(f_env, 1);

    // Interp : Run Command loop
    sh_loop(forbidden);

    // Termin : Perform any shutdown / cleanup

    return EXIT_SUCCESS;
}

