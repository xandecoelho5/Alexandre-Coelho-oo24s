package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.dao.EnderecoDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.dao.HospedeDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Endereco;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Hospede;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLHospedeCadastroController implements Initializable {

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

    private HospedeDAO hospedeDAO;
    private Stage stage;
    private Hospede hospede;
    private EnderecoDAO enderecoDAO;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.hospede = new Hospede();
        this.hospedeDAO = new HospedeDAO();
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
        hospede.setNome(textNome.getText());
        hospede.setCpf(textCpf.getText());
        hospede.setRg(textRg.getText());
        hospede.setNumeroPassaporte(textNroPassaporte.getText());
        hospede.setTelefoneComercial(textTelComercial.getText());
        hospede.setTelefoneResidencial(textTelResidencial.getText());
        hospede.setEmail(textEmail.getText()); 
        hospede.setEndereco(cbEndereco.getValue().toString());
                
        this.hospedeDAO.save(hospede);
        
        this.stage.close();
    }

    public void setCliente(Hospede hospede) {
        this.hospede = hospede;
        if (hospede.getId() != null) {
            this.textId.setText(hospede.getId().toString());
            this.textNome.setText(hospede.getNome());
            this.textCpf.setText(hospede.getCpf());
            this.textRg.setText(hospede.getRg());
            this.textNroPassaporte.setText(hospede.getNumeroPassaporte());
            this.textTelComercial.setText(hospede.getTelefoneComercial());
            this.textTelResidencial.setText(hospede.getTelefoneResidencial());
            this.textEmail.setText(hospede.getEmail());
            String[] texts = hospede.getEndereco().split("-");
            this.cbEndereco.setValue(enderecoDAO.getOne(enderecoDAO.getByNome(texts[0].substring(0, texts[0].length()-1))));      
        }
    }   
}
