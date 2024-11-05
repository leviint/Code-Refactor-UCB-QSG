package OriginalCode;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    Scanner sc = new Scanner(System.in);
    List<Projeto> projetos = new ArrayList<>();

    public static void main(String[] args) {
        Principal pr = new Principal();
        pr.menuPrincipal();
    }

    public void menuPrincipal() {
        boolean projetoRodando = true;

        while (projetoRodando) {
            try {                
                System.out.printf("\t\t_____TaskFlow_____\n\t\tGerenciador de Projeto:\n[1] - Gerenciar Projeto\n[2] - Gerenciar Funcionários de um Projeto\n[3] - Sair\n");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 -> GerenciarProjeto();
                    case 2 -> GerenciarFuncionariosProjeto();
                    case 3 -> {
                        System.out.println("Encerrando o sistema.");
                        projetoRodando = false;
                    }
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número.");
                sc.nextLine();
            }
        }
    }

    public void GerenciarProjeto() {
        System.out.printf("\t\t_____TaskFlow_____\nProjeto:\n[1] - Criar Projeto\n[2] - Remover Projeto\n[3] - Verificar Prazo\n");
        
        int choice = sc.nextInt();
        sc.nextLine(); 
        boolean entradaValida = false;
    
        while (!entradaValida) {
            try {
                switch (choice) {
                    case 1 -> {
                        System.out.println("Digite o nome do projeto:");
                        String nomeProjeto = sc.nextLine();
                        System.out.println("Digite o prazo de entrega do projeto (formato dd/MM/yyyy):");
                        String prazoEntrega = sc.nextLine();
    
                        Projeto projeto = new Projeto(nomeProjeto, prazoEntrega);
    
                        boolean prazoValido = projeto.prazoEntregaValido(prazoEntrega);
    
                        if (prazoValido) {
                            projetos.add(projeto);
                            System.out.println("Projeto " + nomeProjeto + " criado com sucesso.");
                        } else {
                            System.out.println("Projeto " + nomeProjeto + " não pode ser criado.");
                        }
                        entradaValida = true;
                    }
                    case 2 -> {
                        if (projetos.isEmpty()) {
                            System.out.println("Nenhum projeto cadastrado para remover.");
                        } else {
                            listarProjetos();
                            System.out.println("Digite o número do projeto a ser removido:");
                            int index = sc.nextInt() - 1;
                            if (index >= 0 && index < projetos.size()) {
                                Projeto removido = projetos.remove(index);
                                System.out.println("Projeto " + removido.getNome() + " removido.");
                            } else {
                                System.out.println("Índice inválido.");
                            }
                        }
                        entradaValida = true;
                    }
                    case 3 -> {
                        if (projetos.isEmpty()) {
                            System.out.println("Nenhum projeto cadastrado para verificar o prazo.");
                        } else {
                            listarProjetos();
                            System.out.println("Digite o número do projeto para verificar o prazo:");
                            int index = sc.nextInt() - 1;
    
                            if (index >= 0 && index < projetos.size()) {
                                Projeto projeto = projetos.get(index);
                                projeto.exibirPrazo();
                                if (projeto.estaDentroDoPrazo()) {
                                    System.out.println("O projeto está dentro do prazo.");
                                } else {
                                    System.out.println("O prazo do projeto já foi ultrapassado.");
                                }
                            } else {
                                System.out.println("Índice inválido.");
                            }
                        }
                        entradaValida = true;
                    }
                    default -> {
                        System.out.println("Opção inválida. Por favor, escolha uma opção entre 1 e 3.");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite um número válido!");
                sc.nextLine();
            }
        }
    }
    
    

    public void criarProjeto() {
        System.out.println("Digite o nome do projeto:");
        String nomeProjeto = sc.nextLine();
        System.out.println("Digite o prazo de entrega do projeto (formato dd/MM/yyyy):");
        String prazoEntrega = sc.nextLine();

        Projeto projeto = new Projeto(nomeProjeto, prazoEntrega);
        if (projeto.prazoEntregaValido(prazoEntrega)) {
            projetos.add(projeto);
            System.out.println("Projeto " + nomeProjeto + " criado com sucesso.");
        } else {
            System.out.println("Formato de data inválido. Projeto não pode ser criado.");
        }
    }

    public void removerProjeto() {
        listarProjetos();
        System.out.println("Digite o número do projeto a ser removido:");
        int index = sc.nextInt() - 1;

        if (index >= 0 && index < projetos.size()) {
            Projeto removido = projetos.remove(index);
            System.out.println("Projeto " + removido.getNome() + " removido.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public void verificarPrazo() {
        listarProjetos();
        System.out.println("Digite o número do projeto para verificar o prazo:");
        int index = sc.nextInt() - 1;

        if (index >= 0 && index < projetos.size()) {
            Projeto projeto = projetos.get(index);
            projeto.exibirPrazo();
            if (projeto.estaDentroDoPrazo()) {
                System.out.println("O projeto está dentro do prazo.");
            } else {
                System.out.println("O prazo do projeto já foi ultrapassado.");
            }
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public void GerenciarFuncionariosProjeto() {
        if (projetos.isEmpty()) {
            System.out.println("Você primeiro precisa criar um projeto!");
            return;
        }

        listarProjetos();
        System.out.println("Escolha o projeto para gerenciar os funcionários:");
        int indexProjeto = sc.nextInt() - 1;
        sc.nextLine();

        if (indexProjeto >= 0 && indexProjeto < projetos.size()) {
            Projeto projeto = projetos.get(indexProjeto);
            boolean gerenciandoFuncionarios = true;

            while (gerenciandoFuncionarios) {
                System.out.printf("\t\t_____TaskFlow_____\nFuncionários do Projeto %s:\n[1] - Adicionar Funcionário\n[2] - Remover Funcionário\n[3] - Editar Funcionário\n[4] - Listar Funcionários\n[5] - Voltar\n", projeto.getNome());
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 -> adicionarFuncionario(projeto);
                    case 2 -> removerFuncionario(projeto);
                    case 3 -> editarFuncionario(projeto);
                    case 4 -> projeto.listarFuncionarios();
                    case 5 -> gerenciandoFuncionarios = false;
                    default -> System.out.println("Opção inválida.");
                }
            }
        } else {
            System.out.println("Índice de projeto inválido.");
        }
    }

    public void adicionarFuncionario(Projeto projeto) {
        Funcionario fun = new Funcionario();
        System.out.println("Digite o nome do funcionário:");
        fun.setName(sc.nextLine());
        System.out.println("Digite o cargo do funcionário:");
        fun.setCargo(sc.nextLine());
        System.out.println("Digite o salário do funcionário:");
        fun.setSalario(sc.nextDouble());
        projeto.adicionarFuncionario(fun);
    }

    public void removerFuncionario(Projeto projeto) {
        System.out.println("Digite o nome do funcionário a ser removido:");
        String nomeFuncionario = sc.nextLine();
        projeto.removerFuncionarioPorNome(nomeFuncionario);
    }

    public void editarFuncionario(Projeto projeto) {
        System.out.println("Digite o nome do funcionário a ser editado:");
        String nomeFuncionario = sc.nextLine();
        projeto.editarFuncionarioPorNome(nomeFuncionario);
    }

    public void listarProjetos() {
        if (projetos.isEmpty()) {
            System.out.println("Nenhum projeto cadastrado.");
        } else {
            System.out.println("Projetos disponíveis:");
            for (int i = 0; i < projetos.size(); i++) {
                Projeto projeto = projetos.get(i);
                System.out.println((i + 1) + ". " + projeto.getNome());
            }
        }
    }
}
