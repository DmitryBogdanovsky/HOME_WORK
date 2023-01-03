package car.dao;

import car.model.Car;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

@AllArgsConstructor
public class CarDaoImpl implements CarDao {

    SessionFactory sessionFactory;

    @Override
    public void create(Car car) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(car);
            tx.commit();
            System.out.println(car.toString());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public List readIdCar() {
        Session session = sessionFactory.openSession();
        List idCar = session.createQuery("SELECT id FROM Car").list();
        session.close();
        return idCar;
    }

    @Override
    public List<Car> readAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Car", Car.class).list();
        }
    }

}
