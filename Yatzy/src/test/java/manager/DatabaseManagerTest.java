/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tapio Salonen
 */
public class DatabaseManagerTest {
    DatabaseManager databaseManager;
    List<String> resultList;
    List<String> testList;
    public DatabaseManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        databaseManager = new DatabaseManager();
        resultList = new ArrayList<>();
        testList = new ArrayList<>();
        testList.add("INSERT INTO test2 (boolean, content) VALUES ('FALSE', 'kjhdkjfshkdjf');");
        testList.add("INSERT INTO test2 (boolean, content) VALUES ('TRUE', 'safdmlkfs');");
        testList.add("INSERT INTO test2 (boolean, content) VALUES ('TRUE', 'asodifusdfoiuofids');");
        testList.add("INSERT INTO test2 (boolean, content) VALUES ('FALSE', 'sfosifoisufi');");
        testList.add("INSERT INTO test2 (boolean, content) VALUES ('TRUE', 'sdfasf');");
        databaseManager.executeStatement("CREATE TABLE test (id INTEGER PRIMARY KEY, content TEXT);", "testDatabase");
        databaseManager.executeStatement("CREATE TABLE test2 (boolean BOOL, content TEXT);", "testDatabase");
    }
    
    @After
    public void tearDown() {
        databaseManager.executeStatement("DROP TABLE test;", "testDatabase");
        databaseManager.executeStatement("DROP TABLE test2;", "testDatabase");
    }
    
    @Test
    public void testExecuteStatement() {
        String sql = "INSERT INTO test (content) VALUES ('testtesttest');";
        assertTrue(databaseManager.executeStatement(sql, "testDatabase"));
    }
    @Test
    public void testExecuteStatements() {
        List<String> testList = new ArrayList<>();
        testList.add("INSERT INTO test (content) VALUES ('testtesttest');");
        testList.add("INSERT INTO test (content) VALUES ('asfsfd4536456');");
        testList.add("INSERT INTO test (content) VALUES ('sdflkjsf');");
        testList.add("INSERT INTO test (content) VALUES ('9837539475');");
        testList.add("INSERT INTO test (content) VALUES ('akjshjaksfd');");
        assertTrue(databaseManager.executeStatements(testList, "testDatabase"));
    }
    @Test
    public void testSelectFrom() {
        databaseManager.executeStatement("INSERT INTO test2 (boolean, content) VALUES ('TRUE', 'testtesttest');", "testDatabase");
        databaseManager.executeStatements(testList, "testDatabase");
        resultList = databaseManager.selectFrom("SELECT * FROM test2 WHERE boolean = 'TRUE';", "testDatabase", "content");
        assertEquals("asodifusdfoiuofids", resultList.get(2));
    }
}
