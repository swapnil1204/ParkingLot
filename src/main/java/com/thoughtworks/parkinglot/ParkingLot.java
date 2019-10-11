package com.thoughtworks.parkinglot;

import com.thoughtworks.Exceptions.ParkingLotFullException;
import com.thoughtworks.Exceptions.CarNotParkedHereException;
import com.thoughtworks.Exceptions.SameVehicleIsAlreadyParkedException;
import com.thoughtworks.subscriber.Subscriber;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ParkingLot {

    private int capacity;
    private List<Subscriber> subscribers;

    private List<Object> vehicles = new ArrayList<>();

    public ParkingLot(int capacity, List<Subscriber> subscribers) {
        this.capacity = capacity;
        this.subscribers = subscribers;
    }

    public void park(Object object) throws SameVehicleIsAlreadyParkedException, ParkingLotFullException {
        if (!isOutOfCapacity()) {
            throw new ParkingLotFullException();
        }
        if (isVehicleAlreadyParked(object)) {
            throw new SameVehicleIsAlreadyParkedException();
        }
        vehicles.add(object);
        if (isFull()) {
            sendNotificationToAllExistingSubscribersWhenParkingLotIsFull();
        }
    }

    private void sendNotificationToAllExistingSubscribersWhenParkingLotIsFull() {
        for (Subscriber subscriber : subscribers) {
            subscriber.gotNotificationWhenSpaceIsFull();
        }
    }

    private boolean isOutOfCapacity() {
        return vehicles.size() < capacity;
    }

    private boolean isVehicleAlreadyParked(Object object) {
        return vehicles.contains(object);
    }

    private boolean isFull() {
        return vehicles.size() == capacity;
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "capacity=" + capacity +
                '}';
    }

    public Object unPark(Object car) throws CarNotParkedHereException {
        if (isVehicleAlreadyParked(car)) {
            if (!isFull()) {
                vehicles.remove(car);
                return car;
            }
            vehicles.remove(car);
            sendNotificationToAllExistingSubscribersWhenSpaceIsAvailable();
            return car;
        }
        throw new CarNotParkedHereException();
    }

    private void sendNotificationToAllExistingSubscribersWhenSpaceIsAvailable() {
        for (Subscriber subscriber : subscribers) subscriber.gotNotificationWhenSpaceAvailable();
    }

    public void add(Subscriber subscribers) {
        this.subscribers.add(subscribers);
    }

    public void remove(Subscriber subscribers) {
        this.subscribers.remove(subscribers);
    }

    public static Comparator<ParkingLot> capacityComparator = new Comparator<ParkingLot>() {
        @Override
        public int compare(ParkingLot o1, ParkingLot o2) {
            return o1.capacity - o2.capacity;
        }
    };

    private int getFreeSpace() {
        return this.capacity - this.vehicles.size();
    }

    public static Comparator<ParkingLot> freeSpaceComparator = new Comparator<ParkingLot>() {
        @Override
        public int compare(ParkingLot o1, ParkingLot o2) {
            return o1.getFreeSpace() - o2.getFreeSpace();
        }
    };
}
