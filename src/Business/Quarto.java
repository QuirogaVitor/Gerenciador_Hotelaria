package Business;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Data.DAOFactory;
import Model.Quarto.Status;

public class Quarto {

    DAOFactory df;
    public Quarto(DAOFactory df) {
        this.df =  df;
    }

    public Model.Quarto buscarQuartoPorNumero(int numeroQuarto)
    {
        Model.Quarto quarto = df.getQuartoDAO().buscarPorNumero(numeroQuarto);
        if (quarto != null)
        {
            return quarto;
        }
        return null;
    }

    public List<Model.Quarto>BuscarQuartosDisponiveisPorData(LocalDate data){
        return df.getQuartoDAO().buscarQuartosDisponiveisPorData(data);
    }

    public List<Model.Quarto>ListarTodos(){
        return df.getQuartoDAO().listarTodos();
    }

    public String inserirNovoQuartoPadrao(){

        List<Model.Quarto>quartosExistentes = ListarTodos();

        Model.Quarto maior = Collections.max(quartosExistentes, Comparator.comparingInt(Model.Quarto::getNumero));
        Model.Quarto novo = new Model.Quarto();
        novo.setNumero(maior.getNumero() + 1);
        novo.setStatus(Status.VAZIO);
        df.getQuartoDAO().inserir(novo);
        if (df.getQuartoDAO().buscarPorNumero(novo.getNumero()) != null)
        {
            return "Inserido com Sucesso";
        }
        else
        {
            return "Erro ao inserir o novo quarto";
        }
    }
    public void reservar(Model.Quarto quarto)
    {
        if (quarto != null)
        {
            quarto.setStatus(Status.RESERVADO);
            df.getQuartoDAO().atualizar(quarto);
        }
    }

    public void checkin(Model.Quarto quarto)
    {
        if (quarto != null)
        {
            quarto.setStatus(Status.OCUPADO);
            df.getQuartoDAO().atualizar(quarto);
        }
    }

    public void checkout(Model.Quarto quarto) {
        if (quarto != null)
        {
            quarto.setStatus(Status.VAZIO);
            df.getQuartoDAO().atualizar(quarto);
        }
    }
}
