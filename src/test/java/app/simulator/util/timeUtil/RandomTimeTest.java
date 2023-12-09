package app.simulator.util.timeUtil;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomTimeTest {
    private RandomTime rt;

    @Test
    public void testGenerateShoppingTime() {
        int shoppingTime;

        for(int i = 0; i < 5 ; i++){
            shoppingTime = (int) RandomTime.generateShoppingTime();
            System.out.println(shoppingTime);
            assertTrue(shoppingTime >= 1 && shoppingTime <= 30);
        }
    }

    @Test
    public void testGenerateArrivalTime() {
        int arrivalTime;

            arrivalTime = (int)  RandomTime.generateArrivalTimeGap();
            //assertTrue(arrivalTime >= 2 && arrivalTime <= 10);
            System.out.println(arrivalTime);
        assertTrue(arrivalTime >= 0 && arrivalTime <= 10);
    }
}