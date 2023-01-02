package dao;

import model.Person;
import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.MysqlSessionFactory;

import java.util.List;

import static org.junit.Assert.*;

public class PersonDaoImplTest {

    PersonDaoImpl testObject;

    @Before
    public void setUp() {
        testObject = new PersonDaoImpl(MysqlSessionFactory.getSessionFactory("hibernate-test.cfg.xml"));
    }

    @After
    public void tearDown() {
        testObject = null;
    }

    @Test
    public void create() {
        //Given
        int resultBefore = testObject.readAll().size();
        Person person = new Person(null, 55, "John", "Smith");

        //When
        testObject.create(person);

        //Then
        int resultAfter = testObject.readAll().size();
        assertEquals(resultBefore + 1, resultAfter);

    }

    @Test
    public void readAll() {
        //Given
        int sizeBefore = testObject.readAll().size();
        List<Person> person = List.of(
                new Person(null, 20, "John", "Smith"),
                new Person(null, 21, "Jane", "Doe"),
                new Person(null, 22, "Max", "Bloggs"),
                new Person(null, 23, "Sem", "Dorn"),
                new Person(null, 24, "Joe", "Boss"));
        person.forEach(testObject::create);


        //When
        int sizeAfter = testObject.readAll().size();

        //Then
        assertEquals(sizeBefore + person.size(), sizeAfter);
    }

    @Test
    public void loadById() {
        //Given
        Person person = new Person(null, 10, "Ivan", "Shniperson");
        testObject.create(person);

        //When
        Person result = testObject.loadById(person.getId());

        //Then
        assertEquals(person.getId(), result.getId());
        assertEquals(person.getAge(), result.getAge());
        assertEquals(person.getName(), result.getName());
        assertEquals(person.getSurname(), result.getSurname());
    }

    @Test
    public void loadByIdThrowException() {
        //Given
        long id = -1L;
        //When
        //Then
        assertThrows(HibernateException.class, () -> testObject.loadById(id));
    }

    @Test
    public void getById() {
        //Given
        Person person = new Person(null, 20, "Ivan", "Shniperson");
        testObject.create(person);

        //When
        Person result = testObject.getById(person.getId());

        //Then
        assertEquals(person.getId(), result.getId());
        assertEquals(person.getAge(), result.getAge());
        assertEquals(person.getName(), result.getName());
        assertEquals(person.getSurname(), result.getSurname());
    }

    @Test
    public void update() {
        //Given
        Person person = new Person(null, 20, "Ivan", "Shniperson");
        testObject.create(person);

        //When
        String name = "Valik";
        person.setName(name);
        testObject.update(person);

        //Then
        Person result = testObject.getById(person.getId());
        assertEquals(name, result.getName());
    }

    @Test
    public void delete() {
        //Given
        Person person = new Person(null, 20, "Ivan", "Shniperson");
        testObject.create(person);
        int sizeBefore = testObject.readAll().size();

        //When
        testObject.delete(person);

        //Then
        int sizeAfter = testObject.readAll().size();
        assertEquals(sizeBefore - 1, sizeAfter);
    }

}