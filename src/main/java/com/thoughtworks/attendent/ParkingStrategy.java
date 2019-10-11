package com.thoughtworks.attendent;

import com.thoughtworks.Exceptions.AllParkingLotsAreFull;
import com.thoughtworks.Exceptions.ParkingLotFullException;
import com.thoughtworks.Exceptions.SameVehicleIsAlreadyParkedException;
import com.thoughtworks.parkinglot.ParkingLot;

import java.util.List;

public interface ParkingStrategy {
    void park(List<ParkingLot> parkingLots, Object object) throws SameVehicleIsAlreadyParkedException, AllParkingLotsAreFull, ParkingLotFullException;
}
