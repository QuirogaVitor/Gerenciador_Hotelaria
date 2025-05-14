package Model.Usuario;

import Model.Funcionarios.Funcionario;

public class UsuarioFuncionario extends Usuario {
    private Funcionario funcionario;

    // Construtores
    public UsuarioFuncionario() {}

    public UsuarioFuncionario(int id, String usuario, String senha, Funcionario funcionario) {
        super(id, usuario, senha);  // Chama o construtor da classe pai (Usuario)
        this.funcionario = funcionario;
    }

    // Getters e Setters
    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
