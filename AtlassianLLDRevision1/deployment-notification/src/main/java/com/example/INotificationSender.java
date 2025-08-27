package com.example;

import java.util.List;

public interface INotificationSender {
    public void send(List<DeploymentNotification> notifications);
}
