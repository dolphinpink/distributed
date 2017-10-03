 .d8888b.  .d88888b. 888b     d8888888888b.    888888888 d888   .d8888b.  
d88P  Y88bd88P" "Y88b8888b   d8888888   Y88b   888      d8888  d88P  Y88b 
888    888888     88888888b.d88888888    888   888        888         888 
888       888     888888Y88888P888888   d88P   8888888b.  888       .d88P 
888       888     888888 Y888P 8888888888P"         "Y88b 888   .od888P"  
888    888888     888888  Y8P  888888                 888 888  d88P"      
Y88b  d88PY88b. .d88P888   "   888888          Y88b  d88P 888  888"       
 "Y8888P"  "Y88888P" 888       888888           "Y8888P"8888888888888888  
                                                                          
------------------------------------------------------------------------
--------------COMP 512 Distributed System Project Phase 1:--------------
-----------------------Yiwei Xia and Marie Payne------------------------
------------------------------------------------------------------------

The submission has two parts: the folder with all the server/client
code for both the TCP and RMI parts hosted here, and the project slides 
for the demo, in pdf format. 

To run the project, make sure you have the newest version of Java 
installed. 

Before running the scripts, make sure you have modified the contents of
the client's java.policy to:
```bash
grant codebase "file:$PATH/distrbuted/clientsrc/"
```
and the contents of the hosts running the server/middleware's java.policy
to:
```bash
grant codebase "file:$PATH/distrbuted/servercode/"
```
where $PATH is the path to your distributed folder.

To build the project, run:
```bash
$ ./build.sh $PATH
```
where $PATH is the path to your distributed folder. 

To start the middleware, run:
```bash
$ ./midware.sh $PATH $HOSTNAME
```
where $PATH is the path to your distributed folder, and $HOSTNAME is the
hostname of the computer you're running the middleware off of.

To start an RMI server, run:
```bash
$ ./rm.sh \$NUM \$PATH \$HOSTNAME
```
where $NUM is the number of the RMI server (1 through 4), $PATH is the 
path to your distributed folder, and $HOSTNAME is the hostname of the 
computer you're running the RMI server off of.

To start a client, run:
```bash
$ ./client.sh $PATH $HOSTNAME
```
where $PATH is the path to your distributed folder, and $HOSTNAME is the
hostname of the computer you're running the client off of.

