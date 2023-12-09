package app.simulator.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrivalTest {
    /***
     * To test the value you need to understadn Poisson distribution and lamda.
     * You can change values of averageCustomers and timeIntervalsInHours
     */

    // check if the reseult is correct
    @Test
    public void testArrivalTimeGap() {
        for(int i=0; i<6; i++){
            Arrival.generateNextArrival();
        }
     }
}