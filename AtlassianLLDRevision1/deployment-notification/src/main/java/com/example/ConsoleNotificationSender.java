package com.example;

import java.util.List;

public class ConsoleNotificationSender implements INotificationSender {

    @Override
    public void send(List<DeploymentNotification> notifications) {
        for(DeploymentNotification notification : notifications){
            Developer author = notification.getAuthor();
            String version = notification.getVersion();

            System.out.println("Hi Author '" + author.getName() +"', your change got deployed in version '" + version + "'.");
        }
    }

}
