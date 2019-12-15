package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.dao.HospedeDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Hospede;
import br.edu.utfpr.alexandre.coelho.oo24s.util.AlertHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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

public class FXMLHospedeListaController implements Initializable {

    @FXML
    private TableView<Hospede> tableData;
    @FXML
    private TableColumn<Hospede, Long> columnId;
    @FXML
    private TableColumn<Hospede, String> columnNome;
    @FXML
    private TableColumn<Hospede, String> columnTelefone;
    @FXML
    private Button buttonEdit;

    private HospedeDAO hospedeDAO;
    private ObservableList<Hospede> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.hospedeDAO = new HospedeDAO();
        this.tableData.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        setColumnProperties();
        loadData();
    }

    private void setColumnProperties() {
        this.columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefoneComercial"));
    }

    private void loadData() {
        this.list.clear();
        this.list.addAll(this.hospedeDAO.getAll());
        tableData.setItems(list);
    }

    public void openForm(Hospede hospede, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/FXMLHospedeCadastro.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de Hóspede");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) buttonEdit).getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            FXMLHospedeCadastroController controller = loader.getController();

            controller.setHospede(hospede);
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
            Hospede hospede = tableData.getSelectionModel().getSelectedItem();
            this.openForm(hospede, event);
        } else {
            AlertHandler.chooseRecordException();
        }
    }

    @FXML
    private void newRecord(ActionEvent event) {
        this.openForm(new Hospede(), event);
    }

    @FXML
    private void delete(ActionEvent event) {
        if (tableData.getSelectionModel().getSelectedIndex() >= 0) {
            try {
                Hospede hospede = tableData.getSelectionModel().getSelectedItem();
                hospedeDAO.delete(hospede.getId());
                tableData.getItems().remove(tableData.getSelectionModel().getSelectedIndex());
            } catch (Exception e) {
                AlertHandler.removeRecordException(e);
            }
        } else {
            AlertHandler.chooseRecordException();
        }
    }
}
