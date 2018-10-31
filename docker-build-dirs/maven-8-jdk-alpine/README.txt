This is the basis for the jrrdev-cve-2017-5638 setup (The specific
version they used is jdk-8-alpine/Dockerfile in the list below)

It depends on openjdk-8-jdk-alpine

To demonstrate the CVE-2017-5638 exploit one can do:

  docker system prune  # If needed, clean up from previous builds
  docker image ls -a   # Should show nothing except what you had at the start
  ...
  # Assuming we are starting in this directory "maven-8-jdk-alpine"
  docker build . -t maven-8-jdk-alpine

  # (Alternatively this can be done as two instructions:)
     docker build .    # And take note of the hash it creates as the last step
     docker tag ec55037841fb maven-8-jdk-alpine   # Here 'ec55037841fb' is the hash

  cd ../jrrdev-cve-2017-5638
  docker build . -t jrrdev-cve-2017-5638
  docker run -d -p 8080:8080 jrrdev-cve-2017-5638
  python ex2.py http://127.0.0.1:8080/hello "ls -la" # Should show exploit output
  # ...
  # now do whatever testing/debugging you want
  # ...
  docker stop cc8e4c2ba92a
  # Emulated system no longer running but image still present. If you want to run more tests later, go back to the step "docker run ... jrrdev-cve-2017-5638"
  # ...
  # Now we want to delete everything we built
  docker container rm cc8e4c2ba92a
  docker image rm maven-8-jdk-alpine
  docker image ls -a   # Should show nothing except what you had at the start

- - -

Needed files are at:
  https://raw.githubusercontent.com/carlossg/docker-maven/f581ea002e5d067deb6213c00a4d217297cad469/jdk-8-alpine/mvn-entrypoint.sh
  https://raw.githubusercontent.com/carlossg/docker-maven/f581ea002e5d067deb6213c00a4d217297cad469/jdk-8-alpine/settings-docker.xml

- - -

Source: https://hub.docker.com/_/maven/

Short Description

Apache Maven is a software project management and comprehension tool.

Full Description

Supported tags and respective Dockerfile links

3.5.4-jdk-10-slim, 3.5-jdk-10-slim, 3-jdk-10-slim
(jdk-10-slim/Dockerfile)

3.5.4-jdk-10, 3.5-jdk-10, 3-jdk-10 (jdk-10/Dockerfile)

3.5.4-jdk-11-slim, 3.5-jdk-11-slim, 3-jdk-11-slim
(jdk-11-slim/Dockerfile)

3.5.4-jdk-11, 3.5-jdk-11, 3-jdk-11 (jdk-11/Dockerfile)

3.5.4-jdk-7-alpine, 3.5-jdk-7-alpine, 3-jdk-7-alpine
(jdk-7-alpine/Dockerfile)

3.5.4-jdk-7-slim, 3.5-jdk-7-slim, 3-jdk-7-slim (jdk-7-slim/Dockerfile)

3.5.4-jdk-7, 3.5-jdk-7, 3-jdk-7 (jdk-7/Dockerfile)

3.5.4-jdk-8-alpine, 3.5.4-alpine, 3.5-jdk-8-alpine, 3.5-alpine,
3-jdk-8-alpine, alpine (jdk-8-alpine/Dockerfile)

3.5.4-jdk-8-slim, 3.5.4-slim, 3.5-jdk-8-slim, 3.5-slim, 3-jdk-8-slim,
slim (jdk-8-slim/Dockerfile)

3.5.4-jdk-8, 3.5.4, 3.5-jdk-8, 3.5, 3-jdk-8, 3, latest
(jdk-8/Dockerfile)

3.5.4-jdk-9-slim, 3.5-jdk-9-slim, 3-jdk-9-slim (jdk-9-slim/Dockerfile)

3.5.4-jdk-9, 3.5-jdk-9, 3-jdk-9 (jdk-9/Dockerfile)

3.5.4-ibmjava-8-alpine, 3.5.4-ibmjava-alpine, 3.5-ibmjava-8-alpine,
3.5-ibmjava-alpine, 3-ibmjava-8-alpine, ibmjava-alpine
(ibmjava-8-alpine/Dockerfile)

3.5.4-ibmjava-8, 3.5.4-ibmjava, 3.5-ibmjava-8, 3.5-ibmjava,
3-ibmjava-8, 3-ibmjava, ibmjava (ibmjava-8/Dockerfile)

Quick reference

Where to get help:

the Docker Community Forums, the Docker Community Slack, or Stack
Overflow

Where to file issues:

https://github.com/carlossg/docker-maven/issues

Maintained by:

the Maven Project

Supported architectures: (more info)

amd64, arm32v5, arm32v6, arm32v7, arm64v8, i386, ppc64le, s390x

Published image artifact details:

repo-info repo's repos/maven/ directory (history)

(image metadata, transfer size, etc)

Image updates:

official-images PRs with label library/maven

official-images repo's library/maven file (history)

Source of this description:

docs repo's maven/ directory (history)

Supported Docker versions:

the latest release (down to 1.6 on a best-effort basis)

What is Maven?

Apache Maven is a software project management and comprehension tool.
Based on the concept of a project object model (POM), Maven can manage
a project's build, reporting and documentation from a central piece of
information.

How to use this image

You can run a Maven project by using the Maven Docker image directly,
passing a Maven command to docker run:

$ docker run -it --rm --name my-maven-project -v
"$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3.3-jdk-8 mvn
clean install

Building local Docker image (optional)

This is a base image that you can extend, so it has the bare minimum
packages needed. If you add custom package(s) to the Dockerfile, then
you can build your local Docker image like this:

$ docker build --tag my_local_maven:3.5.2-jdk-8 .

Reusing the Maven local repository

The local Maven repository can be reused across containers by creating
a volume and mounting it in /root/.m2.

docker volume create --name maven-repo

docker run -it -v maven-repo:/root/.m2 maven mvn archetype:generate #
will download artifacts

docker run -it -v maven-repo:/root/.m2 maven mvn archetype:generate #
will reuse downloaded artifacts

Or you can just use your home .m2 cache directory that you share e.g.
with your Eclipse/IDEA:

$ docker run -it --rm -v "$PWD":/usr/src/mymaven -v
"$HOME/.m2":/root/.m2 -v "$PWD/target:/usr/src/mymaven/target" -w
/usr/src/mymaven maven mvn clean package

Packaging a local repository with the image

The $MAVEN_CONFIG dir (default to /root/.m2) could be configured as a
volume so anything copied there in a Dockerfile at build time is lost.
For that reason the dir /usr/share/maven/ref/ exists, and anything in
that directory will be copied on container startup to $MAVEN_CONFIG.

To create a pre-packaged repository, create a pom.xml with the
dependencies you need and use this in your Dockerfile.
/usr/share/maven/ref/settings-docker.xml is a settings file that
changes the local repository to /usr/share/maven/ref/repository, but
you can use your own settings file as long as it uses
/usr/share/maven/ref/repository as local repo.

COPY pom.xml /tmp/pom.xml

RUN mvn -B -f /tmp/pom.xml -s /usr/share/maven/ref/settings-docker.xml
dependency:resolve

To add your custom settings.xml file to the image use

COPY settings.xml /usr/share/maven/ref/

For an example, check the tests dir

Running as non-root

Maven needs the user home to download artifacts to, and if the user
does not exist in the image an extra user.home Java property needs to
be set.

For example, to run as user 1000 mounting the host' Maven repo

$ docker run -v ~/.m2:/var/maven/.m2 -ti --rm -u 1000 -e
MAVEN_CONFIG=/var/maven/.m2 maven mvn -Duser.home=/var/maven
archetype:generate

Image Variants

The maven images come in many flavors, each designed for a specific
use case.

maven:<version>

This is the defacto image. If you are unsure about what your needs
are, you probably want to use this one. It is designed to be used both
as a throw away container (mount your source code and start the
container to start your app), as well as the base to build other
images off of.

maven:<version>-slim

This image does not contain the common packages contained in the
default tag and only contains the minimal packages needed to run
maven. Unless you are working in an environment where only the maven
image will be deployed and you have space constraints, we highly
recommend using the default image of this repository.

maven:<version>-alpine

This image is based on the popular Alpine Linux project, available in
the alpine official image. Alpine Linux is much smaller than most
distribution base images (~5MB), and thus leads to much slimmer images
in general.

This variant is highly recommended when final image size being as
small as possible is desired. The main caveat to note is that it does
use musl libc instead of glibc and friends, so certain software might
run into issues depending on the depth of their libc requirements.
However, most software doesn't have an issue with this, so this
variant is usually a very safe choice. See this Hacker News comment
thread for more discussion of the issues that might arise and some
pro/con comparisons of using Alpine-based images.

To minimize image size, it's uncommon for additional related tools
(such as git or bash) to be included in Alpine-based images. Using
this image as a base, add the things you need in your own Dockerfile
(see the alpine image description for examples of how to install
packages if you are unfamiliar).

License

View license information for the software contained in this image.

As with all Docker images, these likely also contain other software
which may be under other licenses (such as Bash, etc from the base
distribution, along with any direct or indirect dependencies of the
primary software being contained).

Some additional license information which was able to be auto-detected
might be found in the repo-info repository's maven/ directory.

As for any pre-built image usage, it is the image user's
responsibility to ensure that any use of this image complies with any
relevant licenses for all software contained within.

