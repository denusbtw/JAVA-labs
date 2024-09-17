package Lab2;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Product {
    private int id;
    private String name;
    private String manufacturer;
    private double price;
    private Date expiration;
    private int quantity;

    public Product(int id, String name, String manufacturer, double price, int quantity, String expiration) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
        this.expiration = createExpirationDate(expiration);
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public double getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public Date getExpiration() {
        return this.expiration;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    static Date createExpirationDate(String expiration){
        String[] array = expiration.split("-");

        int year = Integer.parseInt(array[0]);

        if (year <= 0){
            System.out.println("Invalid year of expiration");
            return null;
        }

        int month = Integer.parseInt(array[1]);

        if (month <= 0 || month > 12){
            System.out.println("Invalid month of expiration");
            return null;
        }

        int day = Integer.parseInt(array[2]);

        if (day <= 0 || day > 31){
            System.out.println("Invalid day of expiration");
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, day, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public String toString() {
        String result = "\nPRODUCT INFO\n";
        result += "ID: " + this.id + "\n";
        result += "Name: " + this.name + "\n";
        result += "Manufacturer: " + this.manufacturer + "\n";
        result += "Price: " + this.getFormattedPrice() + "\n";
        result += "Quantity: " + this.quantity + "\n";
        result += "Expiration: " + this.getFormattedExpiration() + "\n";
        return result;
    }

    public String getFormattedPrice() {
        return String.format("%.2f$", price);
    }

    public String getFormattedExpiration() {
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formater.format(this.expiration);
    }
}