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
    private TextField campoCodigoReserva;

    BusinessFactory bf;

    @FXML
    public void initialize() {
        bf = new BusinessFactory();
    }

    @FXML
    void buscarReserva(ActionEvent event) {
        String textoCodigo = campoCodigoReserva.getText();

        if (textoCodigo.isEmpty()) {
            MensagemUtil.exibirErro("Por favor, preencha o campo de código da reserva.");
            return;
        }

        int codigo;
        codigo = Integer.parseInt(textoCodigo);

        bf.Reserva().buscar(codigo);
    }

    @FXML
    void voltarTelaFuncionario(ActionEvent event) {
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader
                    .load(getClass().getResource("/View/TelaFuncionario.fxml"));
            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene()
                    .getWindow();
            stage.setScene(new javafx.scene.Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
