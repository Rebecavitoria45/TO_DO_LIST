package cliente;

import common.ItodoList;
import common.Tarefa;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class Cliente {

    private static void exibirMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Listar tarefas");
        System.out.println("2. Adicionar tarefa");
        System.out.println("3. Remover tarefa");
        System.out.println("4. Sair");
        System.out.print("Escolha: ");
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ItodoList stub = (ItodoList) registry.lookup("TodoListService");

            Scanner sc = new Scanner(System.in);
            System.out.println("=== To‑Do List ===");
            System.out.print("Informe seu CPF: ");
            String cpf = sc.nextLine().trim();

            stub.registrarUsuario(cpf);

            boolean executando = true;

            while (executando) {
                exibirMenu();
                int opcao;
                try {
                    opcao = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println(" Opção inválida.");
                    continue;
                }

                switch (opcao) {
                    case 1:
                        List<Tarefa> tarefas = stub.listarTarefas(cpf);
                        if (tarefas.isEmpty()) {
                            System.out.println(" Nenhuma tarefa encontrada.");
                        } else {
                            System.out.println(" Suas tarefas:");
                            tarefas.forEach(t -> System.out.println("➡️ " + t));
                        }
                        break;

                    case 2:
                        System.out.print("Descrição da tarefa: ");
                        String descricao = sc.nextLine().trim();
                        if (descricao.isEmpty()) {
                            System.out.println(" Descrição não pode ser vazia.");
                            break;
                        }
                        if (stub.adicionarTarefa(cpf, descricao)) {
                            System.out.println(" Tarefa adicionada!");
                        } else {
                            System.out.println(" Erro ao adicionar tarefa.");
                        }
                        break;

                    case 3:
                        System.out.print("ID da tarefa a remover: ");
                        int id;
                        try {
                            id = Integer.parseInt(sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("ID inválido.");
                            break;
                        }
                        if (stub.removerTarefa(cpf, id)) {
                            System.out.println("Tarefa removida!");
                        } else {
                            System.out.println(" Não foi possível remover (ID inexistente?).");
                        }
                        break;

                    case 4:
                        System.out.println("Encerrando o Sistema...");
                        executando = false;
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }
            }

        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
