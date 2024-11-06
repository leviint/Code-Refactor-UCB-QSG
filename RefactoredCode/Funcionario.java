package RefactoredCode;

public class Funcionario {

    private String nome;   
    private String cargo;
    private double salario;

    public Funcionario() {
        this(null, null, 0);
    }

    public Funcionario(String nome, String cargo, double salario) {
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
    }

    public void setSalario(double salarioNovo) {
        this.salario = salarioNovo;
    }

    public double getSalario() {
        return salario;
    }

    public void setCargo(String cargoNovo) {
        this.cargo = cargoNovo;
    }

    public String getCargo() {
        return cargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nomeNovo) {
        this.nome = nomeNovo;
    }
}
