package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Bug;
import models.User;
import services.Service;

import java.util.List;

public class VerificatorController {
    Service service;
    User user;

    @FXML
    void initialize() {
        // initialize table view and other view obj
        numeColumn.setCellValueFactory(new PropertyValueFactory<Bug, String>("denumire"));
        descriereColumn.setCellValueFactory(new PropertyValueFactory<Bug, String>("descriere"));
        isActiveColumn.setCellValueFactory(new PropertyValueFactory<Bug, Boolean>("isActive"));

        table.setItems(bugsModel);
    }

    public void setService(Service service, User user) {
        this.service = service;
        this.user = user;

        //initialize models
        usernameLabel.setText(user.getUsername());
        rolLabel.setText(user.getTipUser().toString());
        loadBugsModel();
    }

    ObservableList<Bug> bugsModel = FXCollections.observableArrayList();

    @FXML
    private TableView<Bug> table;

    @FXML
    private TableColumn<Bug, String> numeColumn;

    @FXML
    private TableColumn<Bug, String> descriereColumn;

    @FXML
    private TableColumn<Bug, Boolean> isActiveColumn;

    private void loadBugsModel() {
        List<Bug> bugs = service.getBugs();
        bugsModel.setAll(bugs);
    }

    @FXML
    private Label usernameLabel;

    @FXML
    private Label rolLabel;

    @FXML
    private TextField numeBugTextfield;

    @FXML
    private TextField descriereBugTextfield;

    @FXML
    void adaugaBugButtonOnAction(ActionEvent event) {
        Bug bug=new Bug();
        String numeBug=numeBugTextfield.getText();
        String descriereBug=descriereBugTextfield.getText();
        bug.setDenumire(numeBug);
        bug.setDescriere(descriereBug);
        bug.setIsActive(true);
        service.addBug(bug);

        loadBugsModel();
    }
}
