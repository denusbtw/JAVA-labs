package menu;

import model.Tour;
import wishlist.Wishlist;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class AddToWishList implements MenuCommand {
    private static Logger logger = Logger.getLogger(AddToWishList.class.getName());
    private List<Tour> tours;
    private Wishlist wishlist;

    public AddToWishList(List<Tour> tours, Wishlist wishlist) {
        this.tours = tours;
        this.wishlist = wishlist;
    }

    @Override
    public void execute() {
        logger.info("Executing AddToWishList command...");

        Scanner scanner = new Scanner(System.in);
        int option;

        System.out.print("\nEnter index of tour you want to add to main.java.wishlist: ");

        if (scanner.hasNextInt()) {
            option = scanner.nextInt();
            if (option >= 0 && option <= tours.size()) {
                Tour tour = tours.get(option - 1);

                wishlist.addTour(tour);
            } else {
                System.out.printf("%sInvalid index.%s\n", "\u001B[31m", "\u001B[0m");
                logger.warning("Wishlist: invalid index selected +" + option);
            }
        }

        logger.info("Finished executing AddToWishList command");
    }
}
