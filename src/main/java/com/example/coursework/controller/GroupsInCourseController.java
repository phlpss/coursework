package com.example.coursework.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GroupsInCourseController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToMainPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/main-page.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToManageGroup(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/manage-group.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private Button getGroupsByName_Button;

    @FXML
    private ComboBox<?> getGroupsByName_ComboBox;

    @FXML
    private TextField getGroupsByYear_TextField;

    @FXML
    private Button manageGroup_Button;

    @FXML
    void getGroupsByName_ButtonClick(ActionEvent event) {
        //
    }

    @FXML
    void getGroupsByYear_EnterKey(DragEvent event) {

    }
}
