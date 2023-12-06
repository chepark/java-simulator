package app.simulator.models;

/***
 * Singleton class for keeping track of time.
 */
public class Clock {
    private static Clock instance;
    private double time = 0;

    public static Clock getInstance() {
        if (instance == null) {
            instance = new Clock();
        }
        return instance;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }
}
