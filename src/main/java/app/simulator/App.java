package app.simulator;

import app.simulator.DB.DatabaseAccessor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/Fxml/Simulator.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 900, 400);
        stage.setTitle("Simulator");
        stage.setScene(scene);
        stage.show();

        DatabaseAccessor db = new DatabaseAccessor();
        db.getConnection();

        // handle customer arrival:
        // 1. create customer based on input
        // - generated a customer in random time gap
        // - customer type is random: pantti or no-pantti
        // 2. Check customer type
        // - if pantti, then add to queue1
        // - if no-pantti, then add to market

        // create arraylist of service points in sequential order: queue1, pantti, market, queue2, self-service, cashier
        // run service with switch-case:
        // switch (service point)
        // case queue1: pantti empty? -> remove from queue1 and add to pantti
        //            : pantti x empty? -> stay in queue1
        // case pantti: after x minutes/seconds, remove the customer from pantti and add to market
        // case market: random shopping time, then remove from market and add to queue2
        // case queue2: check self-service or cashier empty? -> remove from queue2 and add to self-service or cashier
        // case self-service: after x minutes/seconds, remove the customer from self-service and add to cashier
    }
}