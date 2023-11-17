package com.example.coursework.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

import static com.example.coursework.controller.util.SceneSwitcher.switchScene;

// Set here all groups
public class MainPageController {

    @FXML
    public void switchToGroupsInCourse(ActionEvent event) throws Exception {
        switchScene(event, "/fxml/groups-in-course.fxml");
    }

    @FXML
    public void switchToManageGroup(ActionEvent event) throws IOException {
        switchScene(event, "/fxml/manage-group.fxml");
    }


}
