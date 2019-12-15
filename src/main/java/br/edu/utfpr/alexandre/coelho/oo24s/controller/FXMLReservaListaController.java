package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.dao.ReservaDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Reserva;
import br.edu.utfpr.alexandre.coelho.oo24s.util.AlertHandler;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLReservaListaController implements Initializable {

    @FXML
    private TableView<Reserva> tableData;
    @FXML
    private TableColumn<Reserva, Long> columnId;
    @FXML
    private TableColumn<Reserva, String> columnCliente;
    @FXML
    private TableColumn<Reserva, String> columnQuarto;
    @FXML
    private TableColumn<Reserva, Double> columnValorDiaria;
    @FXML
    private TableColumn<Reserva, LocalDate> columnDataReserva;
    @FXML
    private TableColumn<Reserva, LocalDate> columnDataEntrada;
    @FXML
    private TableColumn<Reserva, LocalDate> columnDataSaida;
    @FXML
    private Button buttonEdit;

    private ReservaDAO reservaDao;
    private ObservableList<Reserva> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.reservaDao = new ReservaDAO();
        this.tableData.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        setColumnProperties();
        loadData();
    }

    private void setColumnProperties() {
        this.columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.columnDataReserva.setCellValueFactory(new PropertyValueFactory<>("dataReserva"));
        this.columnDataEntrada.setCellValueFactory(new PropertyValueFactory<>("dataEntrada"));
        this.columnDataSaida.setCellValueFactory(new PropertyValueFactory<>("dataSaida"));
        this.columnCliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCliente().getNome()));
        this.columnQuarto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuarto().getNumero()));
        this.columnValorDiaria.setCellValueFactory(new PropertyValueFactory<>("valorDiaria"));
    }

    private void loadData() {
        this.list.clear();
        this.list.addAll(this.reservaDao.getAll());
        tableData.setItems(list);
    }

    private void openForm(Reserva reserva, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/FXMLReservaCadastro.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de Reserva");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) buttonEdit).getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            FXMLReservaCadastroController controller = loader.getController();

            controller.setReserva(reserva);
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();
        } catch (IOException e) {
            AlertHandler.openFormException(e);
        }
        loadData();
    }

    @FXML
    private void edit(ActionEvent event) {
        if (tableData.getSelectionModel().getSelectedIndex() >= 0) {
            Reserva reserva = tableData.getSelectionModel().getSelectedItem();
            this.openForm(reserva, event);
        } else {
            AlertHandler.chooseRecordException();
        }
    }

    @FXML
    private void newRecord(ActionEvent event) {
        this.openForm(new Reserva(), event);
    }

    @FXML
    private void delete(ActionEvent event) {
        if (tableData.getSelectionModel().getSelectedIndex() >= 0) {
            try {
                Reserva reserva = tableData.getSelectionModel().getSelectedItem();
                reservaDao.delete(reserva.getId());
                tableData.getItems().remove(tableData.getSelectionModel().getSelectedIndex());
            } catch (Exception e) {
                AlertHandler.removeRecordException(e);
            }
        } else {
            AlertHandler.chooseRecordException();
        }
    }
}
