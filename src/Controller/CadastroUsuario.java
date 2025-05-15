package Controller;

import Business.BusinessFactory;
import Model.Cliente;
import Model.Enums.Cargo;
import Model.Funcionarios.Funcionario;
import Model.Usuario.UsuarioCliente;
import Model.Usuario.UsuarioFuncionario;
import Utils.HashUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.scene.layout.AnchorPane;

public class CadastroUsuario {

    @FXML
    private Button boataoLimparCadastro;

    @FXML
    private Button botaoCadastrar;

    @FXML
    private Button botaoVoltar;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoCpf;

    @FXML
    private TextField campoDataNasc;

    @FXML
    private TextField campoEmail;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private TextField campoTelefone;

    @FXML
    private TextField campoNomeUsuario;

    @FXML
    private ComboBox<Cargo> comboCargo;

    @FXML
    private ComboBox<String> comboTipoUsuario;

    @FXML
    private AnchorPane paneCadastro;

    BusinessFactory businessFactory;

    @FXML
    public void initialize() throws SQLException {
        comboTipoUsuario.getItems().addAll("Funcionario", "Cliente");

        businessFactory = new BusinessFactory();

        comboTipoUsuario.setOnAction(event -> {
            String tipoSelecionado = comboTipoUsuario.getValue();

            if ("Funcionario".equals(tipoSelecionado)) {
                comboCargo.getItems().clear();
                comboCargo.getItems().addAll(Cargo.values());

                comboCargo.setDisable(false);
                comboCargo.setVisible(true);
            } else {
                comboCargo.getItems().clear();
                comboCargo.setDisable(true);
                comboCargo.setVisible(false);
            }
        });

        comboCargo.setDisable(true);
        comboCargo.setVisible(false);
    }

    @FXML
    void cadastrarUsuario(ActionEvent event) {
        String nome = campoNome.getText();
        String cpf = campoCpf.getText();
        String telefone = campoTelefone.getText();
        String senha = campoSenha.getText();
        String tipo = comboTipoUsuario.getValue();
        String nomeUsuario = campoNomeUsuario.getText();
        String email = campoEmail.getText();
        Cargo cargoSelecionado = comboCargo.getValue();
        String msgCadastro = "";
        String senhaHash = HashUtil.gerarMD5(senha);
        String dataDigitada = campoDataNasc.getText(); 
                
        try {
            if (dataDigitada.length() == 8) {
                String dataFormatada = dataDigitada.substring(0, 4) + "-" +
                        dataDigitada.substring(4, 6) + "-" +
                        dataDigitada.substring(6, 8);
                dataNasc = Date.valueOf(dataFormatada);
            } else {
                JOptionPane.showMessageDialog(null, "Formato de data inválido. Use: yyyyMMdd", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, "Data inválida. Verifique e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        switch (tipo) {
            case "Funcionario":
                UsuarioFuncionario uf = new UsuarioFuncionario();
                Funcionario f = new Funcionario(cargoSelecionado, nome, cpf, email, telefone, dataNasc);
                uf.setFuncionario(f);
                uf.setUsuario(nomeUsuario);
                uf.setSenha(senhaHash);
                msgCadastro = businessFactory.UsuarioFuncionario().CadastrarUsuario(uf);
                break;
            case "Cliente":
                UsuarioCliente uc = new UsuarioCliente();
                Cliente c = new Cliente(nome, cpf, email, telefone, dataNasc);
                uc.setCliente(c);
                uc.setUsuario(nomeUsuario);
                uc.setSenha(senhaHash);
                msgCadastro = businessFactory.UsuarioCliente().CadastrarUsuario(uc);
                break;

        }
        if (!msgCadastro.equals("Sucesso")) {
            
            // Exibe a mensagem de erro retornada de
            // Business.UsuarioFuncionario.CadastrarUsuario() que esta em msgCadastro na
            // tela da view
            JOptionPane.showMessageDialog(null, msgCadastro, "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            // Exibe mensagem de cadastrado com sucesso e volta a tela
    }

    @FXML
    void limparCadastro(ActionEvent event) {
        campoNome.clear();
        campoSenha.clear();
        campoCpf.clear();
        campoTelefone.clear();
        campoDataNasc.clear();
        comboTipoUsuario.setValue(null);
        campoNomeUsuario.clear();
    }

    @FXML
    void voltarTelaFuncionario(ActionEvent event) {
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/View/TelaFuncionario.fxml"));
            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
