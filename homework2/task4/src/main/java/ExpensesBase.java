import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DateTimeException;
import java.time.LocalDate;

public class ExpensesBase {

    static MysqlJdbcDataSource mysqlJdbcDataSource;
    public ExpensesBase(MysqlJdbcDataSource mysqlJdbcDataSource) {
        ExpensesBase.mysqlJdbcDataSource = mysqlJdbcDataSource;
    }

    public static void CreateDataBase() {
        try {
            final Connection connection = mysqlJdbcDataSource.getConnection();
            final Statement statement = connection.createStatement();
            String sql = " CREATE TABLE IF NOT EXISTS `expenses` " +
                    "( `id` INT NOT NULL AUTO_INCREMENT," +
                    "`payday` DATE NULL," +
                    "`recipient` VARCHAR(40) NULL," +
                    "`summa` DOUBLE NULL," +
                    "PRIMARY KEY (`id`))";
            statement.executeUpdate(sql);
            System.out.println("DataBase expenses: create successful");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DataBase expenses: isn't create............");
        }
    }

    public boolean addToBase(LocalDate payday, String recipient, double summa) {
        try {
            final Connection connection = mysqlJdbcDataSource.getConnection();
            final Statement statement = connection.createStatement();
            statement.execute("INSERT INTO Expenses (payday, recipient, summa) VALUES('" + payday + "', '" +
                    recipient + "', " + summa + ");");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void printAllFromBase() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.printf("%-6s %-12s %-40s %s\n", "id",  "payday",  "recipient", "summa");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        try {
            final Connection connection = mysqlJdbcDataSource.getConnection();
            final Statement statement = connection.createStatement();
            final String query = "SELECT * FROM expenses.expenses;";
            final ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.printf("%-6d %-12s %-40s %.2f\n", resultSet.getInt("id"),
                        resultSet.getDate("payday").toLocalDate(), resultSet.getString("recipient"),
                        resultSet.getDouble("summa"));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static void addArguments(String[] args, ExpensesBase expensesBase) {
        if (args.length != 3) {
            System.out.println("Error add to database !!!!! \n Usage:  DATE(yyyy-mm-dd) recipient(A-Z,0-9,-_) summa(1-9)");
                     return;

        }
        LocalDate payday;

        try {
            payday = LocalDate.parse(args[0]);
        } catch (DateTimeException e) {
            System.out.println("Error!!!! \nPlease use a valid format! Valid format is (yyyy-mm-dd).");
            return;

        }

        String recipient = args[1];
        if (!recipient.matches("[A-Za-z0-9-]+")) {
            System.out.println("Error!!!!! \n Please use a valid format!\n Only contain letters, digits and '-' symbol.");
            return;
        }
        double summa;

        try {
            summa = Double.parseDouble(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Error!!!!!! \n Can't parse argument summa, please use a valid format!");
            return;
        }
        expensesBase.addToBase(payday, recipient, summa);
    }

}
