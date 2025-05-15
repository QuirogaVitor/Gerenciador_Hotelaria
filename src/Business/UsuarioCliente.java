package Business;

import Data.DAOFactory;
import Model.Cliente;

public class UsuarioCliente {


    DAOFactory df;

    public UsuarioCliente(DAOFactory df){
        this.df = df;
    }

    public String CadastrarUsuario(Model.Usuario.UsuarioCliente usuarioCliente) {
        // Podemos usar alguma lógica para validar o usuario antes de cadastrar, tipo validar CNPJ, ver se a idade é maior de 18, etc... !IMPORTANTE
        // Fazer os IFs, se não passar devolve uma mensagem de erro na String de retorno com o campo que não foi validado

        df.getClienteDAO().inserir(usuarioCliente.getCliente());
        Cliente clienteAdicionado = df.getClienteDAO().buscarPorCpf(usuarioCliente.getCliente().getCpf());
        usuarioCliente.setCliente(clienteAdicionado);
        df.getUsuarioClienteDAO().inserir(usuarioCliente);
        
        return "Sucesso";
    }
}
