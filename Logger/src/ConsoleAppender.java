public class ConsoleAppender implements LogAppender{

    private final LogFormatter formatter;

    public ConsoleAppender(LogFormatter formatter){
        this.formatter = formatter;
    }

    @Override
    public synchronized  void append(LogMessage message) {
        System.out.println(formatter.formatMessage(message)); 
    }
}
