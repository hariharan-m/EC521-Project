Based on rmunafo-maven, but using an earlier version of Maven

  chmod +x mvn-entrypoint.sh
  docker build --tag my_local_maven .
  docker run -it --name maven2 my_local_maven /bin/bash
  echo pre-Phosphor-installation steps will happen here > note1.txt
  docker commit maven2 maven3

  docker volume create --name maven-repo

  docker run -it --name maven4 -v maven-repo:/root/.m2 maven3 /bin/bash

  cd /
  mkdir phs
  cd phs
  git clone 'https://github.com/gmu-swe/phosphor.git'
  cd phosphor/Phosphor
  mvn package
