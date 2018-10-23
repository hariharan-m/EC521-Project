.../docker-build-dirs/0-README.txt

This directory contains all the Dockerfile s used by the configurations
we've gotten from online; these are needed as separate directories
for testing and diagnosing dependencies.

For example, the sample server vulnerable to CVE-2017-5638 is built as
a chain of four dependencies (an image made from jrrdev-cve-2017-5638
is called a "4-layer image"):

  docker-alpine
   `--> openjdk-8-jdk-alpine
       `--> maven-8-jdk-alpine
           `--> jrrdev-cve-2017-5638

In order to incorporate Phosphor, for example, we would need to do one
of the following.

1) Interpose a layer to make a 5-layer image. 

  docker-alpine
   `--> openjdk-8-jdk-alpine
      `--> Phosphored-openjdk-8
          `--> Phosphored-maven-8-jdk (same Dockerfile as maven-8-jdk-alpine)
              `--> Phosphored-jrrdev-cve-2017-5638 (same Dockerfile as jrrdev-cve-2017-5638)

2) Modify one of the layers of the original 4-layer system:

2a)

  docker-alpine
   `--> openjdk-8-jdk-alpine
      `--> Phosphored-maven-8-jdk (Mod the maven-8-jdk-alpine Dockerfile to install/configure Phosphor prior to installing/configuring Maven)
          `--> Phosphored-jrrdev-cve-2017-5638 (same Dockerfile as jrrdev-cve-2017-5638)

2b)

  docker-alpine
   `--> Phosphored-openjdk-8-jdk-alpine (Mod the openjdk-8-jdk-alpine Dockerfile to install/configure Phosphor after installing/configuring OpenJDK)
      `--> Phosphored-maven-8-jdk (same Dockerfile as maven-8-jdk-alpine)
          `--> Phosphored-jrrdev-cve-2017-5638 (same Dockerfile as jrrdev-cve-2017-5638)

3) Combine all of the steps in the chain together making a single
Dockerfile that creates a single-layer image, and modify this
Dockerfile to add the things we need at the proper stages of the
install/configure process.
