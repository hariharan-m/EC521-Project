
To build this image:

  chmod +x mvn-entrypoint.sh   # - - - If needed
  docker build --tag my_local_maven .

  docker run -p 8080:8080/tcp -it --name maven2 my_local_maven /bin/bash

  $CATALINA_HOME/bin/startup.sh
  ps -wwwaux

In browser on your machine (i.e. Docker's "host") use a browser to visit:

  http://127.0.0.1:8080

This should show the "successfully installed Tomcat" page.

Now visit:

  http://127.0.0.1:8080/struts-vuln-server

which should redirect to:

  http://127.0.0.1:8080/struts-vuln-server/records.xhtml

Now in the Docker shell:

  ls -l /tmp

Now on a terminal on your host:

  cd .../EC521-Project/demo
  python send_exploit.py http://127.0.0.1:8080/struts-vuln-server/records.xhtml

