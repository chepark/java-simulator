package app.simulator.entity;

import app.simulator.models.ServicePoint;

import java.util.ArrayList;

public class Record {
    private int id;
    private String servicePoint;
    private String customers;

    public Record(ServicePoint servicePoint) {
        this.servicePoint = servicePoint.getType().toString();
        this.customers = servicePoint.getQueueString();
    }

    public Record(int id, String servicePoint, String customers) {
        this.id = id;
        this.servicePoint = servicePoint;
        this.customers = customers;
    }

    public String getCustomers() {
  return customers;
    }

    public void setCustomers(String customers) {
        this.customers = customers;
    }

    public String getServicePoint() {
        return servicePoint;
    }

    public void setServicePoint(String servicePoint) {
        this.servicePoint = servicePoint;
    }
}
