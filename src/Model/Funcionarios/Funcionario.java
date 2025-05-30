package Model.Funcionarios;

import Model.Enums.Cargo;
import Model.Enums.Funcao;
import java.time.LocalDate;

public class Funcionario {
    private Funcao funcaoAtual;
    private int funcionarioId;
    private Cargo cargo;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;

    public Funcionario(){

    }

    public Funcionario(Cargo cargo, String nome, String cpf, String email, String telefone, LocalDate dataNascimento) {
        this.cargo = cargo;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }

    public void Ocupar(){
        this.funcaoAtual = Funcao.Atendendo;
    }

    public void Desocupar(){
        this.funcaoAtual = Funcao.Aguardando;
    }

    public boolean isOcupado(){
        return this.funcaoAtual == Funcao.Atendendo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }
}
