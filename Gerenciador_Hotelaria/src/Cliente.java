package src;

public class Cliente {
    String nome;
    String dataNasc;
    String telefone;
    String cpf;

    public Cliente(String nome, String dataNasc, String telefone, String cpf) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    void chamarServicoQuarto(int quarto, String tipoServico) {
        System.out.println("A sua chamada já foi cadastrada, um funcionário já está a caminho!");
    }

    //funcao vai ter um if para saber se na tabela quartos o quarto está disponivel juntamente com o nome do hotel na qual vai fazer a reserva
    int fazerReserva(int quarto, String nomeHotel) {
        return 0;
    }
}
