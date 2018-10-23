.../docker-build-dirs/0-README.txt

This directory contains all the Dockerfile s used by the configurations
we've gotten from online; these are needed as separate directories
for testing and diagnosing dependencies.

For example, the sample server vulnerable to CVE-2017-5638 is built
as a chain of four dependencies:

  docker-alpine
     `--> openjdk-8-jdk-alpine
             `--> maven-8-jdk-alpine
                     `--> jrrdev-cve-2017-5638

In order to incorporate Phosphor, for example, we would need to build
a derivative of openjdk-8-jdk-alpine that adds Phosphor, then derive a
"Phosphored-Maven-8-JDK" from that, and then derive a CVE-2017-5638
from that, making a 5-stage chain; or alternatively all of the steps
in the chain could be combined together into a single Dockerfile
