package Controller;

import java.io.IOException;

import DAO.UsuarioDAO;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaLoginFuncionario extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/TelaLoginFuncionario.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Tela de Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private Button botaoLoginFuncionario;

    @FXML
    private TextField campoIdentificador;

    @FXML
    private PasswordField campoSenha;

    @FXML
    void logarFuncionario(ActionEvent event) {
        String nomeUsuario = campoIdentificador.getText();
        String senha = campoSenha.getText();

        UsuarioDAO dao = new UsuarioDAO();
        String tipoUsuario = dao.validarLogin(nomeUsuario, senha);

        if (tipoUsuario != null) {
            System.out.println("Login bem-sucedido! Tipo " + tipoUsuario);

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/View/TelaFuncionario.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Funcionalidade Funcionários");
                stage.setScene(new Scene(root));
                stage.show();

                Stage telaAtual = (Stage) botaoLoginFuncionario.getScene().getWindow();
                telaAtual.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Login");
            alert.setHeaderText(null);
            alert.setContentText("Usuario ou senha inválidos!");
            alert.showAndWait();
        }
    }

}
