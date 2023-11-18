package com.example.coursework.controller;

import com.example.coursework.ObjectFactory;
import com.example.coursework.repository.GroupRepository;
import com.example.coursework.other.Group;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.DragEvent;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.example.coursework.controller.util.SceneSwitcher.switchScene;

public class GroupsInCourseController {

    GroupRepository groupRepository = ObjectFactory.groupRepository();

    @FXML
    public void initialize() {
        List<String> courses = groupRepository.getCourses();
        coursename_comboBox.setItems(FXCollections.observableArrayList(courses));

        List<Integer> years = Arrays.asList(1, 2, 3, 4);
        ObservableList<Integer> observableYears = FXCollections.observableArrayList(years);
        courseYear_comboBox.setItems(observableYears);
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
    private ComboBox<Integer> courseYear_comboBox;

    @FXML
    private ComboBox<String> coursename_comboBox;

    @FXML
    private ListView<Group> groupsByCourseName_ListView;

    @FXML
    private ListView<?> groupsByCourseYear_ListView;


    @FXML
    void getGroupsByCourseName_EnterKey() {
        String selectedCourse = coursename_comboBox.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            List<Group> groupsByCourseName = groupRepository.getGroupsByCourseName(selectedCourse);
            groupsByCourseName_ListView.getItems().clear();
            groupsByCourseName_ListView.getItems().addAll(groupsByCourseName);
        }
    }


    @FXML
    void getGroupsByCourseYear_EnterKey() {
//        groupRepository.getGroupsByCourseYear(/* course year user chose */);
    }
}
