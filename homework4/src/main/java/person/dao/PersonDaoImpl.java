package person.dao;

import person.model.Person;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

@AllArgsConstructor
public class PersonDaoImpl implements PersonDao {
    SessionFactory sessionFactory;

    @Override
    public void create(Person person) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(person);
            tx.commit();
  //          System.out.println(person.toString());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public List<Person> readAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Person", Person.class).list();
        }
    }

    @Override
    public List readIdPerson() {
        Session session = sessionFactory.openSession();
        List idPerson = session.createQuery("SELECT id FROM Person").list();
        session.close();
        return idPerson;
    }
}
