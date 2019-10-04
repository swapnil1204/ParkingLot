package com.thoughtworks.parkinglot;

import com.thoughtworks.ParkingLotFullException;
import com.thoughtworks.SameVehicleIsAlreadyParkedException;
import com.thoughtworks.CarNotParkedHereException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DummyOwner extends Owner {
    public boolean wasCalled = false;
    public boolean wasCalledWhenSpaceAgainAvailable = false;
    int numberOfTimesNotified = 0;
    int numberOfTimesNotifiedWhenSpaceAvailable = 0;

    @Override
    public void gotNotification() {
        wasCalled = true;
        numberOfTimesNotified++;
    }

    @Override
    public void gotNotificationWhenSpaceAvailable() {
        wasCalledWhenSpaceAgainAvailable = true;
        numberOfTimesNotifiedWhenSpaceAvailable++;
    }
}

public class ParkingLotExceptionTest {

    private static DummyOwner dummyOwner;

    @BeforeAll
    static void setup() {
        dummyOwner = new DummyOwner();
    }

    @Test
    void givenParkingLotHasCapacity_WhenPark_ThenShouldPark() throws SameVehicleIsAlreadyParkedException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(1, dummyOwner); //this represent available lots

        assertTrue(parkingLot.park(new Object()));
        //assertDoesNotThrow(() -> parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotIsFull_WhenPark_ThenShouldNotPark() throws ParkingLotFullException, SameVehicleIsAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(1, dummyOwner); // spaceAvailable = 1
        parkingLot.park(new Object()); // spaceAvailable - -

        ParkingLotFullException exception = assertThrows(ParkingLotFullException.class, () -> {
            parkingLot.park(new Object());

        });
    }

    @Test
    void givenParkingLot_WhenParkingSameObjects_ThenShouldNotBeParked() throws SameVehicleIsAlreadyParkedException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(2, dummyOwner);

        Object object = new Object();
        parkingLot.park(object);
        SameVehicleIsAlreadyParkedException exception = assertThrows(SameVehicleIsAlreadyParkedException.class, () -> {
            parkingLot.park(object);
        });

    }

    @Test
    void givenParkingLot_WhenUnParkAlreadyParkedCar_thenShouldBeAbleToUnPark() throws SameVehicleIsAlreadyParkedException, ParkingLotFullException, CarNotParkedHereException {
        ParkingLot parkingLot = new ParkingLot(2, dummyOwner);
        Object alreadyParkedCar = new Object();
        parkingLot.park(alreadyParkedCar);

        assertEquals(alreadyParkedCar, parkingLot.unPark(alreadyParkedCar));
    }

    @Test
    void givenParkingLot_WhenUnParkCarWhichIsNotParked_thenShouldNotBeAbleToUnPark() throws SameVehicleIsAlreadyParkedException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(2, dummyOwner);
        Object CarWhichIsNotParkedIn = new Object();
        Object CarWhichIsParked = new Object();
        parkingLot.park(CarWhichIsParked);

        CarNotParkedHereException exception = assertThrows(CarNotParkedHereException.class, () -> {
            parkingLot.unPark(CarWhichIsNotParkedIn);
        });
    }

    @Test
    void givenParkingLot_WhenUnParkTwoCars_thenShouldBeAbleToUnPark() throws SameVehicleIsAlreadyParkedException, ParkingLotFullException, CarNotParkedHereException {
        ParkingLot parkingLot = new ParkingLot(2, dummyOwner);
        Object carOne = new Object();
        Object carTwo = new Object();
        parkingLot.park(carOne);
        parkingLot.park(carTwo);

        assertEquals(carOne, parkingLot.unPark(carOne));
        assertEquals(carTwo, parkingLot.unPark(carTwo));
    }

    @Test
    void givenParkingLot_WhenParkingLotIsFull_thenShouldNotifyToOwner() throws SameVehicleIsAlreadyParkedException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(2, dummyOwner);

        Object carOne = new Object();
        Object carTwo = new Object();
        parkingLot.park(carOne);
        parkingLot.park(carTwo);

        assertTrue(dummyOwner.wasCalled);
        assertEquals(1,dummyOwner.numberOfTimesNotified);
    }

    @Test
    void givenParkingLot_WhenLotAvailable_thenShouldNotifyToOwner() throws SameVehicleIsAlreadyParkedException, ParkingLotFullException, CarNotParkedHereException {
        ParkingLot parkingLot = new ParkingLot(2, dummyOwner);

        Object carOne = new Object();
        Object carTwo = new Object();
        parkingLot.park(carOne);
        parkingLot.park(carTwo);
        parkingLot.unPark(carOne);

        assertTrue(dummyOwner.wasCalledWhenSpaceAgainAvailable);
        assertEquals(1,dummyOwner.numberOfTimesNotifiedWhenSpaceAvailable);
    }
}
