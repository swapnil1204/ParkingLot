package com.thoughtworks.attendent;

import com.thoughtworks.Exceptions.AllParkingLotsAreFull;
import com.thoughtworks.Exceptions.ParkingLotFullException;
import com.thoughtworks.Exceptions.SameVehicleIsAlreadyParkedException;
import com.thoughtworks.parkinglot.ParkingLot;

import java.util.List;

public class ParkingLotWithMaximumFreeSpace implements ParkingStrategy {

    @Override
    public void park(List<ParkingLot> parkingLots, Object object) throws SameVehicleIsAlreadyParkedException, AllParkingLotsAreFull {
        parkingLots.sort(ParkingLot.freeSpaceComparator.reversed());
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
