# IbmMqWithSpringBoot

This project is meant to demonstrate how to setup IBM Mq with Spring Boot.

Inside the pom.xml you will find a description of the dependencies that Ibm MQ requires.
The required jars can be found in the `jars` directory.

The MqQueueConnectionFactory can be configured in 2 ways which are both implemented: 
 - Binding mode 
 - Client mode
 
 
## Binding mode
When attempting to use Ibm MQ in binding mode and you are getting the following error:
```
java.lang.UnsatisfiedLinkError: mqjbnd (A file or directory in the path name does not exist.)
  at java.lang.ClassLoader.loadLibraryWithPath(ClassLoader.java:994)
  at java.lang.ClassLoader.loadLibraryWithClassLoader(ClassLoader.java:958)
  at java.lang.System.loadLibrary(System.java:453)
  at com.ibm.mq.jmqi.local.LocalMQ.loadLib(LocalMQ.java:1055)
``` 
You can use the following link to resolve this error: [http://www-01.ibm.com/support/docview.wss?uid=swg21248900](http://www-01.ibm.com/support/docview.wss?uid=swg21248900)

## Client mode
When attempting to use Ibm MQ in client mode, please take a look at [https://github.com/ibm-messaging/mq-docker](https://github.com/ibm-messaging/mq-docker).
