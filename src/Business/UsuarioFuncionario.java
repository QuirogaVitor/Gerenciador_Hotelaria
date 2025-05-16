package Business;

import Data.DAOFactory;
import Model.Enums.Cargo;
import Model.Funcionarios.Funcionario;
import Utils.Sessao;
import Utils.ValidadorCPF;

public class UsuarioFuncionario {

    DAOFactory df;

    public UsuarioFuncionario(DAOFactory df){
        this.df = df;
    }

    public Funcionario FazerLogin(String usuario, String senha){

        String senhaHash = Utils.HashUtil.gerarMD5(senha);
        Model.Usuario.UsuarioFuncionario uf = df.getUsuarioFuncionarioDAO().buscarPorUsuario(usuario);
        
        if(uf == null)
            return null;

        if(uf.getUsuario().equals(usuario) && uf.getSenha().equals(senhaHash)){
            Sessao.setFuncionarioLogado(uf.getFuncionario());
            return Sessao.getFuncionarioLogado();
        }
        else
            return null;
    }

    public String CadastrarUsuario (Model.Usuario.UsuarioFuncionario usuarioFuncionario){
        // Podemos usar alguma lógica para validar o usuario antes de cadastrar, tipo validar CNPJ, ver se a idade é maior de 18, etc... !IMPORTANTE
        // Fazer os IFs, se não passar devolve uma mensagem de erro na String de retorno com o campo que não foi validado

        Funcionario funcionarioLogado = Sessao.getFuncionarioLogado();
        if (funcionarioLogado.getCargo() != (Cargo.GERENTE) ){
            return "Você não tem autorização para cadastrar funcionários";
        }
        String cpf = usuarioFuncionario.getFuncionario().getCpf();
        if (!ValidadorCPF.validarCPF(cpf))
        {
            return "CPF Inválido";
        }

        if (df.getFuncionarioDAO().buscarPorCpf(cpf) != null)
        {
            return "Usuário já cadastrado";
        }

        df.getFuncionarioDAO().inserir(usuarioFuncionario.getFuncionario());
        Funcionario funcionarioAdicionado = df.getFuncionarioDAO().buscarPorCpf(usuarioFuncionario.getFuncionario().getCpf());
        usuarioFuncionario.setFuncionario(funcionarioAdicionado);

        df.getUsuarioFuncionarioDAO().inserir(usuarioFuncionario);
        return "Sucesso";
    }
}
