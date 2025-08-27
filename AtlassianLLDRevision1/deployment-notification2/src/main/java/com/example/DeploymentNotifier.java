package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// DeploymentNotifier implements ReceiveEvent:
//     -batchNotifier: SendBatchNotifications
//     -batch: List<Notification>
//     -developer: Map<String, Developer> //developerId
//     -developerInBatchMap: Map<String, Set<String>> //version, Set<developerId>
//     +addDeveloper()
//     +receiveEvent(Event event): void
//    +sendBatchNotifications(): void

public class DeploymentNotifier implements ReceiveEvent{

    private final SendBatchNotifications batchNotifier;
    private final List<Notification> batch;
    private final Map<String, Set<String>> developerInBatchMap; //version, Set<developerId>

    public DeploymentNotifier(SendBatchNotifications notifier){
        this.batchNotifier = notifier;
        this.batch = new ArrayList<>();
        this.developerInBatchMap = new HashMap<>();
    }

    @Override
    public void receiveEvent(Event event) {
        List<String> developers = event.getAuthors();
        String version = event.getVersion();

        if(event.getStatus() == EventStatus.COMPLETED){
            for(String dev : developers){
                if(!developerInBatchMap.containsKey(version)){
                    developerInBatchMap.put(version, new HashSet<>());
                }

                if(developerInBatchMap.get(version).contains(dev)){
                    continue;
                }

                batch.add(new Notification(dev, version));
                developerInBatchMap.get(version).add(dev);
            }
        }
    }

    public void sendBatchEvents(){
        batchNotifier.sendBatchNotifications(batch);
        batch.clear();
        developerInBatchMap.clear();
    }

    public List<Notification> getBatch(){
        return batch;
    }
}
