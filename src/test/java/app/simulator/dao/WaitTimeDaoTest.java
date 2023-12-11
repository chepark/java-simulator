package app.simulator.dao;

import app.simulator.models.WaitingTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/***
 * Test class for WaitingTimeDao
 * Data should be inserted to database before running tests
 * @see app.simulator.dao.WaitingTimeDao
 * @see WaitingTime
 */
@DisplayName("Test WaitingTimeDao class")
class WaitTimeDaoTest {
    private static WaitingTimeDao waitingTimeDao;
    @BeforeAll
    public static void setUp() throws Exception {
        waitingTimeDao = new WaitingTimeDao();
    }

    @Test
    @DisplayName("Test fetching all waiting times from database")
    public void testGetAllWaitingTimes() {
        List<WaitingTime> waitingTimeList = waitingTimeDao.getAllWaitTime();
        assertFalse(waitingTimeList.isEmpty());
    }

    @Test
    @DisplayName("Test fetching waiting time by id from database")
    public void testGetWaitingTimeById() {
        WaitingTime waitingTime = waitingTimeDao.getWaitTime(1);
        assertNotNull(waitingTime);
    }
}