package menu;

import wishlist.Wishlist;

import java.util.logging.Logger;

public class ViewWishList implements MenuCommand {
    private static Logger logger = Logger.getLogger(Wishlist.class.getName());
    private Wishlist wishlist;

    public ViewWishList(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    @Override
    public void execute() {
        logger.info("Executing ViewWishList command...");
        wishlist.displayWishlist();
        logger.info("Finished executing ViewWishList command...");
    }
}
