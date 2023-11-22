package com.example.coursework.controller;

import com.example.coursework.model.Curator;
import com.example.coursework.model.Group;
import com.example.coursework.model.Student;
import com.example.coursework.repository.GroupRepository;
import com.example.coursework.util.AlertUtils;
import com.example.coursework.util.ObjectFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.coursework.controller.util.SceneSwitcher.switchScene;
import static com.example.coursework.model.Curator.getAvailableCurators;

public class AddGroupController {
    Group newGroup;
    List<Student> students = new ArrayList<>();

    GroupRepository groupRepository = ObjectFactory.groupRepository();

    List<String> coursesPZ2 = List.of(
            "Англійська мова",
            "Теорія ймовірності та математична статистика",
            "Алгоритми і структури даних",
            "Практикум з командної роботи",
            "Архітектура комп'ютера та комп'ютерних мереж",
            "Операційні системи");

    List<String> coursesKN2 = List.of(
            "Системний аналіз",
            "Теорія інформації",
            "Математичні методи дослідження операціц",
            "Фізичне виховання",
            "Веб-технології та веб-дизайн",
            "Комп'ютерні мережі");

    List<String> coursesIT2 = List.of(
            "Бази даних",
            "Хмарні технології",
            "Фізичне виховання",
            "Англійська  мова",
            "Філософія");

    @FXML
    private TextField groupName_TextField;

    @FXML
    private ComboBox<String> setCurator_ComboBox;

    @FXML
    private ListView<String> groupInfo_ListView;


    public void initialize() {
        List<String> groupCurators = getAvailableCurators();
        setCurator_ComboBox.setItems(FXCollections.observableArrayList(groupCurators));
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
    public void switchToMainPage(ActionEvent event) throws IOException {
        switchScene(event, "/fxml/main-page.fxml");
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
    public void GetStudents_Action(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Student File");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            List<String> studentsFromFile = readStudentsFromFile(file);
            for (String student : studentsFromFile) {
                Student temp = new Student(student);
                students.add(temp);
            }
        }
    }

    private static final String GROUP_NAME_ERROR = "Неправильна назва групи";
    private static final String GROUP_NAME_PATTERN_ERROR = "Назва групи повинна починатись з абревіатури " +
            "'ПЗ', 'КН', або 'ІТ'\nГруп в курсі може бути не більше восьми\nПриклад: КН-25";

    private static final String GROUP_BLOCKED_PATTERN_ERROR = "Група з таким ім'ям вже існує";

    @FXML
    public void AddNewGroup_Action() {
        String groupName = groupName_TextField.getText();

        if (!isValidGroupName(groupName)) {
            AlertUtils.showErrorAlert(GROUP_NAME_ERROR, GROUP_NAME_ERROR, GROUP_NAME_PATTERN_ERROR);
            return;
        }
        if (groupRepository.contains(groupName)) {
            AlertUtils.showErrorAlert("", "", GROUP_BLOCKED_PATTERN_ERROR);
            return;
        }

        try {
            Group newGroup = createGroup(groupName);
            updateGroupUI(newGroup);
            groupRepository.addGroup(newGroup);
        } catch (Exception e) {
            AlertUtils.showErrorAlert("", e.getMessage(), e.getMessage());
        }
    }

    @FXML
    void goToUserInstruction_Action(ActionEvent event) throws IOException, URISyntaxException {
        URI uri = new URI("https://gamma.app/public/--znuav7o8q7lun8u?mode=doc#card-yrkk0j1dt7rp5j6");
        java.awt.Desktop.getDesktop().browse(uri);
    }

    private Group createGroup(String groupName) throws Exception {
        String curatorName = Optional.ofNullable(setCurator_ComboBox.getSelectionModel().getSelectedItem())
                .orElseThrow(() -> new Exception("Curator is not selected"));
        Curator curator = new Curator(curatorName);

        List<String> courses = determineCoursesByGroupName(groupName);
        Student headman = students.isEmpty() ? null : students.get(0);

        return new Group(groupName, students, students.size(), headman, curator, courses);
    }

    private List<String> determineCoursesByGroupName(String groupName) {
        switch (groupName.charAt(0)) {
            case 'П':
                return new ArrayList<>(coursesPZ2);
            case 'К':
                return new ArrayList<>(coursesKN2);
            case 'І':
                return new ArrayList<>(coursesIT2);
            default:
                throw new IllegalArgumentException("Invalid group name prefix");
        }
    }

    @FXML
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
        if (name == null) {
            return false;
        }
        String pattern = "^(ПЗ|КН|ІТ)-[1-5][1-8]$";
        return name.matches(pattern);
    }

    private void updateGroupUI(Group group) {
        ObservableList<String> groupInfo = FXCollections.observableArrayList();

        groupInfo.add("Curator: " + (group.retrieveCuratorName() != null ? group.retrieveCuratorName() : "None"));
        groupInfo.add("Headman: " + (group.retrieveHeadmanName() != null ? group.retrieveHeadmanName() : "None"));

        if (group.retrieveStudentsNames() != null && !group.retrieveStudentsNames().isEmpty()) {
            groupInfo.add("\n");
            groupInfo.addAll(group.retrieveStudentsNames());
        } else {
            groupInfo.add("No students in the group");
        }

        groupInfo_ListView.setItems(groupInfo);
    }


}
