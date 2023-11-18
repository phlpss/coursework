package com.example.coursework.controller;

import com.example.coursework.ObjectFactory;
import com.example.coursework.repository.GroupRepository;
import com.example.coursework.other.Group;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.coursework.controller.util.SceneSwitcher.switchScene;

public class GroupsInCourseController {

    GroupRepository groupRepository = ObjectFactory.groupRepository();

    @FXML
    public void switchToMainPage(ActionEvent event) throws IOException {
        switchScene(event, "/fxml/main-page.fxml");
    }

    @FXML
    public void switchToManageGroup(ActionEvent event) throws IOException {
        switchScene(event, "/fxml/manage-group.fxml");
    }

    @FXML
    private ComboBox<?> courseYear_comboBox;

    @FXML
    private ComboBox<?> coursename_comboBox;

    @FXML
    private Button manageGroup_Button;

    @FXML
    private ListView<Group> groupsByCourseName_ListView;

    @FXML
    private ListView<?> groupsByCourseYear_ListView;

    private void loadCourseNames() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("C:\\Users\\KATERYNKA\\Desktop\\LPNU\\term 3\\CouseWork\\coursework\\src\\main\\resources\\json\\groups.json");

        List<Group> groups = groupRepository.getGroups();

        Set<String> courseNames = new HashSet<>();
        for (Group group : groups) {
            courseNames.addAll(group.getCourses());
        }
        coursename_comboBox.setItems(FXCollections.observableArrayList());
    }

    @FXML
    void getGroupsByCourseName_EnterKey(ActionEvent event) {
        String selectedCourse = (String) coursename_comboBox.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            List<Group> groupsByCourseName = groupRepository.getGroupsByCourseName(selectedCourse);
            groupsByCourseName_ListView.getItems().clear();
            groupsByCourseName_ListView.getItems().addAll(groupsByCourseName);
        }
    }


    @FXML
    void getGroupsByCourseYear_EnterKey(DragEvent event) {
//        groupRepository.getGroupsByCourseYear(/* course year user chose */);
    }
}
