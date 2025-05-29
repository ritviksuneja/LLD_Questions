import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class LoggerConfig {
    private final List<LogAppender> appenders = new CopyOnWriteArrayList<>();
    private volatile LogLevel logLevel;

    public LoggerConfig(LogLevel logLevel, List<LogAppender> initialAppenders) {
        this.logLevel = logLevel;
        if (initialAppenders != null) {
            this.appenders.addAll(initialAppenders);
        }
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public List<LogAppender> getAppenders() {
        return appenders;
    }

    public void addAppender(LogAppender appender) {
        appenders.add(appender);
    }

    public void removeAppender(LogAppender appender) {
        appenders.remove(appender);
    }
}