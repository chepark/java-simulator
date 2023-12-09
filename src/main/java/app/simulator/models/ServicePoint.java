package app.simulator.models;

import app.simulator.types.ServicePointType;


import java.util.*;

public class ServicePoint {
    ArrayList<Integer> queue;
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

    public void addToQueue(int customerId) {
        queue.addFirst(customerId);
    }

    public Integer removeFromQueue() {
        if (!queue.isEmpty()) {
            int removedCustomer = queue.getLast();
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

    public ArrayList<Integer> getQueue() {
        return queue;
    }

    public ArrayList<String> getQueueString() {
        ArrayList<String> queueString = new ArrayList<>();
        for (int i = 0; i < queue.size(); i++) {
            queueString.add(Integer.toString(queue.get(i)));
        }
        return queueString;
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

    public double getAverageWaitingTime() {
        return (endTime - startTime) / handledCustomers;
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
                ", averageWaitingTime=" + getAverageWaitingTime() +
                ", type=" + type +
                ", queue=" + getQueueString() +
                '}';
    }


}