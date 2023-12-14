package app.simulator.models;

/***
 * Customer to queue in each service point.
 */
public class Customer {
    /***
     * Counter to create customer ids by incrementing 1.
     */
    private static int counter = 1;
    /***
     * Customer id
     */
    private int id;

    /***
     * Constructor to create a customer with an id.
     * Increment the counter by 1.
     */
    public Customer() {
        this.id = counter;
        counter++;
    }

    /***
     * Get the customer id.
     * @return customer id
     */
    public int getId() {
        return id;
    }
}
