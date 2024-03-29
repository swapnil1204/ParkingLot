package com.thoughtworks.parkinglot;

import com.thoughtworks.Exceptions.CarNotParkedHereException;
import com.thoughtworks.Exceptions.ParkingLotFullException;
import com.thoughtworks.Exceptions.SameVehicleIsAlreadyParkedException;
import com.thoughtworks.subscriber.Subscriber;
import com.thoughtworks.parkinglot.dummy.DummySubscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.thoughtworks.parkinglot.dummy.DummyFactory.*;
import static com.thoughtworks.parkinglot.dummy.DummyFactory.getDummyOwner;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    List<Subscriber> subscribers;

    @BeforeEach
    void setup() {
        subscribers = new ArrayList<>();
    }

    @Test
    void givenParkingLotHasCapacity_WhenPark_ThenShouldPark() throws Exception {
        DummySubscriber dummyOwner = getDummyOwner();
        subscribers.add(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(1, subscribers);

        assertDoesNotThrow(() -> parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotIsFull_WhenPark_ThenShouldNotPark() throws Exception {
        DummySubscriber dummyOwner = getDummyOwner();
        subscribers.add(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(1, subscribers);
        parkingLot.park(new Object()); // spaceAvailable - -

        assertThrows(ParkingLotFullException.class, () -> {
            parkingLot.park(new Object());

        });
    }

    @Test
    void givenParkingLot_WhenParkingSameObjects_ThenShouldNotBeParked() throws Exception {
        DummySubscriber dummyOwner = getDummyOwner();
        subscribers.add(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(2, subscribers);

        Object object = new Object();
        parkingLot.park(object);
        assertThrows(SameVehicleIsAlreadyParkedException.class, () -> {
            parkingLot.park(object);
        });
    }

    @Test
    void givenParkingLot_WhenUnParkAlreadyParkedCar_thenShouldBeAbleToUnPark() throws Exception {
        DummySubscriber dummyOwner = getDummyOwner();
        subscribers.add(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(2, subscribers);
        Object alreadyParkedCar = new Object();
        parkingLot.park(alreadyParkedCar);

        assertEquals(alreadyParkedCar, parkingLot.unPark(alreadyParkedCar));
    }

    @Test
    void givenParkingLot_WhenUnParkCarWhichIsNotParked_thenShouldNotBeAbleToUnPark() throws Exception {
        DummySubscriber dummyOwner = getDummyOwner();
        subscribers.add(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(2, subscribers);
        Object CarWhichIsNotParkedIn = new Object();
        Object CarWhichIsParked = new Object();
        parkingLot.park(CarWhichIsParked);

        assertThrows(CarNotParkedHereException.class, () -> {
            parkingLot.unPark(CarWhichIsNotParkedIn);
        });
    }

    @Test
    void givenParkingLot_WhenUnParkTwoCars_thenShouldBeAbleToUnPark() throws Exception {
        DummySubscriber dummyOwner = getDummyOwner();
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
        DummySubscriber dummyOwner = getDummyOwner();
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
        DummySubscriber dummyOwner = getDummyOwner();
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
        DummySubscriber dummyOwner = getDummyOwner();
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
        DummySubscriber dummySecurityGuard = getDummySecurityGuard();
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
        DummySubscriber dummySecurityGuard = getDummySecurityGuard();
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
        DummySubscriber dummySecurityGuard = getDummySecurityGuard();
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
        DummySubscriber dummyOwner = getDummyOwner();
        DummySubscriber dummySecurityGuard = getDummySecurityGuard();
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
    void givenParkingLot_WhenIsFull_thenShouldNotifyToAllIncludingNewSubscriber() throws Exception {
        DummySubscriber dummyOwner = getDummyOwner();
        subscribers.add(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(1, subscribers);
        Object vehicle = new Object();

        DummySubscriber newSubscriber = getNewSubscriber();
        parkingLot.add(newSubscriber);
        parkingLot.park(vehicle);

        assertEquals(1, dummyOwner.numberOfTimesNotifiedWhenParkingLotIsFull);
        assertEquals(1, newSubscriber.numberOfTimesNotifiedWhenParkingLotIsFull);
    }

    @Test
    void givenParkingLot_WhenIsFull_ThenShouldNotifyToAllExcludingUnsubscriber() throws Exception {
        DummySubscriber dummyOwner = getDummyOwner();
        DummySubscriber newSubscriber = getNewSubscriber();

        List<Subscriber> subscribers = new ArrayList<>(asList(dummyOwner, newSubscriber));

        ParkingLot parkingLot = new ParkingLot(1, subscribers);
        Object vehicle = new Object();

        parkingLot.remove(newSubscriber);
        parkingLot.park(vehicle);

        assertEquals(1, dummyOwner.numberOfTimesNotifiedWhenParkingLotIsFull);
        assertEquals(0, newSubscriber.numberOfTimesNotifiedWhenParkingLotIsFull);
    }

}

