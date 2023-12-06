package app.simulator.models;

import app.simulator.types.ServicePointType;
import app.simulator.util.timeUtil.RandomTime;

import java.util.*;
import java.util.stream.DoubleStream;

import static app.simulator.types.ServicePointType.MARKET;

public class ServicePoint {
    ArrayList<Customer> queue;
    private int handledCustomers = 0;
    private ServicePointType type;
    private boolean isIdle = true;
    private EventList eventList;

    public ServicePoint(ServicePointType type, EventList eventlist) {
        this.type = type;
        this.eventList = eventlist;
        queue = new ArrayList<>();
    }

    public void addToQueue(Customer customer) {
        queue.addFirst(customer);
    }

    public Customer removeFromQueue() {
        if (!queue.isEmpty()) {
            Customer removedCustomer = queue.getLast();
            queue.removeLast();
            handledCustomers++;
            return removedCustomer;
        } else {
            return null;
        }
    }

    public ArrayList<Customer> getQueue() {
        return queue;
    }

    public ServicePointType getType() {
        return type;
    }

    public int getHandledCustomers() {
        return handledCustomers;
    }

    public boolean isIdle() {
        return isIdle;
    }

    public void setIdle(boolean idle) {
        isIdle = idle;
    }

    public void beginService(double t) {
        if (type == MARKET) {
            isIdle = true;
        } else isIdle = false; // If service point is market, it is always idle

        double serviceTime;

        if (type == MARKET && handledCustomers == 6) {
            serviceTime = t + RandomTime.generateShoppingTime();
            eventList.addEvent(new Event(serviceTime, type));
            Clock.getInstance().setTime(serviceTime);
        } else {
            serviceTime = t + RandomTime.generate(type);
            eventList.addEvent(new Event(serviceTime, type));
            Clock.getInstance().setTime(serviceTime);
        }

        System.out.println(eventList.toString());
    }
}
