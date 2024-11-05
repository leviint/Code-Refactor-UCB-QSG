package OriginalCode;

public class Funcionario {

    private String Name;   
    private String cargo;
    private double Salario;

    Funcionario(){
        this.Name = null;
        this.cargo = null;
        this.Salario = 0;
    }

    Funcionario(String nome, String cargo, double salario){
        this.Name = nome;
        this.cargo = cargo;
        this.Salario = salario;
    }
    

    public void setSalario(double salarioNovo){
        this.Salario = salarioNovo;
    }

    public double getSalario(){
        return Salario;
    }

    public void setCargo(String cargoNovo){
        this.cargo = cargoNovo;
    }

    public String getCargo(){
        return cargo;
    }

    public String getName(){
        return Name;
    }
    public void setName(String nomeNovo){
        this.Name = nomeNovo;
    }
}
