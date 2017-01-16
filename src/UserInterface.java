import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kirstin
 */
public class UserInterface {
    Stage layout =new Stage();
    Button button1;
    Button button2;
    ChoiceBox cb;
    String area;
    Map selectedData;


    public UserInterface() {
    setStage();
    Selection ();
        reactOnClick1 ();

}

    public void setStage() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(50, 50, 50, 50));

        Scene scene = new Scene(grid, 500, 200);
        layout.setScene(scene);
        layout.setTitle("Growth In Real Estate Prices");
        layout.show();

        Label name1 = new Label("Select area");
        grid.add(name1, 1, 0);
        cb = new ChoiceBox(FXCollections.observableArrayList("Whole Estonia","Only Tallinn"));
        grid.add(cb, 4, 0);

        button1 = new Button();
        button1.setText("Chart on Prices!");
        grid.add(button1, 4, 1);

        button2 = new Button();
        button2.setText("Chart on Transaction Volumes!");
        grid.add(button2, 4, 2);
}
    public void Selection() {
        cb.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            area = (String) newValue;
            System.out.println(area);
        });

    }
    public void reactOnClick1() {
        String prices1 = "Chart on Prices!";
        String prices2 = "Chart on Prices!";
        String transactions1 = "Chart on Transaction Volumes!";
        String transactions2 = "Chart on Transaction Volumes!";



        button1.setOnAction(event -> {
            if (button1.getText().toString().equals(prices1)){
                String selection= cb.getValue().toString();
                Database a = new Database();
                selectedData= a.getSelectedData(selection);
                DataChart priceschart=new DataChart();
                priceschart.drawChart(selectedData);
                a.clearDatabase();
                a.closeConnection();
            }
            else {
                String selection= cb.getValue().toString();
                Database a = new Database();
                selectedData= a.getSelectedData(selection);
                DataChart priceschart=new DataChart();
                priceschart.drawChart(selectedData);
                a.clearDatabase();
                a.closeConnection();
            }

        });

        button2.setOnAction(event -> {
            if (button2.getText().toString().equals(transactions1)){
                String selection= cb.getValue().toString();
                Database a = new Database();
                selectedData= a.getSelectedData3(selection);
                DataChart priceschart=new DataChart();
                priceschart.drawChart(selectedData);
                a.clearDatabase();
                a.closeConnection();
            }
            else {
                String selection= cb.getValue().toString();
                Database a = new Database();
                selectedData= a.getSelectedData3(selection);
                DataChart priceschart=new DataChart();
                priceschart.drawChart(selectedData);
                a.clearDatabase();
                a.closeConnection();
            }

        });


}

        }




