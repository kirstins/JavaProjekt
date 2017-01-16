/**
 * Created by kirstin
 */
public class PricesAndTransactions {
    private int id;
    private int year;
    private String area;
    private double prices;
    private double transactions;

    public PricesAndTransactions(int id, int year, String area, double prices, double transactions) {
        this.id = id;
        this.year = year;
        this.area = area;
        this.prices = prices;
        this.transactions = transactions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public double getPrices() {
        return prices;
    }

    public void setPrices(double prices) {
        this.prices = prices;
    }

    public double getTransactions() {
        return transactions;
    }

    public void setTransactions(double transactions) {
        this.transactions = transactions;
    }
    }





