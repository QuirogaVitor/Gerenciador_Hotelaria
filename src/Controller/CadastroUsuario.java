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
import javafx.scene.control.TextField;

import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JOptionPane;

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

    BusinessFactory businessFactory;

    @FXML
    public void initialize() throws SQLException {
        comboTipoUsuario.getItems().addAll("cliente", "admin");
        businessFactory = new BusinessFactory();
    }

    @FXML
    void cadastrarUsuario(ActionEvent event) {
        String nome = campoNome.getText();
        String cpf = campoCPF.getText();
        Date dataNasc = Date.valueOf(campoDataNasc.getText());
        String telefone = campoTelefone.getText();
        String senha = campoSenha.getText();
        String tipo = comboTipoUsuario.getValue();
        String nomeUsuario = campoNomeUsuario.getText();
        String email = ""; // TROCAR PARA PEGAR DO CAMPO EMAIL
        Cargo cargo = Cargo.RECEPCIONISTA; // TROCAR PARA RECEBER O CARGO DE UM COMBOBOX OU ALGO ASSIM
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
                Funcionario f = new Funcionario(cargo,nome, cpf, email, telefone, dataNasc);
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
        if (!msgCadastro.equals("Sucesso")){
            // Exibe a mensagem de erro retornada de Business.UsuarioFuncionario.CadastrarUsuario() que esta em msgCadastro na tela da view
            return;
        }
        // Exibe mensagem de cadastrado com sucesso e volta a tela

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

    @FXML
    void voltarTelaLogin(ActionEvent event) {
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/View/TelaLogin.fxml"));
            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
