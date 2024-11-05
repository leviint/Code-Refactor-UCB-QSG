package OriginalCode;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;

import java.util.Scanner;

public class Projeto {
    private String nome;
    private LocalDate prazoEntrega;
    private List<Funcionario> funcionarios = new ArrayList<>();

    
    private static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    
    public Projeto() {
        this.nome = "";
        this.prazoEntrega = null;
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
   
    
    public boolean prazoEntregaValido(String prazoEntrega) {
        try {
            this.prazoEntrega = LocalDate.parse(prazoEntrega, FORMATADOR); 
            return true;
        } catch (DateTimeParseException e) {            
            return false;
        }
    }
    
    public void setPrazoEntrega(String prazoEntrega) {
        try {
            this.prazoEntrega = LocalDate.parse(prazoEntrega, FORMATADOR); 
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido. Use o formato dd/MM/yyyy.");
        }
    }

    
    public boolean estaDentroDoPrazo() {
        LocalDate dataAtual = LocalDate.now(); 
        return prazoEntrega.isAfter(dataAtual) || prazoEntrega.isEqual(dataAtual); 
    }

    
    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
        System.out.println("Funcionário " + funcionario.getName() + " adicionado ao projeto.");
    }

    public void removerFuncionarioPorNome(String nome) {
        Funcionario funcionarioParaRemover = encontrarFuncionarioPorNome(nome);
        if (funcionarioParaRemover != null) {
            funcionarios.remove(funcionarioParaRemover);
            System.out.println("Funcionário " + funcionarioParaRemover.getName() + " removido do projeto.");
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    public void listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário neste projeto.");
        } else {
            System.out.println("Funcionários do projeto " + this.nome + ":");
            for (int i = 0; i < funcionarios.size(); i++) {
                Funcionario fun = funcionarios.get(i);
                System.out.println((i + 1) + ". Nome: " + fun.getName() + ", Cargo: " + fun.getCargo() + ", Salário: " + fun.getSalario());
            }
        }
    }

    
    private Funcionario encontrarFuncionarioPorNome(String nome) {
        for (Funcionario fun : funcionarios) {
            if (fun.getName().equalsIgnoreCase(nome)) {
                return fun;
            }
        }
        return null; 
    }

    public void editarFuncionarioPorNome(String nome) {
    Scanner sc = new Scanner(System.in);
    
    Funcionario funcionarioParaEditar = encontrarFuncionarioPorNome(nome);
    if (funcionarioParaEditar != null) {
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                System.out.printf("Escolha o que deseja editar para o funcionário %s:\n[1] - Nome\n[2] - Cargo\n[3] - Salário\n", funcionarioParaEditar.getName());
                int opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1 -> {
                        System.out.printf("Digite o novo nome:\n");
                        String novoNome = sc.nextLine();
                        funcionarioParaEditar.setName(novoNome);
                        System.out.println("Nome atualizado.");
                        entradaValida = true;
                    }
                    case 2 -> {
                        System.out.printf("Digite o novo cargo:\n");
                        String novoCargo = sc.nextLine();
                        funcionarioParaEditar.setCargo(novoCargo);
                        System.out.println("Cargo atualizado.");
                        entradaValida = true;
                    }
                    case 3 -> {
                        System.out.printf("Digite o novo salário:\n");
                        double novoSalario = sc.nextDouble();
                        funcionarioParaEditar.setSalario(novoSalario);
                        System.out.println("Salário atualizado.");
                        entradaValida = true;
                    }
                    default -> {
                        System.out.println("Opção inválida. Por favor, escolha uma opção entre 1 e 3.");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida. Por favor, insira um número inteiro.");
                sc.nextLine();
            }
        }
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
}
