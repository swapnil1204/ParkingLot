package com.thoughtworks.parkinglot;

import com.thoughtworks.attendent.ParkingLotWithMaximumFreeSpace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingLotWithMaximumFreeSpaceTest {

    private List<Subscriber> subscribers;

    @BeforeEach
    void setup() {
        subscribers = new ArrayList<>();
    }

    @Test
    void givenParkingLotWithMaximumFreeSpaceOne_WhenPark_ThenShouldParkVehicleInGivenParkingLot() throws Exception {
        ParkingLot parkingLotWithFreeSpaceOne = new ParkingLot(1, subscribers);
        List<ParkingLot> parkingLots = of(parkingLotWithFreeSpaceOne);
        ParkingLotWithMaximumFreeSpace attendant = new ParkingLotWithMaximumFreeSpace(parkingLots,ParkingLot.freeSpaceComparator);
        Object vehicle = new Object();

        attendant.park(vehicle);

        assertEquals(vehicle, parkingLotWithFreeSpaceOne.unPark(vehicle));
    }
}
