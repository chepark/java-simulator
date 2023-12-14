package app.simulator.models;

import app.simulator.types.ServicePointType;

import java.util.*;

/***
 * It is the main class to get the average waiting time for each service point.
 */
public class ServicePoint {
    /***
     * Queue for each service point.
     */
    ArrayList<Customer> queue;
    /***
     * Start time of the service point.
     * It is the time when the first customer arrives to the service point.
     */
    double startTime;
    /***
     * End time of the service point.
     * It is the time when the last customer leaves the service point.
     */
    double endTime;
    /***
     * Number of customers handled by the service point.
     */
    private int handledCustomers = 0;
    /***
     * Type of the service point: QUEUE1, PANTTI, MARKET, QUEUE2, SELF_CHECKOUT, CASHIER
     */
    private ServicePointType type;
    /***
     * Flag to check if the service point has no customers.
     */
    private boolean isIdle = true;

    /***
     * Constructor for ServicePoint
     * @param type Service point type: QUEUE1, PANTTI, MARKET, QUEUE2, SELF_CHECKOUT, CASHIER
     */
    public ServicePoint(ServicePointType type) {
        this.type = type;
        queue = new ArrayList<>();
    }

    /***
     * Add a customer to the queue.
     * @param c Customer
     */
    public void addToQueue(Customer c) {
        queue.addFirst(c);
    }

    /***
     * Remove a customer from the queue. First in first out.
     * @return Customer
     */
    public Customer removeFromQueue() {
        if (!queue.isEmpty()) {
            Customer removedCustomer = queue.getLast();
            queue.removeLast();
            handledCustomers++;
            return removedCustomer;
        } else {
            return null;
        }
    }

    /***
     * Getter for startTime
     * @return startTime
     */
    public double getStartTime() {
        return startTime;
    }

    /***
     * Setter for startTime
     * @param startTime startTime
     */
    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    /***
     * Getter for endTime
     * @return endTime
     */
    public double getEndTime() {
        return endTime;
    }

    /***
     * Setter for endTime
     * @param endTime endTime
     */
    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    /***
     * Getter for queue
     * @return queue
     */
    public ArrayList<Customer> getQueue() {
        return queue;
    }

    /***
     * Get the queue as a string to save into the record table in the db.
     * @return queue as a string
     */
    public String getQueueString() {
        StringJoiner joiner = new StringJoiner(",");
        for (Customer customer : queue) {
            joiner.add(Integer.toString(customer.getId()));
        }
        return joiner.toString();
    }

    /***
     * Getter for type
     * @return service point type
     */
    public ServicePointType getType() {
        return type;
    }

    /***
     * Getter for handledCustomers
     * @return handledCustomers
     */
    public int getHandledCustomers() {
        return handledCustomers;
    }

    /***
     * Getter for isIdle
     * @return isIdle. Status of the service point
     */
    public boolean isIdle() {
        return isIdle;
    }

    /***
     * Setter for isIdle
     * @param idle status of the service point
     */
    public void setIdle(boolean idle) {
        isIdle = idle;
    }

    /***
     * Get the average waiting time for the service point.
     * @return average waiting time
     */
    public double getAvgWaitingTime() {
        if (handledCustomers != 0) {
            return (endTime - startTime) / handledCustomers;
        } else {
            return 0; // Or any default value you prefer when no customers are handled
        }
    }

    /***
     * Print out the service point information
     * @return String of the service point information
     */
    @Override
    public String toString() {
        return "ServicePoint{" +
                "queue=" + queue +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", handledCustomers=" + handledCustomers +
                ", averageWaitingTime=" + getAvgWaitingTime() +
                ", type=" + type +
                ", queue=" + getQueueString() +
                '}';
    }
}