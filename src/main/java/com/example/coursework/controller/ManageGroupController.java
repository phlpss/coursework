package com.example.coursework.controller;
import javafx.event.ActionEvent;
import java.io.IOException;
import static com.example.coursework.controller.util.SceneSwitcher.switchScene;

public class ManageGroupController {
    public void switchToMainPage(ActionEvent event) throws IOException {
        switchScene(event, "/fxml/main-page.fxml");
    }

}
