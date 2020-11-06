#include<sys/types.h>
#include<sys/wait.h>
#include<stdio.h>
#include<string.h>
#include<unistd.h>
#include<stdlib.h>
#include<time.h>

#define BUF_SIZE 64
#define READ_END 0
#define WRITE_END 1

void make_message(int num, char * message){
  char buftime[26];
  time_t timer;
  struct tm * horodatage_info;
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

    switch (fork()){
        case -1:
            printf("Error forking child 1!\n"); exit(1);
        case 0:
        {
            int status;
            char msg[BUF_SIZE];
            char buf[BUF_SIZE];

            printf("Child 1 executing...\n");

            for(int i=0;i=10;i++)
            {
                make_message(1, msg);
                strcpy(virtualaddr, msg);
                printf("Message sent by child 1: %s\n", virtualaddr);

                // ON FERME LE COTE DU PIPE INUTILE
                close(fd[READ_END]);

                // ON ECRIT DANS LE PIPE
                write(fd[WRITE_END], msg, strlen(msg)+1);
                fflush(stdout);

                // ON FERME LE COTE DU PIPE ECRITURE
                close(fd[WRITE_END]);
                strcpy(buf, virtualaddr);
                
                printf("Message received by child 1 : %s\n",buf);
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

            for(int i=0;i=10;i++)
            {
                // ON FERME LE COTE DU PIPE INUTILE
                close(fd[READ_END]);
                strcpy(buf, virtualaddr);
                printf("Message received by child 2 : %s\n", buf);

                // ON ECRIT DANS LE PIPE
                write(fd[WRITE_END], msg, strlen(msg)+1);
                make_message(2, msg);
                strcpy(virtualaddr, msg);

                // ON FERME LE COTE DU PIPE ECRITURE
                close(fd[WRITE_END]);
            }
            _exit(0);
        }

        case 0:{
         char msg[64];
         printf("Child 1 created !\n");

         // CLOSING THE USELESS SIDES OF PIPES
         close(pipe0[READ_END]);
         close(pipe1[WRITE_END]);

         for(int i =0 ; i<10;i++){
        
            //WRITE IN THE PIPE
            make_message(1,msg);
            write(pipe0[WRITE_END],msg,strlen(msg)+1);
            sleep(1);

            //SHOWING CHILD 2 MESSAGE
            read(pipe1[READ_END],msg,BUF_SIZE);
            printf("Child 1 read : %s\n", msg);
        }
        exit(0);
        }
    break;
    default: break;
    }

    // CHILD 2 FORK
    switch(fork()){
        case -1:{
         printf("Error");
         exit(1);
        }
        case 0:{
         char buf[BUF_SIZE];
         char msg[64];
         
         printf("Child 2 created !\n");

        // CLOSING THE USELESS SIDES OF PIPES
         close(pipe0[WRITE_END]);
         close(pipe1[READ_END]);

        for(int j=0;j<10;j++){
            char msg[64];
            sleep(1);

            // SHOWING CHILD 1 MESSAGE
            read(pipe0[READ_END],msg,BUF_SIZE);
            printf("Child 2 read : %s\n", msg);

            sleep(1);

            // WRITE IN THE PIPE
            make_message(2,msg);
            write(pipe1[WRITE_END],msg,strlen(msg)+1);
            
        }

        _exit(EXIT_SUCCESS);   
    }break;
    
    default:;break;
    }

    printf("Parent waiting for children completion...\n");
    
    for(int i=0;i<=1;i++){
        if (wait(NULL) == -1){ 
            printf("Error waiting.\n");
            exit(EXIT_FAILURE);
        }
    }  
    printf("Parent finishing.\n");

    exit(EXIT_SUCCESS);
}
