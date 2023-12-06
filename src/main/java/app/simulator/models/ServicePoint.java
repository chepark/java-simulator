package app.simulator.models;

import app.simulator.types.ServicePointType;

import java.util.*;

import static app.simulator.types.ServicePointType.MARKET;

public class ServicePoint {
    LinkedList<Customer> queue;
    ArrayList<Customer> customers;
    private int handledCustomers = 0;
    private ServicePointType type;

    public ServicePoint(ServicePointType type) {
        this.type = type;
        queue = new LinkedList<Customer>();
    }

    public void addToQueue(Customer customer) {
        queue.addFirst(customer);
    }

    public Customer removeFromQueue() {
        Customer removedCustomer = queue.getLast();
        queue.removeLast();
        handledCustomers++;
        return removedCustomer;
    }

    public LinkedList<Customer> getQueue() {
        return queue;
    }

    public ServicePointType getType() {
        return type;
    }

    public int getHandledCustomers() {
        return handledCustomers;
    }
}
