package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.db.DatabaseConnection;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Usuario;
import br.edu.utfpr.alexandre.coelho.oo24s.report.GenerateReport;
import br.edu.utfpr.alexandre.coelho.oo24s.util.AlertHandler;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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
import javax.swing.ImageIcon;
import net.sf.jasperreports.view.JasperViewer;

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

    /*@FXML
    private void showReportProduto(ActionEvent event) {
        GenerateReport generateReport = new GenerateReport();
        InputStream file = this.getClass().getResourceAsStream("/report/Reserva.jrxml");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("TITULO", "Relatório de Produtos - JavaFx");
        Image imagem = new ImageIcon(this.getClass().getResource("/imagens/logoUTFPR.jpg")).getImage();
        parameters.put("LOGO", imagem);

        DatabaseConnection conn = DatabaseConnection.getInstance();
        try {
            JasperViewer viewer = generateReport.getReport(conn.getConnection(), parameters, file);
            viewer.setVisible(true);
        } catch (Exception e) {
            AlertHandler.showReportException(e); 
        }
    }

     @FXML
    private void showPieChart(ActionEvent event) throws IOException {
        setDataPane(openVBox("/fxml/FXMLPieChart.fxml"));
    }

    @FXML
    private void showBarChart(ActionEvent event) throws IOException {
        setDataPane(openVBox("/fxml/FXMLBarChart.fxml"));
    }*/
}
