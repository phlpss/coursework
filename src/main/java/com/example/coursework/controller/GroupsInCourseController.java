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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.coursework.controller.util.SceneSwitcher.switchScene;

public class GroupsInCourseController {
    @FXML
    void saveAllChanges_OnAction(ActionEvent event) {
        ManageGroupController controller = (ManageGroupController) event.getSource();
        controller.SaveGroupInfo_Action(new ActionEvent());
    }

    @FXML
    public void switchToGroupsInCourse(ActionEvent event) throws Exception {
        switchScene(event, "/fxml/groups-in-course.fxml");
    }

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
    public void switchToAddGroup(ActionEvent event) throws IOException {
        switchScene(event, "/fxml/add-group.fxml");
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

    @FXML
    void sortGroup_OnAction(ActionEvent event) {
        ListView<Group> activeListView = getActiveListView();
        if (activeListView != null) {
            List<Group> groupsToSort = new ArrayList<>(activeListView.getItems());
            quickSort(groupsToSort, 0, groupsToSort.size() - 1);

            activeListView.getItems().clear();
            activeListView.getItems().addAll(groupsToSort);
        }
    }

    private void quickSort(List<Group> groups, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(groups, begin, end);

            quickSort(groups, begin, partitionIndex - 1);
            quickSort(groups, partitionIndex + 1, end);
        }
    }

    private int partition(List<Group> groups, int begin, int end) {
        Group pivot = groups.get(end);
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (groups.get(j).compareTo(pivot) <= 0) {
                i++;

                Group swapTemp = groups.get(i);
                groups.set(i, groups.get(j));
                groups.set(j, swapTemp);
            }
        }

        Group swapTemp = groups.get(i + 1);
        groups.set(i + 1, groups.get(end));
        groups.set(end, swapTemp);

        return i + 1;
    }

    private ListView<Group> getActiveListView() {
        if (!groupsByCourseName_ListView.getItems().isEmpty()) {
            return groupsByCourseName_ListView;
        } else if (!groupsByCourseYear_ListView.getItems().isEmpty()) {
            return groupsByCourseYear_ListView;
        }
        return null;
    }


    public void updatePage_OnAction(ActionEvent actionEvent) {

    }
}
