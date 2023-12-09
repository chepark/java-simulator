package app.simulator;

import app.simulator.dao.ServiceTimeDao;
import app.simulator.entity.ServiceTime;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;


/**
 * This application is created as a group assignment for Project Programming course in Metropolia UAS.
 *
 * @author Bijay Karki
 * @author Chaeah Park
 * @author Mamita Gurung
 * @author Vladislav Zakatov
 */

public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/Fxml/Simulator.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 900, 400);
        stage.setTitle("Supermarket simulator");
        stage.setScene(scene);
        stage.show();

        ServiceTimeDao serviceDao = new ServiceTimeDao();
        List<ServiceTime> s = serviceDao.getAllServices();
        ServiceTime c = new ServiceTime("service2", 32, 12.0, 13.0, 1.0);
        serviceDao.putService(c);
        System.out.println(s.toString());

    }
}