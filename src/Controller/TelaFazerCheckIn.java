package Controller;

import Business.BusinessFactory;
import Utils.MensagemUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TelaFazerCheckIn {

    @FXML
    private Button botaoValidarReserva;

    @FXML
    private Button botaoVoltar;

    @FXML
    private TextField campoCodigoReserva;

    @FXML
    private TextField campoCpf;

    BusinessFactory bf;

    @FXML
    public void initialize() {
        bf = new BusinessFactory();
    }

    @FXML
    void validarReserva(ActionEvent event) {
        String textoCodigo = campoCodigoReserva.getText();
        String cpf = campoCpf.getText();

        if (textoCodigo.isEmpty() || cpf.isEmpty()) {
            MensagemUtil.exibirErro("Por favor, preencha o campo com o código da reserva.");
            return;
        }

        int codigo;
        
        codigo = Integer.parseInt(textoCodigo);
        if(bf.Reserva().validarReserva(codigo, cpf)){
        bf.Reserva().fazerCheckIn(codigo);
        MensagemUtil.exibirSucesso("Check-in realizado com sucesso!");
       
    }else{
        MensagemUtil.exibirErro("Reserva não encontrada ou CPF inválido");
    }
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
