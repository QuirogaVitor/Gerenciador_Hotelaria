package Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import Business.BusinessFactory;
import Data.DAOFactory;
import Data.QuartoDAO;
import Data.ReservaDAO;
import Model.Cliente;
import Model.Quarto;
import Model.Reserva;
import Utils.MensagemUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

    private Cliente clienteSelecionado;

    BusinessFactory bf;

    @FXML
    public void initialize() {
        bf = new BusinessFactory();
        botaoVerifcarCliente.setDefaultButton(true);
        botaoCriarReserva.setDefaultButton(false);

        campoDataReservar.textProperty().addListener((obs, oldValue, newValue) -> {
            if (clienteSelecionado != null && newValue != null && !newValue.isEmpty()) {
                try {
                    LocalDate data = LocalDate.parse(newValue);
                    preencherQuartosDisponiveis(data);
                } catch (Exception e) {
                    // Ignora se a data estiver inválida enquanto o usuário digita
                }
            }
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
            botaoVerifcarCliente.setDefaultButton(false);
            botaoCriarReserva.setDefaultButton(true);
        } else {
            abrirTelaCadastro(event);
        }

        }catch(Exception ex)
        {
            MensagemUtil.exibirErro("CPF inválido");
            ex.printStackTrace();
        }
    }



    private void abrirTelaCadastro(ActionEvent event) {
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/View/CadastroUsuario.fxml"));
            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void preencherQuartosDisponiveis(LocalDate data) {

        comboQuartoDisponivel.getItems().clear();
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
        MensagemUtil.exibirAviso("Cliente não selecionado");
        return;
    }

    if (dataReservadaStr == null || numeroQuartoStr == null) {
        MensagemUtil.exibirAviso("Preencha todos os campos");
        return;
    }

    try {
        LocalDate dataReservada = LocalDate.parse(dataReservadaStr);
        int numeroQuarto = Integer.parseInt(numeroQuartoStr.trim());

        DAOFactory daoFactory = new DAOFactory();
        QuartoDAO quartoDAO = daoFactory.getQuartoDAO();
        ReservaDAO reservaDAO = daoFactory.getReservaDAO();

        Quarto quarto = quartoDAO.buscarPorNumero(numeroQuarto);
        if (quarto == null || quarto.getStatus() != Quarto.Status.VAZIO) {
            MensagemUtil.exibirErro("Quarto inválido ou indisponível");
            return;
        }

        
        int codigoGerado;
        do {
            codigoGerado = new Random().nextInt(999999);
        } while (bf.Reserva().buscar(codigoGerado) != null);

        Reserva reserva = new Reserva();
        reserva.setCodigoReserva(codigoGerado);
        reserva.setCliente(clienteSelecionado);
        reserva.setQuarto(quarto);
        reserva.setDataReserva(dataReservada);
        reserva.setDataCheckin(null);
        reserva.setDataCheckout(null);

        reservaDAO.inserir(reserva);

        quarto.setStatus(Quarto.Status.RESERVADO);
        quartoDAO.atualizar(quarto);

        MensagemUtil.exibirSucesso("Sucesso! Reserva realizada");

        campoDataReservar.clear();
        comboQuartoDisponivel.setValue(null);
        vBoxSubTela.setVisible(false);
        campoCpf.clear();
        campoCpfBuscado.clear();

    } catch (Exception e) {
        e.printStackTrace();
        MensagemUtil.exibirErro("Erro ao fazer a reserva");
    }
    }

    @FXML
    void voltarTelaFuncionario(ActionEvent event) {
        vBoxSubTela.setVisible(false);
        campoCpf.clear();
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/View/TelaFuncionario.fxml"));
            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
