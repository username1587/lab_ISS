package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.TipUser;
import models.User;
import models.ValidationException;
import services.Service;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class LoginController {
    Service service;

    void initialize() {
        // initialize table view and other view obj
    }

    public void setService(Service service) {
        this.service = service;

        //initialize models
    }

    @FXML
    private TextField usernameTextfield;

    @FXML
    private TextField passwordTextfield;

    @FXML
    private Label eroriLabel;

    @FXML
    void loginButtonOnAction(ActionEvent event) throws IOException {
        String username = usernameTextfield.getText();
        String password = passwordTextfield.getText();

        User user=null;
        try {
            user=service.login(username, password);
            eroriLabel.setText("");
        } catch (ValidationException ex) {
            eroriLabel.setText(ex.getMessage());
            return ;
        }

        if (user==null){
            eroriLabel.setText("invalid username or password");
            return ;
        }

        AnchorPane root;
        if (user.getTipUser().equals(TipUser.PROGRAMATOR))
            root = loadProgramatorController(user);
        else
            root = loadVerificatorController(user);


        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("fereastra user");
        stage.show();
    }

    private AnchorPane loadProgramatorController(User user) throws java.io.IOException {
        FXMLLoader loader = new FXMLLoader();
        URL location = getClass().getResource("/fereastra_programator.fxml");
        loader.setLocation(location);
        AnchorPane root = loader.load();

        ProgramatorController programatorController = loader.getController();
        programatorController.setService(service, user);
        return root;
    }

    private AnchorPane loadVerificatorController(User user) throws java.io.IOException {
        FXMLLoader loader = new FXMLLoader();
        URL location = getClass().getResource("/fereastra_verificator.fxml");
        loader.setLocation(location);
        AnchorPane root = loader.load();

        VerificatorController verificatorController = loader.getController();
        verificatorController.setService(service, user);
        return root;
    }
}
