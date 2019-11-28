package br.edu.utfpr.alexandre.coelho.oo24s.controller;

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
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class FXMLPrincipalController implements Initializable {

    @FXML
    private VBox boxPrincipal;

    private Usuario usuarioAutenticado;

    public void setUsuarioAutenticado(Usuario usuario) {
        this.usuarioAutenticado = usuario;
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
    public void loadCliente(ActionEvent event) throws IOException {       
        setDataPane(openVBox("/fxml/FXMLClienteLista.fxml"));
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
