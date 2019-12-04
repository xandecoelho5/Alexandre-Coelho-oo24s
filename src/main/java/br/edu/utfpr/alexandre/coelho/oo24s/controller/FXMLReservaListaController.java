package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.dao.ClienteDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.dao.ReservaDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Cliente;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Reserva;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLReservaListaController implements Initializable {

    @FXML
    private TableView<Reserva> tableData;
    @FXML
    private TableColumn<Reserva, Long> columnId;
    @FXML
    private TableColumn<Reserva, String> columnCliente;
    @FXML
    private TableColumn<Reserva, String> columnQuarto;
    @FXML
    private TableColumn<Reserva, Double> columnValorDiaria;
    @FXML
    private TableColumn<Reserva, LocalDate> columnDataReserva;
    @FXML
    private TableColumn<Reserva, LocalDate> columnDataEntrada;
    @FXML
    private TableColumn<Reserva, LocalDate> columnDataSaida;
    @FXML
    private Button buttonEdit;

    private ReservaDAO reservaDao;
    private ObservableList<Reserva> list = FXCollections.observableArrayList();         

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.reservaDao = new ReservaDAO();
        this.tableData.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);             
        setColumnProperties();
        loadData();
    }

    private void setColumnProperties() {
        this.columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.columnDataReserva.setCellValueFactory(new PropertyValueFactory<>("dataReserva")); 
        this.columnDataEntrada.setCellValueFactory(new PropertyValueFactory<>("dataEntrada"));
        this.columnDataSaida.setCellValueFactory(new PropertyValueFactory<>("dataSaida"));
        this.columnCliente.setCellValueFactory(cellData -> new SimpleStringProperty( cellData.getValue().getCliente().getNome()));
        this.columnQuarto.setCellValueFactory(cellData -> new SimpleStringProperty( cellData.getValue().getQuarto().getNumero())); 
        this.columnValorDiaria.setCellValueFactory(new PropertyValueFactory<>("valorDiaria")); 
    }

    private void loadData() {
        this.list.clear();
        this.list.addAll(this.reservaDao.getAll());
        tableData.setItems(list);
    }

    private void openForm(Reserva reserva, ActionEvent event) {
        try {
            // Carregar o arquivo fxml e cria um
            //novo stage para a janela Modal
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/fxml/FXMLReservaCadastro.fxml"));                
            AnchorPane pane = (AnchorPane) loader.load();

            //Criando o stage para o modal
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de Reserva");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) buttonEdit).getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            FXMLReservaCadastroController controller = loader.getController();

            controller.setReserva(reserva);
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
        loadData();
    }

    @FXML
    private void edit(ActionEvent event) {
        Reserva reserva = tableData.getSelectionModel().getSelectedItem();
        this.openForm(reserva, event);
    }

    @FXML
    private void newRecord(ActionEvent event) {
        this.openForm(new Reserva(), event);
    }

    @FXML
    private void delete(ActionEvent event) {
        if (tableData.getSelectionModel().getSelectedIndex() >= 0) {
            try {
                Reserva reserva = tableData.getSelectionModel().getSelectedItem();                      
                reservaDao.delete(reserva.getId());
                tableData.getItems().remove(tableData.getSelectionModel().getSelectedIndex());                     
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Ocorreu um erro ao remover o registro!");                      
                alert.setContentText("Por favor, tente realizar a operação novamente!");                      
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Nenhum registro selecionado");  
            alert.setContentText("Por favor, selecione um registro na tabela!");   
            alert.showAndWait();
        }
    }    
    
}
