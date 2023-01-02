package data;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MysqlJdbcDataSource {
    final static String DEFAULT_PROPERTIES_FILE = "expenses.jdbc.properties";

    private final Properties properties;

    public MysqlJdbcDataSource() {
        this(DEFAULT_PROPERTIES_FILE);
    }

    @SneakyThrows
    public MysqlJdbcDataSource(String propertiesFile) {
        properties = new Properties();
        properties.load(MysqlJdbcDataSource.class.getClassLoader().getResourceAsStream(propertiesFile));

        Class.forName(properties.getProperty("driver"));
        System.out.println("Connection success!");
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("username"),
                properties.getProperty("password")
        );
    }
}
