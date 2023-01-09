package dao;

import entyty.Person;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PersonDaoImpl implements PersonDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addPerson(Person person) {
        sessionFactory.getCurrentSession().saveOrUpdate(person);
    }

    @Override
    public Person findById(Integer id) {
        return sessionFactory.getCurrentSession().get(Person.class, id);
    }

    @Override
    public List<Person> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Person", Person.class).list();
    }

    @Override
    public void deletePerson(Person person) {
        Person person1 = sessionFactory.getCurrentSession().load(Person.class, person.getId());
        sessionFactory.getCurrentSession().delete(person1);
    }
}
