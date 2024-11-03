package other;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Tour;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class JSONWriter {
    private static Logger logger = Logger.getLogger(JSONWriter.class.getName());


    public static void saveToJSON(List<Tour> tours, String fileName){
        fileName = "src/main/resources/" + fileName;

        logger.info("Writing tours to " + fileName + "...");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(tours, writer);
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }

        logger.info("Finished writing tours to " + fileName);
    }
}
