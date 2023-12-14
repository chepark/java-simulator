package app.simulator.models;

/***
 * Singleton class for keeping track of time.
 */
public class Clock {
    /***
     * Singleton instance
     */
    private static Clock instance;
    /***
     * Time counter
     */
    private double time = 0;

    /***
     * Get singleton instance
     * @return Clock instance
     */
    public static Clock getInstance() {
        if (instance == null) {
            instance = new Clock();
        }
        return instance;
    }

    /***
     * Get the current time value
     * @return time of the clock instance
     */
    public double getTime() {
        return time;
    }

    /***
     * Set the time value
     * @param time time to set
     */
    public void setTime(double time) {
        this.time = time;
    }
}
