package fundamental;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {
    private static MyLogger instance = null;
    private final Logger logger;

    private MyLogger() {

        try {
            logger = Logger.getLogger("MyLogger");

            // Get current date and time
            LocalDateTime now = LocalDateTime.now();

            // Create directory for logs if it doesn't exist
            Path logsDirectoryPath = Paths.get("Logs");
            if (!Files.exists(logsDirectoryPath)) {
                Files.createDirectory(logsDirectoryPath);
            }

            // Create directory for today's logs if it doesn't exist
            String todayDirectoryName = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Path todayDirectoryPath = Paths.get("Logs", todayDirectoryName);
            if (!Files.exists(todayDirectoryPath)) {
                Files.createDirectory(todayDirectoryPath);
            }

            // Create file handler for log file
            String hour = now.format(DateTimeFormatter.ofPattern("HH"));
            String logFileName = String.format("Log_%s.txt", hour);
            Path logFilePath = Paths.get("Logs", todayDirectoryName, logFileName);
            FileHandler fileHandler = new FileHandler(logFilePath.toString(), true);

            // Set formatter for file handler
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);

            // Add file handler to logger
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized static MyLogger getInstance() {
        if (instance == null) {
            instance = new MyLogger();
        }
        return instance;
    }

    public void logInfo(String message) {
        logger.info(message);
    }

    public void logWarning(String message) {
        logger.warning(message);
    }

    public void logError(String message) {
        logger.severe(message);
    }

}
