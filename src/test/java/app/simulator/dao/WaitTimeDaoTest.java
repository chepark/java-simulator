package app.simulator.dao;

import app.simulator.entity.WaitingTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/***
 * Test class for WaitingTimeDao
 * Data should be inserted to database before running tests
 * @see app.simulator.dao.WaitingTimeDao
 * @see app.simulator.entity.WaitingTime
 */
class WaitTimeDaoTest {
    private static WaitingTimeDao waitingTimeDao;
    @BeforeAll
    public static void setUp() throws Exception {
        waitingTimeDao = new WaitingTimeDao();
    }

    @Test
    public void testGetAllWaitingTimes() {
        List<WaitingTime> waitingTimeList = waitingTimeDao.getAllWaitTime();
        assertFalse(waitingTimeList.isEmpty());
    }

    @Test
    public void testGetWaitingTimeById() {
        WaitingTime waitingTime = waitingTimeDao.getWaitTime(1);
        assertNotNull(waitingTime);
    }
}