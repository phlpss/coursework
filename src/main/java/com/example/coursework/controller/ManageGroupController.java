package com.example.coursework.controller;

import com.example.coursework.util.AlertUtils;
import com.example.coursework.util.ObjectFactory;
import com.example.coursework.model.Group;
import com.example.coursework.repository.GroupRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import static com.example.coursework.controller.util.SceneSwitcher.switchScene;
import static com.example.coursework.model.Curator.getAvailableCurators;

public class ManageGroupController {
    GroupRepository groupRepository = ObjectFactory.groupRepository();

    @FXML
    public void switchToMainPage(ActionEvent event) throws IOException {
        switchScene(event, "/fxml/main-page.fxml");
    }
    @FXML
    public void switchToGroupsInCourse(ActionEvent event) throws Exception {
        switchScene(event, "/fxml/groups-in-course.fxml");
    }

    @FXML
    public void switchToManageGroup(ActionEvent event) throws IOException {
        switchScene(event, "/fxml/manage-group.fxml");
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
    private ListView<String> groupInfo_ListView;

    @FXML
    public void initialize() {
        List<String> groupNames = groupRepository.getGroupNames();
        groupName_ComboBox.setItems(FXCollections.observableArrayList(groupNames));

        List<String> groupCurators = getAvailableCurators();
        newCurator_ComboBox.setItems(FXCollections.observableArrayList(groupCurators));

        newCurator_ComboBox.setDisable(true);
        newHeadman_ComboBox.setDisable(true);
        removeStudent_ComboBox.setDisable(true);
        newStudent_TextField.setDisable(true);

        groupName_ComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                Optional<Group> selectedGroup = groupRepository.getGroupByName(newVal);
                selectedGroup.ifPresent(group -> {
                    newCurator_ComboBox.setDisable(false);
                    newHeadman_ComboBox.setDisable(false);
                    removeStudent_ComboBox.setDisable(false);
                    newStudent_TextField.setDisable(false);

                    List<String> groupStudents = groupRepository.getStudents(selectedGroup.get());
                    newHeadman_ComboBox.setItems(FXCollections.observableArrayList(groupStudents));
                    removeStudent_ComboBox.setItems(FXCollections.observableArrayList(groupStudents));
                });
            } else {
                newCurator_ComboBox.setDisable(true);
                newHeadman_ComboBox.setDisable(true);
                removeStudent_ComboBox.setDisable(true);
                newStudent_TextField.setDisable(true);
            }
        });
    }

    @FXML
    void SetGroupInfo_Action(ActionEvent event) {
        String selectedGroupName = groupName_ComboBox.getSelectionModel().getSelectedItem();
        if (!isValidSelection(selectedGroupName)) {
            showSelectionError("No Group Selected", "Please select a group first.");
            return;
        }

        Optional<Group> selectedGroupOpt = groupRepository.getGroupByName(selectedGroupName);
        if (selectedGroupOpt.isEmpty()) {
            showSelectionError("Group Not Found", "The selected group could not be found.");
            return;
        }

        Group selectedGroup = selectedGroupOpt.get();
        updateGroupCurator(selectedGroup);
        updateGroupHeadman(selectedGroup);
        addNewStudentToGroup(selectedGroup);
        removeStudentFromGroup(selectedGroup);

        updateGroupUI(selectedGroup);
    }


    @FXML
    void SaveGroupInfo_Action(ActionEvent event) {
        groupRepository.flushToFile();
    }

    private void updateGroupCurator(Group group) {
        String newCuratorName = newCurator_ComboBox.getSelectionModel().getSelectedItem();
        if (newCuratorName != null) {
            group.settleCuratorString(newCuratorName);
        }
    }

    private void updateGroupHeadman(Group group) {
        String newHeadmanName = newHeadman_ComboBox.getSelectionModel().getSelectedItem();
        if (newHeadmanName != null) {
            group.settleHeadmanString(newHeadmanName);
        }
    }

    private void addNewStudentToGroup(Group group) {
        String newStudentName = newStudent_TextField.getText();
        if (newStudentName != null && !newStudentName.trim().isEmpty()) {
            group.addStudent(newStudentName.trim());
        }
    }

    private void removeStudentFromGroup(Group group) {
        String studentToRemove = removeStudent_ComboBox.getSelectionModel().getSelectedItem();
        if (studentToRemove != null) {
            group.removeStudent(studentToRemove);
        }
    }

    private boolean isValidSelection(String selection) {
        return selection != null && !selection.isEmpty();
    }

    private void showSelectionError(String header, String content) {
        AlertUtils.showErrorAlert(header, " ", content);
    }

    private void updateGroupUI(Group group) {
        ObservableList<String> groupInfo = FXCollections.observableArrayList();

        groupInfo.add("Curator: " + (group.retrieveCuratorName() != null ? group.retrieveCuratorName() : "None"));
        groupInfo.add("Headman: " + (group.retrieveHeadmanName() != null ? group.retrieveHeadmanName() : "None"));

        if (group.retrieveStudentsNames() != null && !group.retrieveStudentsNames().isEmpty()) {
            groupInfo.add("Students:\n");
            groupInfo.addAll(group.retrieveStudentsNames());
        } else {
            groupInfo.add("No students in the group");
        }

        groupInfo_ListView.setItems(groupInfo);
    }
}
