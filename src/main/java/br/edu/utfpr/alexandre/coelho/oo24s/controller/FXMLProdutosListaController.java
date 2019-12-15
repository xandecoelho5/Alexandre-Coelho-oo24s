package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.dao.ProdutosDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Produtos;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLProdutosListaController implements Initializable {

    @FXML
    private TableView<Produtos> tableData;
    @FXML
    private TableColumn<Produtos, Long> columnId;
    @FXML
    private TableColumn<Produtos, String> columnNome;
    @FXML
    private TableColumn<Produtos, Double> columnValor;
    @FXML
    private TableColumn<Produtos, String> columnCategoria;

    private ProdutosDAO produtoDao;
    private ObservableList<Produtos> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.produtoDao = new ProdutosDAO();
        setColumnProperties();
        loadData();
    }

    private void setColumnProperties() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        columnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
    }

    private void loadData() {
        list.clear();
        list.addAll(produtoDao.getAll());

        tableData.setItems(list);
    }

    private void openForm(Produtos produto, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/FXMLProdutosCadastro.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de Produtos/Serviços");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) event.getSource()).getScene().getWindow());

            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            FXMLProdutosCadastroController controller = loader.getController();

            controller.setProduto(produto);
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
            Produtos produto = tableData.getSelectionModel().getSelectedItem();
            this.openForm(produto, event);
        } else {
            AlertHandler.chooseRecordException();
        }
    }

    @FXML
    private void newRecord(ActionEvent event) {
        this.openForm(new Produtos(), event);
    }

    @FXML
    private void delete(ActionEvent event) {
        if (tableData.getSelectionModel().getSelectedIndex() >= 0) {
            try {
                Produtos produto = tableData.getSelectionModel().getSelectedItem();
                produtoDao.delete(produto.getId());
                tableData.getItems().remove(tableData.getSelectionModel().getSelectedIndex());
            } catch (Exception e) {
                AlertHandler.removeRecordException(e);
            }
        } else {
            AlertHandler.chooseRecordException();
        }
    }
}
