package com.thoughtworks.parkinglot;

import com.thoughtworks.SameVehicleIsAlreadyParkedException;
import com.thoughtworks.ParkingLotFullException;
import com.thoughtworks.CarNotParkedHereException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private Owner owner;
    private int capacity;
    private int spaceAvailable;

    List<Object> vehicles = new ArrayList<>();

    public ParkingLot(int capacity, Owner owner) {
        this.capacity = capacity;
        this.spaceAvailable = capacity;
        this.owner = owner;
    }

    public boolean park(Object object) throws SameVehicleIsAlreadyParkedException, ParkingLotFullException {
        if (spaceAvailable > 0) {
            if (isVehicleAvailable(object)) {
                throw new SameVehicleIsAlreadyParkedException();
            }
            vehicles.add(object);
            if (isFull()) {
                owner.gotNotification();
            }
            spaceAvailable--;
            return true;
        }
        throw new ParkingLotFullException();
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
            return vehicles.remove(vehicles.indexOf(car));
        }
        throw new CarNotParkedHereException();
    }
}
