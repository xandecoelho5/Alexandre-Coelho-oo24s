package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.dao.ProdutosDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Produtos;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLProdutosListaReservaController implements Initializable {

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
    @FXML
    private ChoiceBox cbProdutos;
    @FXML
    private TextField textTotal;

    private ProdutosDAO produtoDao;
    private ObservableList<Produtos> list = FXCollections.observableArrayList();
    private List<Produtos> prodSelecionados;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.produtoDao = new ProdutosDAO();
        this.prodSelecionados = new ArrayList<>();
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
        list.addAll(prodSelecionados);
        tableData.setItems(list);
    }

    @FXML
    private void addToList() {
        Produtos produto = (Produtos) this.cbProdutos.getValue();
        if (!prodSelecionados.contains(produto)) {
            prodSelecionados.add(produto);
        }
        loadData();
        //calcular total ao adicionar;
    }

    @FXML
    private void removeFromList() {
        if (tableData.getSelectionModel().getSelectedIndex() >= 0) {
            try {
                Produtos produto = tableData.getSelectionModel().getSelectedItem();
                prodSelecionados.remove(produto);
                produtoDao.delete(produto.getId());
                tableData.getItems().remove(tableData.getSelectionModel().getSelectedIndex());
                //loadData();
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Ocorreu um erro ao remover o registro!");
                alert.setContentText("Por favor, tente realizar a operação novamente!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Nenhum registro selecionado");
            alert.setContentText("Por favor, selecione um registro na tabela!");
            alert.showAndWait();
        }
    }
}
