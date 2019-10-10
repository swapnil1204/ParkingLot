package com.thoughtworks.attendent;

import com.thoughtworks.AllParkingLotsAreFull;
import com.thoughtworks.ParkingLotFullException;
import com.thoughtworks.SameVehicleIsAlreadyParkedException;
import com.thoughtworks.parkinglot.ParkingLot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ParkingLotWithMaximumCapacity implements ParkingStrategy {

    private final Comparator comparatorType;
    private List<ParkingLot> parkingLots;

    public ParkingLotWithMaximumCapacity(List<ParkingLot> parkingLots, Comparator comparatorType) {
        this.parkingLots = new ArrayList<>(parkingLots);
        this.comparatorType = comparatorType;
    }

    @Override
    public void park(Object object) throws SameVehicleIsAlreadyParkedException, AllParkingLotsAreFull {
        parkingLots.sort(comparatorType.reversed());
        for (ParkingLot parkingLot : parkingLots) {
            try {
                parkingLot.park(object);
                return;
            } catch (ParkingLotFullException ignored) {
            }
        }
        throw new AllParkingLotsAreFull();
    }
}

