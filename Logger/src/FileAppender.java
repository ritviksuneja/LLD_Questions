import java.io.FileWriter;
import java.io.IOException;

public class FileAppender implements LogAppender{

    private final LogFormatter formatter;
    private final String filePath;

    public FileAppender(LogFormatter formatter, String filePath){
        this.formatter = formatter;
        this.filePath = filePath;
    }

    @Override
    public synchronized void append(LogMessage message) {
        String msg = formatter.formatMessage(message);
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(msg + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Failed to write log message: " + e.getMessage());
        }

    }
}
