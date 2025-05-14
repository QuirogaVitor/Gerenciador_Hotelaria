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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaLogin {

    @FXML
    private Button botaoEntrarLogin;

    @FXML
    private TextField campoSenha;

    @FXML
    private TextField campoUsuario;

    private BusinessFactory bf;
    
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
    void telaLoginFuncionario(ActionEvent event) {

    }
}

