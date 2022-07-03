import chavevalor.ChaveValor;
import chavevalor.Tarefa;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;
public class PortalCliente {
    public static void main(String [] args) {
        try{
            Scanner scan = new Scanner(System.in);
            int opcao;
            System.out.println("\nID do cliente: ");
            String idCliente;
            idCliente = scan.next();

            do {
                System.out.println("\n(1) Inserir tarefa");
                System.out.println("(2) Modificar tarefa");
                System.out.println("(3) Listar tarefas");
                System.out.println("(4) Apagar tarefas");
                System.out.println("(5) Apagar tarefa");
                System.out.println("(6) Sair");
                System.out.print("\nOPCAO: ");
                opcao = scan.nextInt();

                switch (opcao){
                    case 1:
                        System.out.println("\nID: " + idCliente);
                        System.out.println("\nTitulo: ");
                        String titulo = scan.next();
                        System.out.println("Descricao: ");
                        String descricao = scan.next();

                        TTransport transport = new TSocket("localhost", 9090);
                        transport.open();
                        TProtocol protocol = new  TBinaryProtocol(transport);
                        ChaveValor.Client client = new ChaveValor.Client(protocol);

                        if(client.inserirTarefa(idCliente, titulo, descricao))
                            System.out.println("\nTarefa inserida.");
                        else
                            System.out.println("\nFalha ao inserir tarefa.");

                        transport.close();
                        break;
                    case 2:
                        System.out.println("Titulo: ");
                        String titulo2 = scan.next();
                        System.out.println("Descricao: ");
                        String novaDescricao = scan.next();

                        transport = new TSocket("localhost", 9090);
                        transport.open();
                        protocol = new  TBinaryProtocol(transport);
                        ChaveValor.Client cliente = new ChaveValor.Client(protocol);

                        if(cliente.modificarTarefa(idCliente, titulo2, novaDescricao))
                            System.out.println("\nCTarefa modificada.");
                        else
                            System.out.println("\nFalha ao modificar tarefa.");

                        transport.close();
                        break;
                    case 3:
                        System.out.println("\nTarefas do cliente de ID: " + idCliente + ":");

                        transport = new TSocket("localhost", 9090);
                        transport.open();
                        protocol = new  TBinaryProtocol(transport);
                        ChaveValor.Client cliente2 = new ChaveValor.Client(protocol);

                        List<Tarefa> tarefasCliente = cliente2.listarTarefas(idCliente);

                        if(tarefasCliente == null)
                            System.out.println("\nCliente de ID: " + idCliente + " nao possui tarefas.");

                        else{ // listar as tarefas do cliente
                            for(int i = 0; i < tarefasCliente.size(); i++){
                                System.out.println("\nTarefa: ");
                                System.out.println("Titulo: " + tarefasCliente.get(i).titulo);
                                System.out.println("Descricao: " + tarefasCliente.get(i).descricao);
                            }
                        }

                        transport.close();
                        break;
                    case 4:
                        transport = new TSocket("localhost", 9090);
                        transport.open();
                        protocol = new  TBinaryProtocol(transport);
                        ChaveValor.Client client4 = new ChaveValor.Client(protocol);

                        if(client4.apagarTarefas(idCliente))
                            System.out.println("\nTarefas foram apagadas.");
                        else
                            System.out.println("\nFalha ao apagar tarefas do cliente de ID: " + idCliente);

                        transport.close();
                        break;
                    case 5:
                        System.out.println("\nTitulo: ");
                        String tituloApagar;
                        tituloApagar = scan.next();

                        transport = new TSocket("localhost", 9090);
                        transport.open();
                        protocol = new  TBinaryProtocol(transport);
                        ChaveValor.Client client5 = new ChaveValor.Client(protocol);

                        if(client5.apagarTarefa(idCliente, tituloApagar))
                            System.out.println("\nTarefa foi apagada.");
                        else
                            System.out.println("\nFalha ao apagar tarefa.");

                        transport.close();
                        break;
                    default:
                        break;
                }

            } while (opcao != 6);
            System.out.println("\nSistema encerrado");
            scan.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
