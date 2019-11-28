package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.dao.UsuarioDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Usuario;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FXMLUsuarioCadastroController implements Initializable {

    @FXML
    private TextField textId;
    @FXML
    private TextField textNome;
    @FXML
    private TextField textCpf;
    @FXML
    private TextField textEmail;
    @FXML
    private PasswordField textSenha;
    @FXML
    private DatePicker dateDataNascimento;
    @FXML
    private CheckBox checkAtivo;
    @FXML
    private ImageView imageFoto;
    @FXML
    private Button buttonFoto;

    private Usuario usuario;
    private UsuarioDAO usuarioDao;
    private Stage dialogStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.usuarioDao = new UsuarioDAO();
        this.usuario = new Usuario();
        this.buttonFoto.setOnAction((final ActionEvent e) -> {
            loadImage();
        });
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        if (usuario.getId() != null) {
            textId.setText(usuario.getId().toString());
            textNome.setText(usuario.getNome());
            textCpf.setText(usuario.getCpf());
            textEmail.setText(usuario.getEmail());
            textSenha.setText(usuario.getSenha());
            dateDataNascimento.setValue(usuario.getDataNascimento());    
            checkAtivo.setSelected(usuario.getAtivo());
            try {
                if (usuario.getFoto() != null) {
                    Image image = new Image(new ByteArrayInputStream(usuario.getFoto()));
                    imageFoto.setImage(image);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    private void loadImage() {
        try {
            final FileChooser fileChooser = new FileChooser();          
            File file = fileChooser.showOpenDialog(dialogStage);
                    
            if (file != null) {
                Image image = new Image(file.toURI().toString());      
                imageFoto.setImage(image);
                usuario.setFoto(Files.readAllBytes(file.toPath()));   
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cancel() {
        this.dialogStage.close();
    }

    @FXML
    private void save() {
        usuario.setNome(textNome.getText());
        usuario.setCpf(textCpf.getText());
        usuario.setEmail(textEmail.getText());
        usuario.setSenha(textSenha.getText());
        usuario.setDataNascimento(dateDataNascimento.getValue());           
        usuario.setAtivo(checkAtivo.isSelected());
        if (this.usuarioDao.isValid(usuario)) {
            this.usuarioDao.save(usuario);
            this.dialogStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Nenhum registro selecionado");                   
            alert.setContentText(this.usuarioDao.getErrors(usuario));
            alert.showAndWait();
        }
    }
}
