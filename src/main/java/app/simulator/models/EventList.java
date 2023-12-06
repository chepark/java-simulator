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

    public Event remove() {
        if (eventList.isEmpty()) {
            return null;
        }
        return eventList.remove();
    }

    public double getOldestEvent() {
        if (eventList.isEmpty()) {
            return 0;
        }

        return eventList.peek().getTime();
    }

    public boolean isEmpty() {
        return eventList.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (Event event : eventList) {
            str.append("").append(event.toString()).append("\n");
        }
        return str.toString();
    }

    public int size() {
        return eventList.size();
    }
}
