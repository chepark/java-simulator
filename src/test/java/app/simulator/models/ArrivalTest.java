package app.simulator.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrivalTest {
    /***
     * To test the value you need to understadn Poisson distribution and lamda.
     * You can change values of averageCustomers and timeIntervalsInHours
     */
    @Test
    public void testArrivalTimeGap() {
        for(int i=0; i<6; i++){
            Arrival.generateNextArrival();
        }

        // check number of arrival is correct-
     }
}