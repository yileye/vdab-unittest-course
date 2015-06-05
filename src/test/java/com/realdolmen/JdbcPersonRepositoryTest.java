package com.realdolmen;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import static junit.framework.TestCase.assertEquals;

public class JdbcPersonRepositoryTest {
    @Before
    public void initializeDataSets() throws Exception {
        // 1. Create DBUnit connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
        IDatabaseConnection databaseConnection = new DatabaseConnection(connection);

        // 2. Load dataset "people.xml" (create it first)
        IDataSet peopleDataset = new FlatXmlDataSetBuilder().build(new File("people.xml"));

        // 3. Execute CLEAN_INSERT operation using 1 and 2
        DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, peopleDataset);
    }

    // 4. Prepare "fixture" for PersonRepository
    private PersonRepository repository = new JdbcPersonRepository();


    @Test
    public void testPersonCanBeFoundById() throws Exception {
        Person person = repository.find(1);
        assertEquals("Jimi", person.getFirstName());
        assertEquals("Hendrix", person.getLastName());
    }
}
