package com.thoughtworks.parkinglot;

import com.thoughtworks.ParkingLotException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotExceptionTest<owner, Static> {

    private static Owner owner;

    @BeforeAll
    static void setup() {
        owner = new Owner();
    }

    @Test
    void givenParkingLotHasCapacity_WhenPark_ThenShouldPark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(1, owner); //this represent available lots

        assertTrue(parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotIsFull_WhenPark_ThenShouldNotPark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(1, owner); // spaceAvailable = 1
        parkingLot.park(new Object()); // spaceAvailable - -

        ParkingLotException exception = assertThrows(ParkingLotException.class, () -> {
            parkingLot.park(new Object());

        });
        assertEquals("parking lot is full", exception.getMessage());
    }

    @Test
    void givenParkingLot_WhenParkingSameObjects_ThenShouldNotBeParked() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2, owner);

        Object object = new Object();
        parkingLot.park(object);
        ParkingLotException exception = assertThrows(ParkingLotException.class, () -> {
            parkingLot.park(object);

        });

        assertEquals("You cannot park same vehicle", exception.getMessage());
    }

    @Test
    void givenParkingLot_WhenUnParkAlreadyParkedCar_thenShouldBeAbleToUnPark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object alreadyParkedCar = new Object();
        parkingLot.park(alreadyParkedCar);

        assertEquals(alreadyParkedCar, parkingLot.unPark(alreadyParkedCar));
    }

    @Test
    void givenParkingLot_WhenUnParkCarWhichIsNotParked_thenShouldNotBeAbleToUnPark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object CarWhichIsNotParkedIn = new Object();
        Object CarWhichIsParked = new Object();
        parkingLot.park(CarWhichIsParked);

        ParkingLotException exception = assertThrows(ParkingLotException.class, () -> {
            parkingLot.unPark(CarWhichIsNotParkedIn);
        });

        assertEquals("the car may not be parked here", exception.getMessage());
    }

    @Test
    void givenParkingLot_WhenUnParkTwoCars_thenShouldBeAbleToUnPark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object carOne = new Object();
        Object carTwo = new Object();
        parkingLot.park(carOne);
        parkingLot.park(carTwo);

        assertEquals(carOne, parkingLot.unPark(carOne));
        assertEquals(carTwo, parkingLot.unPark(carTwo));
    }

    @Test
    void givenParkingLot_WhenParkingLotIsFull_thenShouldNotifyToOwner() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2, owner);

        Object carOne = new Object();
        Object carTwo = new Object();
        parkingLot.park(carOne);
        parkingLot.park(carTwo);

        assertEquals("parking lot is full", owner.getNotification());
    }
}
