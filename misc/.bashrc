# .bashrc

# Source global definitions
if [ -f /usr/socs/Profile ]; then
        . /usr/socs/Profile
fi

# User specific aliases and functions
alias lsa ls -l -a

alias rmi="rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false 1099 &"
alias sss="java -Djava.security.policy=java.policy -Djava.rmi.server.codebase=file:/home/2013/yxia19/distributed/servercode/ ResImpl.ResourceManagerImpl 1099"

alias cc="java -Djava.security.policy=java.policy client cs-28 1099"

ss() {
	rmi
	sss
}

export CLASSPATH=/home/2013/yxia19/distributed/servercode:/home/2013/yxia19/distributed/ResInterface.jar:/home/2013/yxia19/distributed/clientsrc

