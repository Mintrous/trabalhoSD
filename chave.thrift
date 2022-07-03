namespace java chavevalor
namespace py chavevalor

exception IdNotFound {
   1:i64 time,
   2:i32 idCliente
}

service ChaveValor
{
    bool inserirCliente(1:string idCliente, 2:string nome, 3:i32 idade),
    bool modificarCliente(1:string idCliente, 2:string nome, 3:i32 idade),
    bool recuperarCliente(1:string idCliente),
    bool apagarCliente(1:string idCliente),
    bool inserirTarefa(1:string idCliente, 2:string titulo, 3:string descricao),
    bool modificarTarefa(1:string idCliente, 2:string titulo, 3:string descricao),
    list<Tarefa> listarTarefas(1:string idCliente),
    bool apagarTarefas(1:string idCliente),
    bool apagarTarefa(1:string idCliente, 2:string titulo)
}

// estrutura da tarefa
struct Tarefa
{
    1:string titulo;
    2:string descricao;
}

// dados do cliente
struct Client
{
    1:string idCliente;
    2:string nome;
    3:i32 idade;
    4:list <Tarefa> tarefa;
}

// adicionar a estrutura das tarefas que serao parte de cliente
