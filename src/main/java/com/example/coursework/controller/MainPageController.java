package com.example.coursework.controller;

import com.example.coursework.repository.GroupRepository;
import com.example.coursework.util.ObjectFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static com.example.coursework.controller.util.SceneSwitcher.switchScene;

public class MainPageController {
    GroupRepository groupRepository = ObjectFactory.groupRepository();
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
    void save_Action(ActionEvent event) {
        groupRepository.flushToFile();
    }

    @FXML
    void goToUserInstruction_Action(ActionEvent event) throws IOException, URISyntaxException {
        URI uri = new URI("https://gamma.app/public/--znuav7o8q7lun8u?mode=doc#card-yrkk0j1dt7rp5j6");
        java.awt.Desktop.getDesktop().browse(uri);
    }
    public void updatePage_OnAction(ActionEvent actionEvent) {

    }
}
