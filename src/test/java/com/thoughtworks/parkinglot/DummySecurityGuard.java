package com.thoughtworks.parkinglot;

public class DummySecurityGuard implements Subscriber{

    int numberOfTimesNotified = 0;
    int numberOfTimesNotifiedWhenSpaceAvailable = 0;

    @Override
    public void gotNotificationWhenSpaceIsFull() {
        numberOfTimesNotified++;
    }

    @Override
    public void gotNotificationWhenSpaceAvailable() {
        numberOfTimesNotifiedWhenSpaceAvailable++;
    }
}
