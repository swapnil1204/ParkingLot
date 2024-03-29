package com.thoughtworks.subscriber;

import com.thoughtworks.Exceptions.ParkingLotFullException;
import com.thoughtworks.Exceptions.SameVehicleIsAlreadyParkedException;
import com.thoughtworks.parkinglot.ParkingLot;

public class SanjayScenarioTwo{

    public void park(ParkingLot parkingLotOne, ParkingLot parkingLotTwo) {
        Object carOne = new Object();
        try {
            parkingLotOne.park(carOne);
            parkingLotOne.park(carOne);
        } catch (SameVehicleIsAlreadyParkedException | ParkingLotFullException e) {
            System.out.println(e);
        }
    }

//    public static void main(String[] args) {
//        SanjayScenarioTwo sanjayScenarioTwo = new SanjayScenarioTwo();
//        Owner owner = new Owner();
//        sanjayScenarioTwo.park(new ParkingLot(2,owner), new ParkingLot(3,owner));
//    }
}
