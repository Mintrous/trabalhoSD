import chavevalor.Tarefa;
import org.apache.thrift.TException;

import java.sql.ClientInfoStatus;
import java.util.HashMap;
import chavevalor.Client;
import chavevalor.ChaveValor;
import java.util.List;

import javax.swing.*;

// Implementa o serviço
public class Handler implements chavevalor.ChaveValor.Iface {
   private final HashMap<String,Client> id = new HashMap<>();

   // metodos de casos de uso para portal admin
   @Override
   public boolean inserirCliente(String idCliente, String nome, int idade) throws TException {
       if(id.containsKey(idCliente))
           return false;


       Client client = new Client(idCliente, nome, idade, null);
       id.put(idCliente, client);
       return true;
   }

    @Override
    public boolean modificarCliente(String idCliente, String nome, int idade) throws TException {
       if(id.get(idCliente) != null){
           id.get(idCliente).setNome(nome);
           id.get(idCliente).setIdade(idade);
           return true;
       }

       return false;
    }

   @Override
   public boolean recuperarCliente(String idCliente) throws TException {
       if(id.get(idCliente) != null)
           return true;

       return false;
   }

   @Override
   public boolean apagarCliente(String idCliente) throws TException {
       if(id.get(idCliente) != null){
           id.remove(idCliente);
           return true;
       }

       return false;
   }

   // metodos de casos de uso para portal cliente
   @Override
   public boolean inserirTarefa(String idCliente, String titulo, String descricao) throws TException {
       if(recuperarCliente(idCliente)){
           System.out.println("Cliente recuperado.");
           Tarefa tarefa = new Tarefa();
           tarefa.setTitulo(titulo);
           tarefa.setDescricao(descricao);

           getClient(idCliente).addToTarefa(tarefa);
           return true;
       }
       return false;
   }

   @Override
   public boolean modificarTarefa(String idCliente, String titulo, String descricao) throws TException {
       // autentica cliente
       if(recuperarCliente(idCliente)){
           for(int i = 0; i < getClient(idCliente).tarefa.size(); i++){ // percorre pelas tarefas
               if(getClient(idCliente).tarefa.get(i).titulo.equals(titulo)){ // encontra a tarefa com o mesmo titulo para modificar a descricao
                   getClient(idCliente).tarefa.get(i).setDescricao(descricao); // modifica a descricao da tarefa
                   return true;
               }
           }
       }
       return false;
   }

   @Override
   public List<Tarefa> listarTarefas(String idCliente) throws TException { // retorna a lista de tarefas do cliente com ID passado
       return getClient(idCliente).tarefa;
   }

   @Override
   public boolean apagarTarefas(String idCliente) throws TException {
       if(recuperarCliente(idCliente)){
           getClient(idCliente).tarefa.clear();
           System.out.println("Terefas apagadas");
           return true;
       }
       return false;
   }

   @Override
   public boolean apagarTarefa(String idCliente, String titulo) throws TException {
       if(recuperarCliente(idCliente)){
           for(int i = 0; i < getClient(idCliente).tarefa.size(); i++){
               if(getClient(idCliente).tarefa.get(i).titulo.equals(titulo)){ // encontra a tarefa com o mesmo titulo para modificar a descricao
                   getClient(idCliente).tarefa.remove(i); // apaga a tarefa
                   return true;
               }
           }
       }
       return false;
   }

   // retornar um cliente
   public Client getClient(String idCliente){
       return id.get(idCliente);
   }

}

