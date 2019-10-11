package com.thoughtworks.parkinglot;

import com.thoughtworks.attendent.Attendant;
import com.thoughtworks.attendent.ParkingLotWithMaximumFreeSpace;
import com.thoughtworks.subscriber.Subscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingLotWithMaximumFreeSpaceTest {

    private List<Subscriber> subscribers;

    @BeforeEach
    void setup() {
        subscribers = new ArrayList<>();
    }

    @Test
    void givenOneParkingLot_WhenPark_ThenShouldParkVehicleInGivenParkingLot() {
        ParkingLot parkingLotWithFreeSpaceOne = new ParkingLot(1, subscribers);
        List<ParkingLot> parkingLots = of(parkingLotWithFreeSpaceOne);
        ParkingLotWithMaximumFreeSpace parkingLotWithMaximumFreeSpace = new ParkingLotWithMaximumFreeSpace();
        Attendant attendant = new Attendant(parkingLotWithMaximumFreeSpace, parkingLots);
        Object vehicle = new Object();

        assertDoesNotThrow(() -> attendant.park(vehicle));
    }

    @Test
    void givenOneParkingLot_WhenPark_ThenShouldParkAndUnparkVehicleFromGivenParkingLot() throws Exception {
        ParkingLot parkingLotWithFreeSpaceOne = new ParkingLot(1, subscribers);
        List<ParkingLot> parkingLots = of(parkingLotWithFreeSpaceOne);
        ParkingLotWithMaximumFreeSpace parkingLotWithMaximumFreeSpace = new ParkingLotWithMaximumFreeSpace();
        Attendant attendant = new Attendant(parkingLotWithMaximumFreeSpace, parkingLots);
        Object vehicle = new Object();
        attendant.park(vehicle);

        assertEquals(vehicle, parkingLotWithFreeSpaceOne.unPark(vehicle));
    }

    @Test
    void givenTwoParkingLot_WhenPark_ThenShouldParkTheVehicleInMaximumFreeSpaceAvailableParkingLot() throws Exception {
        ParkingLot parkingLotWihFreeSpaceOne = new ParkingLot(1, subscribers);
        ParkingLot parkingLotWithFreeSpaceTwo = new ParkingLot(2, subscribers);
        List<ParkingLot> parkingLots = of(parkingLotWihFreeSpaceOne,parkingLotWithFreeSpaceTwo);
        ParkingLotWithMaximumFreeSpace ParkingLotWithMaximumFreeSpace = new ParkingLotWithMaximumFreeSpace();
        Attendant attendant = new Attendant(ParkingLotWithMaximumFreeSpace, parkingLots);
        Object vehicle = new Object();
        attendant.park(vehicle);

        assertEquals(vehicle, parkingLotWithFreeSpaceTwo.unPark(vehicle));
    }

    @Test
    void givenTwoParkingLot_WhenPark_ThenShouldParkTheVehicleInMaximumFreeSpaceAvailableParkingLotAtTheTime() throws Exception {
        ParkingLot parkingLotWithFreeSpaceTwo = new ParkingLot(2, subscribers);
        ParkingLot parkingLotWithFreeSpaceThree = new ParkingLot(3, subscribers);

        List<ParkingLot> parkingLots = of(parkingLotWithFreeSpaceTwo,parkingLotWithFreeSpaceThree);
        ParkingLotWithMaximumFreeSpace ParkingLotWithMaximumFreeSpace = new ParkingLotWithMaximumFreeSpace();
        Attendant attendant = new Attendant(ParkingLotWithMaximumFreeSpace, parkingLots);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        Object vehicleThree = new Object();
        Object vehicleFour = new Object();

        attendant.park(vehicleOne);
        attendant.park(vehicleTwo);
        attendant.park(vehicleThree);
        attendant.park(vehicleFour);

        assertEquals(vehicleOne, parkingLotWithFreeSpaceThree.unPark(vehicleOne));
        assertEquals(vehicleTwo, parkingLotWithFreeSpaceThree.unPark(vehicleTwo));
        assertEquals(vehicleThree, parkingLotWithFreeSpaceTwo.unPark(vehicleThree));
        assertEquals(vehicleFour, parkingLotWithFreeSpaceTwo.unPark(vehicleFour));
    }

}
