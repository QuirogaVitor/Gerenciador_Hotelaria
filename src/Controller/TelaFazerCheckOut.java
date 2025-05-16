package Controller;

import Business.BusinessFactory;
import Utils.MensagemUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TelaFazerCheckOut {

    @FXML
    private Button botaoBuscarReserva;

    @FXML
    private Button botaoVoltar;

    @FXML
    private TextField campoNumeroQuarto;

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
    void buscarReserva(ActionEvent event) {
        String numeroQuarto = campoNumeroQuarto.getText();
        String codigo = campoCodigoReserva.getText();
        String cpf = campoCpf.getText();

        if(codigo.isEmpty() || numeroQuarto.isEmpty() || cpf.isEmpty()){
            MensagemUtil.exibirErro("Por favor, preencha todos os campos");
            return;
        }
        
        bf.Reserva().buscar(codigo, cpf, numeroQuarto);
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
