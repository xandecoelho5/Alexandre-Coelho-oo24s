package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.dao.UsuarioDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Usuario;
import br.edu.utfpr.alexandre.coelho.oo24s.util.AlertHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FXMLLoginController implements Initializable {

   @FXML
    private TextField textUsuario;
    @FXML
    private TextField textSenha;
    @FXML 
    private Button buttonEntrar;
    
    private UsuarioDAO usuarioDao;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.usuarioDao = new UsuarioDAO();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                textUsuario.requestFocus();
            }
        });
        buttonEntrar.setDefaultButton(true);
    }    
    
    @FXML
    private void login() {
        try {
            Usuario usuario = this.usuarioDao.findByEmailAndSenhaNamedQuery(textUsuario.getText(), textSenha.getText());
                    
            if (usuario != null) {  
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/FXMLPrincipal.fxml"));
             
                VBox root = (VBox) loader.load();
                Scene scene = new Scene(root);
                //scene.getStylesheets().add("/styles/temaDark.css");
                
                Stage stage = new Stage();
                stage.setTitle("Hotelaria Joestar");
                stage.setScene(scene);
                stage.setMaximized(true);
                stage.setResizable(true);
                
                FXMLPrincipalController controller = loader.getController();   
                controller.setUsuarioAutenticado(usuario);
                
                stage.show();
                
                Stage stageLogin = (Stage) buttonEntrar.getScene().getWindow();
                stageLogin.close();           
            }
        } catch (IOException e) {
            AlertHandler.loginException(e);
        }
    }
}
