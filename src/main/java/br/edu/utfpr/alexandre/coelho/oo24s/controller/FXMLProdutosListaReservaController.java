package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.dao.ProdutosDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.dao.ReservaProdutosDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Produtos;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Reserva;
import br.edu.utfpr.alexandre.coelho.oo24s.model.ReservaProdutos;
import br.edu.utfpr.alexandre.coelho.oo24s.util.AlertHandler;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
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
    @FXML
    private TextField textQtde;
    @FXML
    private TextField textValDiarias;
    @FXML
    private TextField textValProdutos;

    private ProdutosDAO produtoDao;
    private ReservaProdutosDAO reservaProdutosDAO;
    private ObservableList<ReservaProdutos> list = FXCollections.observableArrayList();
    private final DecimalFormat df = new DecimalFormat("R$0.00");
    private Reserva reservaAtual;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.produtoDao = new ProdutosDAO();
        this.reservaProdutosDAO = new ReservaProdutosDAO();
        this.reservaAtual = FXMLReservaManutController.getReserva(); 
        
        atualizaProdutos();
        setColumnProperties();
        loadData();
        calcularTotal();
    }

    private void setColumnProperties() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProdutos().getNome()));
        columnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        columnCategoria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProdutos().getCategoria()));
    }

    private void loadData() {
        list.clear();
        list.addAll(reservaProdutosDAO.getAllByReservaId(reservaAtual.getId()));
        tableData.setItems(list);
    }

    @FXML
    private void addToList(ActionEvent event) {
        if (cbProdutos.getValue() != null) {
            Produtos produto = (Produtos) this.cbProdutos.getValue();
            Boolean contem = false;
            for (ReservaProdutos reservaProdutos : list) {
                if (reservaProdutos.getProdutos().getNome().equals(produto.getNome())) {
                    contem = true;
                    break;
                }
            }
            ReservaProdutos reservaProduto = new ReservaProdutos();
            reservaProduto.setProdutos(produto);
            reservaProduto.setReserva(reservaAtual);
            reservaProduto.setValor(produto.getValor());
            if (contem) {
                int qtty = reservaProdutosDAO.getRPbyProdId(produto.getId(), reservaAtual.getId()).getQuantidade();
                reservaProduto.setQuantidade(Integer.parseInt(textQtde.getText()) + qtty);
                try {
                    reservaProdutosDAO.delete(reservaProdutosDAO.getRPbyProdId(produto.getId(), reservaAtual.getId()).getId());
                } catch (Exception ex) {
                    Logger.getLogger(FXMLProdutosListaReservaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                reservaProduto.setQuantidade(Integer.parseInt(textQtde.getText()));
            }
            reservaProdutosDAO.insert(reservaProduto);
            loadData();
            calcularTotal();
        }
    }

    private void atualizaProdutos() {
        ObservableList<Produtos> produtos = FXCollections.observableArrayList(produtoDao.getAll());
        cbProdutos.setItems(produtos);
    }

    private void calcularTotal() {
        Double diarias = reservaAtual.getValorDiaria() * FXMLReservaManutController.getDias();
        textValDiarias.setText(df.format(diarias));
        Double total = list.stream().mapToDouble(v -> v.getValor() * v.getQuantidade()).sum();
        textValProdutos.setText(df.format(total));

        textTotal.setText(df.format(diarias + total));
    }

    @FXML
    private void removeFromList(ActionEvent event) {
        if (tableData.getSelectionModel().getSelectedIndex() >= 0) {
            try {
                ReservaProdutos reservaProd = tableData.getSelectionModel().getSelectedItem();
                reservaProdutosDAO.delete(reservaProd.getId());
                loadData();
                calcularTotal();
            } catch (Exception e) {
                AlertHandler.removeRecordException(e);
            }
        } else {
            AlertHandler.chooseRecordException();
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
        } catch (IOException e) {
            AlertHandler.openFormException(e);
        }
    }
}
