package app.simulator.models;

/***
 * Entity to map the event table in the database.
 * This is used to save all events in service points and their customers.
 */
public class Record {
    /***
     * Id of the record
     */
    private int id;
    /***
     * Service point name
     */
    private String servicePoint;
    /***
     * Customer ids in the service point
     */
    private String customers;

    /***
     * Constructor to create a record.
     * @param servicePoint service point name
     */
    public Record(ServicePoint servicePoint) {
        this.servicePoint = servicePoint.getType().toString();
        this.customers = servicePoint.getQueueString();
    }

    /***
     * Constructor to used to fetch data by id from the database.
     * @param id id of the record
     * @param servicePoint service point name
     * @param customers customer ids in the service point
     */
    public Record(int id, String servicePoint, String customers) {
        this.id = id;
        this.servicePoint = servicePoint;
        this.customers = customers;
    }

    /***
     * Get the id of the record.
     * @return id of the record
     */
    public String getCustomers() {
        return customers;
    }

    /***
     * Get the service point name.
     * @return service point name
     */
    public String getServicePoint() {
        return servicePoint;
    }

}
