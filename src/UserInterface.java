import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Map;

/**
 * Created by kirstin
 */

//Konstruktor - määrab ära, mis juhtub kui objekt kä
public class UserInterface {
    Stage layout =new Stage();
    Button button1;
    Button button2;
    ChoiceBox cb;
    String area;
    Map selectedData;

    //Konstruktor - määrab ära, mis juhtub kui objekt käivitatakse
    public UserInterface() {
    setStage();                 //Stage'iga seonduva loomine
    Selection ();               // Kasutaja saab valida kahe regiooni vahel
        reactOnClick1 ();       // Programm joonistab graafiku vastavalt kasutaja valikule

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
        cb.setValue("Whole Estonia");

        button1 = new Button();
        button1.setText("Chart on Prices!");
        grid.add(button1, 4, 1);

        button2 = new Button();
        button2.setText("Chart on Transaction Volumes!");
        grid.add(button2, 4, 2);
    }

    //Piirkonna valiku muutusele reageerimine
    public void Selection() {
        cb.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            area = (String) newValue;
            System.out.println(area);
        });

    }
    // Programm joonistab valitud graafiku
    public void reactOnClick1() {
        // kui tahetakse, et programm väljastaks hinnakasvu graafiku
        button1.setOnAction(event -> {
                String selection= cb.getValue().toString(); //salvestab valitud piirkonna muutujasse
                Database a = new Database();
                selectedData= a.getSelectedData(selection); //küsib andmebaasist valitud piirkonna andmed
                DataChart priceschart=new DataChart();
                // Põhinedes valikutele, mille alusel tehti SQL-i andmebaasist päring, joonistab programm graafiku
                priceschart.drawChart(selectedData);
                a.clearDatabase();
                a.closeConnection();
        });
        // kui tahetakse, et programm väljastaks tehingumahtude graafiku
        button2.setOnAction(event -> {
                String selection= cb.getValue().toString(); //salvestab valitud piirkonna muutujasse
                Database a = new Database();
                selectedData= a.getSelectedData3(selection); //küsib andmebaasist valitud piirkonna andmed
                DataChart priceschart=new DataChart();
                 // Põhinedes valikutele, mille alusel tehti SQL-i andmebaasist päring, joonistab programm graafiku
                priceschart.drawChart2(selectedData);
                a.clearDatabase();
                a.closeConnection();


        });
}
        }




