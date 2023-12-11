package app.simulator.models;

/***
 * Entity for the simulation result of waiting time of queue1 and queue2
 */
public class WaitingTime {
    private int id;
    private String serviceName;
    private int totalCustomers;
    private double startingTime;
    private double endingTime;
    private double averageWaitingTime;

    // for fetching data
    public WaitingTime(int id, String serviceName, int totalCustomers, double startingTime, double endingTime, double averageWaitingTime) {
        this.id = id;
        this.serviceName = serviceName;
        this.totalCustomers = totalCustomers;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.averageWaitingTime = averageWaitingTime;
    }

    // for saving data
    public WaitingTime(String serviceName, int totalCustomers, double startingTime, double endingTime, double averageWaitingTime) {
        this.serviceName = serviceName;
        this.totalCustomers = totalCustomers;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.averageWaitingTime = averageWaitingTime;
    }

    public String getServiceName() {
        return serviceName;
    }


    public int getTotalCustomers() {
        return totalCustomers;
    }


    public double getStartingTime() {
        return startingTime;
    }


    public double getEndingTime() {
        return endingTime;
    }


    public double getAverageWaitingTime() {
        return averageWaitingTime;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", totalCustomers=" + totalCustomers +
                ", startingTime=" + startingTime +
                ", endingTime=" + endingTime +
                ", averageWaitingTime=" + averageWaitingTime +
                '}';
    }
}
