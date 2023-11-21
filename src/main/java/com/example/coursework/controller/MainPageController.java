package com.example.coursework.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import static com.example.coursework.controller.util.SceneSwitcher.switchScene;

public class MainPageController {
    @FXML
    public void switchToGroupsInCourse(ActionEvent event) throws Exception {
        switchScene(event, "/fxml/groups-in-course.fxml");
    }

    @FXML
    public void switchToManageGroup(ActionEvent event) throws IOException {
        switchScene(event, "/fxml/manage-group.fxml");
    }

    @FXML
    public void switchToAddGroup(ActionEvent event) throws IOException {
        switchScene(event, "/fxml/add-group.fxml");
    }

    @FXML
    void saveAllChanges_OnAction(ActionEvent event) {
        ManageGroupController controller = (ManageGroupController) event.getSource();
        controller.SaveGroupInfo_Action(new ActionEvent());
    }

    public void updatePage_OnAction(ActionEvent actionEvent) {

    }
}
