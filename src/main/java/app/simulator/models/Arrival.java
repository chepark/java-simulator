package app.simulator.models;

import app.simulator.types.CustomerType;
import app.simulator.types.EventType;
import app.simulator.types.ServicePointType;
import app.simulator.util.timeUtil.RandomTime;

import java.util.LinkedList;

public class Arrival {
    public Arrival(Customer c, LinkedList<ServicePoint> servicePoints) {
        if (c.getType() == CustomerType.HAS_PANTTI) {
            // set time to pass to Event by calculating current time + random time
            Clock.getInstance().setTime(Clock.getInstance().getTime() + RandomTime.generate(ServicePointType.QUEUE1));
            // create event
            Event event = new Event(EventType.ARRIVAL, Clock.getInstance().getTime(), ServicePointType.QUEUE1);
            c.setExperiences(event);
            servicePoints.get(0).addToQueue(c);
        } else if (c.getType() == CustomerType.HAS_NO_PANTTI) {
            Clock.getInstance().setTime(Clock.getInstance().getTime() + RandomTime.generate(ServicePointType.MARKET));
            Event event = new Event(EventType.ARRIVAL, Clock.getInstance().getTime(), ServicePointType.MARKET);
            c.setExperiences(event);
            servicePoints.get(2).addToQueue(c);
        }
    }
}
