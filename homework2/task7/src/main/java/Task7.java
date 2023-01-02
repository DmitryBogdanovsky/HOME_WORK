import data.MysqlJdbcDataSource;
import dao.ExpensesDaoImpl;
import dao.RecipientDaoImpl;
import model.Expenses;
import model.Recipient;


public class Task7 {
    public static void main(String[] args) {
        Expenses expenses = new Expenses();
        Recipient recipient = new Recipient();
        MysqlJdbcDataSource mysqlJdbcDataSource = new MysqlJdbcDataSource();
        ExpensesDaoImpl expensesDao = new ExpensesDaoImpl(mysqlJdbcDataSource);
        RecipientDaoImpl recipientDao = new RecipientDaoImpl(mysqlJdbcDataSource);
        if (BaseMethod.addArguments(args, expenses, recipient)) {
            recipientDao.create(recipient);
            expenses.setRecipient_id(recipientDao.getId(recipient));
            expensesDao.create(expenses);
        }
        BaseMethod.printAllFromBase();
    }
}
