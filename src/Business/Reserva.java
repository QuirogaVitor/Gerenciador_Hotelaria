package Business;

import java.util.List;

import Data.DAOFactory;

public class Reserva {
    DAOFactory df;

    public Reserva(DAOFactory df){
        this.df = df;
    }

    

    public boolean validarReserva(int codigo, String cpf) {

        Model.Reserva reserva = df.getReservaDAO().buscarPorCodigo(codigo);
        if (reserva != null && reserva.getCliente().getCpf() == cpf)
        {
            return true;
        }
        return false;
    }



    public Model.Reserva buscar(int codigo) {

        Model.Reserva reserva = df.getReservaDAO().buscarPorCodigo(codigo);

        if (reserva != null)
        {
            return reserva;
        }
            return null;
    
    }

    
}
