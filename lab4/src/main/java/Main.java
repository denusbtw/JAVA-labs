import model.Tour;

import java.util.List;
import java.util.logging.Logger;

import other.GlobalLoggerSetup;
import other.JSONReader;
import wishlist.Wishlist;
import menu.MainMenu;


public class Main {
    private static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        GlobalLoggerSetup.setupGlobalLogging();

        logger.info("Initialing components...");

        List<Tour> availableTours = readToursFromJSON("tours.json");
        Wishlist wishlist = new Wishlist();
        MainMenu menu = new MainMenu(availableTours, wishlist);

        logger.info("Finished initializing components");

        menu.run();
    }

    public static List<Tour> readToursFromJSON(String fileName) {
        return JSONReader.readToursFromJSON(fileName);
    }
}
