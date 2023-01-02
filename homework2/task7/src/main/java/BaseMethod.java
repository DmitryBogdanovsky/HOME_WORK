import dao.ExpensesDaoImpl;
import dao.RecipientDaoImpl;
import model.Expenses;
import model.Recipient;
import data.MysqlJdbcDataSource;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BaseMethod {

    static MysqlJdbcDataSource mysqlJdbcDataSource = new MysqlJdbcDataSource();
    static ExpensesDaoImpl expensesDao = new ExpensesDaoImpl(mysqlJdbcDataSource);
    static RecipientDaoImpl recipientDao = new RecipientDaoImpl(mysqlJdbcDataSource);


    public static void printAllFromBase() {
        List<Expenses> expense = expensesDao.readAll();
        Map<Integer, String> recipients = recipientDao.readAll().stream()
                .collect(Collectors.toMap(Recipient::getId, Recipient::getRecipient_name));

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.printf("%-6s %-12s %-40s %s\n", "id", "payday", "recipient", "summa");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        for (Expenses ex : expense) {
            String recipientName = recipients.getOrDefault(ex.getRecipient_id(), "[no data]");
            System.out.printf("%-6d %-12s %-40s %.2f\n", ex.getId(), ex.getPayday(), recipientName, ex.getSumma());
        }
    }
    static boolean addArguments(String[] args, Expenses expenses, Recipient recipient) {
        if (args.length != 3) {
            System.out.println("Error add to database !!!!! \n Usage:  DATE(yyyy-mm-dd) recipient(A-Z,0-9,-_) summa(1-9)");
            return false;
        }

        LocalDate date;

        try {
            date = LocalDate.parse(args[0]);
        } catch (DateTimeException e) {
            System.out.println("Error!!!! \nPlease use a valid format! Valid format is (yyyy-mm-dd).");
            return false;
        }

        String recipients = args[1];

        double summa;

        try {
            summa = Double.parseDouble(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Error!!!!! \n Please use a valid format!\n Only contain letters, digits and '-' symbol.");
            return false;
        }

        expenses.setPayday(date);
        expenses.setSumma(summa);
        recipient.setRecipient_name(recipients);

        return true;
    }
}
