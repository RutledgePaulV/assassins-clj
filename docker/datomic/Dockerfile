FROM ubuntu:trusty

RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections
RUN apt-get update && apt-get install -y software-properties-common
RUN add-apt-repository -y ppa:webupd8team/java
RUN apt-get update
RUN apt-get install -y oracle-java8-installer
RUN apt-get install -y unzip
RUN rm -rf /var/lib/apt/lists/* && rm -rf /var/cache/oracle-jdk8-installer
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle
ENV DATOMIC_VERSION 0.9.5404
RUN wget -O /tmp/datomic.zip https://my.datomic.com/downloads/free/${DATOMIC_VERSION}
RUN unzip /tmp/datomic.zip -d /tmp/datomic
RUN mv /tmp/datomic/datomic-free-${DATOMIC_VERSION} /etc/datomic
WORKDIR /etc/datomic
ADD run.sh .
CMD ["/etc/datomic/run.sh"]