import java.text.SimpleDateFormat;
import java.util.Date;

public class DefaultFormatter implements LogFormatter{
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    
    @Override
    public String formatMessage(LogMessage msg) {
        return String.format("[%s] [%s] [%s] - %s",
                sdf.format(new Date(msg.getTimestamp())),
                msg.getThreadName(),
                msg.getLevel(),
                msg.getMessage());
    }
}