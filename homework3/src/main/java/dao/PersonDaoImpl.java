package dao;

import model.Person;
import org.hibernate.*;

import java.util.List;

public class PersonDaoImpl implements PersonDao {
    private final SessionFactory sessionFactory;


    public PersonDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Person person) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(person);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public List<Person> readAll() {
        try (Session session = sessionFactory.openSession()) {
            String query = "SELECT a FROM Person AS a" +
                    "";
            return session.createQuery(query, Person.class).list();
        }
    }

    @Override
    public Person loadById(Long id) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            Person person = session.load(Person.class, id);

            // Initialize lazy object
            Hibernate.initialize(person);

            return person;
        }
    }

    @Override
    public Person getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Person.class, id);
        }
    }

    @Override
    public void deleteAndCreate(Person person) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(person);
            session.flush();
            session.save(person);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
            throw e;
        }
    }
    @Override
    public void update(Person person) {
        create(person);
    }

    @Override
    public void delete(Person person) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.refresh(person);
            session.delete(person);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
            throw e;
        }
    }

}
