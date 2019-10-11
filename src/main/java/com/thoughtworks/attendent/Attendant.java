package com.thoughtworks.attendent;

import com.thoughtworks.parkinglot.ParkingLot;

import java.util.ArrayList;
import java.util.List;

public class Attendant {
    private ParkingStrategy parkingStrategy;
    private List<ParkingLot> parkingLots;

    public Attendant(ParkingStrategy parkingStrategy, List<ParkingLot> parkingLots) {
        this.parkingStrategy = parkingStrategy;
        this.parkingLots = new ArrayList<>(parkingLots);
    }

    public void park(Object object) throws Exception {
        parkingStrategy.park(parkingLots, object);
    }
}
