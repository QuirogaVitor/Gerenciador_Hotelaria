package Utils;

import Model.Funcionarios.Funcionario;

public class Sessao {
    private static Funcionario funcionarioLogado;

    public static void setFuncionarioLogado(Funcionario funcionario) {
        Sessao.funcionarioLogado = funcionario;
    }

    public static Funcionario getFuncionarioLogado() {
        return funcionarioLogado;
    }

    public static void limparSessao() {
        Sessao.funcionarioLogado = null;
    }
}