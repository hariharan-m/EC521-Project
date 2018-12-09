Based on rmunafo-maven, but using an earlier version of Maven

  chmod +x mvn-entrypoint.sh
  docker build --tag my_local_maven .
  docker run -it --name maven2 my_local_maven /bin/bash
  echo pre-Phosphor-installation steps will happen here > note1.txt
  exit
  docker commit maven2 maven3

  docker volume create --name maven-repo

  docker run -it --name maven4 -v maven-repo:/root/.m2 maven3 /bin/bash

  cd /
  mkdir phs
  cd phs
  git clone 'https://github.com/gmu-swe/phosphor.git'
  cd phosphor/Phosphor

Fix "cannot find include file" errors:

  vim Makefile
  # insert this in the "(UNAME), Linux" section:
    CCFLAGS += -I/usr/lib/jvm/java-8-openjdk-amd64/include
    CCFLAGS += -I/usr/lib/jvm/java-8-openjdk-amd64/include/linux

Fix the error "Could not find or load main class org.apache.maven.surefire.booter.ForkedBooter"

  vim pom.xml
  # Find <profile> <id>runTests</id> and add this to the <build><plugins> section:

    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-surefire-plugin</artifactId>
      <configuration>
        <useSystemClassLoader>false</useSystemClassLoader>
      </configuration>
    </plugin>

  mvn package
