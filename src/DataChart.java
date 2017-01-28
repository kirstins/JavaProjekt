import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Map;

/**
 * Created by kirstin
 */

public class DataChart {
    // aknaga seonduv
    Stage stage = new Stage();
    StackPane window = new StackPane();
    Scene scene2 = new Scene(window,600, 600);

        //graafiku joonistamisel oli abiks oracle...
        public void drawChart(final Map<Integer, Double> selectedData){
        final NumberAxis xAxis = new NumberAxis(2004, 2016, 1);
        final NumberAxis yAxis = new NumberAxis();

        final LineChart<Number,Number> chart = new LineChart<>(xAxis,yAxis);

        xAxis.setLabel("Year");
        yAxis.setLabel("Average annual growth in the real estate prices");

            XYChart.Series series = new XYChart.Series();
            series.setName("Development in the real estate prices");
            //vajalikud andmed graafiku joonistamiseks saadakse hashmapist
            // graafikule kantakse kõik päritud aasta-hinnakasv paarid
            for (Map.Entry<Integer, Double> entry : selectedData.entrySet())  {
                series.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
                    System.out.println(entry.getKey());
                    System.out.println(entry.getValue());
                }

          chart.getData().add(series);
                window.getChildren().add(chart);
            stage.setScene(scene2);
                stage.show();
}

    public void drawChart2(final Map<Integer, Double> selectedData){
        final NumberAxis xAxis = new NumberAxis(2004, 2016, 1);
        final NumberAxis yAxis = new NumberAxis();

        final LineChart<Number,Number> chart = new LineChart<>(xAxis,yAxis);

        xAxis.setLabel("Year");
        yAxis.setLabel("Annual growth in transactionsvolumes");

        XYChart.Series series = new XYChart.Series();
        series.setName("Development in transactions volumes");

        //vajalikud andmed graafiku joonistamiseks saadakse hashmapist
        // graafikule kantakse kõik päritud aasta-tehingute maht paarid
        for (Map.Entry<Integer, Double> entry : selectedData.entrySet())  {
            series.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        chart.getData().add(series);
        window.getChildren().add(chart);
        stage.setScene(scene2);
        stage.show();
        }
}









