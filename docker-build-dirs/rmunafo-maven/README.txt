
To build this image:

  chmod +x mvn-entrypoint.sh
  docker build --tag my_local_maven .

  docker run -it --name maven2 my_local_maven /bin/sh
  pwd
  echo foo > foo.txt
  exit

You created a small test file and exited the shell. To continue where
you left off (as long as the Docker system is still running on your
machine), just do this:

  docker start -a -i `docker ps -q -l`
  cat foo.txt
  exit

To save changes done so far:

  docker commit maven2 maven3

To proceed further one needs persistent storage. Docker does this with
"volumes". We want one volume for the Maven system to save all of its
persistent settings.

  docker volume rm maven-repo
  docker volume create --name maven-repo

Now the previously committed "maven3" can be started with the volume
attached:

  docker run -it --name maven4 -v maven-repo:/root/.m2 maven3 /bin/sh

  mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app \
   -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

  cd my-app

  type apt apk
  apt install -y vim  # for systems that have #apt#
  apk add vim         # for systems that have #apk#

  vim pom.xml
  # Insert the following at the top level:
  <properties>
    <maven.compiler.source>1.6</maven.compiler.source>
    <maven.compiler.target>1.6</maven.compiler.target>
  </properties>

  and the following at the end:

  <build>
    <plugins>
      <plugin>
        <groupId>com.github.spotbugs</groupId>
        <artifactId>spotbugs-maven-plugin</artifactId>
        <version>3.1.8</version>
        <dependencies>
          <!-- overwrite dependency on spotbugs if you want to specify the version of spotbugs -->
          <dependency>
            <groupId>com.github.spotbugs</groupId>
            <artifactId>spotbugs</artifactId>
            <version>3.1.9</version>
          </dependency>
        </dependencies>
      </plugin>
    </plugins>
  </build>

At this point I exited again to save changes

  exit
  docker commit maven4 maven5
  docker run -it --name maven6 -v maven-repo:/root/.m2 maven5 /bin/sh

  cd my-app
  mvn package
    # Adds plugin-specific commands as subcommands to #mvn#

  mvn spotbugs:spotbugs

  exit
  docker commit maven6 maven7

Now add Java code in either of the following (I'm not sure which) that
does something dubious, like deserialise argv[1]:

  ./src/test/java/com/mycompany/app/AppTest.java
  ./src/main/java/com/mycompany/app/App.java

----

To create persistent files, use a Docker volume.

The "normal" way to make a Docker volume that is stored within the
Docker system and cannot be viewed directly on your machine (host)

  docker volume create --name maven-repo

To see what is in a volume, create a Busybox container and tell
Busybox to run an #ls# command:

  docker run -it --rm -v maven-repo:/vol busybox ls -lR /vol

There is nothing special about that absolute directory name "/vol"
and it can be changed on a per-command basis:

  docker run -it --rm -v maven-repo:/.m2 busybox ls -lR /.m2

To manually view files or do other operations (rename, edit, etc.)
use Busybox as a shell:

  docker run -it --rm -v maven-repo:/.m2 busybox /bin/sh


You can also use a local directory to hold the contents of a "volume".
Docker requires the path of the directory be specified as an absolute
pathname, for which the following examples use the pwd command inside
back-quotes:

  mkdir dir1
  echo foo > dir1/foo.txt
  docker run -it --rm -v `pwd`/dir1:/vol busybox ls -lR /vol
  mkdir dir1/dir2
  echo bar > dir1/dir2/bar.txt
  docker run -it --rm -v `pwd`/dir1:/vol busybox ls -lR /vol

- - -

Sources:
  https://stackoverflow.com/questions/46866933
  https://stackoverflow.com/questions/23439126

----
