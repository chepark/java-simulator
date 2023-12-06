package app.simulator.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SimulatorController {

    // service point status
    @FXML
    private ListView list_queue1;
    @FXML
    private ListView list_pantti;
    @FXML
    private ListView list_market;
    @FXML
    private ListView list_queue2;
    @FXML
    private ListView list_selfservice;
    @FXML
    private ListView list_cashier;

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
     * Get the input value and connect to model-> Customer
     */
    public void getNumOfCustomers() {
        // set the number of customers in the customer model
    }

    /***
     * Get the value and connect it to models-> Clock
     */
    public void changeSpeed() {
        // change the speed of clock on the model
    }

    /***
     * Stop the simulation.
     * @param e
     */
    public void stopSimulation(ActionEvent e) {

    }

    // play button
    public void playSimulation(ActionEvent e) {
        // get customers from model and start to display in table

    }

}
