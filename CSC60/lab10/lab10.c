/* Author(s): Allen Myers.
 *
 * This is lab10.c the csc60mshell
 * This program serves as a skeleton for doing labs 9, and 10.
 * Student is required to use this program to build a mini shell
 * using the specification as documented in direction.
 * Date: Fall 2019
 */

/* The include files section */
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <fcntl.h>
#include <errno.h>

/* Define Section */
#define MAXLINE 80
#define MAXARGS 20
#define MAX_PATH_LENGTH 50
#define TRUE 1

/* function prototypes */
int parseline(char *cmdline, char **argv);
void process_input(int argc, char **argv);
void handle_redir(int argc, char *argv[]);


/* ----------------------------------------------------------------- */
/*                  The main program starts here                     */
/* ----------------------------------------------------------------- */
int main(void)
{
    char cmdline[MAXLINE];
    char *argv[MAXARGS];
    int argc;
    int i;  //for the loops later
    int status;
    pid_t pid;
   // char *cwd;
    /* Loop forever to wait and process commands */
    while (TRUE)
    {
	/* Print your shell name: csc60msh (m for mini shell) */
        printf("csc60msh> ");

	/* Read the command line */
        fgets(cmdline, MAXLINE, stdin);

	/* Call parseline to build argc/argv */
        argc=parseline(cmdline,argv);

        printf("argc= %i \n", argc);
        for(i=0;i<argc;i++)
        {
            printf("argv %i = %s \n",i,argv[i]);
        }

	// If user hits enter key without a command,
        // continue to loop again at the beginning.
        if(argc==0){
            continue;
        }
        printf("argc = %i\n",argc);

	/* Handle build-in command: exit, pwd, or cd  */
        int strcmp(const char *s1, const char *s2);

        if((strcmp(argv[0],"exit"))==0){
            exit(EXIT_SUCCESS);
        }
        else if((strcmp(argv[0],"pwd"))==0){
            char path[MAX_PATH_LENGTH];
            getcwd(path, MAX_PATH_LENGTH);
            printf(path);
            printf("\n");
            continue;
        }
        else if((strcmp(argv[0],"cd"))==0){
            char *dir;
            if(argc==1){
                dir=getenv("HOME");
            }
            else{
                dir=argv[1];
            }
	    status=chdir(dir);
	    if((status)==(-1)){
                perror("error changing directory");
                exit(EXIT_FAILURE);
            }
        }


 
//.......................IGNORE........................	
	/* Else, fork off a process */
      else {
	    pid = fork();
          switch(pid)
    	    {
	    case -1:
		    perror("Shell Program fork error");
	        exit(EXIT_FAILURE);
	   	case 0:
		    /* I am child process. I will execute the command, */
		    /* and call: execvp */
		    process_input(argc, argv);
		    break;
	   	default:
		    /* I am parent process */
		    if (wait(&status) == -1)
		    	perror("Shell Program Error");
		    else
		   	printf("Child returned status: %d\n",status);
		    break;
	    } 	/* end of the switch */
	}	/* end of the if-else-if */
  }		/* end of the while */
} 		/* end of main */

/* ----------------------------------------------------------------- */
/*                  parseline                                        */
/* ----------------------------------------------------------------- */
/* parse input line into argc/argv format */

int parseline(char *cmdline, char **argv)
{
    int argc = 0;
    char *separator = " \n\t"; /* Includes space, Enter, Tab */
 
    /* strtok searches for the characters listed in separator */
    argv[argc] = strtok(cmdline, separator);

    while ((argv[argc] != NULL) && (argc+1 < MAXARGS)) 
    	argv[++argc] = strtok((char *) 0, separator);
     		
    return argc;  
}
/* ----------------------------------------------------------------- */
/*                  process_input                                    */
/* ----------------------------------------------------------------- */
void process_input(int argc, char **argv) {                       

    /* Step 1: Call handle_redir to deal with operators:            */
    /* < , or  >, or both                                           */
    handle_redir(argc,argv);
    int ret = execvp(argv[0], argv);
    /* Step 2: perform system call execvp to execute command        */
     if (ret == -1) {
        fprintf(stderr, "Error on the exec call\n");              
        _exit(EXIT_FAILURE);                                      
     }                                                            
 }
/* ----------------------------------------------------------------- */
void handle_redir(int argc, char *argv[]) {
    int out_loc = 0;
    int in_loc = 0;
    int i;
    for (i = 0; i < argc; i++) {
        if (strcmp(argv[i], ">") == 0) {
            if (out_loc != 0) {
                fprintf(stderr,"Cannot output to more than one file.\n");
                 _exit(EXIT_FAILURE);
            }
            else if (i == 0) {
                fprintf(stderr,"No command entered.\n");
                _exit(EXIT_FAILURE);
            }
        out_loc = i;
        }
        else if (strcmp(argv[i], "<") == 0) {
            if (in_loc != 0) {
                fprintf(stderr,"Cannot input from more than one file.\n");
                _exit(EXIT_FAILURE);
            }
            else if (i == 0) {
                fprintf(stderr,"No command entered.\n");
                _exit(EXIT_FAILURE);
            }
        in_loc = i;
        }
    }

    int fd;
    if (out_loc != 0) {
        if (argv[out_loc + 1] == NULL) {
            fprintf(stderr,"There is no file.");
            _exit(EXIT_FAILURE);
        }
        fd = open(argv[out_loc+1], O_WRONLY | O_CREAT | O_TRUNC, S_IRUSR | S_IWUSR);

        if (fd == -1) {
            fprintf(stderr,"Error opening out_loc file\n");
            _exit(EXIT_FAILURE);
        }
        dup2(fd,1);
        close(fd);
        argv[out_loc] = NULL;
    }
    int fd1;
    if (in_loc != 0) {
        if(argv[in_loc + 1] == NULL) {
            fprintf(stderr,"There is no file.");
            _exit(EXIT_FAILURE);
        }
        fd1 = open(argv[in_loc+1], O_RDONLY,S_IRUSR | S_IWUSR);

        if(fd1 == -1) {
            fprintf(stderr,"Error opening at in_loc file\n");
            _exit(EXIT_FAILURE);
        }
        dup2(fd1,0);
        close(fd1);
        argv[in_loc] = NULL;
    }
}
/* ----------------------------------------------------------------- */
