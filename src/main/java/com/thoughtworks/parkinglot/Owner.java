package com.thoughtworks.parkinglot;

public class Owner {
    private String message;

    public String getNotification() {
        return message;
    }

    public void notify(String message) {
        this.message = message;
    }
}

