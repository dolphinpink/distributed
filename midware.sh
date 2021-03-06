#!/bin/bash

export JAVA_HOME=/usr/lib/jvm/java-1.7.0-openjdk-1.7.0.131.x86_64 export PATH=$JAVA_HOME/bin:$PATH
export CLASSPATH=$CLASSPATH:$1/distributed/clientsrc/:$1/distributed/servercode/:$1/distributed/ResInterface.jar

if [ "$*" == "" ]; then
    echo "No arguments were passed. Proper usage: "
    echo "> ./midware.sh \$PATH \$HOSTNAME "
    echo "where \$PATH is the path to where your distributed"
    echo "file folder is located and \$HOSTNAME is the hostname"
    echo "of the computer you're running the middleware off of. e.g."
    echo "> ./client.sh /home/2015/mpayne cs-19"
    exit 1
fi

rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false 1099 &
java -Djava.security.policy=$1/servercode/java.policy -Djava.rmi.server.codebase=file:$1/distributed/servercode/ ResImpl.Middleware $2 1099