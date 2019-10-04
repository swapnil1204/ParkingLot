package com.thoughtworks.consumer;

import com.thoughtworks.ParkingLotException;
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
        } catch (ParkingLotException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        SanjayScenerioOne sanjayScenerioOne = new SanjayScenerioOne();
        Owner owner = new Owner();
        sanjayScenerioOne.park(new ParkingLot(2,owner), new ParkingLot(3,owner));
    }
}
