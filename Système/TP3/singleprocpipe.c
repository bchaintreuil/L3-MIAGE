# include <stdio.h>
# include <stdlib.h>
# include <stdlib.h>
# include <unistd.h>
#include <fcntl.h>
#include <memory.h>

# define READ  0
# define WRITE 1
# define ENTREE 0
# define SORTIE 1

int main (int argc, char * argv[]){
    FILE *stream;
    char buffer[500];
    char *argvMoinsUn[30];
    for(int i=0; i<argc;i++)
        argvMoinsUn[i]=argv[i+1];

    int res, tabpipe[2];

    if (pipe(tabpipe) == -1){
        perror("Erreur au niveau du pipeline");}

    res=fork();
    if (res == 0)  {
        close(WRITE);
        close(tabpipe[READ]);
        dup(tabpipe[WRITE]);
        execvp(argv[1], argvMoinsUn);
        perror("Erreur EXEC FILS");
        exit(-1);
    }else {
        close(tabpipe[WRITE]);
        read(tabpipe[ENTREE], buffer, 499);
        printf("%s",buffer);
        printf("\n");
    }
}
