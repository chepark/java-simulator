package app.simulator.models;

import app.simulator.types.CustomerType;
import app.simulator.types.EventType;
import app.simulator.types.ServicePointType;
import app.simulator.util.distributions.Normal;
import app.simulator.util.timeUtil.RandomTime;

import java.util.Random;

public class Customer {
    private static int counter = 1;
    private static int handledCustomers = 0;

    private int id;

    public Customer() {
        this.id = counter;
        counter++;
    }

    public static int getHandledCustomers() {
        return handledCustomers;
    }

    public int getId() {
        return id;
    }
}
