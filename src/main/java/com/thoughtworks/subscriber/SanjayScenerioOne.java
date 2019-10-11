package com.thoughtworks.subscriber;

import com.thoughtworks.Exceptions.ParkingLotFullException;
import com.thoughtworks.Exceptions.SameVehicleIsAlreadyParkedException;
import com.thoughtworks.parkinglot.*;

public class SanjayScenerioOne{
    public void park(ParkingLot parkingLotOne, ParkingLot parkingLotTwo) {
        Object carOne = new Object();
        Object carTwo = new Object();
        Object carThree = new Object();

        try {
            parkingLotOne.park(carOne);
            parkingLotOne.park(carTwo);
            parkingLotTwo.park(carThree);
        } catch (SameVehicleIsAlreadyParkedException | ParkingLotFullException e) {
            System.out.println(e);
        }
    }

//    public static void main(String[] args) {
//        SanjayScenerioOne sanjayScenerioOne = new SanjayScenerioOne();
//        DummyOwner owner = new DummyOwner();
//        sanjayScenerioOne.park(new ParkingLot(2,owner), new ParkingLot(3,owner));
//    }
}
