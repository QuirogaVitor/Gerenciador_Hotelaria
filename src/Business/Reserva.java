package Business;

import Data.DAOFactory;
import java.util.LinkedList;
import java.util.List;

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
        return reserva != null && reserva.getCliente().getCpf().equals(cpf);
    }



    public Model.Reserva buscar(int codigo) {

        Model.Reserva reserva = df.getReservaDAO().buscarPorCodigo(codigo);

        if (reserva != null)
        {
            return reserva;
        }
            return null;
    
    }



    public List<Model.Reserva> buscarComFiltros(int codigo, String cpf, int numeroQuarto) {

        List<Model.Reserva> reservasFiltradas = new LinkedList<>();
        if (codigo != 0)
        {
            Model.Reserva reservaCod = df.getReservaDAO().buscarPorCodigo(codigo);
            if (reservaCod != null)
            {
                reservasFiltradas.add(reservaCod);
                return reservasFiltradas;
            }
        }
        List<Model.Reserva> reservas = df.getReservaDAO().listarTodos();
        List<Model.Reserva> reservasCpf = new LinkedList<>();
        List<Model.Reserva> reservasQuarto = new LinkedList<>();

        for (Model.Reserva reserva : reservas) {
            if (reserva.getCliente().getCpf().equals(cpf))
            {
                reservasCpf.add(reserva);
            }
            if (reserva.getQuarto().getNumero() == numeroQuarto)
            {
                reservasQuarto.add(reserva);
            }
        }        
        if (!reservasCpf.isEmpty() && numeroQuarto == 0)
        {
            return reservasCpf;
        }
        if (!reservasQuarto.isEmpty() && cpf == null)
        {
            return reservasQuarto;
        }
        
        for (Model.Reserva reserva : reservasQuarto) {
            if (reserva.getCliente().getCpf().equals(cpf))
            {
                reservasFiltradas.add(reserva);
            }
        }
        for (Model.Reserva reserva : reservasCpf) {
            if (reserva.getQuarto().getNumero() == numeroQuarto && !reservasFiltradas.contains(reserva))
            {
                reservasFiltradas.add(reserva);
            }
        }
        
        return reservasFiltradas;
    }

    
}
