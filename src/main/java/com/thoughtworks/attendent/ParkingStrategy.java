package com.thoughtworks.attendent;

import com.thoughtworks.AllParkingLotsAreFull;
import com.thoughtworks.SameVehicleIsAlreadyParkedException;

public interface ParkingStrategy {
    void park(Object object) throws SameVehicleIsAlreadyParkedException, AllParkingLotsAreFull;
}
