package app.simulator.models;

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

    public String getServicePoint() {
        return servicePoint;
    }
    
}
