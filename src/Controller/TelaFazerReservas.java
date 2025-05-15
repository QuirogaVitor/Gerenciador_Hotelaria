package Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Business.BusinessFactory;
import Data.DAOFactory;
import Data.QuartoDAO;
import Data.ReservaDAO;
import Model.Cliente;
import Model.Quarto;
import Model.Reserva;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class TelaFazerReservas {

    @FXML
    private Button botaoCriarReserva;

    @FXML
    private Button botaoVerifcarCliente;

    @FXML
    private Button botaoVoltar;

    @FXML
    private TextField campoCpf;

    @FXML
    private TextField campoCpfBuscado;

    @FXML
    private TextField campoDataReservar;

    @FXML
    private ComboBox<String> comboQuartoDisponivel;

    @FXML
    private VBox vBoxSubTela;

    @FXML
    DatePicker datePicker;

    private Cliente clienteSelecionado;

    BusinessFactory bf;

    @FXML
    public void initialize() {
        bf = new BusinessFactory();
        datePicker.setOnAction(event -> {
            LocalDate selectedDate = datePicker.getValue();
            preencherQuartosDisponiveis(selectedDate);
        });
    }

    @FXML
    void verificarCliente(ActionEvent event) {
        String cpf = campoCpf.getText();

        try{
            Cliente cliente = bf.Cliente().buscarPorCpf(cpf);

        if (cliente != null) {
            clienteSelecionado = cliente;
            campoCpfBuscado.setText(cliente.getCpf());

            vBoxSubTela.setVisible(true);
            preencherQuartosDisponiveis(LocalDate.MIN);
        } else {
            abrirTelaCadastro(event);
        }

        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }



    private void abrirTelaCadastro(ActionEvent event) {
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader
                    .load(getClass().getResource("/View/CadastroUsuario.fxml"));
            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene()
                    .getWindow();
            stage.setScene(new javafx.scene.Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void preencherQuartosDisponiveis(LocalDate data) {

        List<Quarto> quartos = bf.Quarto().BuscarQuartosDisponiveisPorData(data);

        for (Quarto quarto : quartos) {
                comboQuartoDisponivel.getItems().add(String.valueOf(quarto.getNumero()));
            }
    }

   

    @FXML
    void fazerReserva(ActionEvent event) {
        String dataReservadaStr = campoDataReservar.getText();
        String numeroQuartoStr = comboQuartoDisponivel.getValue();

        if (clienteSelecionado == null) {
            System.out.println("Cliente não selecionado");
            return;
        }

        if (dataReservadaStr == null || numeroQuartoStr == null) {
            System.out.println("Preencha todos os campos");
            return;
        }

        try {
            LocalDate dataReservada = LocalDate.parse(dataReservadaStr);
            int numeroQuarto = Integer.parseInt(numeroQuartoStr);

            DAOFactory daoFactory = new DAOFactory();
            QuartoDAO quartoDAO = daoFactory.getQuartoDAO();
            ReservaDAO reservaDAO = daoFactory.getReservaDAO();

            Quarto quarto = quartoDAO.buscarPorNumero(numeroQuarto);
            if (quarto == null || quarto.getStatus() != Quarto.Status.VAZIO) {
                System.out.println("Quarto inválido ou indisponivel");
                return;
            }

            Reserva reserva = new Reserva();
            reserva.setCodigoReserva(new Random().nextInt(999999));
            reserva.setCliente(clienteSelecionado);
            reserva.setQuarto(quarto);
            reserva.setDataReserva(dataReservada);
            reserva.setDataCheckin(null);
            reserva.setDataCheckout(null);

            reservaDAO.inserir(reserva);

            quarto.setStatus(Quarto.Status.RESERVADO);
            quartoDAO.atualizar(quarto);

            System.out.println("Reserva realizada com sucesso!");

            campoDataReservar.clear();
            comboQuartoDisponivel.setValue(null);
            vBoxSubTela.setVisible(false);
            campoCpf.clear();
            campoCpfBuscado.clear();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao fazer a reserva.");
        }
    }

    @FXML
    void voltarTelaFuncionario(ActionEvent event) {
        vBoxSubTela.setVisible(false);
        campoCpf.clear();
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
