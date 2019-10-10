package com.thoughtworks.attendent;

import com.thoughtworks.ParkingLotFullException;
import com.thoughtworks.SameVehicleIsAlreadyParkedException;
import com.thoughtworks.parkinglot.ParkingLot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ParkingLotWithMaximumFreeSpace implements ParkingStrategy {

    private final Comparator comparatorType;
    private List<ParkingLot> parkingLots;

    public ParkingLotWithMaximumFreeSpace(List<ParkingLot> parkingLots, Comparator comparatorType) {
        this.parkingLots = new ArrayList<>(parkingLots);
        this.comparatorType = comparatorType;
    }

    @Override
    public void park(Object object) throws ParkingLotFullException, SameVehicleIsAlreadyParkedException {
        ParkingLot parkingLot =  parkingLots.get(0);
        parkingLot.park(object);
    }
}
