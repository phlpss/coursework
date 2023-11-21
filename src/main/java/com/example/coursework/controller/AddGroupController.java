package com.example.coursework.controller;

import com.example.coursework.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.coursework.controller.util.SceneSwitcher.switchScene;

public class AddGroupController {
    List<Student> students = new ArrayList<>();

    @FXML
    public void switchToGroupsInCourse(ActionEvent event) throws Exception {
        switchScene(event, "/fxml/groups-in-course.fxml");
    }

    @FXML
    public void switchToManageGroup(ActionEvent event) throws IOException {
        switchScene(event, "/fxml/manage-group.fxml");
    }

    @FXML
    public void switchToMainPage(ActionEvent event) throws IOException {
        switchScene(event, "/fxml/main-page.fxml");
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


    public void SetGroupInfo_Action() {

    }

    public void GetStudents_Action(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Student File");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            List<String> studentsFromFile = readStudentsFromFile(file);
            for(String student : studentsFromFile) {
                Student temp = new Student(student);
                students.add(temp);
            }
        }
    }

    public void SaveGroupInfo_Action() {

    }

    public void AddNewGroup_Action() {


    }

    public void updatePage_OnAction() {

    }

    private List<String> readStudentsFromFile(File file) {
        List<String> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                students.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    private boolean isValidGroupName(String name) {
        if (name.length() < 5) {
            return false;
        }

        char[] charArray = name.toCharArray();

        boolean isValidPrefix =
                (charArray[0] == 'П' && charArray[1] == 'З') ||
                        (charArray[0] == 'К' && charArray[1] == 'Н') ||
                        (charArray[0] == 'О' && charArray[1] == 'І');
        boolean isValidHyphen = charArray[2] == '-';

        boolean isValidYear = Character.isDigit(charArray[3]) && charArray[3] >= '1' && charArray[3] <= '4';
        boolean isValidNumber = Character.isDigit(charArray[4]) && charArray[4] > 0 && charArray[4] < 9;

        return isValidPrefix && isValidHyphen && isValidYear && isValidNumber;
    }
}
