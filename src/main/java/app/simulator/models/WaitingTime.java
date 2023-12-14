package app.simulator.models;

/***
 * Entity for the simulation result of waiting time of queue1 and queue2
 */
public class WaitingTime {
    /**
     * id of the waiting time
     */
    private int id;
    /**
     * name of the service: Queue1 or Queue2
     */
    private String serviceName;
    /**
     * total customers handled during one session in the service
     */
    private int totalCustomers;
    /**
     * starting time of the service
     */
    private double startingTime;
    /**
     * ending time of the service
     */
    private double endingTime;
    /**
     * average waiting time of the service
     */
    private double averageWaitingTime;

    /**
     * constructor for waiting time, used when fetching data.
     *
     * @param id                 id of the waiting time saved in DB
     * @param serviceName        name of the service
     * @param totalCustomers     total customers handled in the service
     * @param startingTime       starting time of the service
     * @param endingTime         ending time of the service
     * @param averageWaitingTime average waiting time of the service
     */
    public WaitingTime(int id, String serviceName, int totalCustomers, double startingTime, double endingTime, double averageWaitingTime) {
        this.id = id;
        this.serviceName = serviceName;
        this.totalCustomers = totalCustomers;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.averageWaitingTime = averageWaitingTime;
    }

    /**
     * constructor for waiting time, used when saving data.
     *
     * @param serviceName        name of the service
     * @param totalCustomers     total customers handled in the service
     * @param startingTime       starting time of the service
     * @param endingTime         ending time of the service
     * @param averageWaitingTime average waiting time of the service
     */
    public WaitingTime(String serviceName, int totalCustomers, double startingTime, double endingTime, double averageWaitingTime) {
        this.serviceName = serviceName;
        this.totalCustomers = totalCustomers;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.averageWaitingTime = averageWaitingTime;
    }

    /**
     * Get the service name
     *
     * @return service name
     */
    public String getServiceName() {
        return serviceName;
    }


    /**
     * Get the total number of customers handled in the service
     *
     * @return total number of customers
     */
    public int getTotalCustomers() {
        return totalCustomers;
    }

    /**
     * Get the starting time of the service
     *
     * @return starting time of the service
     */
    public double getStartingTime() {
        return startingTime;
    }


    /**
     * Get the ending time of the service
     *
     * @return ending time of the service
     */
    public double getEndingTime() {
        return endingTime;
    }

    /**
     * Get the average waiting time of the service
     *
     * @return average waiting time of the service
     */
    public double getAverageWaitingTime() {
        return averageWaitingTime;
    }

    /**
     * Get the data of the waiting time
     *
     * @return string of the waiting time data
     */
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
