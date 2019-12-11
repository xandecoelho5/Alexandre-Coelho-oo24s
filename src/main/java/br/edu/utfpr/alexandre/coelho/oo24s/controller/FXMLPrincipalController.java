package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.model.Reserva;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Usuario;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
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
    @FXML
    private Button buttonHospede;
    @FXML
    private Button buttonQuarto;
    @FXML
    private Button buttonProdutos;
    @FXML
    private Button buttonReserva;
    @FXML
    private Button buttonUsuario;
    
    private static Usuario usuarioAutenticado;

    public void setUsuarioAutenticado(Usuario usuario) {
        usuarioAutenticado = usuario;
    }
    
    public static Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setDataPane(Node node) {
        /* Atualiza o VBox (boxPrincipal) com um 
            novo form (FXML) dependendo do 
            item de menu ou botão acionado
         */
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
    public void loadCliente(ActionEvent event) throws IOException { 
        try{
            setDataPane(openVBox("/fxml/FXMLClienteLista.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" .: Aula 4 - JavaFX :. ");
            alert.setHeaderText("Atenção, ocorreu um erro!");
            alert.setContentText("Falha ao abrir a tela de clientes.");
            alert.showAndWait();
        } 
    }

    @FXML
    public void loadProdutos(ActionEvent event) throws IOException {          
        setDataPane(openVBox("/fxml/FXMLProdutosLista.fxml"));  
    }
    
    @FXML
    public void loadQuarto(ActionEvent event) throws IOException {          
        setDataPane(openVBox("/fxml/FXMLQuartoLista.fxml"));  
    }
    
    @FXML
    public void loadHospede(ActionEvent event) throws IOException {          
        setDataPane(openVBox("/fxml/FXMLHospedeLista.fxml"));  
    }
    
    @FXML
    public void loadReserva(ActionEvent event) throws IOException {          
        setDataPane(openVBox("/fxml/FXMLReservaLista.fxml"));  
    }

    @FXML
    public void loadUsuario(ActionEvent event) throws IOException {           
        setDataPane(openVBox("/fxml/FXMLUsuarioLista.fxml"));
    } 
}
