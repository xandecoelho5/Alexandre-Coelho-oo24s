package br.edu.utfpr.alexandre.coelho.oo24s.controller;

import br.edu.utfpr.alexandre.coelho.oo24s.dao.ReservaDAO;
import br.edu.utfpr.alexandre.coelho.oo24s.dao.ReservaProdutosDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;

public class FXMLChartValorController implements Initializable {

    @FXML
    private AnchorPane pane;
    
    private ReservaDAO reservaDAO;
    private ReservaProdutosDAO reservaProdutosDAO;
    List<PieChart.Data> chartList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reservaDAO = new ReservaDAO();
        reservaProdutosDAO = new ReservaProdutosDAO();
        chartList = new ArrayList<>();     
        
        List<Object[]> listaDiaria = reservaDAO.getValorDiariaMes();
        List<Object[]> listaValProdutos = reservaProdutosDAO.getValorProdutosMes();
        
        for(int i=0; i< listaDiaria.size(); i++) {
            String month = "";
            switch(String.valueOf(listaDiaria.get(i)[0])){
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
            Double value = Double.parseDouble(listaDiaria.get(i)[1].toString()) + Double.parseDouble(listaValProdutos.get(i)[1].toString());
            chartList.add(new PieChart.Data(month, value));
        }
        loadData();
    }

    private void loadData() {
        pane.getChildren().clear();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(chartList);
        PieChart reservasMes = new PieChart(pieChartData);
        reservasMes.setTitle("Valor total recebido por mês");
        pane.getChildren().add(reservasMes);
    }  
}
