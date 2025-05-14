package Model.Usuario;
import Model.Cliente;

public class UsuarioCliente extends Usuario {
    private Cliente cliente;

    // Construtores
    public UsuarioCliente() {}

    public UsuarioCliente(int id, String usuario, String senha, Cliente cliente) {
        super(id, usuario, senha);  // Chama o construtor da classe pai (Usuario)
        this.cliente = cliente;
    }

    // Getters e Setters
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
