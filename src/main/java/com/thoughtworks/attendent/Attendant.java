package com.thoughtworks.attendent;

public class Attendant {
    private ParkingStrategy parkingStrategy;

    public Attendant(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public void park(Object object) throws Exception {
        parkingStrategy.park(object);
    }
}
