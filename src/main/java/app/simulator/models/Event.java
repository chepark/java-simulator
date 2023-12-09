package app.simulator.models;

import app.simulator.types.CustomerType;
import app.simulator.types.EventType;
import app.simulator.types.ServicePointType;
import app.simulator.util.timeUtil.RandomTime;

import java.util.Random;

public class Event implements Comparable<Event> {

    private double time;
    private ServicePointType servicePoint;
    private CustomerType customerType;

    /***
     * Constructor for Event
     * @param t time of event
     * @param sp service point name: Queue1, Pantti, Market, Queue2, SelfCheckout, Checkout
     */
    public Event(double t, ServicePointType sp) {
        this.time = t;
        this.servicePoint = sp;
        setCustomerType();
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
            customerType = CustomerType.HAS_PANTTI;
        } else {
            customerType = CustomerType.HAS_NO_PANTTI;
        }
    }

    /***
     * @return time of event
     */
    public double getTime() {
        return time;
    }

    /***
     * @return service point name: Queue1, Pantti, Market, Queue2, SelfCheckout, Checkout
     */
    public ServicePointType getServicePoint() {
        return servicePoint;
    }


    public boolean hasPantti() {
        return customerType == CustomerType.HAS_PANTTI ? true : false;
    }

    /***
     * It is for priority queue to sort events by time
     * @param otherEvent other event to compare
     * @return -1 if this event is earlier than other event, 1 if this event is later than other event, 0 if they are equal
     */
    @Override
    public int compareTo(Event otherEvent) {
        if (time < otherEvent.time)
            return -1;
        else if (time > otherEvent.time)
            return 1;
        return 0;
    }

    /***
     * Print out the event information
     * @return string representation of event
     */
    @Override
    public String toString() {
        return " " +
                " servicePoint=" + servicePoint +
                ", time=" + time +
                ", customerType=" + customerType +
                +'}';
    }
}
