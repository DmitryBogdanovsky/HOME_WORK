package dao;

import model.Person;

import java.util.List;

public interface PersonDao {
    void create (Person person);
    Person loadById(Long id);
    Person getById(Long id);
    void update (Person person);
    void delete (Person person);
    void deleteAndCreate (Person person);
    List<Person> readAll();
}
