package com.example.coursework.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainApp extends Application {

    @Override
    public void start(Stage mainStage) throws Exception {
        setupStage(mainStage);
    }

    private void setupStage(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/main-page.fxml")));
        Scene scene = new Scene(root);

        stage.setTitle("KATERYNCHOS");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}