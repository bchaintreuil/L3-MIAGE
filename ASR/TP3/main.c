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
#include <sys/types.h>
#include <sys/shm.h>
#include <sys/sem.h>
#include <semaphore.h>
#include <time.h>

#define BUF_SIZE 256

void make_message(int num, char* message) {
    char buftime[26];
    time_t timer;
    struct tm* tm_info;
    time(&timer);
    tm_info = localtime(&timer);
    strftime(buftime, 26, "%Y-%m-%d %H:%M:%S", tm_info);
    sprintf(message, "%s %d at %s\n", "Hello, I'm child number", num, buftime);
}

int main(int argc, char *argv[]) {
    int i;
    int shmid;
    key_t key;

    char *virtualaddr;   /* Cette variable h�rit�e par tous contient,
                          apres l'appel shmat, un pointeur sur le
                          segment m�moire partag� */

    /*--------------------------------------------------------
     * Etape 1 : attacher la mem partagee � l'espace d'adressage du  pere */

    key = ftok(argv[0], 'R'); /* Generer une cle : Cette clef permettra
                              aux fils de retrouver le segment memoire */
    shmid = shmget(key, 1024, 0644 | IPC_CREAT); /* Creation du segment memoire : 1024 octets */
    if (0 > shmid) {
        perror("Shared Mem creation error\n");
        exit(1);
    }

    /* => virtualaddr will be available across fork ! */
    virtualaddr = shmat(shmid, (void *) 0, 0); /* Attachement � l'espace mem du processus :
                                              virtualaddr pointe sur cet espace*/

    sem_t *sem_get;
    sem_t *sem_put;

    sem_get = sem_open("/get", O_CREAT | O_RDWR, 0644, 1);
    sem_put = sem_open("/put", O_CREAT | O_RDWR, 0644, 1);

    /*--------------------------------------------------------
     * Etape 2 : Cr�ation du fils 1
     *           Il ecrit une information dans la shm et il lit une information
     */
    switch(fork()) {
        case -1:
            printf("Error forking child 1!\n");
            exit(1);
        case 0:
            sem_get = sem_open("/get", O_RDWR);
            sem_put = sem_open("/put", O_RDWR);

            char buf[BUF_SIZE];
            printf("Child 1 executing...\n");
            while (1) {
                /* Child 1 writing in shared mem : adresse h�ritee */
                sem_wait(sem_get);
                make_message(1, buf);
                strcpy(virtualaddr, buf);
                sem_post(sem_get);
                printf("Message sent by child 1: %s\n", virtualaddr);

                // On reset le buffer
                memset(buf, 0, sizeof(char) * BUF_SIZE);

                /* Child 1 reading from shared mem */
                sem_wait(sem_put);
                strcpy (buf, virtualaddr);
                sem_post(sem_put);
                printf("Message received by child 1: %s\n", buf);

                // On reset le buffer
                memset(buf, 0, sizeof(char) * BUF_SIZE);
                sleep(1);
            }
            break;
        default:
            break;
    }
}

    /*--------------------------------------------------------
     * Etape 3 : Création du fils 2
     *           Lui lit puis ecrit
     */
    switch (fork()){
        case -1:
            printf("Error forking child 2!\n");
            exit(1);
            break;
        case 0:
            sem_get = sem_open("/get", O_RDWR);
            sem_put = sem_open("/put", O_RDWR);

            char buf[BUF_SIZE];
            printf("Child 2 executing...\n");
            while (1) {
                /* Child 2 read from shared memory */
                sem_wait(sem_get);
                strcpy (buf, virtualaddr);
                printf("Message received by child 2: %s\n", buf);
                sem_post(sem_get);

                // On reset le buffer
                memset(buf, 0, sizeof(char) * BUF_SIZE);

                /* Child 2 writing in shared mem */
                sem_wait(sem_put);
                make_message(2, buf);
                sem_post(sem_put);
                printf("Message sent by child 2: %s\n", virtualaddr);

                // On reset le buffer
                memset(buf, 0, sizeof(char) * BUF_SIZE);
                sleep(1);
            }
            break;
        default:
            break;
    }
}

    /*--------------------------------------------------------
     *  Etape 4 : Wait
     */
    printf("Parent waiting for children completion...\n");
    for(i=0;i<=1;i++){
        if (wait(NULL) == -1){
            printf("Error waiting.\n");
            exit(EXIT_FAILURE);
        }
    }
    printf("Parent finishing.\n");

    /*--------------------------------------------------------
     * Etape 5 : Deleting Shared Memory.
     */
    shmctl(shmid, IPC_RMID, NULL);
    exit(EXIT_SUCCESS);
}
