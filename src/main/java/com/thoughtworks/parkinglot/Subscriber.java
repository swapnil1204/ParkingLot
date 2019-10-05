package com.thoughtworks.parkinglot;

public interface Subscriber {
    void gotNotificationWhenSpaceIsFull();

    void gotNotificationWhenSpaceAvailable();
}

