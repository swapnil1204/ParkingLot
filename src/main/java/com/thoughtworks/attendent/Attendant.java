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
        ParkingLot parkingLotOne = parkingLots.get(0);
        parkingLotOne.park(object);
    }
}

