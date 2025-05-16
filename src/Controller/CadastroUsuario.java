package Controller;

import Business.BusinessFactory;
import Model.Cliente;
import Model.Enums.Cargo;
import Model.Funcionarios.Funcionario;
import Model.Usuario.UsuarioCliente;
import Model.Usuario.UsuarioFuncionario;
import Utils.HashUtil;
import Utils.MensagemUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

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
    private DatePicker dataPickerDataNasc;

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
        LocalDate dataDigitada = dataPickerDataNasc.getValue();

        if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || senha.isEmpty() ||
                tipo == null || nomeUsuario.isEmpty() || email.isEmpty() || dataDigitada == null) {
            MensagemUtil.exibirErro("Por favor, preencha todos os campos obrigatórios.");
            return;
        }

        if (tipo.equals("Funcionario") && cargoSelecionado == null) {
            MensagemUtil.exibirErro("Por favor, selecione o cargo do funcionário.");
            return;
        }

        String senhaHash = HashUtil.gerarMD5(senha);
        String msgCadastro = "";

        switch (tipo) {
            case "Funcionario":
                UsuarioFuncionario uf = new UsuarioFuncionario();
                Funcionario f = new Funcionario(cargoSelecionado, nome, cpf, email, telefone, dataDigitada);
                uf.setFuncionario(f);
                uf.setUsuario(nomeUsuario);
                uf.setSenha(senhaHash);
                msgCadastro = businessFactory.UsuarioFuncionario().CadastrarUsuario(uf);
                break;
            case "Cliente":
                UsuarioCliente uc = new UsuarioCliente();
                Cliente c = new Cliente(nome, cpf, email, telefone, dataDigitada);
                uc.setCliente(c);
                uc.setUsuario(nomeUsuario);
                uc.setSenha(senhaHash);
                msgCadastro = businessFactory.UsuarioCliente().CadastrarUsuario(uc);
                break;
        }

        if (!msgCadastro.equals("Sucesso")) {
            MensagemUtil.exibirErro(msgCadastro);
        } else {
            MensagemUtil.exibirSucesso("Usuário cadastrado com sucesso!");
            limparCadastro(null); // limpa os campos após sucesso
        }
    }

    @FXML
    void limparCadastro(ActionEvent event) {
        campoNome.clear();
        campoSenha.clear();
        campoCpf.clear();
        campoTelefone.clear();
        dataPickerDataNasc.setValue(null);
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
