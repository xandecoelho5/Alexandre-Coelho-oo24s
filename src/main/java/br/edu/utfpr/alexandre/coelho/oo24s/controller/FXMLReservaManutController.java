package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.dao.ClienteDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.dao.HospedeDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.dao.QuartoDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.dao.ReservaDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Cliente;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Hospede;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Quarto;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Reserva;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLReservaManutController implements Initializable {

    @FXML
    private VBox boxProdutos;
    @FXML
    private TextField textCliente;
    @FXML
    private TextField textQuarto;
    @FXML
    private ChoiceBox cbCliente;
    @FXML
    private TextField textValorDiaria;
    @FXML
    private ListView listHospedes;
    @FXML
    private TextField dateReserva;
    @FXML
    private TextField dateStart;
    @FXML
    private TextField dateEnd;
    @FXML
    private Button buttonAddToList;
    @FXML
    private TextField textActualUser;
    @FXML
    private TextField textUserRegistered;
    @FXML
    private ProgressBar progressReserva;
    
    private ReservaDAO reservaDAO;
    private Stage stage;
    private Reserva reserva;
    private ClienteDAO clienteDAO;
    private HospedeDAO hospedeDAO;
    private QuartoDAO quartoDAO;
    private List<Hospede> listaSelecionados;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.reserva = new Reserva();
        this.reservaDAO = new ReservaDAO();
        this.clienteDAO = new ClienteDAO();
        this.hospedeDAO = new HospedeDAO();
        this.quartoDAO = new QuartoDAO();
        
        //SET TEXTFIELD VALUES
        
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(clienteDAO.getAll());
        ObservableList<Hospede> hospedes = FXCollections.observableArrayList(hospedeDAO.getAll());

        this.cbCliente.setItems(clientes);
        this.listHospedes.setItems(hospedes); //GET ALL WITH WHERE RESERVA_ID = RESERVA_ID;
    }  
    
    public void setDataPane(Node node) {
        boxProdutos.getChildren().setAll(node);
    }

    public VBox openVBox(String url) throws IOException {
        VBox v = (VBox) FXMLLoader.load(this.getClass().getResource(url));            
        FadeTransition ft = new FadeTransition(Duration.millis(1000));                
        ft.setNode(v);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        return v;
    }
    
    private void confirmCliente() {
        //SETPANE(boxProdutos);
        //change all the fuckin things
    }
}
