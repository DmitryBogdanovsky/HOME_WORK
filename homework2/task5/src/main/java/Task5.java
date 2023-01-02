public class Task5 {

    public static void main(String[] args) {
        MysqlJdbcDataSource mysqlJdbcDataSource = new MysqlJdbcDataSource();
        ExpensesBase expensesBase = new ExpensesBase(mysqlJdbcDataSource);
        ExpensesBase.CreateDataBase();
        ExpensesBase.addArguments(args, expensesBase);
        expensesBase.printAllFromBase();
    }
}


