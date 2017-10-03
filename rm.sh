#!/bin/bash

# USAGE: /rm.sh $NUM $PATH $HOSTNAME where $NUM is the RMI server number 
# (1 through 4), $PATH is the path to the distributed file folder, and
# $HOSTNAME is the hostname of the computer you're running the RMI
# server off of 

export JAVA_HOME=/usr/lib/jvm/java-1.7.0-openjdk-1.7.0.131.x86_64 export PATH=$JAVA_HOME/bin:$PATH
export CLASSPATH=$CLASSPATH:$2/distributed/clientsrc/:$2/distributed/servercode/:$2/distributed/ResInterface.jar

if [ "$*" == "" ]; then
    echo "No arguments were passed. Proper usage: "
    echo "> ./rm.sh \$NUM \$PATH \$HOSTNAME "
    echo "where \$NUM is the RMI server number (1 through 4),"
    echo "\$PATH is the path to where your distributed file"
    echo "folder is located and \$HOSTNAME is the hostname of"
    echo "the computer you're running the RMI server off of. e.g."
    echo "> ./rm.sh 1 /home/2015/mpayne cs-19"
    exit 1
fi

if [ $1 -eq 1 ]; then

	rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false 1040 &
	java -Djava.security.policy=$2/distributed/servercode/java.policy -Djava.rmi.server.codebase=file:$2/distributed/servercode/ ResImpl.ResourceManagerImpl $1 1040

elif [ $1 -eq 2 ]; then

	rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false 1041 &
	java -Djava.security.policy=$2/distributed/servercode/java.policy -Djava.rmi.server.codebase=file:$2/distributed/servercode/ ResImpl.ResourceManagerImpl $1 1041

elif [ $1 -eq 3 ]; then

	rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false 1042 &
	java -Djava.security.policy=$2/distributed/servercode/java.policy -Djava.rmi.server.codebase=file:$2/distributed/servercode/ ResImpl.ResourceManagerImpl $1 1042

elif [ $1 -eq 4 ]; then

	rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false 1043 &
	java -Djava.security.policy=$2/distributed/servercode/java.policy -Djava.rmi.server.codebase=file:$2/distributed/servercode/ ResImpl.ResourceManagerImpl $1 1043

fi