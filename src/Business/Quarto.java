package Business;

import java.time.LocalDate;
import java.util.List;

import Data.DAOFactory;

public class Quarto {

    DAOFactory df;
    public Quarto(DAOFactory df) {
        this.df =  df;
    }

    public List<Model.Quarto>BuscarQuartosDisponiveisPorData(LocalDate data){
        return df.getQuartoDAO().buscarQuartosDisponiveisPorData(data);
    }

    public List<Model.Quarto>ListarTodos(){
        return df.getQuartoDAO().listarTodos();
    }
}
