package app.simulator.models;

import app.simulator.types.ServicePointType;
import app.simulator.util.timeUtil.RandomTime;

/**
 * Class for generating arrival events.
 */
public class Arrival {
    /**
     * Generates an arrival event.
     *
     * @return Event
     */
    public static Event generateNextArrival() {
        double clockTime = Clock.getInstance().getTime();

        double arrivalTime = clockTime + RandomTime.generateArrivalTimeGap();

        Customer customer = new Customer();

        ServicePointType servicePointType = ServicePointType.ARRIVAL;

        Event arrivalEvent = new Event(arrivalTime, servicePointType);
        Clock.getInstance().setTime(arrivalTime);

        System.out.println("Customer " + customer.getId() + " arrives at " + arrivalTime + " to " + servicePointType);
        return arrivalEvent;
    }
}
