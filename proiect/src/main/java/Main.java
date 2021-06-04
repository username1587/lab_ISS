import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import repositories.IUserRepo;
import repositories.UserRepo;
import services.Service;

import java.util.Date;
import java.util.List;

public class Main {
    static SessionFactory sessionFactory;

    static void initializeSessionFactory() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Exception " + e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    static void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }

    }

    //INSERT
    void addMessage() {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                User message = new User();
                message.setUsername("asd");
                message.setPassword("asd");
                session.save(message);
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    //SELECT
    void getMessages() {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<User> messages =
                        session.createQuery("from User as m", User.class).
//                                setFirstResult(10).setMaxResults(5).
        list();
                System.out.println(messages.size() + " message(s) found:");
                for (User m : messages) {
                    System.out.println(m.getUsername() + ' ' + m.getId());
                }
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    public static void main(String[] args) {
//        try {
//            initialize();
//
//            Main test = new Main();
////            test.addMessage();
//            test.getMessages();
////            test.addMessage();
//            //  test.getMessages();
////            test.updateMessage();
////            test.deleteMessage();
////            test.getMessages();
//        }catch (Exception e){
//            System.err.println("Exception "+e);
//            e.printStackTrace();
//        }finally {
//            close();
//            System.out.println("am rulat codu");
//        }
        initializeSessionFactory();

        IUserRepo userRepo = new UserRepo(sessionFactory);

        Service service = new Service(userRepo,null);

        System.out.println("AM GASIT=" + service.login("asd", "asd").toString());

        closeSessionFactory();
    }
}
