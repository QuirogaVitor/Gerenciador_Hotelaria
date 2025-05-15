package Business;

import Data.DAOFactory;

public class BusinessFactory {
    DAOFactory df;
    public BusinessFactory(){
        df = new DAOFactory();
    }

    public UsuarioFuncionario UsuarioFuncionario(){
        return new UsuarioFuncionario(df);
    }

    public UsuarioCliente UsuarioCliente() {
        return new UsuarioCliente(df);
    }
}
