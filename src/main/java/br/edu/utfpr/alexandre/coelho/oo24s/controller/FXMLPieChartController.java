package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.dao.ReservaDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    List<PieChart.Data> chartList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {                
        reservaDAO = new ReservaDAO();
        chartList = new ArrayList<>();     
        List<Object[]> lista = reservaDAO.getReservasMesData();
        
        for (Object[] ob : lista) {
            String month = "";
            switch(String.valueOf(ob[0])){
                case "1.0": month = "Janeiro"; break;
                case "2.0": month = "Fevereiro"; break;
                case "3.0": month = "Março"; break;
                case "4.0": month = "Abril"; break;
                case "5.0": month = "Maio"; break;
                case "6.0": month = "Junho"; break;
                case "7.0": month = "Julho"; break;
                case "8.0": month = "Agosto"; break;
                case "9.0": month = "Setembro"; break;
                case "10.0": month = "Outubro"; break;
                case "11.0": month = "Novembro"; break;
                case "12.0": month = "Dezembro"; break;
            }
            System.out.println(String.valueOf(ob[0]));
            chartList.add(new PieChart.Data(month, Integer.parseInt(ob[1].toString())));
        }

        loadData();
    }

    private void loadData() {
        pane.getChildren().clear();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(chartList);
        PieChart reservasMes = new PieChart(pieChartData);
        reservasMes.setTitle("Número de reservas por mês");
        pane.getChildren().add(reservasMes);
    }

}
