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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLProdutosCadastroController implements Initializable {

    @FXML
    private TextField textId;
    @FXML 
    private TextField textNome;
    @FXML 
    private TextField textDescricao;
    @FXML
    private TextField textValor;
    @FXML
    private ChoiceBox cbCategoria;
    
    private ProdutosDAO produtoDao;
    private Stage stage;
    private Produtos produto;
    private List<String> categoriasList;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.produtoDao = new ProdutosDAO();
        categoriasList = new ArrayList<>();
        categoriasList.add("Produtos");
        categoriasList.add("Serviços");
        ObservableList<String> categorias = FXCollections.observableArrayList(categoriasList);     
        this.cbCategoria.setItems(categorias);
    }    
    
    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }
    
    public void setProduto(Produtos produto) {
        this.produto = produto;
        if (produto.getId() != null) {
            textId.setText(produto.getId().toString());
            textNome.setText(produto.getNome());
            textDescricao.setText(produto.getDescricao());
            textValor.setText(produto.getValor().toString());
            cbCategoria.setValue(produto.getCategoria());
        }
    }
    @FXML
    private void cancel() {
        this.stage.close();
    }
    @FXML
    private void save() {
        produto.setNome(textNome.getText());
        produto.setValor(Double.parseDouble(textValor.getText()));       
        produto.setDescricao(textDescricao.getText());
        produto.setCategoria(cbCategoria.getSelectionModel().getSelectedItem().toString());
        this.produtoDao.save(produto);
        this.stage.close();
    }
}
