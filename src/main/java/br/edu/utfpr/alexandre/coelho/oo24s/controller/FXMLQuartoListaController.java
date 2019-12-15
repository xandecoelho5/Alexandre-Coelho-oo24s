package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.dao.QuartoDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Quarto;
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

public class FXMLQuartoListaController implements Initializable {

    @FXML
    private TableView<Quarto> tableData;
    @FXML
    private TableColumn<Quarto, Long> columnId;
    @FXML
    private TableColumn<Quarto, String> columnNumero;
    @FXML
    private TableColumn<Quarto, String> columnTipo;
    @FXML
    private TableColumn<Quarto, Integer> columnQtdeCamas;
    @FXML
    private TableColumn<Quarto, Integer> columnQtdeAcomoda;
    @FXML
    private TableColumn<Quarto, Double> columnValorDiaria;
    @FXML
    private Button buttonEdit;
    
    private QuartoDAO quartoDAO;
    private ObservableList<Quarto> list = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.quartoDAO = new QuartoDAO();
        this.tableData.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        setColumnProperties();
        loadData();
    }

    private void setColumnProperties() {
        this.columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.columnNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        this.columnTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        this.columnQtdeCamas.setCellValueFactory(new PropertyValueFactory<>("qtdeCamas"));
        this.columnQtdeAcomoda.setCellValueFactory(new PropertyValueFactory<>("qtdePessoas"));
        this.columnValorDiaria.setCellValueFactory(new PropertyValueFactory<>("valorDiaria"));
    }

    private void loadData() {
        this.list.clear();
        this.list.addAll(this.quartoDAO.getAll());
        tableData.setItems(list);
    }

    private void openForm(Quarto quarto, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/FXMLQuartoCadastro.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de Quarto");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) buttonEdit).getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            FXMLQuartoCadastroController controller = loader.getController();

            controller.setCliente(quarto);
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
            Quarto quarto = tableData.getSelectionModel().getSelectedItem();
            this.openForm(quarto, event);
        } else {
            AlertHandler.chooseRecordException();
        }    
    }

    @FXML
    private void newRecord(ActionEvent event) {
        this.openForm(new Quarto(), event);
    }

    @FXML
    private void delete(ActionEvent event) {
        if (tableData.getSelectionModel().getSelectedIndex() >= 0) {
            try {
                Quarto quarto = tableData.getSelectionModel().getSelectedItem();
                quartoDAO.delete(quarto.getId());
                tableData.getItems().remove(tableData.getSelectionModel().getSelectedIndex());
            } catch (Exception e) {
                AlertHandler.removeRecordException(e);
            }
        } else {
            AlertHandler.chooseRecordException();
        }
    }
}
