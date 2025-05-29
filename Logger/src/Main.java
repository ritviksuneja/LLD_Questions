import java.util.List;

public class Main {
    public static void main(String[] args) {
        LogFormatter formatter = new DefaultFormatter();
        LogAppender consoleAppender = new ConsoleAppender(formatter);
        LogAppender fileAppender = new FileAppender(formatter, "logs.txt");

        // Start with only console logging
        LoggerConfig config = new LoggerConfig(LogLevel.DEBUG, List.of(consoleAppender));
        Logger logger = Logger.getInstance(config);

        logger.info("Only logging to console now.");

        // Dynamically add file logging
        config.addAppender(fileAppender);
        logger.warn("Now logging to both console and file.");

        // Dynamically remove console logging
        config.removeAppender(consoleAppender);
        logger.error("Now logging only to file.");
    }
}
