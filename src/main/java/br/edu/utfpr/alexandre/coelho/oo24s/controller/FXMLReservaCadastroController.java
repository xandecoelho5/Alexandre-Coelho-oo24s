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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
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
    private ListView listSelHospedes;
    @FXML
    private DatePicker dateReserva;
    @FXML
    private DatePicker dateEntrada;
    @FXML
    private DatePicker dateSaida;
    @FXML
    private TextField textMotivo;

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
        this.listaSelecionados = new ArrayList<>();
        List<Reserva> reservas = new ArrayList<>(reservaDAO.getAll());
        List<Quarto> quartos = new ArrayList<>(quartoDAO.getAll());
        
        for (Quarto quarto : quartos) {
            for (Reserva res : reservas) {
                if(quarto.getId().equals(res.getId())){
                    quartos.remove(quarto);
                }
            }
        }
        
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(clienteDAO.getAll());
        ObservableList<Quarto> quartosz = FXCollections.observableArrayList(quartos);
        ObservableList<Hospede> hospedes = FXCollections.observableArrayList(hospedeDAO.getAll());
        
        /*for (Quarto quarto : quartos) {
            for (Reserva res : reservas) {
                if(quarto.getId().equals(res.getId())){
                    quartos.remove(quarto);
                }
            }
        }*/
        
        this.cbCliente.setItems(clientes);
        this.cbQuarto.setItems(quartosz);
        this.listHospedes.setItems(hospedes);
        this.cbQuarto.onActionProperty();
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
        reserva.setCliente((Cliente) cbCliente.getValue());
        reserva.setQuarto((Quarto) cbQuarto.getValue());
        reserva.setValorDiaria(reserva.getQuarto().getValorDiaria());
        reserva.setHospedes(listaSelecionados);
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
            this.textValorDiaria.setText(reserva.getQuarto().getValorDiaria().toString());
            this.listSelHospedes.setItems(FXCollections.observableArrayList(reserva.getHospedes()));
        }
    }

    @FXML
    private void addToList() {
        Hospede hospede = (Hospede) this.listHospedes.getSelectionModel().getSelectedItem();
        if (!listaSelecionados.contains(hospede)) {
            listaSelecionados.add(hospede);
        }
        ObservableList<Hospede> hospedesSelected = FXCollections.observableArrayList(listaSelecionados);
        this.listSelHospedes.setItems(hospedesSelected);
    }

    @FXML
    private void removeFromList() {
        listaSelecionados.remove((Hospede) this.listSelHospedes.getSelectionModel().getSelectedItem());
        ObservableList<Hospede> hospedesSelected = FXCollections.observableArrayList(listaSelecionados);
        this.listSelHospedes.setItems(hospedesSelected);
    }
    
    @FXML
    private void changeValue() {
        Quarto quarto = (Quarto)cbQuarto.getValue();
        this.textValorDiaria.setText(quarto.getValorDiaria().toString());
    }

    /* @FXML
    private void newClienteRecord(ActionEvent event) {
        FXMLClienteListaController clienteLista = new FXMLClienteListaController();
        clienteLista.openForm(new Cliente(), event);
    }
    
    @FXML
    private void newHospedeRecord (ActionEvent event) {
        FXMLHospedeListaController hospedeLista = new FXMLHospedeListaController();
        hospedeLista.openForm(new Hospede(), event);
    }*/
}
