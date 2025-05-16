package Business;

import java.time.LocalDate;
import java.util.List;

import Data.DAOFactory;
import Model.Quarto;

public class Reserva {
    DAOFactory df;

    public Reserva(DAOFactory df){
        this.df = df;
    }

    public String removerReserva(int codigoReserva)
    {
        if (df.getReservaDAO().buscarPorCodigo(codigoReserva) == null)
        {
            return "Erro, reserva não encontrada";
        } 
        
        df.getReservaDAO().excluir(codigoReserva);

        if (df.getReservaDAO().buscarPorCodigo(codigoReserva) == null)
        {
            return "Sucesso!";
        }

        return "Erro ao excluir, tente novamente";
    }

    public String inserirReserva(Model.Reserva reserva)
    {    
        if (df.getReservaDAO().buscarPorCodigo(reserva.getCodigoReserva()) != null)
        {
            return "Erro, código de reserva existente, tente novamente";
        }
        df.getReservaDAO().inserir(reserva);
        if (df.getReservaDAO().buscarPorCodigo(reserva.getCodigoReserva()) != null)
        {
            return "Sucesso!";
        }
        return "Erro inserindo nova reserva";
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
