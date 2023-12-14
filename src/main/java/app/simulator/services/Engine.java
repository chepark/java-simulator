package app.simulator.services;

import app.simulator.dao.RecordDao;
import app.simulator.dao.WaitingTimeDao;
import app.simulator.models.Record;
import app.simulator.models.WaitingTime;
import app.simulator.models.*;
import app.simulator.types.ServicePointType;
import app.simulator.util.timeUtil.RandomTime;

import static app.simulator.types.ServicePointType.*;

public class Engine extends Thread {
    protected EventList eventList;
    private int numOfCustomers;
    private ServicePoint[] servicePoints;

    public Engine() {
        numOfCustomers = RandomTime.getCustomerNumber();
        eventList = new EventList<Event>();
        servicePoints = new ServicePoint[6];

        servicePoints[0] = new ServicePoint(QUEUE1); // Queue1
        servicePoints[1] = new ServicePoint(PANTTI); // Pantti
        servicePoints[2] = new ServicePoint(MARKET); // Market
        servicePoints[3] = new ServicePoint(QUEUE2); // Queue2
        servicePoints[4] = new ServicePoint(SELF_CHECKOUT); // SelfCheckout
        servicePoints[5] = new ServicePoint(CASHIER);  // Cashier
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

        // process events by removing the first event from the eventList
        // when an event is removed,
        // 1) add or remove customers from the queue in the service point
        // 2) set start time or end time of the service point
        // 3) save the service point record to db
        while (true) {
            Event eventToProcess = eventList.remove(); // first event in the eventList
            if (eventToProcess == null) {
                return;
            }

            System.out.println("Event" + eventToProcess.toString());
            t = eventToProcess.getTime();
            ServicePointType servicePointType = eventToProcess.getServicePoint();
            switch (servicePointType) {
            case ARRIVAL:
                c = new Customer();
                if (eventToProcess.hasPantti()) {
                    servicePoints[0].addToQueue(c);
                    eventList.addEvent(new Event(t, QUEUE1));

                    if (servicePoints[0].getHandledCustomers() == 0) {
                        servicePoints[0].setStartTime(t);
                    }
                    // save event record to db
                    saveEventRecordToDB(servicePoints[0]);
                } else {
                    servicePoints[2].addToQueue(c);
                    eventList.addEvent(new Event(t, MARKET));
                    // save event record to db
                    saveEventRecordToDB(servicePoints[2]);
                }
                break;
            case QUEUE1:
                t = t + RandomTime.generate(QUEUE1);
                if (servicePoints[1].isIdle()) {
                    c = servicePoints[0].removeFromQueue();
                    servicePoints[0].setEndTime(t);
                    servicePoints[1].addToQueue(c);
                    eventList.addEvent(new Event(t, PANTTI));
                    // save event record to db
                    saveEventRecordToDB(servicePoints[0]);
                    saveEventRecordToDB(servicePoints[1]);
                }
                break;
            case PANTTI:
                c = servicePoints[1].removeFromQueue();
                servicePoints[1].setIdle(true);
                servicePoints[2].addToQueue(c);
                t = t + RandomTime.generateShoppingTime();
                eventList.addEvent(new Event(t, MARKET));
                // save event record to db
                saveEventRecordToDB(servicePoints[1]);
                saveEventRecordToDB(servicePoints[2]);
                break;
            case MARKET:
                if (servicePoints[3].isIdle() || servicePoints[2].getQueue().size() < 6) {
                    c = servicePoints[2].removeFromQueue();

                    if (servicePoints[3].getHandledCustomers() == 0) {
                        servicePoints[3].setStartTime(t);
                    }

                    servicePoints[3].addToQueue(c);
                    t = t + RandomTime.generate(QUEUE2);
                    eventList.addEvent(new Event(t, QUEUE2));

                    // save event record to db
                    saveEventRecordToDB(servicePoints[2]);
                    saveEventRecordToDB(servicePoints[3]);
                }
                break;
            case QUEUE2:
                if (servicePoints[4].isIdle()) {
                    c = servicePoints[3].removeFromQueue();
                    servicePoints[3].setEndTime(t);
                    servicePoints[4].addToQueue(c);
                    t = t + RandomTime.generate(SELF_CHECKOUT);
                    eventList.addEvent(new Event(t, SELF_CHECKOUT));

                    // save event record to db
                    saveEventRecordToDB(servicePoints[3]);
                    saveEventRecordToDB(servicePoints[4]);
                } else if (servicePoints[5].isIdle()) {
                    c = servicePoints[3].removeFromQueue();
                    servicePoints[3].setEndTime(t);
                    servicePoints[5].addToQueue(c);
                    t = t + RandomTime.generate(CASHIER);
                    eventList.addEvent(new Event(t, CASHIER));

                    // save event record to db
                    saveEventRecordToDB(servicePoints[3]);
                    saveEventRecordToDB(servicePoints[5]);
                }
                break;
            case SELF_CHECKOUT:
                servicePoints[4].removeFromQueue();
                servicePoints[3].setEndTime(t);
                servicePoints[4].setIdle(true);

                // save event record to db
                saveEventRecordToDB(servicePoints[4]);

                break;
            case CASHIER:
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
    }

    protected void saveEventRecordToDB(ServicePoint servicePoint) {
        Record r = new Record(servicePoint);
        RecordDao recordDao = new RecordDao();
        recordDao.saveRecord(r);
    }
}
