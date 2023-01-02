
import data.MysqlJdbcDataSource;
import dao.RecipientDaoImpl;
import model.Recipient;
import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;

public class ReceiverDaoImplTest {
    MysqlJdbcDataSource mysqlJdbcDataSource;

    @Before
    public void setUp() {
        mysqlJdbcDataSource = new MysqlJdbcDataSource("expenses_test.jdbc.properties");
    }

    @After
    public void tearDown() {
        mysqlJdbcDataSource = null;
    }

    @Test
    @SneakyThrows
    public void testCreate() {
        Recipient recipient = new Recipient();
        RecipientDaoImpl recipientDao = new RecipientDaoImpl(mysqlJdbcDataSource);

        Connection connection = mysqlJdbcDataSource.getConnection();

        ResultSet rs = connection.createStatement().executeQuery("select count(*) from Recipient;");
        rs.next();
        int initialSize = rs.getInt(1);
        Assert.assertEquals(0, initialSize);

        recipient.setRecipient_name("Magazine");
        recipientDao.create(recipient);

        rs = connection.createStatement().executeQuery("select count(*) from Recipient;");
        rs.next();
        int actualSize = rs.getInt(1);
        Assert.assertEquals(1, actualSize);
        connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
        connection.createStatement().executeUpdate("truncate table Expenses;");
        connection.createStatement().executeUpdate("truncate table Recipient;");
        connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");
        connection.close();
    }

    @Test
    @SneakyThrows
    public void testReadAll() {
        // Given
        Recipient recipient = new Recipient();
        RecipientDaoImpl recipientDao = new RecipientDaoImpl(mysqlJdbcDataSource);

        Connection connection = mysqlJdbcDataSource.getConnection();

        ResultSet rs = connection.createStatement().executeQuery("select count(*) from Recipient;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);

        for (int i = 0; i < 10; i++) {
            recipient.setRecipient_name("Magazine" + i);
            recipientDao.create(recipient);

            // When
            recipientDao.create(recipient);
        }

        // Then
        assertEquals(10, recipientDao.readAll().size());
        connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
        connection.createStatement().executeUpdate("truncate table Expenses;");
        connection.createStatement().executeUpdate("truncate table Recipient;");
        connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");
        connection.close();
    }

    @Test
    @SneakyThrows
    public void testGetId() {
        Recipient recipient = new Recipient();
        RecipientDaoImpl receiverDao = new RecipientDaoImpl(mysqlJdbcDataSource);

        Connection connection = mysqlJdbcDataSource.getConnection();

        recipient.setRecipient_name("Magazine");

        Assert.assertEquals(0, receiverDao.getId(recipient));

        receiverDao.create(recipient);
        Assert.assertTrue(receiverDao.getId(recipient) != 0);

        connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
        connection.createStatement().executeUpdate("truncate table Expenses;");
        connection.createStatement().executeUpdate("truncate table Recipient");
        connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");
        connection.close();
    }
}