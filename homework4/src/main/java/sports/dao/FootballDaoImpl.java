package sports.dao;


import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import sports.model.Football;

import java.util.List;

@AllArgsConstructor
public class FootballDaoImpl implements FootballDao{

    SessionFactory sessionFactory;

    @Override
    public void create(Football football){
        Transaction tx = null;
        try {
            Session session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(football);
            tx.commit();
        }catch (Exception e){
            if (tx != null) tx.rollback();
            throw e;
        }
    }
    @Override
    public List<Football> readAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Football", Football.class).list();
        }
    }

    @Override
    public List readIdFootball() {
        Session session = sessionFactory.openSession();
        List idFootball = session.createQuery("SELECT id FROM Football").list();
        session.close();
        return idFootball;
    }
}
