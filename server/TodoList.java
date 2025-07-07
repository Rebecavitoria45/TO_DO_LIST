package server;

import common.ItodoList;
import common.Tarefa;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TodoList extends UnicastRemoteObject implements ItodoList {

    private final Map<String, List<Tarefa>> tarefasPorUsuario;
    private int proximoId;

    public TodoList() throws RemoteException {
        super();
        this.tarefasPorUsuario = new HashMap<>();
        this.proximoId = 1;
    }

    @Override
    public synchronized boolean registrarUsuario(String cpf) throws RemoteException {
        if (tarefasPorUsuario.containsKey(cpf)) {
            return false;
        }
        tarefasPorUsuario.put(cpf, new ArrayList<>());
        return true;
    }

    @Override
    public synchronized boolean adicionarTarefa(String cpf, String descricao) throws RemoteException {
        List<Tarefa> tarefas = tarefasPorUsuario.get(cpf);
        if (tarefas == null) {
            return false;
        }
        Tarefa novaTarefa = new Tarefa(proximoId, descricao);
        tarefas.add(novaTarefa);
        proximoId++;
        return true;
    }

    @Override
    public List<Tarefa> listarTarefas(String cpf) throws RemoteException {
        return tarefasPorUsuario.getOrDefault(cpf, new ArrayList<>());
    }

    @Override
    public synchronized boolean removerTarefa(String cpf, int id) throws RemoteException {
        List<Tarefa> tarefas = tarefasPorUsuario.get(cpf);
        if (tarefas == null) {
            return false;
        }
        return tarefas.removeIf(tarefa -> tarefa.getId() == id);
    }

    public static void main(String[] args) {
        try {
            TodoList servidor = new TodoList();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("//localhost/TodoListService", servidor);
            System.out.println(">>> Servidor pronto e aguardando conexões...");
        } catch (Exception e) {
            System.err.println("Ocorreu um erro no servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}