package com.thoughtworks.parkinglot;

import com.thoughtworks.ParkingLotException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private Owner owner;
    private int capacity;
    private int spaceAvailable;

    List<Object> vehicles = new ArrayList<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.spaceAvailable = capacity;
        this.owner = null;
    }

    public ParkingLot(int capacity, Owner owner) {
        this.capacity = capacity;
        this.spaceAvailable = capacity;
        this.owner = owner;
    }

    public boolean park(Object object) throws ParkingLotException {
        if (spaceAvailable > 0) {
            if (vehicles.contains(object)) {
                throw new ParkingLotException("You cannot park same vehicle");
            }
            vehicles.add(object);
            if (vehicles.size() == capacity) {
                if (owner != null) {
                    owner.setNotification("parking lot is full");
                }
            }
            spaceAvailable--;
            return true;
        }
        throw new ParkingLotException("parking lot is full");
    }


    @Override
    public String toString() {
        return "ParkingLot{" +
                "capacity=" + capacity +
                '}';
    }

    public Object unPark(Object car) throws ParkingLotException {
        if (vehicles.contains(car)) {
            return vehicles.remove(vehicles.indexOf(car));
        }
        throw new ParkingLotException("the car may not be parked here");
    }

}
