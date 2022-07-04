# Trabalho de Sistemas Distribuídos

Ambiente: Windows 10

comandos: 
- thift --gen java chave.thrift
- javac -cp jars\slf4j-nop-1.7.25.jar;jars\javax.annotation-api-1.3.2.jar;jars\libthrift-0.16.0.jar;jars\slf4j-api-1.7.36.jar;gen-java -d . *.java
- java -cp jars\slf4j-nop-1.7.25.jar;jars\javax.annotation-api-1.3.2.jar;jars\libthrift-0.16.0.jar;jars\slf4j-api-1.7.36.jar;gen-java;. Server.java
- Os portais Cliente e Administrador recebem o mesmo comando do Server.

Relatório:

Foi estabelecida a conexão com o servidor e os 2 portais (Administrador e cliente) ja se comunicam.
Os serviços que implementam os casos de uso (métodos) foram criados e aparentam estar funcionando bem.

Ainda não consegui implementar a parte de pub-sub do MQTT.

Observação: O portal do cliente que implementa os casos de uso de tarefas, pede pelo ID no início e o ID permanece unico durante a execução. Então para trocar o ID, deve-se executar outra vez e informá-lo.
