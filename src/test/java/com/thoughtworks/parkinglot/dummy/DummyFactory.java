package com.thoughtworks.parkinglot.dummy;

public class DummyFactory {

    public static DummySubscriber getDummyOwner() {
        return new DummySubscriber();
    }

    public static DummySubscriber getDummySecurityGuard() {
        return new DummySubscriber();
    }

    public static DummySubscriber getNewSubscriber() {
        return new DummySubscriber();
    }

}
