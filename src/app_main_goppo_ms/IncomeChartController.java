/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_main_goppo_ms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class IncomeChartController {

    @FXML
    private AreaChart<String, Number> incomeAreaChart;

    @FXML
    private BarChart<String, Number> incomeBarChart;
    
    @FXML
    private PieChart incomePieChart;
    
    @FXML
    public void initialize() {
        // Sample data
        XYChart.Series<String, Number> incomeData = new XYChart.Series<>();
        incomeData.setName("Monthly Income");

        incomeData.getData().add(new XYChart.Data<>("January", 5000));
        incomeData.getData().add(new XYChart.Data<>("February", 7000));
        incomeData.getData().add(new XYChart.Data<>("March", 6000));
        incomeData.getData().add(new XYChart.Data<>("April", 8000));
        incomeData.getData().add(new XYChart.Data<>("May", 7500));
        incomeData.getData().add(new XYChart.Data<>("June", 8500));
        incomeData.getData().add(new XYChart.Data<>("Julai", 1000));
        incomeData.getData().add(new XYChart.Data<>("Agoust", 6000));
        incomeData.getData().add(new XYChart.Data<>("Sptember", 8000));
        incomeData.getData().add(new XYChart.Data<>("October", 7500));
        incomeData.getData().add(new XYChart.Data<>("November", 8500));
        incomeData.getData().add(new XYChart.Data<>("Desember", 8500));
        
        
         ObservableList<PieChart.Data> incomeData1 = FXCollections.observableArrayList(
            new PieChart.Data("Salary", 5000),
            new PieChart.Data("Investments", 3000),
            new PieChart.Data("Freelance", 1500),
            new PieChart.Data("Others", 1000),
            new PieChart.Data("Salary", 5000),
            new PieChart.Data("Investments", 3000),
            new PieChart.Data("Freelance", 1500),
            new PieChart.Data("Others", 1000)
        );
        // Adding data to the chart
        //incomeAreaChart.getData().add(incomeData);
        //incomeBarChart.getData().add(incomeData);
        incomePieChart.setData(incomeData1);
    }
}