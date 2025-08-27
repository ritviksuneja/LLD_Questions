package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NotificationService implements IReceiveEvent, ISendBatchNotifications{
    private final INotificationSender notificationSender;
    private final List<DeploymentNotification> batch;
    private final Map<String, Set<Developer>> versionDevelopers;

    public NotificationService(INotificationSender notificationSender) {
        this.notificationSender = notificationSender;
        this.batch = new ArrayList<>();
        this.versionDevelopers = new HashMap<>();
    }

    @Override
    public void receiveEvent(Event event) {
        String version = event.getVersion();
        List<Developer> authors = event.getAuthors();
        DeploymentStatus status = event.getStatus();

        if(!versionDevelopers.containsKey(version)){
            versionDevelopers.put(version, new HashSet<>());
        }

        //create the notification and add it in the batch if the developer is not in the map
        if(status == DeploymentStatus.COMPLETED){
            for(Developer author : authors){
                if(!versionDevelopers.get(version).contains(author)){
                    versionDevelopers.get(version).add(author);
                    DeploymentNotification notification = new DeploymentNotification(author, version);
                    batch.add(notification);
                }
            }
        }
    }

    @Override
    public void sendNotifications() {
        notificationSender.send(batch);
        batch.clear();
    }

    public List<DeploymentNotification> getBatch() {
        return List.copyOf(batch);
    }
}
