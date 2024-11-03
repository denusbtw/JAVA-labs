package menu;

import wishlist.Wishlist;

import java.util.logging.Logger;


public class Exit implements MenuCommand{
    private static Logger logger = Logger.getLogger(Exit.class.getName());
    private Wishlist wishlist;

    public Exit(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    @Override
    public void execute() {
        logger.info("Executing Exit command...");
        wishlist.saveToJSON("wishlist.json");
        System.out.println("Thank you for using the travel app");

        logger.info("Exiting travel app...");
        logger.info("Finished executing Exit command...");
        System.exit(0);
    }
}
