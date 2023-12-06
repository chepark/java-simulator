package app.simulator.models;

import app.simulator.types.EventType;
import app.simulator.types.ServicePointType;
import app.simulator.util.timeUtil.RandomTime;

public class Event implements Comparable<Event> {
    private EventType eventType;
    private double time;
    private double shoppingTime;
    private ServicePointType servicePoint;

    public Event(EventType et, double t, ServicePointType sp) {
        this.eventType = et;
        this.time = t;
        this.servicePoint = sp;

        if (eventType == EventType.ARRIVAL && sp == ServicePointType.MARKET) {
            shoppingTime = RandomTime.generateShoppingTime();
        }
    }

    public EventType getEventType() {
        return eventType;
    }

    public double getTime() {
        return time;
    }

    public double getShoppingTime() {
        return shoppingTime;
    }

    public ServicePointType getServicePoint() {
        return servicePoint;
    }


    @Override
    public int compareTo(Event otherEvent) {
        if (time < otherEvent.time)
            return -1;
        else if (time > otherEvent.time)
            return 1;
        return 0;
    }
}
