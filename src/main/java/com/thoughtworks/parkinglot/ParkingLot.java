package com.thoughtworks.parkinglot;

import com.thoughtworks.SameVehicleIsAlreadyParkedException;
import com.thoughtworks.ParkingLotFullException;
import com.thoughtworks.CarNotParkedHereException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private int capacity;
    private List<Subscriber> subscribers;

    private List<Object> vehicles = new ArrayList<>();

    public ParkingLot(int capacity, List<Subscriber> subscribers) {
        this.capacity = capacity;
        this.subscribers = subscribers;
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
            for (Subscriber subscriber : subscribers) {
                subscriber.gotNotificationWhenSpaceIsFull();
            }
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
            for (Subscriber subscriber : subscribers) subscriber.gotNotificationWhenSpaceAvailable();
            return car;
        }
        throw new CarNotParkedHereException();
    }
}
