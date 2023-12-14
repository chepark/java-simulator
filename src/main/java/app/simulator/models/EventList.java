package app.simulator.models;

import java.util.PriorityQueue;

/***
 * Event list for the simulation.
 * @param <E> Event type
 */
public class EventList<E> {
    /***
     * Priority queue to sort events by time.
     */
    private PriorityQueue<Event> eventList;

    /***
     * Constructor to create an event list.
     */
    public EventList() {
        eventList = new PriorityQueue<>();
    }

    /***
     * Add an event to the event list.
     * @param event event to add
     */
    public void addEvent(Event event) {
        eventList.add(event);
    }

    /***
     * Get the first event from the event list to remove.
     * @return first event
     */
    public Event remove() {
        if (eventList.isEmpty()) {
            return null;
        }
        return eventList.remove();
    }

    /***
     * Check the event information stored in the event list.
     * @return event information
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (Event event : eventList) {
            str.append(event.toString()).append("\n");
        }
        return str.toString();
    }

}
