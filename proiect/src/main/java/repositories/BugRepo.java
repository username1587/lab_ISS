package repositories;

import models.Bug;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class BugRepo implements IBugRepo {
    private final SessionFactory sessionFactory;

    public BugRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Bug> getBugs() {
        List<Bug> bugs = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                bugs = session.createQuery("from Bug as m", Bug.class).
//                        setFirstResult(10).setMaxResults(5).
                        list();
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return bugs;
    }

    @Override
    public List<Bug> getActiveBugs() {
        List<Bug> bugs = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                bugs = session.createQuery("from Bug as b where b.isActive = true", Bug.class).
//                        setFirstResult(10).setMaxResults(5).
        list();
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return bugs;
    }

    @Override
    public void addBug(Bug bug) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(bug);
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public void solveBug(Bug bug) {
        try(Session session = sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx = session.beginTransaction();
                Bug _bug = session.load( Bug.class, bug.getId() );

                _bug.setIsActive(false);

                //session.update(_bug);
                tx.commit();

            } catch(RuntimeException ex){
                if (tx!=null)
                    tx.rollback();
            }
        }
    }

}
