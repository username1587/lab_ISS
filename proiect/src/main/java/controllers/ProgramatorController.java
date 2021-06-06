package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Bug;
import models.User;
import observer.Observable;
import observer.Observer;
import services.Service;

import java.util.Arrays;
import java.util.List;

public class ProgramatorController implements Observer {
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

        service.addObserver(this);
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
        List<Bug> bugs = service.getActiveBugs();
        bugsModel.setAll(bugs);
    }

    @FXML
    private Label usernameLabel;

    @FXML
    private Label rolLabel;

    @FXML
    void solveBugOnAction(ActionEvent event) {
        Bug selectedBug = table.getSelectionModel().getSelectedItem();
        service.solveBug(selectedBug,user);
//        loadBugsModel();
    }

    @Override
    public void update() {
        loadBugsModel();
    }
}
