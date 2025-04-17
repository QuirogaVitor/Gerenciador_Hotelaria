package Model;

import java.time.LocalDate;

import DAO.ReservaDAO;

public class Reserva {
    Quarto quarto;
    Cliente cliente;
    String dataReserva;
    
    public Reserva(Quarto quarto, Cliente cliente){
        this.quarto = quarto;
        this.cliente = cliente;
        this.dataReserva = LocalDate.now().toString();
    }

    public static Reserva buscarReservaByQuarto(Quarto quarto) {
        // busca no banco uma reserva no numero informado do quarto
        String filtro = "WHERE quarto.numero = " + quarto.getNumeroDoQuarto();
        return ReservaDAO.getReserva(filtro);
    }
}
