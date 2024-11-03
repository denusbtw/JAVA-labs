package wishlist;

import model.Tour;
import other.JSONReader;
import other.JSONWriter;
import other.TablePrinter;

import java.util.List;
import java.util.logging.Logger;


public class Wishlist {
    private static Logger logger = Logger.getLogger(Wishlist.class.getName());
    private List<Tour> tours;

    public Wishlist() {
        tours = JSONReader.readToursFromJSON("wishlist.json");
    }

    public void addTour(Tour tour){
        if (tours.contains(tour)) {
            System.out.println("Can't add. This tour is already in the wishlist.");
            logger.warning("Can't add. This tour is already in the wishlist.");
            return;
        }
        tours.add(tour);

        System.out.printf("%s+ %s%s\n", "\u001B[32m", tour.getName(), "\u001B[0m");
        logger.info("Wishlist: + " + tour.getName());
    }

    public void removeTour(int index){
        if (index >= 0 && index < tours.size()){
            Tour tour = tours.get(index);
            tours.remove(index);

            System.out.printf("%s- %s%s\n", "\u001B[31m", tour.getName(), "\u001B[0m");
            logger.info("Wishlist: - " + tour.getName());
        } else {
            System.out.printf("%sInvalid index.%s\n", "\u001B[31m", "\u001B[0m");
            logger.warning("Wishlist: Invalid index selected - " + index);
        }
    }

    public List<Tour> getTours() {
        return tours;
    }

    public void displayWishlist(){
        System.out.println();

        TablePrinter tablePrinter = new TablePrinter();
        tablePrinter.print(tours);
    }

    public void saveToJSON(String fileName){
        JSONWriter.saveToJSON(tours, fileName);
    }
}
