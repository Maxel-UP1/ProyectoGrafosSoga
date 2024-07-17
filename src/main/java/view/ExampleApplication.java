package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ExampleApplication extends Application {

    public static Stage currentStage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ExampleApplication.class.getResource("/view/ExampleAplication.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 550);
        stage.setTitle("Administrador de Paquetes");
        stage.setScene(scene);
        stage.show();
        currentStage = stage;
    }

    public static void main(String[] args) {
        launch();
    }
}
