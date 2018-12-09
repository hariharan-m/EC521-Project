
To build this image:

  chmod +x mvn-entrypoint.sh   # - - - If needed
  docker build --tag my_local_maven .

  docker run -it --name maven2 my_local_maven /bin/bash

The next commands to run would be these, but they don't work yet:

  ./run_examples.sh /phosphor/Phosphor/target

----

  docker volume rm maven-repo  # - - - If needed
  docker volume create --name maven-repo

  docker run -it --name maven2 -v maven-repo:/root/.m2 my_local_maven /bin/bash
