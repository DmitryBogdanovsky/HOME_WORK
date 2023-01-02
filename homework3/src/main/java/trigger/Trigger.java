package trigger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Trigger {
    private static void doQuery(SessionFactory sessionFactory, String query) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.createNativeQuery(query).executeUpdate();
            tx.commit();
        }
    }
    public static void createUpperTrigger(SessionFactory sessionFactory) {
        String query = "CREATE TRIGGER UPPERCASE_INSERT BEFORE INSERT ON Person " +
                    "FOR EACH ROW SET NEW.SURNAME = UPPER(NEW.SURNAME);";
        doQuery(sessionFactory,query);
    }

    public static void dropUpperTrigger(SessionFactory sessionFactory) {
        String query = "DROP TRIGGER UPPERCASE_INSERT;";
        doQuery(sessionFactory, query);
    }
}
