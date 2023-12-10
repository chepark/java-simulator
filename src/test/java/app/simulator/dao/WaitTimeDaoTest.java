package app.simulator.dao;

import app.simulator.entity.WaitingTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WaitTimeDaoTest {
    private static WaitingTimeDao waitingTimeDao;
    // test fetching data
    @BeforeAll
    public static void setUp() throws Exception {
        waitingTimeDao = new WaitingTimeDao();
    }

    @Test
    public void testGetAllWaitingTimes() {
        List<WaitingTime> waitingTimeList = waitingTimeDao.getAllWaitTime();
        assertTrue(waitingTimeList.size() > 0);
    }
}