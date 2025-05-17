package Controller;

import java.time.LocalDate;
import java.util.List;

import Business.BusinessFactory;
import Model.Quarto;
import Model.Reserva;
import Utils.MensagemUtil;
import java.awt.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaReservas {

    @FXML
    private Button botaoPesquisar;

    @FXML
    private Button botaoVoltar;

    @FXML
    private TextField campoCodigo;

    @FXML
    private TextField campoCpf;

    @FXML
    private TextField campoQuarto;

    @FXML
    private TableColumn<Reserva, Integer> colunaCodigo;

    @FXML
    private TableColumn<Reserva, LocalDate> colunaDataEntrada;

    @FXML
    private TableColumn<Reserva, LocalDate> colunaDataSaida;

    @FXML
    private TableColumn<Reserva, Integer> colunaQuarto;

    @FXML
    private TableColumn<Reserva, String> colunaStatus;

    @FXML
    private TableView<Reserva> tabelaReservas;

    private ObservableList<Reserva> listaReservas = FXCollections.observableArrayList();

    private BusinessFactory bf;

    @FXML
    public void initialize() {
        bf = new BusinessFactory();
        tabelaReservas.setItems(listaReservas);

        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaQuarto.setCellValueFactory(new PropertyValueFactory<>("numeroQuarto"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colunaDataEntrada.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
        colunaDataSaida.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
    }

    @FXML
    void pesquisarReserva(ActionEvent event) {
        String textoCodigo = campoCodigo.getText().trim();
        String cpf = campoCpf.getText().trim();
        String textoQuarto = campoQuarto.getText().trim();

        int codigo = 0;
        int numeroQuarto = 0;

        
        if (!textoCodigo.isEmpty()) {
            try {
                codigo = Integer.parseInt(textoCodigo);
            } catch (NumberFormatException e) {
                MensagemUtil.exibirErro("O código da reserva deve ser um número inteiro.");
                return;
            }
        }

        
        if (!textoQuarto.isEmpty()) {
            try {
                numeroQuarto = Integer.parseInt(textoQuarto);
            } catch (NumberFormatException e) {
                MensagemUtil.exibirErro("O número do quarto deve ser um número inteiro.");
                return;
            }
        }

        
        List<Reserva> resultado = bf.Reserva().buscarComFiltros(codigo, cpf, numeroQuarto);
        listaReservas.setAll(resultado);
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