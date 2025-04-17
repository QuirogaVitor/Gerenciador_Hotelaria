package Model;

import Model.Enums.TipoServico;

public class Quarto {
    private int numeroDoQuarto;
    private boolean isOcupado;
    private boolean isReservado;
    private TipoServico tipoServico;

    public Quarto(int numero){
        this.numeroDoQuarto = numero;
        this.isOcupado = false;
        this.isReservado = false;
        this.tipoServico = TipoServico.SemServico;
    }

    public int getNumeroDoQuarto(){
        return this.numeroDoQuarto;
    }

    public boolean getIsOcupado(){
        return this.isOcupado;
    }

    public boolean getIsReservado(){
        return this.isReservado;
    }   

    public TipoServico getServicoDeQuartoChamado(){
            return this.tipoServico;
    }

    public Reserva Reservar(Cliente cliente) {
        this.isReservado = true;
        return new Reserva(null, cliente);
    }

    public void cancelarReserva(){}
}

