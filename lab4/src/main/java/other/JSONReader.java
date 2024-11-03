package other;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Tour;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class JSONReader {
    private static Logger logger = Logger.getLogger(JSONReader.class.getName());

    public static List<Tour> readToursFromJSON(String fileName){
        fileName = "src/main/resources/" + fileName;

        logger.info("Reading tours from " + fileName + "...");

        List<Tour> tours = new ArrayList<>();

        Gson gson = new Gson();
        try (FileReader reader = new FileReader(fileName)) {
            Type tourListType = new TypeToken<List<Tour>>() {}.getType();

            tours = gson.fromJson(reader, tourListType);
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }

        logger.info("Finished reading tours from " + fileName);
        return tours;
    }
}
