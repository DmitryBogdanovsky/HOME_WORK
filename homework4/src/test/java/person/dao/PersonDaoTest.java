package person.dao;

import embeddable.Adress;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import person.model.Employee;
import person.model.Person;
import person.model.Student;
import util.MysqlSessionFactory;

import static org.junit.Assert.assertEquals;

public class PersonDaoTest {

    PersonDao testObject;

    @Before
    public void setUp() {
        testObject = new PersonDaoImpl(MysqlSessionFactory
                .getSessionFactory("hibernate-test.cfg.xml"));
    }

    @After
    public void tearDown() {
        testObject = null;
    }

    @Test
    public void create() {
        //Given
        Person employee = Employee
                .builder()
                .name("VLADIMIR")
                .surname("LENIN")
                .age(40)
                .adress(new Adress(2, "Lenina", "Minsk", 220055))
                .company("Goverment")
                .salary(1000000)
                .build();
        Person student = Student
                .builder()
                .name("Edgar")
                .surname("Po")
                .age(35)
                .adress(new Adress(23, "Nezavisimosty", "Minsk", 220035))
                .faculty("Basters")
                .mark(9.9)
                .build();
        Person person = Person
                .builder()
                .name("Dmitry")
                .surname("Suhodrishenko")
                .age(50)
                .adress(new Adress(21, "Nezavisimosty", "Minsk", 220200))
                .build();

        int initialSize = testObject.readAll().size();

        //When
        testObject.create(employee);
        testObject.create(student);
        testObject.create(person);

        //Then
        int actualSize = testObject.readAll().size();
        assertEquals(initialSize + 3, actualSize);
    }

    @Test
    public void EmbeddablePerson() {
        //Given
        Person person = Person
                .builder()
                .name("Dmitry")
                .surname("Suhodrishenko")
                .age(50)
                .adress(new Adress(21, "Nezavisimosty", "Minsk", 220200))
                .build();

        //When
        testObject.create(person);

        //Then
        assertEquals(21, person.getAdress().getHouse());
        assertEquals("Nezavisimosty", person.getAdress().getStreet());
        assertEquals("Minsk", person.getAdress().getCity());
        assertEquals(220200, person.getAdress().getPostalCode());

    }

}