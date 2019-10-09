package com.thoughtworks.attendent;

import com.thoughtworks.AllParkingLotsAreFull;
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
    }

    public void park(Object object) throws SameVehicleIsAlreadyParkedException, AllParkingLotsAreFull {
        parkingLots.sort(Collections.reverseOrder());

        for (ParkingLot parkingLot : parkingLots) {
            try {
                parkingLot.park(object);
                return;
            } catch (ParkingLotFullException e) {
               e.printStackTrace();
                //continue;
            }
        }
        throw new AllParkingLotsAreFull();
    }
}

