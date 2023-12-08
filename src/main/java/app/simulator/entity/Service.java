package app.simulator.entity;

public class Service {
    private String serviceName;
    private int totalCustomers;
    private double startingTime;
    private double endingTime;
    private double averageServiceTime;

    public Service(String serviceName, int totalCustomers, double startingTime, double endingTime, double averageServiceTime) {
        this.serviceName = serviceName;
        this.totalCustomers = totalCustomers;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.averageServiceTime = averageServiceTime;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(int totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public double getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(double startingTime) {
        this.startingTime = startingTime;
    }

    public double getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(double endingTime) {
        this.endingTime = endingTime;
    }

    public double getAverageServiceTime() {
        return averageServiceTime;
    }

    public void setAverageServiceTime(double averageServiceTime) {
        this.averageServiceTime = averageServiceTime;
    }

}
