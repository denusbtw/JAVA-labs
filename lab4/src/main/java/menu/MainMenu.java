package menu;

import model.Tour;
import wishlist.Wishlist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;


public class MainMenu {
    private static Logger logger = Logger.getLogger(MainMenu.class.getName());
    private Map<Integer, MenuCommand> commands;
    private List<Tour> tours;
    private Wishlist wishlist;

    public MainMenu(List<Tour> tours, Wishlist wishlist) {
        logger.info("Initializing MainMenu...");

        commands = new HashMap<>();
        this.tours = tours;
        this.wishlist = wishlist;

        commands.put(1, new ViewAllTours(tours));
        commands.put(2, new FilterTours(tours));
        commands.put(3, new SortTours(tours));
        commands.put(4, new ViewWishList(wishlist));
        commands.put(5, new AddToWishList(tours, wishlist));
        commands.put(6, new EditWishlist(wishlist));
        commands.put(7, new Exit(wishlist));

        logger.info("Finished initializing MainMenu");
    }

    public void displayMenu(){
        System.out.println("1. View all available tours");
        System.out.println("2. Filter tours");
        System.out.println("3. Sort tours");
        System.out.println("4. View wishlist");
        System.out.println("5. Add tour to wishlist");
        System.out.println("6. Edit wishlist");
        System.out.println("7. Exit");
    }

    public void executeCommand(int option) {
        MenuCommand command = commands.get(option);
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Invalid option");
        }
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        int option;

        while (true) {
            System.out.println();
            displayMenu();
            System.out.print("Please select an option: ");
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                executeCommand(option);
            } else {
                System.out.println("Please enter a valid number.");
                scanner.next();
            }
        }
    }
}