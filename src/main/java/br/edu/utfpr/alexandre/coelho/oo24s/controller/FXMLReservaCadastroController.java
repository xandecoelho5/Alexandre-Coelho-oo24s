package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.dao.ClienteDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.dao.HospedeDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.dao.QuartoDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.dao.ReservaDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Cliente;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Hospede;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Quarto;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Reserva;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLReservaCadastroController implements Initializable {

    @FXML
    private TextField textId;
    @FXML
    private ChoiceBox cbCliente;
    @FXML
    private ChoiceBox cbQuarto;
    @FXML
    private TextField textValorDiaria;
    @FXML
    private ListView listHospedes;
    @FXML
    private DatePicker dateReserva;
    @FXML
    private DatePicker dateEntrada;
    @FXML
    private DatePicker dateSaida;
    @FXML
    private TextField textMotivo;
    @FXML
    private Button buttonNovoCliente;
    @FXML 
    private Button buttonNovoHospede;
    
    private ReservaDAO reservaDAO;
    private Stage stage;
    private Reserva reserva;
    private ClienteDAO clienteDAO;
    private HospedeDAO hospedeDAO;
    private QuartoDAO quartoDAO;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.reserva = new Reserva();
        this.reservaDAO = new ReservaDAO();
        this.clienteDAO = new ClienteDAO();
        this.hospedeDAO = new HospedeDAO();
        this.quartoDAO = new QuartoDAO();
        
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(clienteDAO.getAll());
        ObservableList<Quarto> quartos = FXCollections.observableArrayList(quartoDAO.getAll());        
        ObservableList<Hospede> hospedes = FXCollections.observableArrayList(hospedeDAO.getAll());        
                        
        this.cbCliente.setItems(clientes);
        this.cbQuarto.setItems(quartos);
        this.listHospedes.setItems(hospedes);
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
        reserva.setCliente((Cliente)cbCliente.getValue());
        reserva.setQuarto((Quarto)cbQuarto.getValue());      
        reserva.setValorDiaria(reserva.getQuarto().getValorDiaria());
        reserva.setHospedes(listHospedes.getSelectionModel().getSelectedItems());
        reserva.setDataEntrada(dateEntrada.getValue());
        reserva.setDataReserva(dateReserva.getValue());
        reserva.setDataSaida(dateSaida.getValue());
        reserva.setMotivo(textMotivo.getText());
                
        this.reservaDAO.save(reserva);
        
        this.stage.close();
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
        if (reserva.getId() != null) {
            this.textId.setText(reserva.getId().toString());
            this.dateEntrada.setValue(reserva.getDataEntrada());
            this.dateReserva.setValue(reserva.getDataReserva());
            this.dateSaida.setValue(reserva.getDataSaida());
            this.textMotivo.setText(reserva.getMotivo());
            this.cbCliente.setValue(reserva.getCliente());
            this.cbQuarto.setValue(reserva.getQuarto());
            this.listHospedes.setItems(FXCollections.observableArrayList(reserva.getHospedes()));       
        }
    }     
    
    @FXML
    private void newClienteRecord(ActionEvent event) {
        FXMLClienteListaController clienteLista = new FXMLClienteListaController();
        clienteLista.openForm(new Cliente(), event);
    }
    
    @FXML
    private void newHospedeRecord (ActionEvent event) {
        FXMLHospedeListaController hospedeLista = new FXMLHospedeListaController();
        hospedeLista.openForm(new Hospede(), event);
    }
}
