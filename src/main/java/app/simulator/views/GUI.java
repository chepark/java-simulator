package app.simulator.views;

import app.simulator.controllers.SimulatorController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This application is created as a group assignment for Project Programming course in Metropolia UAS.
 *
 * @author Bijay Karki
 * @author Chaeah Park
 * @author Mamita Gurung
 * @author Vladislav Zakatov
 */

public class GUI extends Application {
    private SimulatorController simulatorController;

    public static void main(String[] args) {
        launch();
    }

    public void init() {
        simulatorController = new SimulatorController(this);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("/Fxml/Simulator.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 900, 400);
        stage.setTitle("Supermarket simulator");
        stage.setScene(scene);
        stage.show();
    }

}