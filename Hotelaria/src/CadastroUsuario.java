import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CadastroUsuario {

    @FXML
    private Button boataoLimparCad;

    @FXML
    private Button botaoCadastrar;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoCPF;

    @FXML
    private TextField campoDataNasc;

    @FXML
    private TextField campoTelefone;
    
    @FXML
    private TextField campoSenha;

    @FXML
    private ComboBox<String> comboTipoUsuario;

    @FXML
    private TextField campoNomeUsuario;

    @FXML
    public void initialize() {
        comboTipoUsuario.getItems().addAll("cliente", "admin");
    }

    @FXML
    void cadastrarUsuario(ActionEvent event) {
        String nome = campoNome.getText();
        String cpf = campoCPF.getText();
        String dataNasc = campoDataNasc.getText();
        String telefone = campoTelefone.getText();
        String senha = campoSenha.getText();
        String tipo = comboTipoUsuario.getValue();
        String nomeUsuario = campoNomeUsuario.getText();
        UsuarioDAO dao = new UsuarioDAO();
        dao.cadastrarUsuario(nome, senha, cpf, telefone, dataNasc, tipo, nomeUsuario);
    }

    @FXML
    void limparCadastro(ActionEvent event) {
        campoNome.clear();
        campoSenha.clear();
        campoCPF.clear();
        campoTelefone.clear();
        campoDataNasc.clear();
        comboTipoUsuario.setValue(null);
        campoNomeUsuario.clear();
    }
}
