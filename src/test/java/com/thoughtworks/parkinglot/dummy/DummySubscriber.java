package com.thoughtworks.parkinglot.dummy;

import com.thoughtworks.parkinglot.Subscriber;

public class DummySubscriber implements Subscriber {

    public int numberOfTimesNotifiedWhenParkingLotIsFull = 0;
    public int numberOfTimesNotifiedWhenSpaceAvailable = 0;


    @Override
    public void gotNotificationWhenSpaceIsFull() {
        numberOfTimesNotifiedWhenParkingLotIsFull++;
    }

    @Override
    public void gotNotificationWhenSpaceAvailable() {
        numberOfTimesNotifiedWhenSpaceAvailable++;
    }
}
