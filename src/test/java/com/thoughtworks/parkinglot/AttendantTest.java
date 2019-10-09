package com.thoughtworks.parkinglot;

import com.thoughtworks.attendent.Attendant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.*;

class AttendantTest {

    private List<Subscriber> subscribers;

    @BeforeEach
    void setup() {
        subscribers = new ArrayList<>();
    }

    @Test
    void givenOneParkingLot_WhenPark_ThenShouldParkVehicleInGivenParkingLot() {
        ParkingLot parkingLotWithCapacityOne = new ParkingLot(1, subscribers);
        List<ParkingLot> parkingLots = of(parkingLotWithCapacityOne);
        //List<ParkingLot> parkingLots = new ArrayList<>(Collections.singleton(parkingLotWithCapacityOne));
        Attendant attendant = new Attendant(parkingLots);
        Object vehicle = new Object();

        assertDoesNotThrow(() -> attendant.park(vehicle));
    }

    @Test
    void givenOneParkingLot_WhenPark_ThenShouldParkAndUnparkVehicleFromGivenParkingLot() throws Exception {
        ParkingLot parkingLotWithCapacityOne = new ParkingLot(1, subscribers);
        List<ParkingLot> parkingLots = of(parkingLotWithCapacityOne);
        Attendant attendant = new Attendant(parkingLots);
        Object vehicle = new Object();
        attendant.park(vehicle);

        assertEquals(vehicle, parkingLotWithCapacityOne.unPark(vehicle));
    }

    @Test
    void givenTwoParkingLot_WhenPark_ThenShouldParkTheVehicleInMaximumCapacityParkingLot() throws Exception {
        ParkingLot parkingLotWithCapacityOne = new ParkingLot(1, subscribers);
        ParkingLot parkingLotWithCapacityTwo = new ParkingLot(2, subscribers);
        List<ParkingLot> parkingLots = of(parkingLotWithCapacityOne, parkingLotWithCapacityTwo);

        Attendant attendant = new Attendant(parkingLots);
        Object vehicle = new Object();
        attendant.park(vehicle);

        assertEquals(vehicle, parkingLotWithCapacityTwo.unPark(vehicle));
    }

    @Test
    void givenTwoParkingLot_WhenMaximumCapacityParkingLotIsFull_ThenShouldParkTheVehicleInAnotherMaximumCapacityParkingLot() throws Exception {
        ParkingLot parkingLotWithCapacityTwo = new ParkingLot(2, subscribers);
        ParkingLot parkingLotWithCapacityThree = new ParkingLot(3, subscribers);
        List<ParkingLot> parkingLots = of(parkingLotWithCapacityTwo, parkingLotWithCapacityThree);

        Attendant attendant = new Attendant(parkingLots);
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        Object vehicleThree = new Object();
        Object vehicleFour = new Object();

        attendant.park(vehicleOne);
        attendant.park(vehicleTwo);
        attendant.park(vehicleThree);

        attendant.park(vehicleFour);

        assertEquals(vehicleFour, parkingLotWithCapacityTwo.unPark(vehicleFour));
    }
}

