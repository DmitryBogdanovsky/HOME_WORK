import data.MysqlJdbcDataSource;
import dao.ExpensesDaoImpl;
import dao.RecipientDaoImpl;
import model.Expenses;
import model.Recipient;
import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class ExpenseDaoImplTest {
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
        Expenses expenses = new Expenses();
        Recipient recipient = new Recipient();
        ExpensesDaoImpl expenseDao = new ExpensesDaoImpl(mysqlJdbcDataSource);
        RecipientDaoImpl receiverDao = new RecipientDaoImpl(mysqlJdbcDataSource);

        Connection connection = mysqlJdbcDataSource.getConnection();

        ResultSet rs = connection.createStatement().executeQuery("select count(*) from Expenses;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);

        expenses.setSumma(100);
        expenses.setPayday(LocalDate.now());

        recipient.setRecipient_name("Magazine");
        receiverDao.create(recipient);

        expenses.setRecipient_id(receiverDao.getId(recipient));
        expenseDao.create(expenses);


        rs = connection.createStatement().executeQuery("select count(*) from Expenses;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
        connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
        connection.createStatement().executeUpdate("TRUNCATE TABLE Expenses;");
        connection.createStatement().executeUpdate("TRUNCATE TABLE Recipient;");
        connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");
        connection.close();
    }

    @Test
    @SneakyThrows
    public void testReadAll() {
        // Given
        Expenses expenses = new Expenses();
        Recipient recipient = new Recipient();
        ExpensesDaoImpl expenseDao = new ExpensesDaoImpl(mysqlJdbcDataSource);
        RecipientDaoImpl recipientDao = new RecipientDaoImpl(mysqlJdbcDataSource);

        Connection connection = mysqlJdbcDataSource.getConnection();

        ResultSet rs = connection.createStatement().executeQuery("select count(*) from Expenses;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);

        for (int i = 0; i < 10; i++) {
            expenses.setSumma(i);
            expenses.setPayday(LocalDate.now());

            recipient.setRecipient_name("Magazine" + i);
            recipientDao.create(recipient);

            expenses.setRecipient_id(recipientDao.getId(recipient));

            // When
            expenseDao.create(expenses);
        }

        // Then
        assertEquals(10, expenseDao.readAll().size());
        connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
        connection.createStatement().executeUpdate("truncate table Expenses;");
        connection.createStatement().executeUpdate("truncate table Recipient;");
        connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");
        connection.close();
    }

}