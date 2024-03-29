package com.thoughtworks.parkinglot;

import com.thoughtworks.attendent.Attendant;
import com.thoughtworks.attendent.ParkingLotWithMaximumCapacity;
import com.thoughtworks.subscriber.Subscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.*;

class ParkingLotWithMaximumCapacityTest {

    private List<Subscriber> subscribers;

    @BeforeEach
    void setup() {
        subscribers = new ArrayList<>();
    }

    @Test
    void givenOneParkingLot_WhenPark_ThenShouldParkVehicleInGivenParkingLot() {
        ParkingLot parkingLotWithCapacityOne = new ParkingLot(1, subscribers);
        List<ParkingLot> parkingLots = of(parkingLotWithCapacityOne);
        ParkingLotWithMaximumCapacity parkingLotWithMaximumCapacity = new ParkingLotWithMaximumCapacity();
        Object vehicle = new Object();

        Attendant attendant = new Attendant(parkingLotWithMaximumCapacity, parkingLots);
        assertDoesNotThrow(() -> attendant.park(vehicle));
    }

    @Test
    void givenOneParkingLot_WhenPark_ThenShouldParkAndUnparkVehicleFromGivenParkingLot() throws Exception {
        ParkingLot parkingLotWithCapacityOne = new ParkingLot(1, subscribers);
        List<ParkingLot> parkingLots = of(parkingLotWithCapacityOne);
        ParkingLotWithMaximumCapacity parkingLotWithMaximumCapacity = new ParkingLotWithMaximumCapacity();
        Attendant attendant = new Attendant(parkingLotWithMaximumCapacity, parkingLots);
        Object vehicle = new Object();
        attendant.park(vehicle);

        assertEquals(vehicle, parkingLotWithCapacityOne.unPark(vehicle));
    }

    @Test
    void givenTwoParkingLot_WhenPark_ThenShouldParkTheVehicleInMaximumCapacityParkingLot() throws Exception {
        ParkingLot parkingLotWithCapacityOne = new ParkingLot(1, subscribers);
        ParkingLot parkingLotWithCapacityTwo = new ParkingLot(2, subscribers);
        List<ParkingLot> parkingLots = of(parkingLotWithCapacityOne,parkingLotWithCapacityTwo);
        ParkingLotWithMaximumCapacity parkingLotWithMaximumCapacity = new ParkingLotWithMaximumCapacity();
        Attendant attendant = new Attendant(parkingLotWithMaximumCapacity, parkingLots);
        Object vehicle = new Object();
        attendant.park(vehicle);

        assertEquals(vehicle, parkingLotWithCapacityTwo.unPark(vehicle));
    }

    @Test
    void givenTwoParkingLot_WhenMaximumCapacityParkingLotIsFull_ThenShouldParkTheVehicleInAnotherMaximumCapacityParkingLot() throws Exception {
        ParkingLot parkingLotWithCapacityTwo = new ParkingLot(2, subscribers);
        ParkingLot parkingLotWithCapacityThree = new ParkingLot(3, subscribers);

        List<ParkingLot> parkingLots = of(parkingLotWithCapacityTwo,parkingLotWithCapacityThree);
        ParkingLotWithMaximumCapacity parkingLotWithMaximumCapacity = new ParkingLotWithMaximumCapacity();
        Attendant attendant = new Attendant(parkingLotWithMaximumCapacity, parkingLots);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        Object vehicleThree = new Object();
        Object vehicleFour = new Object();

        attendant.park(vehicleOne);
        attendant.park(vehicleTwo);
        attendant.park(vehicleThree);
        attendant.park(vehicleFour);

        assertEquals(vehicleOne, parkingLotWithCapacityThree.unPark(vehicleOne));
        assertEquals(vehicleTwo, parkingLotWithCapacityThree.unPark(vehicleTwo));
        assertEquals(vehicleThree, parkingLotWithCapacityThree.unPark(vehicleThree));
        assertEquals(vehicleFour, parkingLotWithCapacityTwo.unPark(vehicleFour));
    }

    @Test
    void givenThreeParkingLot_WhenMaximumCapacityParkingLotIsFull_ThenShouldParkTheVehicleInRemainingMaximumCapacityParkingLot() throws Exception {
        ParkingLot parkingLotWithCapacityOne = new ParkingLot(1, subscribers);
        ParkingLot parkingLotWithCapacityTwo = new ParkingLot(2, subscribers);
        ParkingLot parkingLotWithCapacityThree = new ParkingLot(3, subscribers);
        List<ParkingLot> parkingLots = of(parkingLotWithCapacityTwo, parkingLotWithCapacityThree, parkingLotWithCapacityOne);

        ParkingLotWithMaximumCapacity parkingLotWithMaximumCapacity = new ParkingLotWithMaximumCapacity();
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        Object vehicleThree = new Object();
        Object vehicleFour = new Object();
        Object vehicleFive = new Object();
        Object vehicleSix = new Object();
        Attendant attendant = new Attendant(parkingLotWithMaximumCapacity, parkingLots);
        attendant.park(vehicleOne);
        attendant.park(vehicleTwo);
        attendant.park(vehicleThree);
        attendant.park(vehicleFour);
        attendant.park(vehicleFive);
        attendant.park(vehicleSix);

        assertEquals(vehicleSix, parkingLotWithCapacityOne.unPark(vehicleSix));
    }

}

