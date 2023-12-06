package app.simulator.services;

import app.simulator.models.*;
import app.simulator.types.CustomerType;
import app.simulator.types.EventType;
import app.simulator.types.ServicePointType;
import app.simulator.util.timeUtil.RandomTime;

import java.util.ArrayList;
import java.util.LinkedList;

public class Engine {
    private final LinkedList<ServicePoint> servicePoints = new LinkedList<>();
    private final ServicePointType[] servicePointTypes = ServicePointType.values();
    private int numberOfCustomers;

    public Engine(int numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
    }

    private boolean simulationOn () {
        if (servicePoints.get(5).getHandledCustomers() == numberOfCustomers)
            return false;
        else
            return true;
    }

    public void run () {
        initialize();
        runService();
    }

    protected void initialize() {
        // initialize the simulator
        // 1. set the list of service points
        for (int i = 0; i < 6; i++) {
            servicePoints.add(new ServicePoint(servicePointTypes[i]));
        }

        // 2. create customers
        int numberOfCustomers = 3; // TODO: get the data from input
        for (int i = 0; i < numberOfCustomers; i++) {
            Customer c = new Customer();
            new Arrival(c, servicePoints);
        }
    }

    protected void runService() {
        while (simulationOn()) {
         for(int i = 0; i < servicePoints.size(); i++) {
             ServicePoint sp = servicePoints.get(i);
             Customer c = sp.removeFromQueue(); // !!! There could be no customer in the queue
             double time = c.getExperiences().getRecentEvent().getTime();

             ServicePoint nextSp;

             if (sp.getType() == ServicePointType.QUEUE1) {
               nextSp = servicePoints.get(i+1);
               // If Pantti has no queue.
                if (nextSp.getQueue().size() == 0) {
                     // define the removal time randomly
                     time = time + RandomTime.generate(ServicePointType.QUEUE1);
                     // set the removal event
                     Event awayEvent = new Event(EventType.AWAY, time, ServicePointType.QUEUE1);
                     c.setExperiences(awayEvent);
                     // set the arrival event for the next service point
                    Event arrivalEvent = new Event(EventType.ARRIVAL, time, ServicePointType.PANTTI);
                    c.setExperiences(arrivalEvent);
                    // add the customer to the next queue
                    nextSp.addToQueue(c);
                }
             } else if (sp.getType() == ServicePointType.PANTTI) {
                  time = time + RandomTime.generate(ServicePointType.PANTTI);
                    Event awayEvent = new Event(EventType.AWAY, time, ServicePointType.PANTTI);
                    c.setExperiences(awayEvent);

                    Event arrivalEvent = new Event(EventType.ARRIVAL, time, ServicePointType.MARKET);
                    c.setExperiences(arrivalEvent);

                    nextSp = servicePoints.get(i+1); // Market service point
                    nextSp.addToQueue(c);
             } else if (sp.getType() == ServicePointType.MARKET) {

             } else if (sp.getType() == ServicePointType.QUEUE2) {

             } else if (sp.getType() == ServicePointType.SELF_CHECKOUT || sp.getType() == ServicePointType.CASHIER){

             }

         }
        }
    }
}

