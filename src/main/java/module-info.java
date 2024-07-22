module com.example.ejemplojfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires org.jgrapht.core;
    requires com.google.gson;

    opens view to javafx.fxml;

    exports view;
    exports controlers;

    opens controlers to javafx.fxml;
    opens controlerView to javafx.fxml;
    opens model to com.google.gson;

    requires java.desktop;
}