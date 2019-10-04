package com.thoughtworks.parkinglot;

import com.thoughtworks.ParkingLotFullException;
import com.thoughtworks.SameVehicleIsAlreadyParkedException;
import com.thoughtworks.CarNotParkedHereException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class DummyOwner extends Owner {
    public boolean wasCalled = false;

    @Override
    public void gotNotification() {
        wasCalled = true;
    }
}

public class ParkingLotExceptionTest {

    private static Owner owner;

    @BeforeAll
    static void setup() {
        owner = new Owner();
    }

    @Test
    void givenParkingLotHasCapacity_WhenPark_ThenShouldPark() throws SameVehicleIsAlreadyParkedException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(1, owner); //this represent available lots

        assertTrue(parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotIsFull_WhenPark_ThenShouldNotPark() throws ParkingLotFullException, SameVehicleIsAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(1, owner); // spaceAvailable = 1
        parkingLot.park(new Object()); // spaceAvailable - -

        ParkingLotFullException exception = assertThrows(ParkingLotFullException.class, () -> {
            parkingLot.park(new Object());

        });
    }

    @Test
    void givenParkingLot_WhenParkingSameObjects_ThenShouldNotBeParked() throws SameVehicleIsAlreadyParkedException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(2, owner);

        Object object = new Object();
        parkingLot.park(object);
        SameVehicleIsAlreadyParkedException exception = assertThrows(SameVehicleIsAlreadyParkedException.class, () -> {
            parkingLot.park(object);
        });

    }

    @Test
    void givenParkingLot_WhenUnParkAlreadyParkedCar_thenShouldBeAbleToUnPark() throws SameVehicleIsAlreadyParkedException, ParkingLotFullException, CarNotParkedHereException {
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object alreadyParkedCar = new Object();
        parkingLot.park(alreadyParkedCar);

        assertEquals(alreadyParkedCar, parkingLot.unPark(alreadyParkedCar));
    }

    @Test
    void givenParkingLot_WhenUnParkCarWhichIsNotParked_thenShouldNotBeAbleToUnPark() throws SameVehicleIsAlreadyParkedException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object CarWhichIsNotParkedIn = new Object();
        Object CarWhichIsParked = new Object();
        parkingLot.park(CarWhichIsParked);

        CarNotParkedHereException exception = assertThrows(CarNotParkedHereException.class, () -> {
            parkingLot.unPark(CarWhichIsNotParkedIn);
        });
    }

    @Test
    void givenParkingLot_WhenUnParkTwoCars_thenShouldBeAbleToUnPark() throws SameVehicleIsAlreadyParkedException, ParkingLotFullException, CarNotParkedHereException {
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object carOne = new Object();
        Object carTwo = new Object();
        parkingLot.park(carOne);
        parkingLot.park(carTwo);

        assertEquals(carOne, parkingLot.unPark(carOne));
        assertEquals(carTwo, parkingLot.unPark(carTwo));
    }

    @Test
    void givenParkingLot_WhenParkingLotIsFull_thenShouldNotifyToOwner() throws SameVehicleIsAlreadyParkedException, ParkingLotFullException {
        DummyOwner dummyOwner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2, dummyOwner);

        Object carOne = new Object();
        Object carTwo = new Object();
        parkingLot.park(carOne);
        parkingLot.park(carTwo);

        assertTrue(dummyOwner.wasCalled);
    }
}
