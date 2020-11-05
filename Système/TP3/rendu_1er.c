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
  int i;
  int shmid;
  key_t key;
  sem_t *put, *get;

  char *virtualaddr;   
  /* Cette variable h�rit�e par tous contient,
  apres l'appel shmat, un pointeur sur le 
  segment m�moire partag� */

  /*--------------------------------------------------------
   * Etape 1 : attacher la mem partagee � l'espace d'adressage du  pere */

  key = ftok(argv[0],'R'); /* Generer une cle : Cette clef permettra
                              aux fils de retrouver le segment memoire */
  shmid = shmget(key, 1024, 0644|IPC_CREAT); /* Creation du segment memoire : 1024 octets */
  if (0 > shmid){
    perror("Shared Mem creation error\n");
    exit(1);  
  }
  
  /* => virtualaddr will be available across fork ! */
  virtualaddr = shmat(shmid, (void*)0, 0); /* Attachement � l'espace mem du processus :
                                              virtualaddr pointe sur cet espace*/

    // Création du sémaphore initialisé à 0
    int init_sem_value = 0;
    sem_unlink("/test");
    put = sem_open("/test", O_CREAT|O_RDWR, 0644, init_sem_value);
    get = sem_open("/test", O_CREAT|O_RDWR, 0644, init_sem_value);

  /*--------------------------------------------------------
   * Etape 2 : Cr�ation du fils 1 
   *           Il ecrit une information dans la shm et il lit une information
   */
  
  switch (fork()){
    case -1:
      printf("Error forking child 1!\n"); exit(1);
    case 0:
      {
        char buf[BUF_SIZE];
        char msg[BUF_SIZE];

        printf("\nChild 1 executing...\n");

        for(int i=0;i=10;i++)
        {
          /* Child 1 writing in shared mem : adresse h�ritee */

          make_message(1, msg);
          strcpy(virtualaddr, msg);
          printf("Message sent by child 1: %s\n", virtualaddr);

          sem_post(put);

          sleep(1);

          /* Child 1 reading from shared mem */
          sem_wait(get);
          strcpy (buf, virtualaddr);
                  
          printf("Message received by child 1: %s\n",buf);
        }        
        _exit(0);

      }
      break;
    default:
      break;
  }

    /*--------------------------------------------------------
    * Etape 3 : Cr�ation du fils 2 
    *           Lui lit puis ecrit
    */  
  switch (fork()){
    case -1:
      printf("Error forking child 2!\n"); exit(1);
    case 0:
      {
        char buf[BUF_SIZE];
        char msg[BUF_SIZE];

        printf("\nChild 2 executing...\n");
        
        for(int i=0;i=10;i++)
        {
          sleep(1);
          /* Child 2 read from shared memory */
          sem_wait(put);
          strcpy(buf, virtualaddr);
          printf("Message received by child 2: %s\n", buf);

          /* Child 2 writing in shared mem */
          make_message(2,msg);
          strcpy (virtualaddr, msg);
      
          sem_post(get);
        }
        
        _exit(EXIT_SUCCESS);
      }
      break;  
    default: break;
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

    // Unlink des sémaphores/delete
    sem_unlink("/test");

  /*--------------------------------------------------------
   * Etape 5 : Deleting Shared Memory.
   */
  shmctl (shmid, IPC_RMID, NULL);
  exit(EXIT_SUCCESS);
}
