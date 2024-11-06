package RefactoredCode;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private final Scanner sc = new Scanner(System.in);
    private final List<Projeto> projetos = new ArrayList<>();

    public static void main(String[] args) {
        new Principal().menuPrincipal();
    }

    public void menuPrincipal() {
        while (true) {
            System.out.println("_____TaskFlow_____\nGerenciador de Projeto:\n[1] - Gerenciar Projeto\n[2] - Gerenciar Funcionários de um Projeto\n[3] - Sair");
            int choice = getIntInput();

            switch (choice) {
                case 1 -> gerenciarProjeto();
                case 2 -> gerenciarFuncionariosProjeto();
                case 3 -> {
                    System.out.println("Encerrando o sistema.");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void gerenciarProjeto() {
        System.out.println("_____TaskFlow_____\nProjeto:\n[1] - Criar Projeto\n[2] - Remover Projeto\n[3] - Verificar Prazo");
        int choice = getIntInput();

        switch (choice) {
            case 1 -> criarProjeto();
            case 2 -> removerProjeto();
            case 3 -> verificarPrazo();
            default -> System.out.println("Opção inválida.");
        }
    }

    private void criarProjeto() {
        System.out.print("Digite o nome do projeto: ");
        String nome = sc.nextLine();
        System.out.print("Digite o prazo de entrega (formato dd/MM/yyyy): ");
        String prazo = sc.nextLine();

        Projeto projeto = new Projeto(nome, prazo);
        if (projeto.getPrazoEntrega() != null) {
            projetos.add(projeto);
            System.out.println("Projeto criado com sucesso.");
        } else {
            System.out.println("Formato de data inválido.");
        }
    }

    private void removerProjeto() {
        listarProjetos();
        System.out.print("Digite o número do projeto a ser removido: ");
        int index = getIntInput() - 1;

        if (isIndexValid(index, projetos)) {
            System.out.println("Projeto " + projetos.remove(index).getNome() + " removido.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    private void verificarPrazo() {
        listarProjetos();
        System.out.print("Digite o número do projeto para verificar o prazo: ");
        int index = getIntInput() - 1;

        if (isIndexValid(index, projetos)) {
            Projeto projeto = projetos.get(index);
            projeto.exibirPrazo();
            System.out.println(projeto.estaDentroDoPrazo() ? "Dentro do prazo." : "Prazo ultrapassado.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    private void gerenciarFuncionariosProjeto() {
        if (projetos.isEmpty()) {
            System.out.println("Nenhum projeto disponível.");
            return;
        }
        listarProjetos();
        System.out.print("Escolha o projeto: ");
        int index = getIntInput() - 1;

        if (isIndexValid(index, projetos)) {
            Projeto projeto = projetos.get(index);
            while (true) {
                System.out.printf("Funcionários do Projeto %s:\n[1] - Adicionar\n[2] - Remover\n[3] - Editar\n[4] - Listar\n[5] - Voltar\n", projeto.getNome());
                int opcao = getIntInput();

                switch (opcao) {
                    case 1 -> projeto.adicionarFuncionario(obterFuncionario());
                    case 2 -> removerFuncionario(projeto);
                    case 3 -> editarFuncionario(projeto);
                    case 4 -> projeto.listarFuncionarios();
                    case 5 -> {
                        return;
                    }
                    default -> System.out.println("Opção inválida.");
                }
            }
        } else {
            System.out.println("Índice inválido.");
        }
    }

    private Funcionario obterFuncionario() {
        System.out.print("Nome do funcionário: ");
        String nome = sc.nextLine();
        System.out.print("Cargo: ");
        String cargo = sc.nextLine();
        System.out.print("Salário: ");
        double salario = getDoubleInput();
        return new Funcionario(nome, cargo, salario);
    }

    private void removerFuncionario(Projeto projeto) {
        System.out.print("Nome do funcionário a remover: ");
        String nome = sc.nextLine();
        projeto.removerFuncionarioPorNome(nome);
    }

    private void editarFuncionario(Projeto projeto) {
        System.out.print("Nome do funcionário a editar: ");
        String nome = sc.nextLine();
        projeto.editarFuncionarioPorNome(nome);
    }

    private void listarProjetos() {
        System.out.println("Projetos disponíveis:");
        for (int i = 0; i < projetos.size(); i++) {
            System.out.println((i + 1) + " - " + projetos.get(i).getNome());
        }
    }

    private int getIntInput() {
        while (true) {
            try {
                int input = sc.nextInt();
                sc.nextLine();
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
                sc.nextLine();
            }
        }
    }

    private double getDoubleInput() {
        while (true) {
            try {
                double input = sc.nextDouble();
                sc.nextLine();
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um valor numérico.");
                sc.nextLine();
            }
        }
    }

    private boolean isIndexValid(int index, List<?> list) {
        return index >= 0 && index < list.size();
    }
}
