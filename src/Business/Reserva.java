package Business;

import Data.DAOFactory;
import Model.Quarto;
import Model.Reserva.Status;

import java.time.LocalDate;
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
        if (reserva.getDataCheckout().isBefore(reserva.getDataCheckin()))
        {
            return "Erro, data de saída não pode ser menor que a data de entrada";
        }
        reserva.setStatus(Status.AGUARDANDO);
        df.getReservaDAO().inserir(reserva);
        Model.Reserva reservaFeita = df.getReservaDAO().buscarPorCodigo(reserva.getCodigoReserva());
        if (reservaFeita != null)
        {
            BusinessFactory bf = new BusinessFactory();
            bf.Quarto().reservar(reservaFeita.getQuarto());
            return "Sucesso! Código da nova reserva é: " + reservaFeita.getCodigoReserva();
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
        if(codigo == 0 && cpf == "" && numeroQuarto == 0)
            {
                return reservas;
            }
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
        if (!reservasQuarto.isEmpty() && cpf == "")
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

    public String fazerCheckIn(int codigoReserva)
    {
        Model.Reserva reserva = df.getReservaDAO().buscarPorCodigo(codigoReserva);
        if (reserva != null)
        {
            reserva.setDataCheckin(LocalDate.now());
            reserva.setStatus(Status.OCUPADO);
            df.getReservaDAO().atualizar(reserva);
            BusinessFactory bf = new BusinessFactory();
            bf.Quarto().checkin(reserva.getQuarto());
            return "Sucesso!";
        }
        
        return "Erro, reserva não encontrada";
        
    }

    public String fazerCheckOut(int numeroQuarto)
    {
        Model.Reserva reserva = df.getReservaDAO().buscarAtivaPorNumeroQuarto(numeroQuarto);
        if (reserva != null)
        {
            reserva.setDataCheckout(LocalDate.now());
            reserva.setStatus(Status.FINALIZADO);
            df.getReservaDAO().atualizar(reserva);
            BusinessFactory bf = new BusinessFactory();
            bf.Quarto().checkout(reserva.getQuarto());
            return "Sucesso";
        }

        return "Erro ao fazer Checkout";
    }

    public List<Model.Reserva>listar()
    {
        return df.getReservaDAO().listarTodos();
    }

    
}
