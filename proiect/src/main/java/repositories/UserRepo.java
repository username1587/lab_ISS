package repositories;

import models.User;
import models.ValidationException;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserRepo implements IUserRepo {
    private final SessionFactory sessionFactory;

    public UserRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getUserByIdAndPassword(String username, String password) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<User> users =
                        session.createQuery("from User as u where u.username = :username", User.class)
                                .setParameter("username", username)
                                .list();

                if (users.size() == 0) {
                    throw new ValidationException("invalid username");
                }

                if (users.get(0).getPassword().equals(password))
                    return users.get(0);

                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        throw new ValidationException("invalid username or password");
    }
}
