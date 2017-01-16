import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kirstin
 */
public class Database {

    Map<String, Double> selectedData;


    Connection conn = null;

    public Database(){
        createConnection();
        createTable ();
    }

    private void createConnection() {
        try {
            Class.forName("org.sqlite.JDBC");                          // Laen draiveri sqlite.jar failist
            conn = DriverManager.getConnection("jdbc:sqlite:database.db"); // Ühenduse loomine andmebaasi failiga
            System.out.println("Opened database successfully");//
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    private void createTable() {

        try {
            String sql = "CREATE TABLE IF NOT EXISTS PRICESANDTRANSACTIONS (ID INTEGER, YEAR INTEGER, " +
                    "REGION TEXT, PRICES NUMERIC, TRANSACTIONS NUMERIC);";
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);
            stat.close();

            String query = "INSERT INTO PRICESANDTRANSACTIONS VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = conn.prepareStatement(query);

            ArrayList<PricesAndTransactions> list = getDataFromCvsFile("pricesandtransactions.csv");

            for (int i=0; i < list.size(); i++) {
                preparedStatement.setInt(1, list.get(i).getId());
                preparedStatement.setInt(2, list.get(i).getYear());
                preparedStatement.setString(3, list.get(i).getArea());
                preparedStatement.setDouble(4, list.get(i).getPrices());
                preparedStatement.setDouble(5, list.get(i).getTransactions());


                preparedStatement.executeUpdate();
                System.out.println("No problem occurred"+ (i+1));

            }  } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<PricesAndTransactions> getDataFromCvsFile (String filePath) {

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader bReader = null;
        ArrayList <PricesAndTransactions> values = new ArrayList<PricesAndTransactions>();

        try {
            fis = new FileInputStream(filePath);
            isr = new InputStreamReader(fis);
            bReader = new BufferedReader(isr);
            String line = null;
            String []strPrices = null;

            while (true) {
                line = bReader.readLine();
                if (line == null) {
                    break;
                } else {
                    strPrices = line.split(",");
                    values.add(new PricesAndTransactions(Integer.parseInt(strPrices[0]), Integer.parseInt(strPrices[1]), (strPrices[2]), Double.parseDouble(strPrices[3]), Double.parseDouble(strPrices[4])));

                }
            }
        } catch (Exception e) {
            System.out.println("File reading error!");
            e.printStackTrace();

        }finally {
            try {
                bReader.close();
                isr.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return values;
    }


    public Map<Integer, Double> getSelectedData (String region){
        Map<Integer, Double> selectedData = new HashMap<>();
        try {
            Statement stat = conn.createStatement();

            String sql = "SELECT YEAR, PRICES FROM PRICESANDTRANSACTIONS WHERE REGION = ('" +region+ "') ORDER BY YEAR";
            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()){
                Integer year= rs.getInt("YEAR");
                Double Prices = rs.getDouble("PRICES");
                selectedData.put(year,Prices);
                System.out.println(selectedData);

            }
            } catch (SQLException e){
                e.printStackTrace();
    }
    return selectedData;}

    public Map<Integer, Double> getSelectedData2 (String region2){
        Map<Integer, Double> selectedData = new HashMap<>();
        try {
            Statement stat = conn.createStatement();

            String sql = "SELECT YEAR, PRICES FROM PRICESANDTRANSACTIONS WHERE REGION = ('" +region2+ "') ORDER BY YEAR";
            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()){
                Integer year= rs.getInt("YEAR");
                Double Prices = rs.getDouble("PRICES");
                selectedData.put(year,Prices);
                System.out.println(selectedData);

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return selectedData;}

    public Map<Integer, Double> getSelectedData3 (String region3){
        Map<Integer, Double> selectedData = new HashMap<>();
        try {
            Statement stat = conn.createStatement();

            String sql = "SELECT YEAR, TRANSACTIONS FROM PRICESANDTRANSACTIONS WHERE REGION = ('" +region3+ "') ORDER BY YEAR";
            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()){
                Integer year= rs.getInt("YEAR");
                Double Transactions = rs.getDouble("TRANSACTIONS");
                selectedData.put(year,Transactions);
                System.out.println(selectedData);

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return selectedData;}

    public Map<Integer, Double> getSelectedData4 (String region4){
        Map<Integer, Double> selectedData = new HashMap<>();
        try {
            Statement stat = conn.createStatement();

            String sql = "SELECT YEAR, TRANSACTIONS FROM PRICESANDTRANSACTIONS WHERE REGION = ('" +region4+ "') ORDER BY YEAR";
            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()){
                Integer year= rs.getInt("YEAR");
                Double Transactions = rs.getDouble("TRANSACTIONS");
                selectedData.put(year,Transactions);
                System.out.println(selectedData);

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return selectedData;}


    public void clearDatabase() {
        try {
            Statement stat = conn.createStatement();
            String sql = ("DELETE FROM PRICESANDTRANSACTIONS");
            stat.executeUpdate(sql);
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
            }
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connection closed");
    }


}





