package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Business.BusinessFactory;
import Data.DAOFactory;
import Model.Funcionarios.Funcionario;
import Model.Usuario.UsuarioFuncionario;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaLogin extends Application {

    @FXML
    private Button BotaoLimparLogin;

    @FXML
    private Button botaoEntrarLogin;

    @FXML
    private Button botaoIrTelaCadastro;

    @FXML
    private TextField campoSenha;

    @FXML
    private TextField campoUsuario;

    private BusinessFactory bf;

    
    public static void main(String[] args) {
        launch(args); 
    }

    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/TelaLogin.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Tela de Login");
        primaryStage.setScene(scene);
        primaryStage.show();
        bf = new BusinessFactory();
    }

    private void abrirTela(String fxml) {
       //Preencher com código para abrir tela com funcionalidade do admin ou usuário
    }
    
    @FXML
    void FazerLogin(ActionEvent event) throws SQLException {
        Funcionario funcionarioLogado = bf.UsuarioFuncionario().FazerLogin(campoUsuario.getText(), campoSenha.getText());

        if (funcionarioLogado == null)
        {
            // Exibir erro na tela de usuario ou senha invalido
            return;
        }

        // Abrir a próxima tela pois o usuario é e senha são validos
    }

    @FXML
    void LimparLogin(ActionEvent event) {
        campoSenha.clear();
        campoUsuario.clear();
    }

    @FXML
    void chamarTelaCadastro(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/CadastroUsuario.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Cadastro de Usuário");
            stage.setScene(new Scene(root));
            stage.show();

            
            Stage telaAtual = (Stage) botaoIrTelaCadastro.getScene().getWindow();
            telaAtual.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

