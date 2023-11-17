package com.example.coursework.controller.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneSwitcher {
    public static void switchScene(ActionEvent event, String fxmlFile) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(SceneSwitcher.class.getResource(fxmlFile)));

        // Create new scene with same size as current scene
        Scene scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
    }
}
