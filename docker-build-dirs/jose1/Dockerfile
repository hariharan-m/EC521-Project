# FROM ubuntu:latest
FROM tomcat:9-jre8

# From: https://github.com/tianon/docker-brew-ubuntu-core/issues/122
# we're doing it first so there's no need to do the "dpkg|grep|xargs apt-get reinstall" thing
# RUN rm /etc/dpkg/dpkg.cfg.d/excludes

RUN apt update
RUN apt-get install -y \
  apt-file \
  bash \
  curl \
  g++ \
  g++-6 \
  gcc \
  git \
  make \
  man-db \
  manpages \
  openjdk-8-doc \
  openjdk-8-jdk \
  procps \
  tar \
  vim \
  wget

ARG USER_HOME_DIR="/root"
ARG MAVEN_VERSION=3.6.0
ARG SHA=fae9c12b570c3ba18116a4e26ea524b29f7279c17cbaadc3326ca72927368924d9131d11b9e851b8dc9162228b6fdea955446be41207a5cfc61283dd8a561d2f
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
  && echo "${SHA}  /tmp/apache-maven.tar.gz" | sha512sum -c - \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

COPY mvn-entrypoint.sh /usr/local/bin/mvn-entrypoint.sh
COPY settings-docker.xml /usr/share/maven/ref/

RUN cd / \
 && git clone 'https://github.com/gmu-swe/phosphor.git'

COPY phos-Makefile /phosphor/Phosphor/Makefile

COPY phos-pom.xml /phosphor/Phosphor/pom.xml

RUN cd /phosphor/Phosphor \
 && mvn package

RUN cd /phosphor/Phosphor \
 && mkdir target/jdk1.8.0_181

RUN cd /phosphor/Phosphor/target/jdk1.8.0_181 \
 && (cd /docker-java-home ; tar c .) | tar xfBpv -

# This works, though the testing stage gives error "Could not find or load main class org.apache.maven.surefire.booter.ForkedBooter"
RUN cd /phosphor/Phosphor \
 && mvn install

RUN cd / \
 && mkdir pe

RUN cd /pe \
 && git clone 'https://github.com/josepablocam/phosphor-examples.git'

COPY jose-pom.xml /pe/phosphor-examples/pom.xml

RUN cd /pe/phosphor-examples \
 && mvn install:install-file -Dfile=Phosphor-0.0.4-SNAPSHOT.jar \
   -DgroupId=edu.columbia.cs.psl.phosphor -DartifactId=Phosphor \
   -Dversion=0.0.4-SNAPSHOT -Dpackaging=jar

RUN cd /pe/phosphor-examples \
 && mvn package

#RUN cd /pe/phosphor-examples \
# && ./run_examples.sh /phosphor/Phosphor/target

ENTRYPOINT ["/usr/local/bin/mvn-entrypoint.sh"]
CMD ["mvn"]
