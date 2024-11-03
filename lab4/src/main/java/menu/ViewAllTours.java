package menu;

import model.Tour;
import other.TablePrinter;

import java.util.List;
import java.util.logging.Logger;

public class ViewAllTours implements MenuCommand {
    private static Logger logger = Logger.getLogger(ViewAllTours.class.getName());
    private List<Tour> tours;

    public ViewAllTours(List<Tour> tours) {
        this.tours = tours;
    }

    @Override
    public void execute(){
        logger.info("Executing ViewAllTours command...");
        System.out.println();

        TablePrinter tablePrinter = new TablePrinter();
        tablePrinter.print(tours);
        logger.info("Finished executing ViewAllTours command...");
    }
}
