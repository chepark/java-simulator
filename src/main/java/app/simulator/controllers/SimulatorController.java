package app.simulator.controllers;

import app.simulator.models.ServicePoint;
import app.simulator.services.Engine;
import app.simulator.util.timeUtil.RandomTime;
import app.simulator.views.GUI;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SimulatorController {

    @FXML
    public ListView<String> list_selfcheckout;
    // service point status
    @FXML
    private ListView<String> list_queue1;
    @FXML
    private ListView<String> list_pantti;
    @FXML
    private ListView<String> list_market;
    @FXML
    private ListView<String> list_queue2;
    @FXML
    private ListView<String> list_cashier;

    // input
    @FXML
    private TextField input_number;
    // slider
    @FXML
    private Slider speed_slider;
    // buttons
    @FXML
    private Button btn_play;
    @FXML
    private Button btn_stop;

    private Engine simulatorEngine;

    private ServicePoint[] servicePoints;

    private GUI gui;
/*
    public SimulatorController(GUI gui) {
        this.gui = gui;
    }
*/

    /***
     * Display list of customer id waiting for returning pantti at Queue1.
     */
    public void setQueue1() {
        list_queue1.getItems().add("hello");
    }

    /***
     * Display one customer id at Pantti.
     */
    public void setPantti() {
        list_pantti.getItems().add("hello");
    }

    /***
     * Display list of customer id in Market
     */
    public void setMarket() {
        // get customers from model and display in list

    }

    /***
     * Display list of customer id at Queue2
     */
    public void setQueue2() {
        // get customers from model and display in list
    }

    /***
     * Display one customer id at self-checkout
     */
    public void setSelfService() {
        // get customers from model and display in list
    }

    /***
     * Display one customer id at casheir
     */
    public void setCashier() {
        // get customers from model and display in list
    }

    /***
     * Get the value and connect it to models-> Clock
     */
    public void handleSliderRelease(MouseEvent e) {
        // change the speed of clock on the model
        double speed = speed_slider.getValue();
        RandomTime.setSpeed(speed);
    }

    /***
     * Stop the simulation.
     * @param e
     */
    public void handleStopClick(MouseEvent e) {
        simulatorEngine.interrupt();
        System.out.println("stop");
    }

    // play button
    public void playSimulation(ActionEvent e) throws InterruptedException {
        // get customers from model and start to display in table
        int customers = Integer.parseInt(input_number.getText());
        if (customers == 0 || input_number.getText().equals("")) {
            System.out.println("Please enter the number of customers");
            return;
        }
        RandomTime.setCustomerNumber(customers);

        simulatorEngine = new Engine();
        simulatorEngine.start();

        servicePoints = simulatorEngine.getServicePoints();
        list_queue1.getItems().addAll(servicePoints[0].getQueueString());
        list_pantti.getItems().addAll(servicePoints[1].getQueueString());
        list_market.getItems().addAll(servicePoints[2].getQueueString());
        list_queue2.getItems().addAll(servicePoints[3].getQueueString());
        list_selfcheckout.getItems().addAll(servicePoints[4].getQueueString());
        list_cashier.getItems().addAll(servicePoints[5].getQueueString());
    }
}