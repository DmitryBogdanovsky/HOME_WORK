package trigger;

import dao.PersonDaoImpl;
import model.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.MysqlSessionFactory;

import static org.junit.Assert.*;

public class TriggerTest {
    PersonDaoImpl personNew;

    @Before
    public void setUp() {
        personNew = new PersonDaoImpl(MysqlSessionFactory
                .getSessionFactory("hibernate-test.cfg.xml"));
    }

    @After
    public void tearDown() {
        personNew = null;
    }

    @Test
    public void createUpperTrigger() {
        //Given
        String surname = "inanov";
        Person person = new Person(null, 18, "ivan", surname);

        //When
        Trigger.createUpperTrigger(MysqlSessionFactory
                .getSessionFactory("hibernate-test.cfg.xml"));
        personNew.create(person);
        Trigger.dropUpperTrigger(MysqlSessionFactory
                .getSessionFactory("hibernate-test.cfg"));
        person = personNew.getById(person.getId());
        //Then
        assertEquals(surname.toUpperCase(), person.getSurname());
    }

}