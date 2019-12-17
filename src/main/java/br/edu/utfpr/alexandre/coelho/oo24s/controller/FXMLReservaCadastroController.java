package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.dao.ClienteDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.dao.HospedeDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.dao.QuartoDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.dao.ReservaDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Cliente;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Hospede;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Quarto;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Reserva;
import br.edu.utfpr.alexandre.coelho.oo24s.util.AlertHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLReservaCadastroController implements Initializable {

    @FXML
    private TextField textId;
    @FXML
    private ChoiceBox cbCliente;
    @FXML
    private ChoiceBox cbQuarto;
    @FXML
    private TextField textValorDiaria;
    @FXML
    private ListView listHospedes;
    @FXML
    private ListView listSelHospedes;
    @FXML
    private DatePicker dateReserva;
    @FXML
    private DatePicker dateEntrada;
    @FXML
    private DatePicker dateSaida;
    @FXML
    private TextField textMotivo;
    @FXML
    private Button buttonAddToList;

    private ReservaDAO reservaDAO;
    private Stage stage;
    private Reserva reserva;
    private ClienteDAO clienteDAO;
    private HospedeDAO hospedeDAO;
    private QuartoDAO quartoDAO;
    private List<Hospede> listaSelecionados;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.reserva = new Reserva();
        this.reservaDAO = new ReservaDAO();
        this.clienteDAO = new ClienteDAO();
        this.hospedeDAO = new HospedeDAO();
        this.quartoDAO = new QuartoDAO();
        this.listaSelecionados = new ArrayList<>();

        ObservableList<Cliente> clientes = FXCollections.observableArrayList(clienteDAO.getSemReserva());
        ObservableList<Quarto> quartos = FXCollections.observableArrayList(quartoDAO.getSemReserva());
        ObservableList<Hospede> hospedes = FXCollections.observableArrayList(hospedeDAO.getHSemReserva());

        this.cbCliente.setItems(clientes);
        this.cbQuarto.setItems(quartos);
        this.listHospedes.setItems(hospedes);
        this.cbQuarto.onActionProperty();
    }

    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void cancel() {
        this.stage.close();
    }

    @FXML
    private void save() {
        if ((cbCliente.getValue() != null) && (cbQuarto.getValue() != null) && (dateReserva.getValue() != null)
                && (dateEntrada.getValue() != null) && (dateSaida.getValue() != null)) {
            if (dateSaida.getValue().compareTo(dateEntrada.getValue()) > 0) {
                reserva.setAberta(Boolean.TRUE);
                reserva.setCliente((Cliente) cbCliente.getValue());
                reserva.setQuarto((Quarto) cbQuarto.getValue());
                reserva.setValorDiaria(reserva.getQuarto().getValorDiaria());
                reserva.setHospedes(listaSelecionados);
                reserva.setDataEntrada(dateEntrada.getValue());
                reserva.setDataReserva(dateReserva.getValue());
                reserva.setDataSaida(dateSaida.getValue());
                reserva.setMotivo(textMotivo.getText());
                reserva.setUsuario(FXMLPrincipalController.getUsuarioAutenticado());

                this.reservaDAO.save(reserva);
                this.stage.close();
            } else {
                AlertHandler.dateException();
            }
        } else {
            AlertHandler.validationFormException();
        }
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
        if (reserva.getId() != null) {
            this.textId.setText(reserva.getId().toString());
            this.dateEntrada.setValue(reserva.getDataEntrada());
            this.dateReserva.setValue(reserva.getDataReserva());
            this.dateSaida.setValue(reserva.getDataSaida());
            this.textMotivo.setText(reserva.getMotivo());
            this.cbCliente.setValue(reserva.getCliente());
            this.cbQuarto.setValue(reserva.getQuarto());
            this.textValorDiaria.setText(reserva.getQuarto().getValorDiaria().toString());
            this.listSelHospedes.setItems(FXCollections.observableArrayList(reserva.getHospedes()));
        }
    }

    @FXML
    private void addToList() {
        if (listHospedes.getSelectionModel().getSelectedIndex() > -1) {
            Hospede hospede = (Hospede) this.listHospedes.getSelectionModel().getSelectedItem();
            if (!listaSelecionados.contains(hospede)) {
                listaSelecionados.add(hospede);
            }
            ObservableList<Hospede> hospedesSelected = FXCollections.observableArrayList(listaSelecionados);
            this.listSelHospedes.setItems(hospedesSelected);
        }
    }

    @FXML
    private void removeFromList() {
        listaSelecionados.remove((Hospede) this.listSelHospedes.getSelectionModel().getSelectedItem());
        ObservableList<Hospede> hospedesSelected = FXCollections.observableArrayList(listaSelecionados);
        this.listSelHospedes.setItems(hospedesSelected);
    }

    @FXML
    private void changeValue() {
        Quarto quarto = (Quarto) cbQuarto.getValue();
        this.textValorDiaria.setText(quarto.getValorDiaria().toString());
    }

    @FXML
    private void newClienteRecord(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/FXMLClienteCadastro.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de Cliente");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) buttonAddToList).getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            FXMLClienteCadastroController controller = loader.getController();

            controller.setCliente(new Cliente());
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();
        } catch (IOException e) {
            AlertHandler.openFormException(e);
        }
    }

    @FXML
    private void newHospedeRecord(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/FXMLHospedeCadastro.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de Hóspede");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) buttonAddToList).getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            FXMLHospedeCadastroController controller = loader.getController();

            controller.setHospede(new Hospede());
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();
        } catch (IOException e) {
            AlertHandler.openFormException(e);
        }
    }
}
