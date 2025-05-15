package Controller;
import java.io.IOException;
import java.sql.SQLException;
import Business.BusinessFactory;
import Model.Funcionarios.Funcionario;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaLogin extends Application {


    @FXML
    private Button botaoEntrarLogin;

    @FXML
    private PasswordField campoSenha;

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
    }
    
    @FXML
    void FazerLogin(ActionEvent event) throws SQLException {
        this.bf = new BusinessFactory();
        Funcionario funcionarioLogado = bf.UsuarioFuncionario().FazerLogin(campoUsuario.getText(), campoSenha.getText());

        if (funcionarioLogado == null)
        {
            System.out.println("Login não efetuado, usuario ou senha invalidos");
            return;
        }
        try {
            System.out.println("Login efetuado com sucesso!");
            Parent root = FXMLLoader.load(getClass().getResource("/View/TelaFuncionario.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Funcionalidade Funcionários");
            stage.setScene(new Scene(root));
            stage.show();

            Stage telaAtual = (Stage) botaoEntrarLogin.getScene().getWindow();
            telaAtual.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void LimparLogin(ActionEvent event) {
        campoSenha.clear();
        campoUsuario.clear();
    }

}

