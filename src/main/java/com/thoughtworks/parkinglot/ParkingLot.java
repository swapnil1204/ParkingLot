package com.thoughtworks.parkinglot;

import com.thoughtworks.ParkingLotException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private int capacity;
    private int spaceAvailable;
    List<Object> vehicles = new ArrayList<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.spaceAvailable = capacity;
    }

    public boolean park(Object object) throws ParkingLotException {
        if (spaceAvailable > 0) {
            if (vehicles.contains(object)) {
                throw new ParkingLotException("You cannot park same vehicle");
            }
            vehicles.add(object);
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
            vehicles.remove(car);
            return car;
        }
        if (vehicles.isEmpty()) {
            throw new ParkingLotException("the parking lot has no car");
        }
        throw new ParkingLotException("the car may not be parked here");
    }

}
