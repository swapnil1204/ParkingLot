package com.thoughtworks.parkinglot;

public class Owner {
    String message; //TODO -  make it private

    public String getNotification() {
        return message;
    }

    public void notify(String message) {
        this.message = message;
    }
}

