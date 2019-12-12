package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.dao.ProdutosDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.dao.ReservaDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.dao.ReservaProdutosDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Produtos;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Reserva;
import br.edu.utfpr.alexandre.coelho.oo24s.model.ReservaProdutos;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
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
    private TableView<ReservaProdutos> tableData;
    @FXML
    private TableColumn<ReservaProdutos, Long> columnId;
    @FXML
    private TableColumn<ReservaProdutos, String> columnNome;
    @FXML
    private TableColumn<ReservaProdutos, Double> columnValor;
    @FXML
    private TableColumn<ReservaProdutos, Integer> columnQuantidade;
    @FXML
    private TableColumn<ReservaProdutos, String> columnCategoria;
    @FXML
    private ChoiceBox cbProdutos;
    @FXML
    private TextField textTotal;

    private ProdutosDAO produtoDao;
    private ReservaDAO reservaDAO;
    private ReservaProdutosDAO reservaProdutosDAO;
    private ObservableList<ReservaProdutos> list = FXCollections.observableArrayList();
    private List<Produtos> prodSelecionados;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.produtoDao = new ProdutosDAO();
        this.reservaDAO = new ReservaDAO();
        this.reservaProdutosDAO = new ReservaProdutosDAO();
        this.prodSelecionados = new ArrayList<>();

        //prodSelecionados.addAll(produtoDao.getByReservaId(FXMLReservaManutController.getReserva().getId()));

        atualizaProdutos();
        setColumnProperties();
        loadData();
    }

    private void setColumnProperties() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProdutos().getNome()));
        columnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        columnQuantidade.setCellFactory(new PropertyValueFactory<>("quantidade"));
        columnCategoria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProdutos().getCategoria()));
    }

    private void loadData() {
        list.clear();
        list.addAll(reservaProdutosDAO.getAll());
        tableData.setItems(list);
    }

    @FXML
    private void addToList(ActionEvent event) {
        Produtos produto = (Produtos) this.cbProdutos.getValue();
        ReservaProdutos reservaProduto = new ReservaProdutos();
        reservaProduto.setProdutos(produto);
        reservaProduto.setQuantidade(10);
        reservaProduto.setReserva(FXMLReservaManutController.getReserva());
        reservaProduto.setValor(produto.getValor());
        reservaProdutosDAO.insert(reservaProduto);
        loadData();
        calcularTotal();
    }

    private void atualizaProdutos() {
        ObservableList<Produtos> produtos = FXCollections.observableArrayList(produtoDao.getAll());
        cbProdutos.setItems(produtos);
    }

    private void calcularTotal() {
        DecimalFormat df = new DecimalFormat("R$0.00");
        Double total = list.stream().mapToDouble(v -> v.getValor() * v.getQuantidade()).sum();
        textTotal.setText(df.format(total));
    }

    @FXML
    private void removeFromList(ActionEvent event) {
        if (tableData.getSelectionModel().getSelectedIndex() >= 0) {
            try {
                ReservaProdutos reservaProd = tableData.getSelectionModel().getSelectedItem();
                reservaProdutosDAO.delete(reservaProd.getId());
                calcularTotal();
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Ocorreu um erro ao remover o registro!", "Por favor, tente realizar a operação novamente!");
            }
        } else {
            showAlert("Nenhum registro selecionado", "Por favor, selecione um registro na tabela!");
        }
    }

    @FXML
    private void newProduct(ActionEvent event) {
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

            controller.setProduto(new Produtos());
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();
            atualizaProdutos();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Ocorreu um erro ao abrir a janela de cadastro!", "Por favor, tente realizar a operação novamente!");
        }
    }

    private void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
