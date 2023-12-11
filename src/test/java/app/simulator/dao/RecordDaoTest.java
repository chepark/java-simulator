package app.simulator.dao;

import app.simulator.models.Record;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/***
 * Test class for RecordDao
 * @see app.simulator.dao.RecordDao
 * @see Record
 */
class RecordDaoTest {
private static RecordDao recordDao;
    @BeforeAll
    public static void setUp() throws Exception {
        recordDao = new RecordDao();
    }

    @Test
    @DisplayName("Test removing all rows in event table")
    public void testRemoveAllRecords() {
        recordDao.removeAllRecords();
        assertEquals(0, recordDao.getAllRecords().size());
    }
}