# FROM ubuntu:latest
FROM tomcat:9-jre8

# From: https://github.com/tianon/docker-brew-ubuntu-core/issues/122
# we're doing it first so there's no need to do the "dpkg|grep|xargs apt-get reinstall" thing
# RUN rm /etc/dpkg/dpkg.cfg.d/excludes

RUN apt update
RUN apt-get install -y \
  bash \
  curl \
  git \
  man-db \
  manpages \
  openjdk-8-jdk \
  procps \
  tar \
  vim \
  wget

ARG USER_HOME_DIR="/root"

# uncomment the desired version of Maven

ARG MAVEN_VERSION=3.5.4
ARG SHA=2a803f578f341e164f6753e410413d16ab60fabe31dc491d1fe35c984a5cce696bc71f57757d4538fe7738be04065a216f3ebad4ef7e0ce1bb4c51bc36d6be86
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries

# ARG MAVEN_VERSION=3.6.0
# ARG SHA=fae9c12b570c3ba18116a4e26ea524b29f7279c17cbaadc3326ca72927368924d9131d11b9e851b8dc9162228b6fdea955446be41207a5cfc61283dd8a561d2f
# ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries

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

ENTRYPOINT ["/usr/local/bin/mvn-entrypoint.sh"]
CMD ["mvn"]
