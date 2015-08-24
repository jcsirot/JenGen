FROM debian:jessie
MAINTAINER Jean-Christophe Sirot <jcsirot@chelonix.com>

RUN apt-get update -qq && apt-get install -yqq wget curl

# Install java
RUN curl -s -k -L -C - -b "oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u60-b27/server-jre-8u60-linux-x64.tar.gz | tar xfz - -C /

ENV JAVA_HOME /jdk1.8.0_60
ENV PATH $PATH:$JAVA_HOME/bin

WORKDIR /home/jengen

ADD target/jengen-server.jar /home/jengen/jengen-server.jar
ADD start.sh /home/jengen/start.sh

EXPOSE 9090
ENTRYPOINT ["./start.sh"]
