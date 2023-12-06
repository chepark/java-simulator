package app.simulator.models;

import app.simulator.types.CustomerType;
import app.simulator.types.EventType;
import app.simulator.types.ServicePointType;
import app.simulator.util.distributions.Normal;
import app.simulator.util.timeUtil.RandomTime;

import java.util.Random;

public class Customer {
    private int counter = 1;
    private int id;
    private CustomerType type;
    private double arrivalTime;
    private double checkoutTime;
    private EventList<Event> experiences; // history of customer events

    public Customer() {
        this.id = counter;
        setCustomerType();
        experiences = new EventList<>();
    }

    public int getId() {
        return id;
    }

    public CustomerType getType() {
        return type;
    }

    public EventList<Event> getExperiences() {
        return experiences;
    }

    public void setExperiences(Event e) {
        experiences.addEvent(e);
    }


    /***
     * Sets customer type randomly
     * if random number is less than 5, customer has pantti
     * else customer has no pantti.
     */
    private void setCustomerType() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(10) + 1;

        if (randomNumber < 5) {
            type = CustomerType.HAS_PANTTI;
        } else {
            type = CustomerType.HAS_NO_PANTTI;
        }
    }
}
