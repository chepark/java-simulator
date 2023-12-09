package app.simulator.models;

import app.simulator.types.ServicePointType;


import java.util.*;

public class ServicePoint {
    ArrayList<Customer> queue;
    double startTime;
    double endTime;
    private int handledCustomers = 0;
    private ServicePointType type;
    private boolean isIdle = true;
    private EventList eventList;

    public ServicePoint(ServicePointType type, EventList eventlist) {
        this.type = type;
        this.eventList = eventlist;
        queue = new ArrayList();
    }

    public void addToQueue(Customer c) {
        queue.addFirst(c);
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

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public ArrayList<Customer> getQueue() {
        return queue;
    }

    public String getQueueString() {
        StringJoiner joiner = new StringJoiner(",");
        for (Customer customer : queue) {
            joiner.add(Integer.toString(customer.getId()));
        }
        return joiner.toString();
    }

    public int getQueueSize() {
        return queue.size();
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

    public double getAvgWaitingTime() {
        if (handledCustomers != 0) {
            return (endTime - startTime) / handledCustomers;
        } else {
            return 0; // Or any default value you prefer when no customers are handled
        }
    }

    public void reset() {
        handledCustomers = 0;
        startTime = 0;
        endTime = 0;
        isIdle = true;
        queue.clear();
    }

    @Override
    public String toString() {
        return "ServicePoint{" +
                "queue=" + queue +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", handledCustomers=" + handledCustomers +
                ", averageWaitingTime=" + getAvgWaitingTime() +
                ", type=" + type +
                ", queue=" + getQueueString() +
                '}';
    }


}