package com.thoughtworks.attendent;

import com.thoughtworks.ParkingLotFullException;
import com.thoughtworks.SameVehicleIsAlreadyParkedException;
import com.thoughtworks.parkinglot.ParkingLot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Attendant {

    private List<ParkingLot> parkingLots;

    public Attendant(List<ParkingLot> parkingLots) {
        this.parkingLots = new ArrayList<>(parkingLots);
        //this.parkingLots = parkingLots;
    }

    public void park(Object object) throws ParkingLotFullException, SameVehicleIsAlreadyParkedException {
        Collections.sort(parkingLots);
        try {
            ParkingLot parkingLotWithMaximumCapacity = parkingLots.get(parkingLots.size() - 1);
            parkingLotWithMaximumCapacity.park(object);
        }
        catch (Exception e){
            ParkingLot parkingLotWithMaximumCapacity = parkingLots.get(parkingLots.size() - 2);
            parkingLotWithMaximumCapacity.park(object);
        }
    }
}

