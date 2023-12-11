package app.simulator.models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static app.simulator.types.ServicePointType.PANTTI;
import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    private static Event event;

    @BeforeAll
    public static void setup() throws Exception {
        Customer c = new Customer();
        event = new Event(10.0, PANTTI);
    }

    @Test
    @DisplayName("Test time is set correctly")
    public void testGetTime() {
        assertEquals(10.0, event.getTime());
    }

    @Test
    @DisplayName("Test ServicePointType is set correctly")
    public void testServicePointType() {
        assertEquals(PANTTI, event.getServicePoint());
    }
}