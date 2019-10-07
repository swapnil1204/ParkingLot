package com.thoughtworks.parkinglot;

import com.thoughtworks.CarNotParkedHereException;
import com.thoughtworks.ParkingLotFullException;
import com.thoughtworks.SameVehicleIsAlreadyParkedException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

//    private List<Object> Subscribers = new ArrayList<>();

    @Test
    void givenParkingLotHasCapacity_WhenPark_ThenShouldPark() throws Exception {
        DummyOwner dummyOwner = new DummyOwner();
        List<Subscriber> subscribers = new ArrayList<>();
        subscribers.add(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(1, subscribers); //this represent available lots

        assertDoesNotThrow(() -> parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotIsFull_WhenPark_ThenShouldNotPark() throws Exception {
        DummyOwner dummyOwner = new DummyOwner();
        List<Subscriber> subscribers = new ArrayList<>();
        subscribers.add(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(1, subscribers); // spaceAvailable = 1
        parkingLot.park(new Object()); // spaceAvailable - -

        ParkingLotFullException exception = assertThrows(ParkingLotFullException.class, () -> {
            parkingLot.park(new Object());

        });
    }

    @Test
    void givenParkingLot_WhenParkingSameObjects_ThenShouldNotBeParked() throws Exception {
        DummyOwner dummyOwner = new DummyOwner();
        List<Subscriber> subscribers = new ArrayList<>();
        subscribers.add(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(2, subscribers);

        Object object = new Object();
        parkingLot.park(object);
        SameVehicleIsAlreadyParkedException exception = assertThrows(SameVehicleIsAlreadyParkedException.class, () -> {
            parkingLot.park(object);
        });
    }

    @Test
    void givenParkingLot_WhenUnParkAlreadyParkedCar_thenShouldBeAbleToUnPark() throws Exception {
        DummyOwner dummyOwner = new DummyOwner();
        List<Subscriber> subscribers = new ArrayList<>();
        subscribers.add(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(2, subscribers);
        Object alreadyParkedCar = new Object();
        parkingLot.park(alreadyParkedCar);

        assertEquals(alreadyParkedCar, parkingLot.unPark(alreadyParkedCar));
    }

    @Test
    void givenParkingLot_WhenUnParkCarWhichIsNotParked_thenShouldNotBeAbleToUnPark() throws Exception {
        DummyOwner dummyOwner = new DummyOwner();
        List<Subscriber> subscribers = new ArrayList<>();
        subscribers.add(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(2, subscribers);
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
        List<Subscriber> subscribers = new ArrayList<>();
        subscribers.add(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(2, subscribers);
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
        List<Subscriber> subscribers = new ArrayList<>();
        subscribers.add(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(2, subscribers);

        Object carOne = new Object();
        Object carTwo = new Object();
        parkingLot.park(carOne);
        parkingLot.park(carTwo);

        assertEquals(1, dummyOwner.numberOfTimesNotifiedWhenParkingLotIsFull);
    }

    @Test
    void givenParkingLot_WhenLotAvailable_thenShouldNotifyToOwner() throws Exception {
        DummyOwner dummyOwner = new DummyOwner();
        List<Subscriber> subscribers = new ArrayList<>();
        subscribers.add(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(2, subscribers);
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
        List<Subscriber> subscribers = new ArrayList<>();
        subscribers.add(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(2, subscribers);
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
        List<Subscriber> subscribers = new ArrayList<>();
        subscribers.add(dummySecurityGuard);
        ParkingLot parkingLot = new ParkingLot(2, subscribers);

        Object carOne = new Object();
        Object carTwo = new Object();
        parkingLot.park(carOne);
        parkingLot.park(carTwo);

        assertEquals(1, dummySecurityGuard.numberOfTimesNotifiedWhenParkingLotIsFull);
    }

    @Test
    void givenParkingLotIsFull_WhenParkingLotAvailable_thenShouldNotifyToSecurityGuard() throws Exception {
        DummySecurityGuard dummySecurityGuard = new DummySecurityGuard();
        List<Subscriber> subscribers = new ArrayList<>();
        subscribers.add(dummySecurityGuard);
        ParkingLot parkingLot = new ParkingLot(2, subscribers);
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
        List<Subscriber> subscribers = new ArrayList<>();
        subscribers.add(dummySecurityGuard);
        ParkingLot parkingLot = new ParkingLot(2, subscribers);
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
        List<Subscriber> subscribers = new ArrayList<>();
        subscribers.add(dummyOwner);
        subscribers.add(dummySecurityGuard);
        ParkingLot parkingLot = new ParkingLot(2, subscribers);
        Object carOne = new Object();
        Object carTwo = new Object();
        parkingLot.park(carOne);
        parkingLot.park(carTwo);

        assertEquals(1, dummyOwner.numberOfTimesNotifiedWhenParkingLotIsFull);
        assertEquals(1, dummySecurityGuard.numberOfTimesNotifiedWhenParkingLotIsFull);
    }

    @Test
    void givenParkingLot_WhenParkingLotIsFull_thenShouldNotifyToAllSubscriber() throws Exception {
        DummyOwner dummyOwner = new DummyOwner();
        List<Subscriber> subscriber =new ArrayList<>();
        subscriber.add(dummyOwner);
        ParkingLot parkingLot =new ParkingLot(1,subscriber);
        Object vehicle = new Object();

        NewSubscriber newSubscriber = new NewSubscriber();
        parkingLot.Add(newSubscriber);
        parkingLot.park(vehicle);

        assertEquals(1,dummyOwner.numberOfTimesNotifiedWhenParkingLotIsFull);
        assertEquals(1,newSubscriber.numberOfTimesNotifiedWhenParkingLotIsFull);
    }
}
