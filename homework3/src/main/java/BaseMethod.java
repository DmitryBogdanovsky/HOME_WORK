import dao.PersonDaoImpl;
import model.Person;

import java.util.List;

public class BaseMethod {


    public static void addPerson(PersonDaoImpl personDao) {
        List<Person> personList = List.of(
                new Person(null, 25, "ivan", "petrov"),
                new Person(null, 30, "vasja", "sidorov"),
                new Person(null, 20, "dima", "ivanov"),
                new Person(null, 18, "fedor", "shniperson"),
                new Person(null, 37, "alexander", "kirkorov")
        );
        personList.forEach(personDao::create);

    }

    public static void deleteAndCreateMethod(PersonDaoImpl personDao) {
        Person person = new Person(null, 45, "vladimir", "lenin");
        personDao.create(person);
        // Example of using flush() method
        personDao.deleteAndCreate(person);
    }

    public static void printAllFromDatabase(PersonDaoImpl personDao) {
        personDao.readAll().forEach(System.out::println);
    }
}
