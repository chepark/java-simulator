package app.simulator.entity;

public class ServiceTime {
    private int id;
    private String serviceName;
    private int totalCustomers;
    private double startingTime;
    private double endingTime;
    private double averageServiceTime;

    // for fetching data
    public ServiceTime(int id, String serviceName, int totalCustomers, double startingTime, double endingTime, double averageServiceTime) {
        this.id = id;
        this.serviceName = serviceName;
        this.totalCustomers = totalCustomers;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.averageServiceTime = averageServiceTime;
    }

    // for saving data
    public ServiceTime(String serviceName, int totalCustomers, double startingTime, double endingTime, double averageServiceTime) {
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

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", totalCustomers=" + totalCustomers +
                ", startingTime=" + startingTime +
                ", endingTime=" + endingTime +
                ", averageServiceTime=" + averageServiceTime +
                '}';
    }
}
