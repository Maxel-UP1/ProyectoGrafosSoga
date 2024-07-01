module com.example.ejemplojfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires org.jgrapht.core;


    opens view to javafx.fxml;
    exports view;
    exports controlers;
    opens controlers to javafx.fxml;
}