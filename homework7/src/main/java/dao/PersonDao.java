package dao;


import entyty.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDao {

    void addPerson(Person person);

    Person findById(Integer id);

    List<Person> findAll();

    void deletePerson(Person person);


}
