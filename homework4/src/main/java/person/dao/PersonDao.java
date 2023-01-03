package person.dao;

import person.model.Person;
import java.util.List;

public interface PersonDao {
   void create(Person person);
    List <Long> readIdPerson();
    List<Person> readAll();

}
