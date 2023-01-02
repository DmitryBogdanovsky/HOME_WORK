import dao.PersonDaoImpl;
import trigger.Trigger;
import util.MysqlSessionFactory;

public class Task23 {
    public static void main(String[] args) {
        PersonDaoImpl personDao = new PersonDaoImpl(MysqlSessionFactory.getSessionFactory());
        Trigger.createUpperTrigger(MysqlSessionFactory.getSessionFactory());
        BaseMethod.addPerson(personDao);
        Trigger.dropUpperTrigger(MysqlSessionFactory.getSessionFactory());
        BaseMethod.deleteAndCreateMethod(personDao);
        BaseMethod.printAllFromDatabase(personDao);
    }
}
