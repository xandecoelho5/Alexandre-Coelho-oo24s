package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.dao.ClienteDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Cliente;
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
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLClienteListaController implements Initializable {

    @FXML
    private TableView<Cliente> tableData;
    @FXML
    private TableColumn<Cliente, Long> columnId;
    @FXML
    private TableColumn<Cliente, String> columnNome;
    @FXML
    private TableColumn<Cliente, String> columnTelefone;
    @FXML
    private TableColumn<Cliente, String> columnEmail;
    @FXML
    private TableColumn<Cliente, String> columnCpf;
    @FXML
    private Button buttonEdit;

    private ClienteDAO clienteDao;
    private ObservableList<Cliente> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.clienteDao = new ClienteDAO();
        this.tableData.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        setColumnProperties();
        loadData();
    }

    private void setColumnProperties() {
        this.columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefoneComercial"));
        this.columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
    }

    private void loadData() {
        this.list.clear();
        this.list.addAll(this.clienteDao.getAll());
        tableData.setItems(list);
    }

    public void openForm(Cliente cliente, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/FXMLClienteCadastro.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de Cliente");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) buttonEdit).getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            FXMLClienteCadastroController controller = loader.getController();

            controller.setCliente(cliente);
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
            Cliente cliente = tableData.getSelectionModel().getSelectedItem();
            this.openForm(cliente, event);
        } else {
            AlertHandler.chooseRecordException();
        }
    }

    @FXML
    private void newRecord(ActionEvent event) {
        this.openForm(new Cliente(), event);
    }

    @FXML
    private void delete(ActionEvent event) {
        if (tableData.getSelectionModel().getSelectedIndex() >= 0) {
            try {
                Cliente cliente = tableData.getSelectionModel().getSelectedItem();
                clienteDao.delete(cliente.getId());
                tableData.getItems().remove(tableData.getSelectionModel().getSelectedIndex());
            } catch (Exception e) {
                AlertHandler.removeRecordException(e);
            }
        } else {
            AlertHandler.chooseRecordException();
        }
    }
}
