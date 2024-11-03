package model;

import java.util.Comparator;

public class TourComparators {
    public static final Comparator<Tour> BY_NAME = Comparator.comparing(Tour::getName);
    public static final Comparator<Tour> BY_PRICE = Comparator.comparing(Tour::getPrice);
    public static final Comparator<Tour> BY_RATING = Comparator.comparing(Tour::getRating);
    public static final Comparator<Tour> BY_NUMBER_OF_DAYS = Comparator.comparing(Tour::getNumberOfDays);
}
