package app.simulator.models;


public class Customer {
    private static int counter = 1;

    private int id;

    public Customer() {
        this.id = counter;
        counter++;
    }

    public int getId() {
        return id;
    }
}
