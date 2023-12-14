package app.simulator.views;

import app.simulator.controllers.SimulatorController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main class of the application.
 * It is the starting point to run the application.
 */
public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/Fxml/Simulator.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 400);

        stage.setTitle("Supermarket simulator");
        stage.setScene(scene);
        stage.show();

        SimulatorController controller = fxmlLoader.getController();
        controller.setInitialFocus();
    }
}