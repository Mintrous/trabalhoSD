import org.apache.thrift.TException;
import java.util.HashMap;
import chavevalor.Client;
import chavevalor.ChaveValor;

// Implementa o serviço
public class ClienteHandler implements chavevalor.ChaveValor.Iface {
   private final HashMap<String,Client> id = new HashMap<>();

   // metodos de casos de uso
   @Override
   public boolean inserirCliente(String idCliente, String nome, int idade) throws TException {
       if(id.containsKey(idCliente))
           return false;


       Client client = new Client(idCliente, nome, idade);
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

}

