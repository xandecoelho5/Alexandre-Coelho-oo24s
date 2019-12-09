package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.dao.ClienteDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.dao.QuartoDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Cliente;
import br.edu.utfpr.alexandre.coelho.oo24s.model.ETipoQuarto;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Quarto;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLQuartoCadastroController implements Initializable {

    @FXML
    private TextField textId;
    @FXML
    private TextField textNumero;
    @FXML
    private ChoiceBox cbTipo;
    @FXML
    private TextField textQtdeCamas;
    @FXML
    private TextField textQtdeAcomoda;
    @FXML
    private TextField textValorDiaria;

    private QuartoDAO quartoDAO;
    private Stage stage;
    private Quarto quarto;
   // private final ETipoQuarto[] tipoQuarto = ETipoQuarto.values(); cbJob.setModel(new DefaultComboBoxModel(job)); 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.quarto = new Quarto();
        this.quartoDAO = new QuartoDAO();
        ObservableList<ETipoQuarto> tipos = FXCollections.observableArrayList(new ArrayList<>(Arrays.asList(ETipoQuarto.values())));
        this.cbTipo.setItems(tipos);   
    }    
    
    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void cancel() {
        this.stage.close();
    }

    @FXML
    private void save() {
        quarto.setNumero(textNumero.getText()); 
        quarto.setQtdeCamas(Integer.parseInt(textQtdeCamas.getText()));
        quarto.setQtdePessoas(Integer.parseInt(textQtdeAcomoda.getText()));
        quarto.setValorDiaria(Double.parseDouble(textValorDiaria.getText()));
        quarto.setTipo(ETipoQuarto.valueOf(cbTipo.getValue().toString()));
                
        this.quartoDAO.save(quarto);      
        this.stage.close();
    }

    public void setCliente(Quarto quarto) {
        this.quarto = quarto;
        if (quarto.getId() != null) {
            this.textId.setText(quarto.getId().toString());
            this.textNumero.setText(quarto.getNumero());
            this.textQtdeCamas.setText(String.valueOf(quarto.getQtdeCamas()));
            this.textQtdeAcomoda.setText(quarto.getQtdePessoas().toString());
            this.textValorDiaria.setText(String.valueOf(quarto.getValorDiaria()));
            //fazer alterações pro choicebox       
        }
    }    
}
