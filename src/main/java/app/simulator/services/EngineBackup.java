package app.simulator.services;

import app.simulator.models.*;
import app.simulator.types.ServicePointType;
import app.simulator.util.timeUtil.RandomTime;


public class EngineBackup extends Thread {
    private final ServicePointType[] servicePointTypes = ServicePointType.values();
    protected EventList eventList;
    protected EventList copiedEventList;
    private int numOfCustomers;
    private ServicePoint[] servicePoints;
    private boolean running = false;

    public EngineBackup() {
        //numOfCustomers = 6;
        numOfCustomers = RandomTime.getCustomerNumber();
        eventList = new EventList<Event>();
        servicePoints = new ServicePoint[6];

        servicePoints[0] = new ServicePoint(ServicePointType.QUEUE1, eventList); // Queue1
        servicePoints[1] = new ServicePoint(ServicePointType.PANTTI, eventList); // Pantti
        servicePoints[2] = new ServicePoint(ServicePointType.MARKET, eventList); // Market
        servicePoints[3] = new ServicePoint(ServicePointType.QUEUE2, eventList); // Queue2
        servicePoints[4] = new ServicePoint(ServicePointType.SELF_CHECKOUT, eventList); // SelfCheckout
        servicePoints[5] = new ServicePoint(ServicePointType.CASHIER, eventList);  // Cashier
    }

    public ServicePoint[] getServicePoints() {
        return servicePoints;
    }

    public void run () {
        initialize();
       // prepareEvents();
        // reset all service points to run the copiedEvents
        for (int i = 0; i < 6; i++) {
            servicePoints[i].reset();
        }
        running = true;
        //System.out.println(copiedEventList.toString());
        //processCopiedEventList();
        System.out.println(servicePoints[0].toString());
        System.out.println(servicePoints[3].toString());
    }

    protected void initialize() {
        // create arrival event
        for (int i = 0; i < numOfCustomers; i++) {
            Event arrivalEvent = Arrival.generateNextArrival();
            eventList.addEvent(arrivalEvent);
        }
    }
/*
    protected void prepareEvents() {
        Customer c;
        double t; // time
        //copiedEventList = new EventList<>();

        while (true) {
            Event eventToProcess = eventList.remove(); // first event in the eventList
            if (eventToProcess == null) {
                return;
            }
            //copiedEventList.addEvent(eventToProcess);
            t = eventToProcess.getTime();
            switch (eventToProcess.getServicePoint()) {
            case ServicePointType.ARRIVAL:
                if (eventToProcess.hasPantti()) {
                    servicePoints[0].addToQueue(new Customer());
                    eventList.addEvent(new Event(t, ServicePointType.QUEUE1));

                    if (servicePoints[0].getHandledCustomers() == 0) {
                        servicePoints[0].setStartTime(t);
                    }
                } else {
                    servicePoints[2].addToQueue(new Customer());
                    eventList.addEvent(new Event(t, ServicePointType.MARKET));
                }
                break;
            case ServicePointType.QUEUE1:
                t = t + RandomTime.generate(ServicePointType.QUEUE1);
                if (servicePoints[1].isIdle()) {
                    c = servicePoints[0].removeFromQueue();

                    servicePoints[0].setEndTime(t);

                    servicePoints[1].addToQueue(c);
                    servicePoints[1].addToQueue(c);
                    //
                    eventList.addEvent(new Event(t, ServicePointType.PANTTI));
                }
                break;
            case ServicePointType.PANTTI:
                c = servicePoints[1].removeFromQueue();
                servicePoints[1].setIdle(true);
                servicePoints[2].addToQueue(c);
                t = t + RandomTime.generateShoppingTime();
                eventList.addEvent(new Event(t, ServicePointType.MARKET));
                break;
            case ServicePointType.MARKET:
                if (servicePoints[3].isIdle() || servicePoints[2].getQueue().size() < 6) {
                    c = servicePoints[2].removeFromQueue();

                    if (servicePoints[3].getHandledCustomers() == 0) {
                        servicePoints[3].setStartTime(t);
                    }

                    servicePoints[3].addToQueue(c);
                    t = t + RandomTime.generate(ServicePointType.QUEUE2);
                    eventList.addEvent(new Event(t, ServicePointType.QUEUE2));

                }
                break;
            case ServicePointType.QUEUE2:
                if (servicePoints[4].isIdle()) {
                    c = servicePoints[3].removeFromQueue();

                    servicePoints[3].setEndTime(t);

                    servicePoints[4].addToQueue(c);
                    t = t + RandomTime.generate(ServicePointType.SELF_CHECKOUT);
                    eventList.addEvent(new Event(t, ServicePointType.SELF_CHECKOUT));
                } else if (servicePoints[5].isIdle()) {
                    c = servicePoints[3].removeFromQueue();

                    servicePoints[3].setEndTime(t);
                    servicePoints[5].addToQueue(c);
                    t = t + RandomTime.generate(ServicePointType.CASHIER);
                    eventList.addEvent(new Event(t, ServicePointType.CASHIER));
                }
                break;
            case ServicePointType.SELF_CHECKOUT:
                servicePoints[4].removeFromQueue();

                servicePoints[3].setEndTime(t);
                servicePoints[4].setIdle(true);
                break;
            case ServicePointType.CASHIER:
                servicePoints[5].removeFromQueue();

                servicePoints[3].setEndTime(t);
                servicePoints[5].setIdle(true);
                break;
            }
            System.out.println("EventList size: " + eventToProcess.toString());
            System.out.println(servicePoints[0].toString());
            System.out.println(servicePoints[1].toString());
            System.out.println(servicePoints[2].toString());
            System.out.println(servicePoints[3].toString());
            System.out.println(servicePoints[4].toString());
            System.out.println(servicePoints[5].toString());
        }

    }

    protected void processCopiedEventList() {
        Customer c;
        double time;

        while (running) {

            Event eventToProcess = copiedEventList.remove(); // first event in the eventList
            if (eventToProcess == null) {
                return;
            }
            time = eventToProcess.getTime();

            switch (eventToProcess.getServicePoint()) {
            case ServicePointType.ARRIVAL:
                if (eventToProcess.hasPantti()) {
                    servicePoints[0].addToQueue(new Customer());
                    // if this customer is the first customer in the queue, set the start time
                    if (servicePoints[0].getHandledCustomers() == 0) {
                        servicePoints[0].setStartTime(time);
                    }
                } else {
                    servicePoints[2].addToQueue(new Customer());
                }
                break;
            case ServicePointType.QUEUE1:
                if (servicePoints[1].isIdle()) {
                    c = servicePoints[0].removeFromQueue();
                    // if handled customer and total customer is same, set endtime
                    servicePoints[0].setEndTime(time);
                    servicePoints[1].addToQueue(c);
                }
                break;
            case ServicePointType.PANTTI:
                c = servicePoints[1].removeFromQueue();
                servicePoints[1].setIdle(true);
                servicePoints[2].addToQueue(c);
                break;
            case ServicePointType.MARKET:
                if (servicePoints[3].isIdle() || servicePoints[2].getQueue().size() < 6) {
                    c = servicePoints[2].removeFromQueue();
                    servicePoints[3].addToQueue(c);
                    // if this customer is the first customer in the queue, set the start time
                    if (servicePoints[3].getHandledCustomers() == 0) {
                        servicePoints[3].setStartTime(time);
                    }
                }
                break;
            case ServicePointType.QUEUE2:
                if (servicePoints[4].isIdle()) {
                    c = servicePoints[3].removeFromQueue();
                    // if this customer is the last customer in the queue, set the end time

                    servicePoints[3].setEndTime(time);

                    servicePoints[4].addToQueue(c);
                } else if (servicePoints[5].isIdle()) {
                    c = servicePoints[3].removeFromQueue();
                    // if this customer is the last customer in the queue, set the end time

                    servicePoints[3].setEndTime(time);

                    servicePoints[5].addToQueue(c);
                }
                break;
            case ServicePointType.SELF_CHECKOUT:
                servicePoints[4].removeFromQueue();
                servicePoints[4].setIdle(true);
                break;
            case ServicePointType.CASHIER:
                servicePoints[5].removeFromQueue();
                servicePoints[5].setIdle(true);
                break;
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");

            }
        }
    }
*/
    @Override
    public void interrupt() {
        running = false;
        super.interrupt();
    }
}