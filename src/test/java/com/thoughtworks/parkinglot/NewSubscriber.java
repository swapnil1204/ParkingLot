package com.thoughtworks.parkinglot;

public class NewSubscriber implements Subscriber{
    int numberOfTimesNotifiedWhenParkingLotIsFull = 0;
    int numberOfTimesNotifiedWhenSpaceAvailable = 0;

    @Override
    public void gotNotificationWhenSpaceIsFull() {
        numberOfTimesNotifiedWhenParkingLotIsFull++;
    }

    @Override
    public void gotNotificationWhenSpaceAvailable() {
        numberOfTimesNotifiedWhenSpaceAvailable++;
    }
}
