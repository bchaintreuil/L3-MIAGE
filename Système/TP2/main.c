/*
  Fichier : shell_cp_executetocomplete.c
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>
#include <assert.h>
#include <errno.h>


/*---------------------------------------------*/

char *sh_read_line(FILE *f) {
    char *line = NULL;
    ssize_t bufsize = 0; // donc getline realise l'allocation
    getline(&line, &bufsize,
            f); // Extracts characters from is and stores them into str until the delimitation character delim is found (or the newline character, '\n', for (2)).
    return line;
}

/*---------------------------------------------*/

#define LSH_TOK_BUFSIZE 64
#define LSH_TOK_DELIM " \t\n"

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

/*---------------------------------------------*/

int sh_execute(char **args) { // TODO: Traitement des erreurs
    pid_t pid = fork();
    if (pid == 0) {
        // Child
        int errno;
        if (execvp(args[0], args) == -1) {
            // Erreur d'exécution
        }
        exit(errno);
    } else if (pid > 0) {
        //Parent
        int status = 0;

        wait(&status);
        while(!WIFEXITED(status));
        status = WEXITSTATUS(status);

        return status;
    } else {
        //fork error
    }
}

/*---------------------------------------------*/
char *LCSubStr(char *X, char *Y) {
    size_t m = strlen(X);
    size_t n = strlen(Y);

    // Create a table to store lengths of longest common
    // suffixes of substrings.   Note that LCSuff[i][j]
    // contains length of longest common suffix of X[0..i-1]
    // and Y[0..j-1]. The first row and first column entries
    // have no logical meaning, they are used only for
    // simplicity of program
    int LCSuff[m + 1][n + 1];

    // To store length of the longest common substring
    int len = 0;

    // To store the index of the cell which contains the
    // maximum value. This cell's index helps in building
    // up the longest common substring from right to left.
    int row, col;

    /* Following steps build LCSuff[m+1][n+1] in bottom
       up fashion. */
    for (int i = 0; i <= m; i++) {
        for (int j = 0; j <= n; j++) {
            if (i == 0 || j == 0)
                LCSuff[i][j] = 0;

            else if (X[i - 1] == Y[j - 1]) {
                LCSuff[i][j] = LCSuff[i - 1][j - 1] + 1;
                if (len < LCSuff[i][j]) {
                    len = LCSuff[i][j];
                    row = i;
                    col = j;
                }
            } else
                LCSuff[i][j] = 0;
        }
    }

    // if true, then no common substring exists
    if (len) {
        // allocate space for the longest common substring
        char *resultStr = (char *) malloc((len + 1) * sizeof(char));

        // traverse up diagonally form the (row, col) cell
        // until LCSuff[row][col] != 0
        while (LCSuff[row][col] != 0) {
            resultStr[--len] = X[row - 1]; // or Y[col-1]

            // move diagonally up to previous cell
            row--;
            col--;
        }
        return resultStr;
    } else {
        return NULL;
    }
}

char is_forbidden(char **args, char *f_env) {
    char *buffer;
    char *token;
    char *lcss;
    for (int i = 0; args[i] != NULL; i++) {
        // f_env = "test:mp3"
        lcss = LCSubStr(f_env, args[i]);
        if (lcss) {
            buffer = strdup(f_env);
            token = strtok(buffer, ":");
            while (token) {
                if (!strcmp(token, lcss)) {
                    return 1;
                }
                token = strtok(NULL, ":");
            }
        }
    }
    return 0;
}

/*---------------------------------------------*/

void sh_loop(char *f_env) {
    char *prompt = "l3miage shell > ";
    char *line;
    char **args;
    char *buffer;
    int status;

    do {
        printf("%s", prompt);
        fflush(stdout);

        line = sh_read_line(stdin);
        args = sh_split_line(line);

        if (args[0] == NULL) {
            continue;
        } else if (!strcmp(args[0], "exit")) {
            exit(0); // TODO : end instructions
        } else if (!strcmp(args[0], "newf")) {
            // Ajout dans les variables d'environnement
            if (*f_env) {
                buffer = (char *) malloc(sizeof(char) * (strlen(f_env) + strlen(args[1]) + (size_t) 2));
                strcpy(buffer, f_env);
                strcat(buffer, ":");
                strcat(buffer, args[1]);
            } else {
                buffer = (char *) malloc(sizeof(char) * (strlen(f_env) + strlen(args[1]) + (size_t) 1));
                strcpy(buffer, args[1]);
            }

            setenv("FORBIDDEN", buffer, 1);

            free(buffer);
            buffer = NULL;

            free(f_env);
            f_env = strdup(getenv("FORBIDDEN"));
        } else if (!strcmp(args[0], "rmf")) {
            if (is_forbidden(args, f_env)) {
                char *substr = strstr(f_env, args[1]);

                size_t len_substr = strlen(args[1]) + 1; // +1 pour le ':'
                size_t len_before_substr = substr - f_env;
                size_t len_after_substr = strlen(substr + len_substr);

                //assert(len_before_substr+len_after_substr+len_substr == strlen(f_env));

                if (len_before_substr == 0) {
                    buffer = (char *) malloc(sizeof(char) * (len_after_substr) + (size_t) 1);
                    strncpy(buffer, f_env + len_substr, len_after_substr);
                } else if (len_after_substr == 0) {
                    buffer = (char *) malloc(sizeof(char) * (len_before_substr));
                    strncpy(buffer, f_env, len_before_substr - 1);
                } else {
                    buffer = (char *) malloc(sizeof(char) * (len_before_substr + len_after_substr) + (size_t) 1);
                    strncpy(buffer, f_env, len_before_substr);
                    strncat(buffer, substr + len_substr, len_after_substr);
                }

                setenv("FORBIDDEN", buffer, 1);

                free(buffer);
                buffer = NULL;

                free(f_env);
                f_env = strdup(getenv("FORBIDDEN"));
            } else {
                printf("%s is not a forbidden element\n", args[1]);
            }
        } else if (is_forbidden(args, f_env)) {
            printf("Travaille au lieu de jouer !\n");
        } else {
            status = sh_execute(args);
            if (status == 1) {
                printf("Syntaxe incorrecte\nStatus code : %d\n", status);
                fflush(stdout);
            } else if (status == 2){
                printf("Commande inexistante\nStatus code : %d\n", status);
                fflush(stdout);
            }
        }

        /*sh_free(line); */
        /*sh_free(args); */
    } while (1); //TODO: modif to status
}

/*==============================================*/

void handler(int signum) {
    printf("\nIl faut taper \"exit\" pour quitter le terminal !\n");
    fflush(stdout);
}

int main(int argc, char *argv[]) {

    // Init : Load config files, if any
    signal(SIGINT, handler);
    if (getenv("FORBIDDEN")) {
        char *f_env = strdup(getenv("FORBIDDEN"));
        sh_loop(f_env);
    } else {
        char *f_env = (char *) malloc(sizeof(char));
        sh_loop(f_env);
    }

    // Termin : Perform any shutdown / cleanup

    return EXIT_SUCCESS;
}

