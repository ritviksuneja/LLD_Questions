import java.util.concurrent.atomic.AtomicInteger;

public class GetUniqueId {
    private static final AtomicInteger id = new AtomicInteger(1);
    
    public static int getUniqueId(){
        return id.getAndIncrement();
    }
}
