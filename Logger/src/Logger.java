public class Logger {
    private static volatile Logger instance;
    private static final Object lock = new Object();

    private final LoggerConfig config;

    private Logger(LoggerConfig config) {
        this.config = config;
    }

    @SuppressWarnings("DoubleCheckedLocking")
    public static Logger getInstance(LoggerConfig config) {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new Logger(config);
                }
            }
        }
        return instance;
    }

    private void log(LogLevel level, String message) {
        if (!level.shouldLog(config.getLogLevel())) return;

        LogMessage msg = new LogMessage(level, message);
        for (LogAppender appender : config.getAppenders()) {
            appender.append(msg);
        }
    }

    public void debug(String message) { log(LogLevel.DEBUG, message); }
    public void info(String message)  { log(LogLevel.INFO, message); }
    public void warn(String message)  { log(LogLevel.WARN, message); }
    public void error(String message) { log(LogLevel.ERROR, message); }
}
