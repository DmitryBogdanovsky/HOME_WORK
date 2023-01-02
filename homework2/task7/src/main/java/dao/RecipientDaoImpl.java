package dao;

import data.MysqlJdbcDataSource;
import model.Recipient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RecipientDaoImpl implements Dao<Recipient> {
    MysqlJdbcDataSource mysqlJdbcDataSource;

    public RecipientDaoImpl(MysqlJdbcDataSource mysqlJdbcDataSource) {
        this.mysqlJdbcDataSource = mysqlJdbcDataSource;
    }

    @Override
    public void create(Recipient recipient) {
        Connection connection;
        String statement = "INSERT IGNORE INTO Recipient (recipient_name) VALUES(?)";

        try {
            connection = mysqlJdbcDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, recipient.getRecipient_name());
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Recipient> readAll() {
        List<Recipient> recipients = new ArrayList<>();
        try {
            final Connection connection = mysqlJdbcDataSource.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM Recipient");
            while (resultSet.next()) {
                final Recipient recipient = new Recipient();
                recipient.setId(resultSet.getInt("id"));
                recipient.setRecipient_name(resultSet.getString("recipient_name"));
                recipients.add(recipient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipients;
    }

    @Override
    public void update(Recipient record) {

    }

    @Override
    public void delete(Recipient record) {

    }

    public int getId(Recipient recipient) {
        int id = 0;

        try {
            final Connection connection = mysqlJdbcDataSource.getConnection();
            String statement = "SELECT id FROM Recipient WHERE recipient_name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, recipient.getRecipient_name());
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
