package com.thoughtworks.attendent;

import com.thoughtworks.Exceptions.AllParkingLotsAreFull;
import com.thoughtworks.Exceptions.ParkingLotFullException;
import com.thoughtworks.Exceptions.SameVehicleIsAlreadyParkedException;

public interface ParkingStrategy {
    void park(Object object) throws SameVehicleIsAlreadyParkedException, AllParkingLotsAreFull, ParkingLotFullException;
}
