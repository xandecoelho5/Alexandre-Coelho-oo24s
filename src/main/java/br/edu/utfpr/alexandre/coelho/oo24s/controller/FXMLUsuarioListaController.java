package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.dao.UsuarioDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Usuario;
import br.edu.utfpr.alexandre.coelho.oo24s.util.AlertHandler;
import java.io.IOException;
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
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLUsuarioListaController implements Initializable {

    @FXML
    private TableView<Usuario> tableData;
    @FXML
    private TableColumn<Usuario, Long> columnId;
    @FXML
    private TableColumn<Usuario, String> columnNome;
    @FXML
    private Button buttonEdit;

    private UsuarioDAO usuarioDao;
    private ObservableList<Usuario> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.usuarioDao = new UsuarioDAO();
        this.tableData.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        setColumnProperties();
        loadData();
    }

    private void setColumnProperties() {
        this.columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    }

    private void loadData() {
        this.list.clear();
        this.list.addAll(this.usuarioDao.getAll());
        tableData.setItems(list);
    }

    private void openForm(Usuario usuario, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/FXMLUsuarioCadastro.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de Usuário");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) buttonEdit).getScene().getWindow());

            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            FXMLUsuarioCadastroController controller = loader.getController();
            controller.setUsuario(usuario);
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();
        } catch (IOException e) {
            AlertHandler.openFormException(e);
        }
        loadData();
    }

    @FXML
    private void edit(ActionEvent event) {
        if (tableData.getSelectionModel().getSelectedIndex() >= 0) {
            Usuario usuario = tableData.getSelectionModel().getSelectedItem();
            this.openForm(usuario, event);
        } else {
            AlertHandler.chooseRecordException();
        }
    }

    @FXML
    private void newRecord(ActionEvent event) {
        this.openForm(new Usuario(), event);
    }

    @FXML
    private void delete(ActionEvent event) {
        // NAO DEIXAR EXCLUIR O USUÁRIO ATUAL
        if (tableData.getSelectionModel().getSelectedIndex() >= 0) {
            try {
                Usuario usuario = tableData.getSelectionModel().getSelectedItem();
                usuarioDao.delete(usuario.getId());
                tableData.getItems().remove(tableData.getSelectionModel().getSelectedIndex());
            } catch (Exception e) {
                AlertHandler.removeRecordException(e);
            }
        } else {
            AlertHandler.chooseRecordException();
        }
    }
}
