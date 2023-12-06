package app.simulator.models;

import app.simulator.types.EventType;
import app.simulator.types.ServicePointType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    private static Event event;

    @BeforeAll
    public static void setup() throws Exception {
        Customer c = new Customer();
        event = new Event(EventType.DONE, 10.0, ServicePointType.PANTTI);
    }

    @Test
    public void testGetTime() {
        assertEquals(10.0, event.getTime());
    }

    @Test
    public void testServicePointType() {
        assertEquals(ServicePointType.PANTTI, event.getServicePoint());
    }
}