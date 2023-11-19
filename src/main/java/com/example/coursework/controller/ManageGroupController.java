package com.example.coursework.controller;

import com.example.coursework.util.ObjectFactory;
import com.example.coursework.model.Group;
import com.example.coursework.repository.GroupRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.example.coursework.controller.util.SceneSwitcher.switchScene;
import static com.example.coursework.model.Curator.getAvailableCurators;

public class ManageGroupController {
    GroupRepository groupRepository = ObjectFactory.groupRepository();

    public void switchToMainPage(ActionEvent event) throws IOException {
        switchScene(event, "/fxml/main-page.fxml");
    }

    @FXML
    private ComboBox<String> groupName_ComboBox;

    @FXML
    private ComboBox<String> newCurator_ComboBox;

    @FXML
    private ComboBox<String> newHeadman_ComboBox;

    @FXML
    private TextField newStudent_TextField;

    @FXML
    private ComboBox<String> removeStudent_ComboBox;

    @FXML
    public void initialize() {
        List<String> groupNames = groupRepository.getGroupNames();
        groupName_ComboBox.setItems(FXCollections.observableArrayList(groupNames));

        List<String> groupCurators = getAvailableCurators();
        newCurator_ComboBox.setItems(FXCollections.observableArrayList(groupCurators));

        newHeadman_ComboBox.setDisable(true);
        removeStudent_ComboBox.setDisable(true);
        newStudent_TextField.setDisable(true);

        // Add listener to groupName_ComboBox
        groupName_ComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                // Retrieve the selected group
                Optional<Group> selectedGroup = groupRepository.getGroupByName(newVal);
                selectedGroup.ifPresent(group -> {
                    // Enable ComboBoxes and pass the selected group to methods
                    newHeadman_ComboBox.setDisable(false);
                    removeStudent_ComboBox.setDisable(false);
                    newStudent_TextField.setDisable(false);

                    List<String> groupStudents = groupRepository.getStudents(selectedGroup.get());
                    newHeadman_ComboBox.setItems(FXCollections.observableArrayList(groupStudents));
                    removeStudent_ComboBox.setItems(FXCollections.observableArrayList(groupStudents));
                });
            } else {
                newHeadman_ComboBox.setDisable(true);
                removeStudent_ComboBox.setDisable(true);
                newStudent_TextField.setDisable(true);
            }
        });
    }

    @FXML
    void SetGroupInfo_Action(ActionEvent event) {
        Optional<Group> selectedGroup = groupRepository.getGroupByName(groupName_ComboBox.getSelectionModel().getSelectedItem());
        selectedGroup.get().setCuratorString(newCurator_ComboBox.getSelectionModel().getSelectedItem());
        selectedGroup.get().setHeadmanString(newHeadman_ComboBox.getSelectionModel().getSelectedItem());
        selectedGroup.get().addStudent(newStudent_TextField.getSelectedText());
        selectedGroup.get().removeStudent(removeStudent_ComboBox.getSelectionModel().getSelectedItem());

        // TODO: show it in the ListView group: Curator:
        //                                      Headman:
        //                                      Students

    }

}
