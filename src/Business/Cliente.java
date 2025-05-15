package Business;

import java.security.InvalidParameterException;

import Data.DAOFactory;
import Utils.ValidadorCPF;

public class Cliente {
    DAOFactory df;

    public Cliente(DAOFactory df){
        this.df = df;
    }

    public Model.Cliente buscarPorCpf(String cpf) {
        
        if(!ValidadorCPF.validarCPF(cpf)){
            throw new InvalidParameterException("Cpf inválido");
        }

        return df.getClienteDAO().buscarPorCpf(cpf);
    }
}
