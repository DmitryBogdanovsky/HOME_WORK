package service;


import dao.PersonDao;
import entyty.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonService {

@Autowired
PersonDao personDao;

  @Transactional
    public void addPerson(Person person){
        personDao.addPerson(person);
    }

  @Transactional
    public Person findById(Integer id){
        return personDao.findById(id);
    }

    public List<Person> findAll() {
        return personDao.findAll();
    }

}
