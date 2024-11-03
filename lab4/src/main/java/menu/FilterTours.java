package menu;

import model.Filter;
import model.Tour;
import other.TablePrinter;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class FilterTours implements MenuCommand {
    private static Logger logger = Logger.getLogger(FilterTours.class.getName());
    private List<Tour> tours;

    public FilterTours(List<Tour> tours) {
        this.tours = tours;
    }

    @Override
    public void execute(){
        logger.info("Executing FilterTours command...");
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter type of tour (or leave blank): ");
        String searchType = scanner.nextLine();

        System.out.print("Enter transport (or leave blank): ");
        String transport = scanner.nextLine();

        System.out.print("Enter meal option (or leave blank): ");
        String mealOption = scanner.nextLine();

        System.out.print("Enter minimum price: ");
        int minPrice = scanner.nextInt();

        System.out.print("Enter maximum price: ");
        int maxPrice = scanner.nextInt();

        System.out.print("Enter minimum rating: ");
        double minRating = scanner.nextDouble();

        System.out.print("Enter maximum rating: ");
        double maxRating = scanner.nextDouble();

        Filter filter = new Filter(
                searchType.isEmpty() ? null : searchType,
                transport.isEmpty() ? null : transport,
                mealOption.isEmpty() ? null : mealOption,
                minPrice,
                maxPrice,
                minRating,
                maxRating
        );

        List<Tour> filteredTours = filter.applyFilter(tours);

        logger.info("Filter has been applied: searchType = " + searchType + ", transport = " + transport
                + ", mealOption = " + mealOption + ", minPrice = " + minPrice + ", maxPrice = " + maxPrice
                + ", minRating = " + minRating + ", maxRating = " + maxRating);

        if (filteredTours.isEmpty()) {
            System.out.println("\nNo tours found.");
        } else {
            System.out.println();

            TablePrinter tablePrinter = new TablePrinter();
            tablePrinter.print(filteredTours);
        }

        logger.info("Finished executing FilterTours command");
    }
}
