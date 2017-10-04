#!/bin/bash

export JAVA_HOME=/usr/lib/jvm/java-1.7.0-openjdk-1.7.0.131.x86_64 export PATH=$JAVA_HOME/bin:$PATH
export CLASSPATH=$CLASSPATH:$1/distributed/clientsrc/:$1/distributed/servercode/:$1/distributed/ResInterface.jar

if [ "$*" == "" ]; then
    echo "No arguments were passed. Proper usage: "
    echo "> ./build.sh \$PATH "
    echo "where \$PATH is the path to where your distributed"
    echo "file folder is located. e.g."
    echo "> ./build.sh /home/2015/mpayne"
    exit 1
fi

javac ./servercode/ResImpl/ResourceManager.java
jar cvf ./servercode/ResInterface.jar ./servercode/ResImpl/ResourceManager.class
javac ./servercode/ResImpl/ResourceManagerImpl.java
javac ./servercode/ResImpl/Middleware.java
javac ./servercode/ResImpl/ClientRunner.java
chmod -R 705 ../distributed/