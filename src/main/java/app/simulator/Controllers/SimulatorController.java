package app.simulator.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class SimulatorController {

    // UI Selectors for the FXML file
    // table

    @FXML
    public TableColumn column_pantti;
    @FXML
    public TableColumn column_market;
    @FXML
    public TableColumn column_queue;
    @FXML
    public TableColumn column_selfservice;
    @FXML
    public TableColumn column_cashier;
    // input
    @FXML
    public TextField input_number;
    // buttons
    @FXML
    public Button btn_slower;
    @FXML
    public Button btn_faster;
    @FXML
    public Button btn_play;
    @FXML
    public Button btn_stop;

     /*
     input change handler:
     1. input should only accept numbers
     2. input should be filled before clicking play button
     3. create test code
     */

    /*
    button click handlers:
    -- slower and faster button
    -- play button
    1. when play button is clicked, the input should be disabled and input should have a value
    2. when play button is clicked, the slower, faster, and stop button should be active
    -- stop button

    */
}
