package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ItodoList extends Remote {
    boolean registrarUsuario(String cpf) throws RemoteException;
    boolean adicionarTarefa(String cpf, String descricao) throws RemoteException;
    List<Tarefa> listarTarefas(String cpf) throws RemoteException;
    boolean removerTarefa(String cpf, int id) throws RemoteException;
}
