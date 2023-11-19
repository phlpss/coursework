package com.example.coursework.controller;

import com.example.coursework.util.ObjectFactory;
import com.example.coursework.repository.GroupRepository;
import com.example.coursework.model.Group;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.example.coursework.controller.util.SceneSwitcher.switchScene;

public class GroupsInCourseController {

    GroupRepository groupRepository = ObjectFactory.groupRepository();

    @FXML
    public void initialize() {
        List<String> courses = groupRepository.getCourses();
        courseName_comboBox.setItems(FXCollections.observableArrayList(courses));

        List<String> years = Arrays.asList("1", "2", "3", "4");
        courseYear_comboBox.setItems(FXCollections.observableArrayList(years));
    }

    @FXML
    public void switchToMainPage(ActionEvent event) throws IOException {
        switchScene(event, "/fxml/main-page.fxml");
    }

    @FXML
    public void switchToManageGroup(ActionEvent event) throws IOException {
        switchScene(event, "/fxml/manage-group.fxml");
    }

    @FXML
    private ComboBox<String> courseYear_comboBox;

    @FXML
    private ComboBox<String> courseName_comboBox;

    @FXML
    private ListView<Group> groupsByCourseName_ListView;

    @FXML
    private ListView<Group> groupsByCourseYear_ListView;


    @FXML
    void getGroupsByCourseName_EnterKey() {
        String selectedCourse = courseName_comboBox.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            List<Group> groupsByCourseName = groupRepository.getGroupsByCourseName(selectedCourse);
            groupsByCourseName_ListView.getItems().clear();
            groupsByCourseName_ListView.getItems().addAll(groupsByCourseName);
        }
    }


    @FXML
    void getGroupsByCourseYear_EnterKey() {
        String selectedCourse = courseYear_comboBox.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            List<Group> groupsByCourseYear = groupRepository.getGroupsByCourseYear(selectedCourse);
            groupsByCourseYear_ListView.getItems().clear();
            groupsByCourseYear_ListView.getItems().addAll(groupsByCourseYear);
        }
    }

    // TODO: if group was selected in list, and button pressed -> switchToManageGroup and call UpdateGroupInfo_Action
}
