public class Task6 {
    public static void main(String[] args) {
        MysqlJdbcDataSource mysqlJdbcDataSource = new MysqlJdbcDataSource();
        ExpensesBase expensesBase = new ExpensesBase(mysqlJdbcDataSource);
        expensesBase.printAllFromBase();
        ExpensesBase.printData();
    }
}
