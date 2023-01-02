import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;

public class ExpensesBase {

    static MysqlJdbcDataSource mysqlJdbcDataSource;

    public ExpensesBase(MysqlJdbcDataSource mysqlJdbcDataSource) {
        ExpensesBase.mysqlJdbcDataSource = mysqlJdbcDataSource;
    }

    public static void printListOfRecipient() {
        String query = "SELECT recipient, sum(summa) FROM expenses GROUP BY recipient;";

        try {
            final Connection connection = mysqlJdbcDataSource.getConnection();
            PreparedStatement prepStat = connection.prepareStatement(query);
            final ResultSet resultSet = prepStat.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("recipient") + " " + resultSet.getDouble("sum(summa)"));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printSumMaxDay() {
        String query = "SELECT payday,SUM(`summa`) FROM Expenses GROUP BY payday order by MAX(`summa`) DESC LIMIT 1;";

        try {
            final Connection connection = mysqlJdbcDataSource.getConnection();
            PreparedStatement prepStatMax = connection.prepareStatement(query);
            final ResultSet resultSet = prepStatMax.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getDouble(2));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printMaxExpenseDay() {
        String query = "SELECT payday,MAX(`summa`) FROM Expenses GROUP BY payday order by SUM(`summa`) DESC LIMIT 1;";

        try {
            final Connection connection = mysqlJdbcDataSource.getConnection();
            PreparedStatement prepStatMaxDay = connection.prepareStatement(query);
            final ResultSet resultSet = prepStatMaxDay.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getDouble(2));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();


        }
    }

    public void printAllFromBase() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.printf("%-6s %-12s %-40s %s\n", "id", "payday", "recipient", "summa");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        try {
            final Connection connection = mysqlJdbcDataSource.getConnection();
            final Statement statement = connection.createStatement();
            final String query = "SELECT * FROM expenses.expenses;";
            final ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.printf("%-6d %-12s %-40s %.2f", resultSet.getInt("id"),
                        resultSet.getDate("payday").toLocalDate(), resultSet.getString("recipient"),
                        resultSet.getDouble("summa"));
                System.out.println();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printData() {
        System.out.println("\nСписок получателей платежа,\nи сумма платежей по каждому из них:");
        printListOfRecipient();

        System.out.println();

        System.out.println("Сумма платежей за день,\nкогда был наибольший платеж:");
        printSumMaxDay();

        System.out.println();

        System.out.println("Наибольший платеж за день,\nкогда сумма платежей была наибольшей:");
        printMaxExpenseDay();
    }
}
