package Business;

import Data.DAOFactory;
import Model.Funcionarios.Funcionario;

import java.sql.SQLException;

public class UsuarioFuncionario {

    DAOFactory df;

    public UsuarioFuncionario(DAOFactory df){
        this.df = df;
    }

    public Funcionario FazerLogin(String usuario, String senha){

        String senhaHash = Utils.HashUtil.gerarMD5(senha);
        Model.Usuario.UsuarioFuncionario uf = df.getUsuarioFuncionarioDAO().buscarPorUsuario(usuario);

        if(uf.getUsuario().equals(usuario) && uf.getSenha().equals(senhaHash))
            return uf.getFuncionario();
        else
            return null;
    }

    public String CadastrarUsuario (Model.Usuario.UsuarioFuncionario usuarioFuncionario){
        // Podemos usar alguma lógica para validar o usuario antes de cadastrar, tipo validar CNPJ, ver se a idade é maior de 18, etc... !IMPORTANTE
        // Fazer os IFs, se não passar devolve uma mensagem de erro na String de retorno com o campo que não foi validado

        df.getFuncionarioDAO().inserir(usuarioFuncionario.getFuncionario());
        Funcionario funcionarioAdicionado = df.getFuncionarioDAO().buscarPorCpf(usuarioFuncionario.getFuncionario().getCpf());
        usuarioFuncionario.setFuncionario(funcionarioAdicionado);
        df.getUsuarioFuncionarioDAO().inserir(usuarioFuncionario);
        return "Sucesso";
    }
}
