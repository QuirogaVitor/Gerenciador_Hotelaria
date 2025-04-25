package Model;

import Model.Enums.TipoServico;

public class Cliente {
    String nome;
    String dataNasc;
    String telefone;
    String cpf;
    String login;

    public Cliente(String nome, String dataNasc, String telefone, String cpf, String login) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.telefone = telefone;
        this.cpf = cpf;
        this.login = login;
    }

    public void fazerCheckout() {
        throw new UnsupportedOperationException("Unimplemented method 'fazerCheckout'");
    }

    public void fazerCheckin() {
        throw new UnsupportedOperationException("Unimplemented method 'fazerCheckin'");
    }

    void chamarServicoQuarto(int quarto, TipoServico tipoServico) {
        System.out.println("A sua chamada já foi cadastrada, um funcionário já está a caminho!");
    }

    //funcao vai ter um if para saber se na tabela quartos o quarto está disponivel juntamente com o nome do hotel na qual vai fazer a reserva
    public Reserva fazerReserva(Quarto quarto) {
            return quarto.Reservar(this);
        }
    }

