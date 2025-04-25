package Model;

import Model.Enums.StatusQuarto;
import Model.Enums.TipoServico;

public class Quarto {
    int id;
    private int numeroDoQuarto;
    private boolean isOcupado;
    private boolean isReservado;
    private TipoServico tipoServico;
    StatusQuarto status;

    public Quarto(int numero){
        this.numeroDoQuarto = numero;
        this.isOcupado = false;
        this.isReservado = false;
        this.tipoServico = TipoServico.SemServico;
    }

    public Quarto(int quartoId, int numero, StatusQuarto statusQuarto){
        this.numeroDoQuarto = numero;
        this.id = quartoId;
        this.status = statusQuarto;
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
        try{
            if(this.status != StatusQuarto.Vazio){
                throw new Exception("Quarto já está ocupado");
            }
            this.status = StatusQuarto.reservado;
            return new Reserva(this, cliente);
        }catch(Exception exception){
            System.out.println(exception.getMessage());
        }
       return null;
    }

    public void cancelarReserva(){}
}

