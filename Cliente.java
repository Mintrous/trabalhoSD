import chavevalor.ChaveValor;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import java.util.Scanner;
import java.util.UUID;
public class Cliente {
  public static void main(String [] args) {
    try{
      Scanner scan = new Scanner(System.in);
      int opcao;



      do {
        System.out.println("\n(1) Inserir cliente");
        System.out.println("(2) Modificar cliente");
        System.out.println("(3) Recuperar cliente");
        System.out.println("(4) Apagar cliente");
        System.out.println("(5) Sair");
        System.out.print("\nOPCAO: ");
        opcao = scan.nextInt();

        switch (opcao){
          case 1:
            String uniqueID = UUID.randomUUID().toString();
            System.out.println("\nID: " + uniqueID);
            System.out.println("\nIdade: ");
            int idade = scan.nextInt();
            System.out.println("Nome: ");
            String nome = scan.next();

            TTransport transport = new TSocket("localhost", 9090);
            transport.open();
            TProtocol protocol = new  TBinaryProtocol(transport);
            ChaveValor.Client client = new ChaveValor.Client(protocol);

            if(client.inserirCliente(uniqueID, nome, idade)){
              System.out.println("\nCliente foi inserido.");
              System.out.println(uniqueID);
            }

            else
              System.out.println("\nFalha ao inserir cliente.");

            transport.close();
            break;
          case 2:
            System.out.println("\nID: ");
            String id = scan.next();
            System.out.println("Nome: ");
            String nome2 = scan.next();
            System.out.println("Idade: ");
            int idade2 = scan.nextInt();

            transport = new TSocket("localhost", 9090);
            transport.open();
            protocol = new  TBinaryProtocol(transport);
            ChaveValor.Client cliente = new ChaveValor.Client(protocol);

            if(cliente.modificarCliente(id, nome2, idade2))
              System.out.println("\nCliente modificado.");
            else
              System.out.println("\nFalha ao modificar cliente.");

            transport.close();
            break;
          case 3:
            System.out.println("\nID: ");
            String id3 = scan.next();

            transport = new TSocket("localhost", 9090);
            transport.open();
            protocol = new  TBinaryProtocol(transport);
            ChaveValor.Client cliente2 = new ChaveValor.Client(protocol);

            if(cliente2.recuperarCliente(id3)){
              System.out.println("\nCliente de ID" + id3 + "foi recuperado.");
            }
            else
              System.out.println("\nFalha ao recuperar cliente.");

            transport.close();
            break;
          case 4:
            System.out.println("\nID: ");
            String id4 = scan.next();

            transport = new TSocket("localhost", 9090);
            transport.open();
            protocol = new  TBinaryProtocol(transport);
            ChaveValor.Client client4 = new ChaveValor.Client(protocol);

            if(client4.apagarCliente(id4))
              System.out.println("\nCliente apagado.");
            else
              System.out.println("\nFalha ao apagar cliente.");

            transport.close();
            break;
          default:
            break;
        }

      } while (opcao != 5);
      System.out.println("\nSistema encerrado");
      scan.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
