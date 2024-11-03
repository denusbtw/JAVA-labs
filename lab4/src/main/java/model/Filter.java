package model;

import java.util.List;
import java.util.stream.Collectors;

public class Filter {
    private String searchType;
    private String transport;
    private String mealOption;
    private int minPrice;
    private int maxPrice;
    private double minRating;
    private double maxRating;

    public Filter(String searchType, String transport, String mealOption, int minPrice, int maxPrice,
                  double minRating, double maxRating) {
        this.searchType = searchType;
        this.transport = transport;
        this.mealOption = mealOption;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minRating = minRating;
        this.maxRating = maxRating;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public void setMealOption(String mealOption) {
        this.mealOption = mealOption;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setMinRating(double minRating) {
        this.minRating = minRating;
    }

    public void setMaxRating(double maxRating) {
        this.maxRating = maxRating;
    }

    public List<Tour> applyFilter(List<Tour> tours){
        return tours.stream()
                .filter(tour -> (searchType == null || tour.getType().equalsIgnoreCase(searchType)))
                .filter(tour -> (transport == null || tour.getTransport().equalsIgnoreCase(transport)))
                .filter(tour -> (mealOption == null || tour.getMealOption().equalsIgnoreCase(mealOption)))
                .filter(tour -> tour.getPrice() >= minPrice && tour.getPrice() <= maxPrice)
                .filter(tour -> tour.getRating() >= minRating && tour.getRating() <= maxRating)
                .collect(Collectors.toList());
    }
}
