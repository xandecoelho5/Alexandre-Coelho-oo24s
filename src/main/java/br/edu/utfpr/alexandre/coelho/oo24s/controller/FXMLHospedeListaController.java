package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.dao.HospedeDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Hospede;
import java.net.URL;
import java.util.ResourceBundle;
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

public class FXMLHospedeListaController implements Initializable {

    @FXML
    private TableView<Hospede> tableData;
    @FXML
    private TableColumn<Hospede, Long> columnId;
    @FXML
    private TableColumn<Hospede, String> columnNome;
    @FXML
    private TableColumn<Hospede, String> columnTelefone;
    @FXML
    private Button buttonEdit;

    private HospedeDAO hospedeDAO;
    private ObservableList<Hospede> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.hospedeDAO = new HospedeDAO();
        this.tableData.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        setColumnProperties();
        loadData();
    }

    private void setColumnProperties() {
        this.columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefoneComercial"));
    }

    private void loadData() {
        this.list.clear();
        this.list.addAll(this.hospedeDAO.getAll());
        tableData.setItems(list);
    }

    public void openForm(Hospede hospede, ActionEvent event) {
        try {
            // Carregar o arquivo fxml e cria um
            //novo stage para a janela Modal
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/fxml/FXMLHospedeCadastro.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            //Criando o stage para o modal
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de Hóspede");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) buttonEdit).getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            FXMLHospedeCadastroController controller = loader.getController();

            controller.setHospede(hospede);
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
        Hospede hospede = tableData.getSelectionModel().getSelectedItem();
        this.openForm(hospede, event);
    }

    @FXML
    private void newRecord(ActionEvent event) {
        this.openForm(new Hospede(), event);
    }

    @FXML
    private void delete(ActionEvent event) {
        if (tableData.getSelectionModel().getSelectedIndex() >= 0) {
            try {
                Hospede hospede = tableData.getSelectionModel().getSelectedItem();
                hospedeDAO.delete(hospede.getId());
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
