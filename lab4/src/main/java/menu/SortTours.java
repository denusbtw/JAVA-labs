package menu;

import model.Tour;
import model.TourComparators;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class SortTours implements MenuCommand {
    private static Logger logger = Logger.getLogger(SortTours.class.getName());
    private List<Tour> tours;

    public SortTours(List<Tour> tours) {
        this.tours = tours;

    }

    @Override
    public void execute() {
        logger.info("Executing SortTours command...");
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nChoose attribute to sort by:");
        System.out.println("1. Price");
        System.out.println("2. Rating");
        System.out.println("3. Number of Days");
        System.out.println("4. Name");

        int attributeChoice = scanner.nextInt();

        System.out.println("\nChoose sorting order:");
        System.out.println("1. Ascending");
        System.out.println("2. Descending");

        int orderChoice = scanner.nextInt();

        Comparator<Tour> comparator;
        switch (attributeChoice) {
            case 1 -> comparator = TourComparators.BY_PRICE;
            case 2 -> comparator = TourComparators.BY_RATING;
            case 3 -> comparator = TourComparators.BY_NUMBER_OF_DAYS;
            case 4 -> comparator = TourComparators.BY_NAME;
            default -> {
                System.out.printf("%sInvalid attribute choice. No sorting applied.%s\n", "\u001B[31m", "\u001B[0m");
                return;
            }
        }

        if (orderChoice == 2) {
            comparator = comparator.reversed();
        } else if (orderChoice != 1) {
            System.out.printf("%sInvalid order choice. No sorting applied.%s\n", "\u001B[31m", "\u001B[0m");
            return;
        }

        Collections.sort(tours, comparator);
        logger.info("Finished executing SortTours command");
    }
}
