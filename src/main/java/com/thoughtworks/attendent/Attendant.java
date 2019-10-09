package com.thoughtworks.attendent;

import com.thoughtworks.ParkingLotFullException;
import com.thoughtworks.SameVehicleIsAlreadyParkedException;
import com.thoughtworks.parkinglot.ParkingLot;

import java.util.List;

public class Attendant {

    private List<ParkingLot> parkingLots;

    public Attendant(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public void park(Object object) throws ParkingLotFullException, SameVehicleIsAlreadyParkedException {
        int temp = 0;
        int index = 0;
        for (int i = 0; parkingLots.size() > i; i++) {
            int capacity = parkingLots.get(i).getCapacity();
            if (capacity > temp) {
                temp = capacity;
                index = i;
            }
        }
        ParkingLot parkingVehicleInMaxCapacityLot = parkingLots.get(index);
        parkingVehicleInMaxCapacityLot.park(object);
    }


}

