package com.thoughtworks.parkinglot;

import com.thoughtworks.ParkingLotFullException;
import com.thoughtworks.SameVehicleIsAlreadyParkedException;
import com.thoughtworks.CarNotParkedHereException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DummyOwner implements Subscriber {
    int numberOfTimesNotified = 0;
    int numberOfTimesNotifiedWhenSpaceAvailable = 0;

    @Override
    public void gotNotificationWhenSpaceIsFull() {
        numberOfTimesNotified++;
    }

    @Override
    public void gotNotificationWhenSpaceAvailable() {
        numberOfTimesNotifiedWhenSpaceAvailable++;
    }
}

class DummySecurityGuard implements Subscriber {
    int numberOfTimesNotified = 0;
    int numberOfTimesNotifiedWhenSpaceAvailable = 0;

    @Override
    public void gotNotificationWhenSpaceIsFull() {
        numberOfTimesNotified++;
    }

    @Override
    public void gotNotificationWhenSpaceAvailable() {
        numberOfTimesNotifiedWhenSpaceAvailable++;
    }
}

public class ParkingLotExceptionTest {

    @Test
    void givenParkingLotHasCapacity_WhenPark_ThenShouldPark() throws Exception {
        DummyOwner dummyOwner = new DummyOwner();
        List observer = new ArrayList<>();
        observer.add(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(1, observer); //this represent available lots

        assertDoesNotThrow(() -> parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotIsFull_WhenPark_ThenShouldNotPark() throws Exception {
        DummyOwner dummyOwner = new DummyOwner();
        List observer = new ArrayList<>();
        observer.add(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(1, observer); // spaceAvailable = 1
        parkingLot.park(new Object()); // spaceAvailable - -

        ParkingLotFullException exception = assertThrows(ParkingLotFullException.class, () -> {
            parkingLot.park(new Object());

        });
    }

    @Test
    void givenParkingLot_WhenParkingSameObjects_ThenShouldNotBeParked() throws Exception {
        DummyOwner dummyOwner = new DummyOwner();
        List observer = new ArrayList<>();
        observer.add(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(2, observer);

        Object object = new Object();
        parkingLot.park(object);
        SameVehicleIsAlreadyParkedException exception = assertThrows(SameVehicleIsAlreadyParkedException.class, () -> {
            parkingLot.park(object);
        });
    }

    @Test
    void givenParkingLot_WhenUnParkAlreadyParkedCar_thenShouldBeAbleToUnPark() throws Exception {
        DummyOwner dummyOwner = new DummyOwner();
        List observer = new ArrayList<>();
        observer.add(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(2, observer);
        Object alreadyParkedCar = new Object();
        parkingLot.park(alreadyParkedCar);

        assertEquals(alreadyParkedCar, parkingLot.unPark(alreadyParkedCar));
    }

    @Test
    void givenParkingLot_WhenUnParkCarWhichIsNotParked_thenShouldNotBeAbleToUnPark() throws Exception {
        DummyOwner dummyOwner = new DummyOwner();
        List observer = new ArrayList<>();
        observer.add(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(2, observer);
        Object CarWhichIsNotParkedIn = new Object();
        Object CarWhichIsParked = new Object();
        parkingLot.park(CarWhichIsParked);

        CarNotParkedHereException exception = assertThrows(CarNotParkedHereException.class, () -> {
            parkingLot.unPark(CarWhichIsNotParkedIn);
        });
    }

    @Test
    void givenParkingLot_WhenUnParkTwoCars_thenShouldBeAbleToUnPark() throws Exception {
        DummyOwner dummyOwner = new DummyOwner();
        List observer = new ArrayList<>();
        observer.add(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(2, observer);
        Object carOne = new Object();
        Object carTwo = new Object();
        parkingLot.park(carOne);
        parkingLot.park(carTwo);

        assertEquals(carOne, parkingLot.unPark(carOne));
        assertEquals(carTwo, parkingLot.unPark(carTwo));
    }

    @Test
    void givenParkingLot_WhenParkingLotIsFull_thenShouldNotifyToOwner() throws Exception {
        DummyOwner dummyOwner = new DummyOwner();
        List observer = new ArrayList<>();
        observer.add(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(2, observer);

        Object carOne = new Object();
        Object carTwo = new Object();
        parkingLot.park(carOne);
        parkingLot.park(carTwo);

        assertEquals(1, dummyOwner.numberOfTimesNotified);
    }

    @Test
    void givenParkingLot_WhenLotAvailable_thenShouldNotifyToOwner() throws Exception {
        DummyOwner dummyOwner = new DummyOwner();
        List observer = new ArrayList<>();
        observer.add(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(2, observer);
        Object carOne = new Object();
        Object carTwo = new Object();
        parkingLot.park(carOne);
        parkingLot.park(carTwo);

        parkingLot.unPark(carOne);

        assertEquals(1, dummyOwner.numberOfTimesNotifiedWhenSpaceAvailable);
    }

    @Test
    void givenParkingLotIsFull_WhenUnparkTwice_thenShouldNotifyToOwnerOnce() throws Exception {
        DummyOwner dummyOwner = new DummyOwner();
        List observer = new ArrayList<>();
        observer.add(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(2, observer);
        Object carOne = new Object();
        Object carTwo = new Object();
        parkingLot.park(carOne);
        parkingLot.park(carTwo);

        parkingLot.unPark(carOne);
        parkingLot.unPark(carTwo);

        assertEquals(1, dummyOwner.numberOfTimesNotifiedWhenSpaceAvailable);
    }

    @Test
    void givenParkingLot_WhenParkingLotIsFull_thenShouldNotifyToSecurityGuard() throws Exception {
        DummySecurityGuard dummySecurityGuard = new DummySecurityGuard();
        List observer = new ArrayList<>();
        observer.add(dummySecurityGuard);
        ParkingLot parkingLot = new ParkingLot(2, observer);

        Object carOne = new Object();
        Object carTwo = new Object();
        parkingLot.park(carOne);
        parkingLot.park(carTwo);

        assertEquals(1, dummySecurityGuard.numberOfTimesNotified);
    }

    @Test
    void givenParkingLotIsFull_WhenParkingLotAvailable_thenShouldNotifyToSecurityGuard() throws Exception {
        DummySecurityGuard dummySecurityGuard = new DummySecurityGuard();
        List observer = new ArrayList<>();
        observer.add(dummySecurityGuard);
        ParkingLot parkingLot = new ParkingLot(2, observer);
        Object carOne = new Object();
        Object carTwo = new Object();
        parkingLot.park(carOne);
        parkingLot.park(carTwo);

        parkingLot.unPark(carOne);

        assertEquals(1, dummySecurityGuard.numberOfTimesNotifiedWhenSpaceAvailable);
    }

    @Test
    void givenParkingLotIsFull_WhenUnparkTwice_thenShouldNotifyToSecurityGuardOnce() throws Exception {
        DummySecurityGuard dummySecurityGuard = new DummySecurityGuard();
        List observer = new ArrayList<>();
        observer.add(dummySecurityGuard);
        ParkingLot parkingLot = new ParkingLot(2, observer);
        Object carOne = new Object();
        Object carTwo = new Object();
        parkingLot.park(carOne);
        parkingLot.park(carTwo);

        parkingLot.unPark(carOne);
        parkingLot.unPark(carTwo);

        assertEquals(1, dummySecurityGuard.numberOfTimesNotifiedWhenSpaceAvailable);
    }

    @Test
    void givenParkingLot_WhenParkingLotIsFull_thenShouldNotifyToSecurityGuardAndOwner() throws Exception {
        DummyOwner dummyOwner = new DummyOwner();
        DummySecurityGuard dummySecurityGuard = new DummySecurityGuard();
        List observer = new ArrayList<>();
        observer.add(dummyOwner);
        observer.add(dummySecurityGuard);
        ParkingLot parkingLot = new ParkingLot(2, observer);
        Object carOne = new Object();
        Object carTwo = new Object();
        parkingLot.park(carOne);
        parkingLot.park(carTwo);

        assertEquals(1, dummyOwner.numberOfTimesNotified);
        assertEquals(1, dummySecurityGuard.numberOfTimesNotified);
    }
}
