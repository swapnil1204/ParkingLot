package com.thoughtworks.attendent;

import com.thoughtworks.AllParkingLotsAreFull;
import com.thoughtworks.ParkingLotFullException;
import com.thoughtworks.SameVehicleIsAlreadyParkedException;
import com.thoughtworks.parkinglot.ParkingLot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotWithMaximumCapacity implements ParkingStrategy {

    private List<ParkingLot> parkingLots;

    public ParkingLotWithMaximumCapacity(List<ParkingLot> parkingLots) {
        this.parkingLots = new ArrayList<>(parkingLots);
    }

    @Override
    public void park(Object object) throws SameVehicleIsAlreadyParkedException, AllParkingLotsAreFull {
        parkingLots.sort(ParkingLot.capacityComparator.reversed());
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

