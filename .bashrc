
# Source global definitions
if [ -f /usr/socs/Profile ]; then
        . /usr/socs/Profile
fi

# User specific aliases and functions
alias lsa ls -l -a


cc() {
	java -Djava.security.policy=/home/2013/yxia19/distributed/servercode/java.policy ResImpl.ClientRunner $1 1099		
}

ss() {
	rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false 1099 &
	java -Djava.security.policy=/home/2013/yxia19/distributed/servercode/java.policy -Djava.rmi.server.codebase=file:/home/2013/yxia19/distributed/servercode/ ResImpl.Middleware $1 1099
}

rm1() {
	rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false 1040 &
	java -Djava.security.policy=/home/2013/yxia19/distributed/servercode/java.policy -Djava.rmi.server.codebase=file:/home/2013/yxia19/distributed/servercode/ ResImpl.ResourceManagerImpl $1 1040
}

rm2() {
	rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false 1041 &
	java -Djava.security.policy=/home/2013/yxia19/distributed/servercode/java.policy -Djava.rmi.server.codebase=file:/home/2013/yxia19/distributed/servercode/ ResImpl.ResourceManagerImpl $1 1041
}

rm3() {
	rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false 1042 &
	java -Djava.security.policy=/home/2013/yxia19/distributed/servercode/java.policy -Djava.rmi.server.codebase=file:/home/2013/yxia19/distributed/servercode/ ResImpl.ResourceManagerImpl $1 1042
}

rm4() {
	rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false 1043 &
	java -Djava.security.policy=/home/2013/yxia19/distributed/servercode/java.policy -Djava.rmi.server.codebase=file:/home/2013/yxia19/distributed/servercode/ ResImpl.ResourceManagerImpl $1 1043
}


bb() {
	javac /home/2013/yxia19/distributed/servercode/ResImpl/ResourceManager.java
	jar cvf /home/2013/yxia19/distributed/servercode/ResInterface.jar /home/2013/yxia19/distributed/servercode/ResImpl/ResourceManager.class
	javac /home/2013/yxia19/distributed/servercode/ResImpl/ResourceManagerImpl.java
	javac /home/2013/yxia19/distributed/servercode/ResImpl/Middleware.java
	javac /home/2013/yxia19/distributed/servercode/ResImpl/ClientRunner.java
	chmod -R 705 /home/2013/yxia19/distributed/
}

export CLASSPATH=$CLASSPATH:/home/2013/yxia19/distributed/clientsrc/:/home/2013/yxia19/distributed/servercode/:/home/2013/yxia19/distributed/ResInterface.jar

