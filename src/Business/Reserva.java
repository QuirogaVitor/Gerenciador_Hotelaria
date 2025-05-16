package Business;

import java.util.List;

import Data.DAOFactory;

public class Reserva {
    DAOFactory df;

    public Reserva(DAOFactory df){
        this.df = df;
    }

    

    public void validarReserva(int codigo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validarReserva'");
    }



    public List<Model.Reserva> buscar(int codigo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscar'");
    }



    public List<Model.Reserva> buscarComFiltros(Integer codigo, String cpf, Integer numeroQuarto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarComFiltros'");
    }

    
}
