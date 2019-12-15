package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.model.Usuario;
import br.edu.utfpr.alexandre.coelho.oo24s.util.AlertHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLPrincipalController implements Initializable {

    @FXML
    private VBox boxPrincipal;
    @FXML
    private Button buttonCliente;

    private static Usuario usuarioAutenticado;

    public void setUsuarioAutenticado(Usuario usuario) {
        usuarioAutenticado = usuario;
    }

    public static Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setDataPane(Node node) {
        boxPrincipal.getChildren().setAll(node);
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

    @FXML
    private void openReservas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/FXMLReservaManut.fxml"));
            BorderPane pane = (BorderPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Controle de Reservas");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) buttonCliente).getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            FXMLReservaManutController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();
        } catch (IOException e) {
            AlertHandler.openFormException(e);
        }
    }

    @FXML
    public void loadCliente(ActionEvent event) throws IOException {
        try {
            setDataPane(openVBox("/fxml/FXMLClienteLista.fxml"));
        } catch (IOException e) {
            AlertHandler.loadFormException("clientes", e);
        }
    }

    @FXML
    public void loadProdutos(ActionEvent event) throws IOException {
        try {
            setDataPane(openVBox("/fxml/FXMLProdutosLista.fxml"));
        } catch (IOException e) {
            AlertHandler.loadFormException("produtos", e);
        }
    }

    @FXML
    public void loadQuarto(ActionEvent event) throws IOException {
        try {
            setDataPane(openVBox("/fxml/FXMLQuartoLista.fxml"));
        } catch (IOException e) {
            AlertHandler.loadFormException("produtos", e);
        }
    }

    @FXML
    public void loadHospede(ActionEvent event) throws IOException {
        try {
            setDataPane(openVBox("/fxml/FXMLHospedeLista.fxml"));
        } catch (IOException e) {
            AlertHandler.loadFormException("hóspedes", e);
        }
    }

    @FXML
    public void loadReserva(ActionEvent event) throws IOException {
        try {
            setDataPane(openVBox("/fxml/FXMLReservaLista.fxml"));
        } catch (IOException e) {
            AlertHandler.loadFormException("reservas", e);
        }
    }

    @FXML
    public void loadUsuario(ActionEvent event) throws IOException {
        try {
            setDataPane(openVBox("/fxml/FXMLUsuarioLista.fxml"));
        } catch (IOException e) {
            AlertHandler.loadFormException("usuários", e);
        }
    }
}
