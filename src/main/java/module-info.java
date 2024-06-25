module com.example.ejemplojfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens view to javafx.fxml;
    exports view;
    exports controlers;
    opens controlers to javafx.fxml;
}