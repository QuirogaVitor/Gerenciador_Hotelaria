package Controller;

import java.io.IOException;

import javax.management.RuntimeErrorException;

import Business.BusinessFactory;
import Utils.MensagemUtil;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TelaFuncionario {

    @FXML
    private Button botaoCadastrarFuncionario;

    @FXML
    private Button botaoCheckIn;

    @FXML
    private Button botaoCheckOut;

    @FXML
    private Button botaoCriarQuarto;

    @FXML
    private Button botaoServicoQuarto;

    @FXML
    private Button botaoVizualizarReservas;

    @FXML
    private Button botaoFazerReservas;

    BusinessFactory bf;

    @FXML
    public void initialize() {
        bf = new BusinessFactory();
    }

    @FXML
    void cadastrarUsuario(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/CadastroUsuario.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Cadastro de Usuário");
            stage.setScene(new Scene(root));
            stage.show();

            Stage telaAtual = (Stage) botaoCadastrarFuncionario.getScene().getWindow();
            telaAtual.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void fazerCheckIn(ActionEvent event) {
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/View/TelaFazerCheckIn.fxml"));
            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void fazerCheckOut(ActionEvent event) {
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/View/TelaFazerCheckOut.fxml"));
            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void fazerReservas(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/TelaFazerReservas.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Cadastro de Usuário");
            stage.setScene(new Scene(root));
            stage.show();

            Stage telaAtual = (Stage) botaoCadastrarFuncionario.getScene().getWindow();
            telaAtual.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void vizualizarReservas(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/TelaReservas.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Cadastro de Usuário");
            stage.setScene(new Scene(root));
            stage.show();

            Stage telaAtual = (Stage) botaoCadastrarFuncionario.getScene().getWindow();
            telaAtual.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void criarQuarto(ActionEvent event) {
        bf.Quarto().inserirNovoQuartoPadrao();
        MensagemUtil.exibirSucesso("Quarto Criado");
    }
}
