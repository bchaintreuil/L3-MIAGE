/*  Version "-1" d'un �change SHM System V 
    entre deux fils.
    Author : GM
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/wait.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/ipc.h>
#include <sys/types.h>
#include <sys/sem.h>
#include <semaphore.h>
#include <sys/shm.h>
#include <time.h>

#define BUF_SIZE 256
#define READ_END 0
#define WRITE_END 1

void make_message(int num, char *message){
  // REMPLIT LE BUFFER MESSAGE
  char bufftime[26];
  time_t timer;
  struct tm* tm_info;
  time(&timer);
  tm_info=localtime(&timer);
  strftime(bufftime, 26, "%Y-%m-%d %H:%M:%S", tm_info);
  sprintf(message, "%s %d at %s\n", "Hello, i'm child number", num, bufftime);
};

int main(int argc, char *argv[]){
    key_t key = ftok(argv[0],'R');
    int shmid = shmget(key, 1024, 0644|IPC_CREAT);
    char * virtualaddr = shmat(shmid, (void*)0, 0);
    int fd[2];
    int sd[2];

    switch (fork()){
        case -1:
            printf("Error forking child 1!\n"); exit(1);
        case 0:
        {
            int status;
            char msg[BUF_SIZE];
            char buf[BUF_SIZE];

            printf("Child 1 executing...\n");

            for(int i=0;i<4;i++)
            {
                close(fd[READ_END]);
                close(sd[WRITE_END]);

                // Ecriture dans le pipe
                make_message(1,msg);
                write(fd[WRITE_END], msg, strlen(msg)+1);
                sleep(1);

                // Affichage du message du fils 2
                read(sd[READ_END], msg, BUF_SIZE);
                printf("Message received by child 1 : %s\n", msg);
            }
            _exit(0);
        }
        break;
        
        default:
        break;
    }

    switch (fork()){
        case -1:
            printf("Error forking child 1!\n"); exit(1);
        case 0:
        {
            int status;
            char msg[BUF_SIZE];
            char buf[BUF_SIZE];

            printf("\nChild 2 executing...\n");

            for(int i=0;i<4;i++)
            {
                sleep(1);
                close(fd[WRITE_END]);
                close(sd[READ_END]);   

                // Affichage du message du fils 2
                read(fd[READ_END], msg, strlen(msg)+1);
                printf("Message received by child 2 :  %s\n", msg);

                // Ecriture dans le pipe
                make_message(2,msg);
                write(sd[WRITE_END], msg, BUF_SIZE);
            }
            _exit(0);
        }
        break;
        
        default:
        break;
    }
    
    printf("Parent waiting for children completion...\n");
    for(int i=0;i<=1;i++){
        if (wait(NULL) == -1){ 
            printf("Error waiting.\n");
            exit(EXIT_FAILURE);
        }
    }  
    printf("Parent finishing.\n");

    return 0;
}
