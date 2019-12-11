package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.dao.ClienteDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.dao.HospedeDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.dao.QuartoDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.dao.ReservaDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Cliente;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Hospede;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Quarto;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Reserva;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    private static Reserva reserva;
    private ClienteDAO clienteDAO;
    private HospedeDAO hospedeDAO;
    private QuartoDAO quartoDAO;
    private List<Hospede> listaSelecionados;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reserva = new Reserva();
        this.reservaDAO = new ReservaDAO();
        this.clienteDAO = new ClienteDAO();
        this.hospedeDAO = new HospedeDAO();
        this.quartoDAO = new QuartoDAO();
        
        //ObservableList<Cliente> clientes = FXCollections.observableArrayList(clienteDAO.getAll());
        List<Cliente> listaClientes = new ArrayList<>();
        listaClientes.addAll(clienteDAO.getClientesComReserva());
        System.out.println(listaClientes);
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(listaClientes);
        
        this.cbCliente.setItems(clientes); //only clientes that have reservas;
    }  
    
    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
    private void cancel() {
        this.stage.close();
    }
    
    public void setDataPane(Node node) {
        boxProdutos.getChildren().setAll(node);
    }

    public static Reserva getReserva() {
        return reserva;
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
    
    private void refreshData(){
        textCliente.setText(reserva.getCliente().getNome());
        textQuarto.setText(reserva.getQuarto().getNumero());
        textValorDiaria.setText(String.valueOf(reserva.getQuarto().getValorDiaria()));
        dateReserva.setText(reserva.getDataReserva().toString());
        dateStart.setText(reserva.getDataEntrada().toString());
        dateEnd.setText(reserva.getDataSaida().toString());
        ObservableList<Hospede> hospedes = FXCollections.observableArrayList(reserva.getHospedes());
        this.listHospedes.setItems(hospedes);
        Usuario usuario = FXMLPrincipalController.getUsuarioAutenticado();  
        textActualUser.setText(usuario.getNome());
        textUserRegistered.setText(usuario.getNome());
        //textUserRegistered.setText(reserva.getUsuario().getNome());
    }
    
    @FXML
    public void confirmCliente(ActionEvent event) throws IOException {    
        if(!(cbCliente.getValue() == null)){
            Cliente c = (Cliente) cbCliente.getValue();
            reserva = reservaDAO.getOne(reservaDAO.getByNome(c.getNome()));
            refreshData();
            setDataPane(openVBox("/fxml/FXMLProdutosListaReserva.fxml")); 
        }   
    }
}
