package com.thoughtworks.parkinglot;

import com.thoughtworks.CarNotParkedHereException;
import com.thoughtworks.ParkingLotFullException;
import com.thoughtworks.SameVehicleIsAlreadyParkedException;
import com.thoughtworks.attendent.Attendant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Attendant attendant = new Attendant(parkingLots);
        Object vehicle = new Object();

        assertDoesNotThrow(() -> attendant.park(vehicle));
    }

    @Test
    void givenOneParkingLot_WhenParkAndUnpark_ThenShouldParkVehicleInGivenParkingLot() throws Exception {
        ParkingLot parkingLotWithCapacityOne = new ParkingLot(1, subscribers);
        List<ParkingLot> parkingLots = of(parkingLotWithCapacityOne);
        Attendant attendant = new Attendant(parkingLots);
        Object vehicle = new Object();
        attendant.park(vehicle);

        assertEquals(vehicle,parkingLotWithCapacityOne.unPark(vehicle));
    }
}
