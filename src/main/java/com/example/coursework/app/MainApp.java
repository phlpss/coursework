package com.example.coursework.app;

import com.example.coursework.model.Group;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class MainApp extends Application {

    @Override
    public void start(Stage mainStage) throws Exception {
        setupStage(mainStage);
        initializeGroups();
    }

    private void setupStage(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/main-page.fxml")));
        Scene scene = new Scene(root);

        stage.setTitle("KATERYNCHOS");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public void initializeGroups() throws Exception {
        String content = new String(Files.readAllBytes(Paths.get("C:\\Users\\KATERYNKA\\Desktop\\LPNU\\term 3\\CouseWork\\coursework\\src\\main\\resources\\json\\groups.json")));
        ObjectMapper objectMapper = new ObjectMapper();
        List<Group> groups = objectMapper.readValue(content, new TypeReference<>() {});
        groups.forEach(System.out::println);
    }

    public static void main(String[] args) {
        launch(args);
    }
}