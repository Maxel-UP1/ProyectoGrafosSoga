module com.example.ejemplojfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires org.jgrapht.core;
    requires com.google.gson;
    requires java.desktop;


    opens view to javafx.fxml;
    opens controlers to javafx.fxml;
    opens controlerView to javafx.fxml;
    opens model to javafx.base, javafx.fxml, com.google.gson;


    exports view;
    exports controlers;
    exports controlerView;
}