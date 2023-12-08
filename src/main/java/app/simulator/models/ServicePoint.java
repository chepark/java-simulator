package app.simulator.models;

import app.simulator.types.ServicePointType;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.*;

public class ServicePoint {
    //ArrayList<Customer> queue;
    //ObservableList<Customer> oq;

    ObservableList<Customer> queue = FXCollections.observableArrayList();
    ;
    double startTime;
    double endTime;
    private int handledCustomers = 0;
    private ServicePointType type;
    private boolean isIdle = true;
    private EventList eventList;

    public ServicePoint(ServicePointType type, EventList eventlist) {
        this.type = type;
        this.eventList = eventlist;
        //queue = new ArrayList ();

        queue.addListener(new ListChangeListener() { // add an event listerer for the observable list
            @Override
            public void onChanged(
                    ListChangeListener.Change c) { // Method that will execute when any changes occured
                //System.out.println("Changes found ...  " + c.getList() + type); // Show a message that a change occured
            }
        });
    }

    public void removeListener() {
        queue.removeListener(new ListChangeListener() { // add an event listerer for the observable list
            @Override
            public void onChanged(
                    ListChangeListener.Change c) { // Method that will execute when any changes occured
                // System.out.println("Removing listener  " + c.getList()); // Show a message that a change occured
            }
        });
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
            // add remove listener here?
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

    public ObservableList<Customer> getQueue() {
        return queue;
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
                ", isIdle=" + isIdle +
                '}';
    }


}