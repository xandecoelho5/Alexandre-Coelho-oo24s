package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.dao.ReservaDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;

public class FXMLPieChartController implements Initializable {

    @FXML
    private AnchorPane pane;
    
    private ReservaDAO reservaDAO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reservaDAO = new ReservaDAO();
        loadData();
    }

    private void loadData() {
        pane.getChildren().clear();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(reservaDAO.getReservasMesData());
        PieChart reservasMes = new PieChart(pieChartData);
        reservasMes.setTitle("Número de reservas por mês");
        pane.getChildren().add(reservasMes);
    }

}
