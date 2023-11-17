module com.example.coursework {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.coursework.app to javafx.fxml;
    exports com.example.coursework.app;

    exports com.example.coursework.controller to javafx.fxml;
    exports com.example.coursework.other to javafx.fxml;
    opens com.example.coursework.controller to javafx.fxml;

}
