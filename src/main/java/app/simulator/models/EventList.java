package app.simulator.models;

import java.util.PriorityQueue;

public class EventList<E> {
    private PriorityQueue<Event> eventList;

    public EventList() {
        eventList = new PriorityQueue<Event>();
    }

    public void addEvent(Event event) {
        eventList.add(event);
    }

    public Event getRecentEvent() {
        return eventList.peek();
    }
}
