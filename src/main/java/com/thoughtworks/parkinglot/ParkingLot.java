package com.thoughtworks.parkinglot;

import com.thoughtworks.SameVehicleIsAlreadyParkedException;
import com.thoughtworks.ParkingLotFullException;
import com.thoughtworks.CarNotParkedHereException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private Subscriber subscriber;
    private int capacity;

    List<Object> vehicles = new ArrayList<>();

    public ParkingLot(int capacity, Subscriber subscriber) {
        this.capacity = capacity;
        this.subscriber = subscriber;
    }

    public void park(Object object) throws SameVehicleIsAlreadyParkedException, ParkingLotFullException {
        if (!(vehicles.size() < capacity)) {
            throw new ParkingLotFullException();
        }
        if (isVehicleAvailable(object)) {
            throw new SameVehicleIsAlreadyParkedException();
        }
        vehicles.add(object);
        if (isFull()) {
            subscriber.gotNotificationWhenSpaceIsFull();
        }
    }


    private boolean isVehicleAvailable(Object object) {
        return vehicles.contains(object);
    }

    private boolean isFull() {
        return vehicles.size() == capacity;
    }


    @Override
    public String toString() {
        return "ParkingLot{" +
                "capacity=" + capacity +
                '}';
    }

    public Object unPark(Object car) throws CarNotParkedHereException {
        if (isVehicleAvailable(car)) {
            if (!isFull()) {
                vehicles.remove(car);
                return car;
            }
            vehicles.remove(car);
            subscriber.gotNotificationWhenSpaceAvailable();
            return car;
        }
        throw new CarNotParkedHereException();
    }
}
