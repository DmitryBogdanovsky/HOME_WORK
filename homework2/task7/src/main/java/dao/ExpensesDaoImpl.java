package dao;

import model.Expenses;
import data.MysqlJdbcDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpensesDaoImpl implements Dao<Expenses> {
       MysqlJdbcDataSource mysqlJdbcDataSource;

    public ExpensesDaoImpl(MysqlJdbcDataSource mysqlJdbcDataSource) {
        this.mysqlJdbcDataSource = mysqlJdbcDataSource;
    }

    @Override
    public void create(Expenses expenses) {
        Connection connection;
        String statement = "INSERT INTO Expenses (payday, recipient_Id, summa) VALUES(?, ?, ?)";

        try {
            connection = mysqlJdbcDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setDate(1, Date.valueOf(expenses.getPayday()));
            preparedStatement.setInt(2, expenses.getRecipient_id());
            preparedStatement.setDouble(3, expenses.getSumma());
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Expenses> readAll() {
        List<Expenses> expenses = new ArrayList<>();
        try {
            final Connection connection = mysqlJdbcDataSource.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM Expenses");
            while (resultSet.next()) {
                final Expenses expense = new Expenses();
                expense.setId(resultSet.getInt("id"));
                expense.setRecipient_id(resultSet.getInt("recipient_Id"));
                expense.setPayday(resultSet.getDate("payday").toLocalDate());
                expense.setSumma(resultSet.getDouble("summa"));
                expenses.add(expense);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }

    @Override
    public void update(Expenses record) {

    }

    @Override
    public void delete(Expenses record) {

    }

}
