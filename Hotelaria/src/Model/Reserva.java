package Model;

import DAO.ReservaDAO;

public class Reserva {
    Quarto quarto;
    Cliente cliente;
    String dataReserva;
    
    public Reserva(Quarto quarto, Cliente cliente){
        this.quarto = quarto;
        this.cliente = cliente;
    }

    public static Reserva buscarReservaByQuarto(Quarto quarto) {
        // busca no banco uma reserva no numero informado do quarto
        String filtro = "WHERE quarto.numero = " + quarto.getNumeroDoQuarto();
        return ReservaDAO.getReserva(filtro);
    }
}
