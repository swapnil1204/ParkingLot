package com.thoughtworks.parkinglot.dummy;

public class DummyFactory {
    public static DummyOwner getDummyOwner() {
        return new DummyOwner();
    }

    public static DummySecurityGuard getDummySecurityGuard() {
        return new DummySecurityGuard();
    }

    public static NewSubscriber getNewSubscriber() {
        return new NewSubscriber();
    }
}
