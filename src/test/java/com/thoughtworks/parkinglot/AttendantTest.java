package com.thoughtworks.parkinglot;

import com.thoughtworks.CarNotParkedHereException;
import com.thoughtworks.attendent.Attendant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class AttendantTest {

    private List<Subscriber> subscribers;

    @BeforeEach
    void setup() {
        subscribers = new ArrayList<>();
    }

    @Test
    void givenOneParkingLot_WhenPark_ThenShouldParkInGivenParkingLot() {
        ParkingLot parkingLotWithCapacityOne = new ParkingLot(1, subscribers);
        List<ParkingLot> parkingLots = of(parkingLotWithCapacityOne);
        Attendant attendant = new Attendant(parkingLots);
        Object vehicle = new Object();

        assertDoesNotThrow(() -> attendant.park(vehicle));
    }
}
