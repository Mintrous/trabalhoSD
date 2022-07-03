import chavevalor.ChaveValor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class Server {


  public static void main(String [] args) {
    try {
      ClienteHandler handler = new ClienteHandler();
      ChaveValor.Processor processor = new ChaveValor.Processor(handler);

      TServerTransport serverTransport = new TServerSocket(9090);
      TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

      System.out.println("Starting the simple server...");
      server.serve();
    } catch (Exception x) {
      x.printStackTrace();
    }
  }

}
