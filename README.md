# trabalhoSD

Ambiente: Windows 10

comandos: 
- thift --gen java chavevalor.thrift
- javac -cp jars\slf4j-nop-1.7.25.jar;jars\javax.annotation-api-1.3.2.jar;jars\libthrift-0.16.0.jar;jars\slf4j-api-1.7.36.jar;gen-java -d . *.java
- java -cp jars\slf4j-nop-1.7.25.jar;jars\javax.annotation-api-1.3.2.jar;jars\libthrift-0.16.0.jar;jars\slf4j-api-1.7.36.jar;gen-java;. Server.java
