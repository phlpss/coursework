package com.example.coursework.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;

import java.io.IOException;

import static com.example.coursework.controller.util.SceneSwitcher.switchScene;

public class GroupsInCourseController {
    @FXML
    public void switchToMainPage(ActionEvent event) throws IOException {
        switchScene(event, "/fxml/main-page.fxml");
    }

    @FXML
    public void switchToManageGroup(ActionEvent event) throws IOException {
        switchScene(event, "/fxml/manage-group.fxml");
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
    }

    @FXML
    void getGroupsByYear_EnterKey(DragEvent event) {

    }
}
