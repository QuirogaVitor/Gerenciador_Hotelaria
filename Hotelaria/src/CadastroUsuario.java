import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
    private AnchorPane paneCadastro;

    @FXML
    void cadastrarUsuario(ActionEvent event) {
        String nome = campoNome.getText();
        String cpf = campoCPF.getText();
        String dataNasc = campoDataNasc.getText();
        String telefone = campoTelefone.getText();
        String senha = campoSenha.getText();

        UsuarioDAO dao = new UsuarioDAO();
        dao.cadastrarUsuario(nome, senha, cpf, telefone, dataNasc);
    }

    @FXML
    void limparCadastro(ActionEvent event) {
        campoNome.clear();
        campoSenha.clear();
        campoCPF.clear();
        campoTelefone.clear();
        campoDataNasc.clear();
    }

}

