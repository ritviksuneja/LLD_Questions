package com.example;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final List<Subscription> subscriptions;

    public Customer(){
        subscriptions = new ArrayList<>();
    }

    public void addSubscription(Subscription subscription){
        subscriptions.add(subscription);
    }

    public void removeSubscription(Subscription subscription){
        subscriptions.remove(subscription);
    }

    public List<Subscription> getSubscriptions(){
        return subscriptions;
    }
}
