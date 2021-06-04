package gui;

import controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import repositories.BugRepo;
import repositories.IBugRepo;
import repositories.IUserRepo;
import repositories.UserRepo;
import services.Service;

import java.io.IOException;

public class MainFx extends Application {

    static SessionFactory sessionFactory;

    static void initializeSessionFactory() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
//            System.err.println("Exception " + e);
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    static void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }

    }

    Service service;

    @Override
    public void start(Stage primaryStage) throws Exception {
        initializeSessionFactory();

        IUserRepo userRepo = new UserRepo(sessionFactory);
        IBugRepo bugRepo = new BugRepo(sessionFactory);

        service = new Service(userRepo, bugRepo);

//        System.out.println("AM GASIT=" + service.login("asd", "asd").toString());

        initView(primaryStage);

//        closeSessionFactory();

    }

    private void initView(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        var location = getClass().getResource("/fereastra_login.fxml");
        loader.setLocation(location);
        AnchorPane root = loader.load();

        LoginController loginController = loader.getController();
        loginController.setService(service);

        stage.setScene(new Scene(root));
        stage.setTitle("test practic map");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }


}



