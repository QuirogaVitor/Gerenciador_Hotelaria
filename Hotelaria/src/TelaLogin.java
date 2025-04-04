import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    // Método principal
    public static void main(String[] args) {
        launch(args); // chama o método start
    }

    // Abre a tela de login
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TelaLogin.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Tela de Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void FazerLogin(ActionEvent event) {
        // Aqui você pode validar o login
    }

    @FXML
    void LimparLogin(ActionEvent event) {
        campoSenha.clear();
        campoUsuario.clear();
    }

    @FXML
    void chamarTelaCadastro(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CadastroUsuario.fxml"));
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

