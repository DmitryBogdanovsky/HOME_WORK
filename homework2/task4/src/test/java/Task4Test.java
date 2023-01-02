import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;


public class Task4Test {

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
    public void testInsert() {
        Connection connection = mysqlJdbcDataSource.getConnection();
        ResultSet rs = connection.createStatement().executeQuery("select count(*) from Expenses;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        connection.createStatement().executeUpdate("INSERT INTO expenses ( payday, recipient, summa)" +
                " VALUES ('2022-11-15', 'PROSTORE' ,'250')");

        rs = connection.createStatement().executeQuery("select count(*) from Expenses;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
        connection.createStatement().executeUpdate("TRUNCATE TABLE Expenses;");
        connection.close();
    }

}
