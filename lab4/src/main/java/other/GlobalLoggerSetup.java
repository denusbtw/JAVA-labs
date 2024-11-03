package other;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;


public class GlobalLoggerSetup {

    private static String getNextLogFileName(){
        File logsDir = new File("logs");
        if (!logsDir.exists()) {
            logsDir.mkdirs();
        }

        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        return new File(logsDir, "app_" + timestamp + ".log").getAbsolutePath();
    }

    public static void setupGlobalLogging() {
        Logger rootLogger = Logger.getLogger("");

        Handler[] handlers = rootLogger.getHandlers();
        for (Handler handler : handlers) {
            if (handler instanceof ConsoleHandler) {
                rootLogger.removeHandler(handler);
            }
        }

        try {
            String logFileName = getNextLogFileName();

            FileHandler fileHandler = new FileHandler(logFileName,true);
            fileHandler.setFormatter(new SimpleFormatter());
            rootLogger.addHandler(fileHandler);

            rootLogger.info("Logging to file: " + logFileName);
        } catch (IOException e) {
            rootLogger.severe("Failed to set up file logging: " + e.getMessage());
        }
    }
}
