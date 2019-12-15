package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.dao.ClienteDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.dao.EnderecoDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Cliente;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Endereco;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLClienteCadastroController implements Initializable {
    
    @FXML
    private TextField textId;
    @FXML
    private TextField textNome;
    @FXML
    private TextField textCpf;
    @FXML
    private TextField textRg;
    @FXML
    private TextField textNroPassaporte;
    @FXML
    private TextField textTelComercial;
    @FXML
    private TextField textTelResidencial;
    @FXML
    private TextField textEmail;
    @FXML
    private ChoiceBox cbEndereco;

    private ClienteDAO clienteDAO;
    private Stage stage;
    private Cliente cliente;
    private EnderecoDAO enderecoDAO;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cliente = new Cliente();
        this.clienteDAO = new ClienteDAO();
        this.enderecoDAO = new EnderecoDAO();
        ObservableList<Endereco> enderecos = FXCollections.observableArrayList(this.enderecoDAO.getAll());
        this.cbEndereco.setItems(enderecos); 
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
        cliente.setNome(textNome.getText());
        cliente.setCpf(textCpf.getText());
        cliente.setRg(textRg.getText());
        cliente.setNumeroPassaporte(textNroPassaporte.getText());
        cliente.setTelefoneComercial(textTelComercial.getText());
        cliente.setTelefoneResidencial(textTelResidencial.getText());
        cliente.setEmail(textEmail.getText()); 
        cliente.setEndereco(cbEndereco.getValue().toString());
                
        this.clienteDAO.save(cliente);
        this.stage.close();
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        if (cliente.getId() != null) {
            this.textId.setText(cliente.getId().toString());
            this.textNome.setText(cliente.getNome());
            this.textCpf.setText(cliente.getCpf());
            this.textRg.setText(cliente.getRg());
            this.textNroPassaporte.setText(cliente.getNumeroPassaporte());
            this.textTelComercial.setText(cliente.getTelefoneComercial());
            this.textTelResidencial.setText(cliente.getTelefoneResidencial());
            this.textEmail.setText(cliente.getEmail());
            String[] texts = cliente.getEndereco().split("-");
            this.cbEndereco.setValue(enderecoDAO.getOne(enderecoDAO.getByNome(texts[0].substring(0, texts[0].length()-1))));     
        }
    }
}
