package model;

public class Tour {
    private String name;
    private String type;
    private String transport;
    private String mealOption;
    private int numberOfDays;
    private int price;
    private double rating;

    public Tour(String name, String type, String transport, String mealOption, int numberOfDays, int price, double rating) {
        this.name = name;
        this.type = type;
        this.transport = transport;
        this.mealOption = mealOption;
        this.numberOfDays = numberOfDays;
        this.price = price;
        this.rating = rating;
    }

    public String getName(){ return name; }
    public String getType(){ return type; }
    public String getTransport(){ return transport; }
    public String getMealOption(){ return mealOption; }
    public int getNumberOfDays(){ return numberOfDays; }
    public int getPrice(){ return price; }
    public double getRating(){ return rating; }


    @Override
    public String toString() {
        return "Tour{name='" + name + "', type='" + type + "', transport='" + transport + "', mealOption='" + mealOption +
                "', numberOfDays=" + numberOfDays + ", price=" + price + ", rating=" + rating + "}";
    }
}
