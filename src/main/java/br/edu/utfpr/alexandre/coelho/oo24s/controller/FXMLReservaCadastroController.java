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
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
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
    @FXML
    private Button buttonAddToList;

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
        List<Hospede> hospedes = new ArrayList<>(hospedeDAO.getAll());

        if (!reservas.isEmpty()) {
            for (int i = 0; i < quartos.size(); i++) {
                Long quartoId = quartos.get(i).getId();

                for (int j = 0; j < reservas.size(); j++) {
                    Long quartoResId = reservas.get(j).getQuarto().getId();
                    if (Objects.equals(quartoId, quartoResId)) {
                        quartos.remove(i);
                    }
                }
            }
            for (int i = hospedes.size()-1; i >= 0 ; i--) {
                Long hospedeId = hospedes.get(i).getId();
                
                for (int j = 0; j < reservas.size(); j++) {
                    for (int k = 0; k < reservas.get(j).getHospedes().size(); k++) { // 1 , 2 ,3 
                        Long hospedeResId = reservas.get(j).getHospedes().get(k).getId(); // 1 2 3 1 e 1 remove; 
                        if (Objects.equals(hospedeId, hospedeResId)) {
                            hospedes.remove(hospedes.get(i));
                        }
                    }
                }
            }
        }

        ObservableList<Cliente> clientes = FXCollections.observableArrayList(clienteDAO.getAll());
        ObservableList<Quarto> quartosz = FXCollections.observableArrayList(quartos);
        ObservableList<Hospede> hospedesz = FXCollections.observableArrayList(hospedes);

        this.cbCliente.setItems(clientes);
        this.cbQuarto.setItems(quartosz);
        this.listHospedes.setItems(hospedesz);
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
        Quarto quarto = (Quarto) cbQuarto.getValue();
        this.textValorDiaria.setText(quarto.getValorDiaria().toString());
    }

     @FXML
    private void newClienteRecord(ActionEvent event) {
        //FXMLClienteListaController clienteLista = new FXMLClienteListaController();
        //clienteLista.openForm(new Cliente(), event);
        try {
            // Carregar o arquivo fxml e cria um
            //novo stage para a janela Modal
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/fxml/FXMLClienteCadastro.fxml"));                
            AnchorPane pane = (AnchorPane) loader.load();

            //Criando o stage para o modal
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de Cliente");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) buttonAddToList).getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            FXMLClienteCadastroController controller = loader.getController();

            controller.setCliente(new Cliente());
            controller.setDialogStage(dialogStage);
            // Exibe a janela Modal e espera até o usuário
            //fechar
            dialogStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um erro ao abrir a janela de cadastro!");       
            alert.setContentText("Por favor, tente realizar a operação novamente!");      
            alert.showAndWait();
        }
    }
    
    @FXML
    private void newHospedeRecord (ActionEvent event) {
        FXMLHospedeListaController hospedeLista = new FXMLHospedeListaController();
        hospedeLista.openForm(new Hospede(), event);
    }
}
