package app.simulator.services;

import app.simulator.dao.RecordDao;
import app.simulator.dao.WaitingTimeDao;
import app.simulator.entity.Record;
import app.simulator.entity.WaitingTime;
import app.simulator.models.*;
import app.simulator.types.ServicePointType;
import app.simulator.util.timeUtil.RandomTime;

public class Engine extends Thread {
    protected EventList eventList;
    private int numOfCustomers;
    private ServicePoint[] servicePoints;

    public Engine() {
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

    public void run() {
        initialize();
        processEvents();

        saveResultToDB(servicePoints[0]);
        saveResultToDB(servicePoints[3]);

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

    protected void processEvents() {
        Customer c;
        double t; // time

        while (true) {
            Event eventToProcess = eventList.remove(); // first event in the eventList
            if (eventToProcess == null) {
                return;
            }

            System.out.println("Event" + eventToProcess.toString());
            t = eventToProcess.getTime();
            switch (eventToProcess.getServicePoint()) {
            case ServicePointType.ARRIVAL:
                c = new Customer();
                if (eventToProcess.hasPantti()) {
                    servicePoints[0].addToQueue(c);
                    eventList.addEvent(new Event(t, ServicePointType.QUEUE1));

                    if (servicePoints[0].getHandledCustomers() == 0) {
                        servicePoints[0].setStartTime(t);
                    }
                    // save event record to db
                    saveEventRecordToDB(servicePoints[0]);
                } else {
                    servicePoints[2].addToQueue(c);
                    eventList.addEvent(new Event(t, ServicePointType.MARKET));
                    // save event record to db
                    saveEventRecordToDB(servicePoints[2]);
                }
                break;
            case ServicePointType.QUEUE1:
                t = t + RandomTime.generate(ServicePointType.QUEUE1);
                if (servicePoints[1].isIdle()) {
                    c = servicePoints[0].removeFromQueue();
                    servicePoints[0].setEndTime(t);
                    servicePoints[1].addToQueue(c);
                    eventList.addEvent(new Event(t, ServicePointType.PANTTI));
                    // save event record to db
                    saveEventRecordToDB(servicePoints[0]);
                    saveEventRecordToDB(servicePoints[1]);
                }
                break;
            case ServicePointType.PANTTI:
                c = servicePoints[1].removeFromQueue();
                servicePoints[1].setIdle(true);
                servicePoints[2].addToQueue(c);
                t = t + RandomTime.generateShoppingTime();
                eventList.addEvent(new Event(t, ServicePointType.MARKET));
                // save event record to db
                saveEventRecordToDB(servicePoints[1]);
                saveEventRecordToDB(servicePoints[2]);
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

                    // save event record to db
                    saveEventRecordToDB(servicePoints[2]);
                    saveEventRecordToDB(servicePoints[3]);
                }
                break;
            case ServicePointType.QUEUE2:
                if (servicePoints[4].isIdle()) {
                    c = servicePoints[3].removeFromQueue();
                    servicePoints[3].setEndTime(t);
                    servicePoints[4].addToQueue(c);
                    t = t + RandomTime.generate(ServicePointType.SELF_CHECKOUT);
                    eventList.addEvent(new Event(t, ServicePointType.SELF_CHECKOUT));

                    // save event record to db
                    saveEventRecordToDB(servicePoints[3]);
                    saveEventRecordToDB(servicePoints[4]);
                } else if (servicePoints[5].isIdle()) {
                    c = servicePoints[3].removeFromQueue();
                    servicePoints[3].setEndTime(t);
                    servicePoints[5].addToQueue(c);
                    t = t + RandomTime.generate(ServicePointType.CASHIER);
                    eventList.addEvent(new Event(t, ServicePointType.CASHIER));

                    // save event record to db
                    saveEventRecordToDB(servicePoints[3]);
                    saveEventRecordToDB(servicePoints[5]);
                }
                break;
            case ServicePointType.SELF_CHECKOUT:
                servicePoints[4].removeFromQueue();
                servicePoints[3].setEndTime(t);
                servicePoints[4].setIdle(true);

                // save event record to db
                saveEventRecordToDB(servicePoints[4]);

                break;
            case ServicePointType.CASHIER:
                servicePoints[5].removeFromQueue();
                servicePoints[3].setEndTime(t);
                servicePoints[5].setIdle(true);

                // save event record to db
                saveEventRecordToDB(servicePoints[5]);
                break;
            }
        }
    }

    protected void saveResultToDB(ServicePoint servicePoint) {
        WaitingTime w = new WaitingTime(servicePoint.getType().toString(), servicePoint.getHandledCustomers(), servicePoint.getStartTime(), servicePoint.getEndTime(), servicePoint.getAvgWaitingTime());
        WaitingTimeDao waitingTimeDao = new WaitingTimeDao();
        waitingTimeDao.putWaitTime(w);
        System.out.println("Data saved!");
    }

    protected void saveEventRecordToDB(ServicePoint servicePoint) {
        Record r = new Record(servicePoint);
        RecordDao recordDao = new RecordDao();
        recordDao.saveRecord(r);
    }
}
