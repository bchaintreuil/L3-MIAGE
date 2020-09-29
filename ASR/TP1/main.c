#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>

#define MAXCOUNT 100
#define BUFSIZE 50

void parentProcess() {
    int i;
    char buf[BUFSIZE];
    for(i = 1; i <= MAXCOUNT; i++) {
        sprintf(buf, "This line is from parent, value = %d\n", i);
        write(1, buf, strlen(buf));
    }
}

void childProcess() {
    int i;
    char buf[BUFSIZE];
    for(i = 1; i <= MAXCOUNT; i++) {
        sprintf(buf, "This line is from child, value = %d\n", i);
        write(1, buf, strlen(buf));
    }
}

int main() {
    pid_t pid;
    pid = fork();
    if(pid == 0) {
        childProcess();
    } else {
        parentProcess();
    }
}
