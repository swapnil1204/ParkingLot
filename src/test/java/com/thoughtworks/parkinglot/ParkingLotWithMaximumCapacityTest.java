package com.thoughtworks.parkinglot;

import com.thoughtworks.attendent.ParkingLotWithMaximumCapacity;
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
        ParkingLotWithMaximumCapacity attendant = new ParkingLotWithMaximumCapacity(parkingLots,ParkingLot.capacityComparator);
        Object vehicle = new Object();

        assertDoesNotThrow(() -> attendant.park(vehicle));
    }

    @Test
    void givenOneParkingLot_WhenPark_ThenShouldParkAndUnparkVehicleFromGivenParkingLot() throws Exception {
        ParkingLot parkingLotWithCapacityOne = new ParkingLot(1, subscribers);
        List<ParkingLot> parkingLots = of(parkingLotWithCapacityOne);
        ParkingLotWithMaximumCapacity attendant = new ParkingLotWithMaximumCapacity(parkingLots,ParkingLot.capacityComparator);
        Object vehicle = new Object();
        attendant.park(vehicle);

        assertEquals(vehicle, parkingLotWithCapacityOne.unPark(vehicle));
    }

    @Test
    void givenTwoParkingLot_WhenPark_ThenShouldParkTheVehicleInMaximumCapacityParkingLot() throws Exception {
        ParkingLot parkingLotWithCapacityOne = new ParkingLot(1, subscribers);
        ParkingLot parkingLotWithCapacityTwo = new ParkingLot(2, subscribers);
        List<ParkingLot> parkingLots = of(parkingLotWithCapacityOne, parkingLotWithCapacityTwo);

        ParkingLotWithMaximumCapacity attendant = new ParkingLotWithMaximumCapacity(parkingLots,ParkingLot.capacityComparator);
        Object vehicle = new Object();
        attendant.park(vehicle);

        assertEquals(vehicle, parkingLotWithCapacityTwo.unPark(vehicle));
    }

    @Test
    void givenTwoParkingLot_WhenMaximumCapacityParkingLotIsFull_ThenShouldParkTheVehicleInAnotherMaximumCapacityParkingLot() throws Exception {
        ParkingLot parkingLotWithCapacityTwo = new ParkingLot(2, subscribers);
        ParkingLot parkingLotWithCapacityThree = new ParkingLot(3, subscribers);
        List<ParkingLot> parkingLots = of(parkingLotWithCapacityTwo, parkingLotWithCapacityThree);

        ParkingLotWithMaximumCapacity attendant = new ParkingLotWithMaximumCapacity(parkingLots,ParkingLot.capacityComparator);
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
    void givenThreeParkingLot_WhenMaximumCapacityParkingLotIsFull_ThenShouldParkTheVehicleInRemainingMaximumCapacityParkingLot() throws Exception {//TODO
        ParkingLot parkingLotWithCapacityOne = new ParkingLot(1, subscribers);
        ParkingLot parkingLotWithCapacityTwo = new ParkingLot(2, subscribers);
        ParkingLot parkingLotWithCapacityThree = new ParkingLot(3, subscribers);
        List<ParkingLot> parkingLots = of(parkingLotWithCapacityTwo, parkingLotWithCapacityThree, parkingLotWithCapacityOne);

        ParkingLotWithMaximumCapacity attendant = new ParkingLotWithMaximumCapacity(parkingLots,ParkingLot.capacityComparator);
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        Object vehicleThree = new Object();
        Object vehicleFour = new Object();
        Object vehicleFive = new Object();
        Object vehicleSix = new Object();

        parkingLotWithCapacityThree.park(vehicleOne);
        parkingLotWithCapacityThree.park(vehicleTwo);
        parkingLotWithCapacityThree.park(vehicleThree);
        parkingLotWithCapacityTwo.park(vehicleFour);
        parkingLotWithCapacityTwo.park(vehicleFive);

        attendant.park(vehicleSix);


        assertEquals(vehicleSix, parkingLotWithCapacityOne.unPark(vehicleSix));
    }

}

