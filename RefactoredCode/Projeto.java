package RefactoredCode;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Projeto {
    private String nome;
    private LocalDate prazoEntrega;
    private final List<Funcionario> funcionarios = new ArrayList<>();

    private static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Projeto() {
        this("", null);
    }

    public Projeto(String nome, String prazoEntrega) {
        this.nome = nome;
        setPrazoEntrega(prazoEntrega);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getPrazoEntrega() {
        return prazoEntrega;
    }

    public boolean setPrazoEntrega(String prazoEntrega) {
        try {
            this.prazoEntrega = LocalDate.parse(prazoEntrega, FORMATADOR);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido. Use o formato dd/MM/yyyy.");
            return false;
        }
    }

    public boolean estaDentroDoPrazo() {
        return prazoEntrega != null && (prazoEntrega.isAfter(LocalDate.now()) || prazoEntrega.isEqual(LocalDate.now()));
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
        System.out.println("Funcionário " + funcionario.getNome() + " adicionado ao projeto.");
    }

    public void removerFuncionarioPorNome(String nome) {
        Funcionario funcionario = encontrarFuncionarioPorNome(nome);
        if (funcionario != null) {
            funcionarios.remove(funcionario);
            System.out.println("Funcionário " + funcionario.getNome() + " removido do projeto.");
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    public void listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário neste projeto.");
        } else {
            System.out.println("Funcionários do projeto " + nome + ":");
            funcionarios.forEach(func -> System.out.println("Nome: " + func.getNome() + ", Cargo: " + func.getCargo() + ", Salário: " + func.getSalario()));
        }
    }

    private Funcionario encontrarFuncionarioPorNome(String nome) {
        return funcionarios.stream()
                .filter(func -> func.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    public void editarFuncionarioPorNome(String nome) {
        Funcionario funcionario = encontrarFuncionarioPorNome(nome);
        if (funcionario != null) {
            new FuncionarioEditor().editarFuncionario(funcionario);
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    public void exibirPrazo() {
        if (prazoEntrega != null) {
            System.out.println("Prazo de entrega do projeto: " + prazoEntrega.format(FORMATADOR));
        } else {
            System.out.println("Prazo de entrega não definido.");
        }
    }

    private static class FuncionarioEditor {
        private final Scanner sc = new Scanner(System.in);

        public void editarFuncionario(Funcionario funcionario) {
            boolean entradaValida = false;
            while (!entradaValida) {
                try {
                    System.out.printf("Escolha o que deseja editar para o funcionário %s:\n[1] - Nome\n[2] - Cargo\n[3] - Salário\n", funcionario.getNome());
                    int opcao = sc.nextInt();
                    sc.nextLine();

                    switch (opcao) {
                        case 1 -> {
                            System.out.print("Digite o novo nome: ");
                            funcionario.setNome(sc.nextLine());
                            entradaValida = true;
                        }
                        case 2 -> {
                            System.out.print("Digite o novo cargo: ");
                            funcionario.setCargo(sc.nextLine());
                            entradaValida = true;
                        }
                        case 3 -> {
                            System.out.print("Digite o novo salário: ");
                            funcionario.setSalario(sc.nextDouble());
                            entradaValida = true;
                        }
                        default -> System.out.println("Opção inválida. Escolha uma opção entre 1 e 3.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Opção inválida. Insira um número inteiro.");
                    sc.nextLine();
                }
            }
        }
    }
}
