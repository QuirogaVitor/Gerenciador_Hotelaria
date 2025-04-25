package Model;

import java.sql.Date;

import DAO.ClienteDAO;
import DAO.QuartoDAO;
import DAO.ReservaDAO;
import Model.Enums.StatusReserva;

public class Reserva {
    int idReserva;
    Quarto quarto;
    Cliente cliente;
    Date dataCheckIn;
    Date dataCheckOut;
    StatusReserva status;

    
    public Reserva(Quarto quarto, Cliente cliente){
        this.quarto = quarto;
        this.cliente = cliente;
    }

    public Reserva(int idReserva, int idCliente, int idQuarto, Date dataCheckIn, Date dataCheckOut, StatusReserva statusReserva){
        this.idReserva = idReserva;
        String filtro = "WHERE id = " + idQuarto;
        this.quarto = QuartoDAO.buscarQuarto(filtro);
        filtro = "WHERE id = " + idCliente;
        this.cliente = ClienteDAO.buscarCliente(filtro);
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
        this.status = statusReserva;
    }

    public static Reserva buscarReservaByQuarto(Quarto quarto) {
        // busca no banco uma reserva no numero informado do quarto
        String filtro = "WHERE quarto.numero = " + quarto.getNumeroDoQuarto();
        return ReservaDAO.buscarReserva(filtro);
    }

    public static Reserva buscarReservaByCliente(Cliente cliente){
        String filtro = "WHERE cliente.cpf = " + cliente.cpf;
        return ReservaDAO.buscarReserva(filtro);
    }
}
