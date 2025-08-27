import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.example.DeploymentNotification;
import com.example.DeploymentStatus;
import com.example.Developer;
import com.example.Event;
import com.example.INotificationSender;
import com.example.NotificationService;

public class NotificationServiceTests {
    private NotificationService notificationService;
    private INotificationSender notificationSender;
    private Developer alice, bob;

    @BeforeEach
    public void setup(){
        notificationSender = mock(INotificationSender.class);
        notificationService = new NotificationService(notificationSender);
        alice = new Developer("1", "Alice");
        bob = new Developer("2", "Bob");
    }

    @Test
    void testCompletedEventAddsNotification() {
        Event event = new Event("v1", DeploymentStatus.COMPLETED);
        event.addAuthor(alice);
        event.addAuthor(bob);

        notificationService.receiveEvent(event);

        List<DeploymentNotification> batch = notificationService.getBatch();
        assert batch.size() == 2;
    }

    @Test
    void testDuplicateDevelopersNotNotifiedTwice() {
        Event event1 = new Event("v1", DeploymentStatus.COMPLETED);
        event1.addAuthor(alice);
        Event event2 = new Event("v1", DeploymentStatus.COMPLETED);
        event2.addAuthor(alice);
        

        notificationService.receiveEvent(event1);
        notificationService.receiveEvent(event2);

        List<DeploymentNotification> batch = notificationService.getBatch();
        assert batch.size() == 1; // Alice should only appear once
    }

    @Test
    void testSendNotificationsClearsBatch(){
        Event event = new Event("v1", DeploymentStatus.COMPLETED);
        event.addAuthor(alice);

        notificationService.receiveEvent(event);
        notificationService.sendNotifications();

        verify(notificationSender, times(1)).send(anyList());
        assert notificationService.getBatch().isEmpty();
    }
}
