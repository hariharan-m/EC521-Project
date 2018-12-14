# EC521 Demo Application

This application runs a Apache Tomcat server with two webapps both built off
of Apache Struts 2.5.12. This is the last released version vulnerable to
CVE-2017-9805, which is purposefully exploited and (hopefully!) defended
against in this demo.

The first webapp is a simple web app written by us that aims to be a minimal
implementation of the Struts 2 REST plugin. The second webapp is Struts' own
demo application for their REST plugin.

We used [Phosphor](https://github.com/gmu-swe/phosphor) as a way to enable 
dyanmic taint tracking in the JVM.


## Building the demo

To run the demo, you will need to download/install:
	* A version of Java 8 (this was originally used with jdk1.8.0_191)
	* A version of Apache Tomcat (this was originally run on 7.0.92)

First, instrument your Java with Phosphor. `instrument_jdk.sh` lists the 
command to do so. You may need to modify the command or script to point
to the correct Java directory (replace jdk1.8.0_191 with the proper value).

Optionally, you may also instrument the .war files ahead of time. Your
Phosphor-instrumented Java will instrument it at runtime, but instrumenting
ahead of time will lower the startup time for Tomcat. Use the script
`instrument_war.sh` to instrument the .war files if you want, and remember to
update the JAVA_HOME and TOMCAT_ROOT variables first.


## Running the server

To run the server, first copy the .war files to your Tomcat server's webapps/
directory (`instrument_war.sh` above will do this for you automatically if 
set correctly). Next copy the `setenv.sh` script to the server's bin/ 
directory. Update this script to point $JAVA_HOME towards your instrumented
JDK/JRE, and PHJ towards the Phosphor-0.0.4-SNAPSHOR.jar file in this project.

Once done, run tomcat/bin/startup.sh to start the server. If everything is set
correctly, you should see the Tomcat home page on whatever IP and port you set
(by default, it's localhost:8080). The two webapps will be available as:
	* <base_url>/struts-vuln-server
	* <base_url>/struts2-rest-showcase


## Testing Phosphor

Once the server is up and running, you can try to reproduce the exploit
detailed in CVE-2017-9805. To send the exploit, run:

```python send_exploit.py <url>```

Where `<url>` is e.g. "localhost:8080/struts-vuln-server". Any URL under the
two web applications should be vulnerable.

The script sends the `exploit_payload.xml` content as a POST request to the
supplied URL. On vulnerable Struts applications, the XML will be decoded into
a `java.lang.ProcessBuilder` object, and runs the embedded command. In this
case, the command is simply `touch /tmp/exploited`.

If Phosphor is successfully implemented, then it should detect a tainted input
from an XStream.fromXML() method being passed to the ProcessBuilder
constructor, and raise an exception. If Phosphor is not set up correctly,
or if you run the exploit on a non-instrumented server, then the ProcessBuilder
will run. You can confirm this by checking for the existence of
`/tmp/exploited` on the machine running your Tomcat server.


## Other notes

As of the time of this writing, the exploit still works on our demo 
application. We currently believe that either:
	1. There is an issue with our Phosphor configuration, or
	2. There is a bug in Phosphor's taint tracking.

As unlikely as it seems, we believe the latter to be the more likely case.
The taint tracking will work on trivial examples *as is* (e.g., creating a
tainted method that returns a String and passes it to ProcessBuilder in one
of the RecordController handlers), but anything that tries to taint track
XStream.fromXML() will fail.

