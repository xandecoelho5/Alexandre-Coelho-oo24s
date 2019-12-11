package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.dao.ProdutosDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.dao.ReservaDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Produtos;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Reserva;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private ReservaDAO reservaDAO;
    private ObservableList<Produtos> list = FXCollections.observableArrayList();
    private List<Produtos> prodSelecionados;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.produtoDao = new ProdutosDAO();
        this.reservaDAO = new ReservaDAO();
        this.prodSelecionados = new ArrayList<>();
        
        prodSelecionados.addAll(produtoDao.getByReservaId(FXMLReservaManutController.getReserva().getId()));
        
        ObservableList<Produtos> produtos = FXCollections.observableArrayList(produtoDao.getAll());
        cbProdutos.setItems(produtos);
        
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
    private void addToList(ActionEvent event) {
        Produtos produto = (Produtos) this.cbProdutos.getValue();
        if (!prodSelecionados.contains(produto)) {
            prodSelecionados.add(produto);
            atualizaReserva();
        }
        loadData();
    }
    
    private void atualizaReserva(){
        Reserva reserva = reservaDAO.getOne(FXMLReservaManutController.getReserva().getId());
        reserva.setProdutos(prodSelecionados);
        reservaDAO.update(reserva);
        calcularTotal();
    }
    
    private void calcularTotal() {
        DecimalFormat df = new DecimalFormat("R$0.00");
        Double total = 0.0;
        for (Produtos produto : prodSelecionados) {
            total += produto.getValor();
        }
        textTotal.setText(df.format(total));
    }
    
    @FXML
    private void removeFromList(ActionEvent event) {
        if (tableData.getSelectionModel().getSelectedIndex() >= 0) {
            try {
                Produtos produto = tableData.getSelectionModel().getSelectedItem();
                prodSelecionados.remove(produto);              
                tableData.getItems().remove(tableData.getSelectionModel().getSelectedIndex());
                atualizaReserva();              
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

    @FXML
    private void newProduct(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/fxml/FXMLProdutosCadastro.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de Produtos/Serviços");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) event.getSource()).getScene().getWindow());

            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            FXMLProdutosCadastroController controller = loader.getController();

            controller.setProduto(new Produtos());
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um erro ao abrir a janela de cadastro!");
            alert.setContentText("Por favor, tente realizar a operação novamente!");
            alert.showAndWait();
        }
    }
}
