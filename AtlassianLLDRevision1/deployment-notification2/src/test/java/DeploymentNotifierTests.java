

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.DeploymentNotifier;
import com.example.Event;
import com.example.EventStatus;
import com.example.Notification;
import com.example.SendBatchNotifications;

public class DeploymentNotifierTests {
    private DeploymentNotifier deploymentNotifier;
    private SendBatchNotifications notifier;

    @BeforeEach
    public void setup(){
        this.notifier = Mockito.mock(SendBatchNotifications.class);
        this.deploymentNotifier = new DeploymentNotifier(notifier);
    }

    @Test
    public void testSuccessfulNotifications(){
        Event e1 = new Event(EventStatus.COMPLETED, "v1");
        e1.addAuthor("aman");
        e1.addAuthor("biswa");
        Event e2 = new Event(EventStatus.COMPLETED, "v1");
        e2.addAuthor("aman");
        deploymentNotifier.receiveEvent(e1);
        List<Notification> batch = deploymentNotifier.getBatch();
        assertEquals(2, batch.size());
        Event e3= new Event(EventStatus.COMPLETED, "v1");
        e3.addAuthor("aman");
        deploymentNotifier.sendBatchEvents();
        deploymentNotifier.receiveEvent(e3);
        deploymentNotifier.sendBatchEvents();
        Mockito.verify(notifier, Mockito.times(2))
       .sendBatchNotifications(Mockito.anyList());
    }

    @Test
    public void testSuccessfulNotifications1() {
        // Prepare events
        Event e1 = new Event(EventStatus.COMPLETED, "v1");
        e1.addAuthor("aman");
        e1.addAuthor("biswa");

        Event e2 = new Event(EventStatus.COMPLETED, "v1");
        e2.addAuthor("aman");

        // Receive events
        deploymentNotifier.receiveEvent(e1);
        deploymentNotifier.receiveEvent(e2);

        // Verify batch size after receiving events
        List<Notification> batch = deploymentNotifier.getBatch();
        assertEquals(2, batch.size()); // 2 authors from e1 + 1 author from e2

        // Send batch notifications twice
        deploymentNotifier.sendBatchEvents();
        deploymentNotifier.sendBatchEvents();

        // Receive another event and send batch again
        Event e3 = new Event(EventStatus.COMPLETED, "v1");
        e3.addAuthor("aman");
        deploymentNotifier.receiveEvent(e3);
        deploymentNotifier.sendBatchEvents();

        // Verify that the notifier's sendBatchNotifications was called exactly 3 times
        Mockito.verify(notifier, Mockito.times(3))
               .sendBatchNotifications(Mockito.anyList());
    }
}
