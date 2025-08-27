package com.example;

import java.util.List;

public class ConsoleNotifier implements SendBatchNotifications{

    @Override
    public void sendBatchNotifications(List<Notification> batch) {
        for(Notification notif : batch){
            String author = notif.getAuthor();
            String version = notif.getVersion();

            System.out.println("Hi, developer '" + author + "'', you changes are successfully deployed to version + '" + version + "'.");
        }
    }
}
