package app.simulator.controllers;

import app.simulator.dao.RecordDao;
import app.simulator.entity.Record;
import app.simulator.models.ServicePoint;
import app.simulator.services.Engine;
import app.simulator.util.timeUtil.RandomTime;
import app.simulator.views.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.List;


public class SimulatorController {

    @FXML
    public TextArea queue1_status;
    @FXML
    public TextArea pantti_status;
    @FXML
    public TextArea market_status;
    @FXML
    public TextArea queue2_status;
    @FXML
    public TextArea selfcheckout_status;
    @FXML
    public TextArea casher_status;
    @FXML
    private TextField input_number;
    @FXML
    private Slider speed_slider;
    @FXML
    private Button btn_play;
    @FXML
    private Button btn_stop;

    private App view;
    private Engine simulatorEngine;
    private List<Record> records;
    private RecordDao recordDao = new RecordDao();

    public void setAppReference(App view) {
        this.view = view;
    }

    /***
     * Display list of customer id waiting for returning pantti at Queue1.
     */
    public void setStatue(TextArea textArea, String string) {
        textArea.clear();
        textArea.setText(string);
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
        // reset Record table in DB
        recordDao.removeAllRecords();

        int customers = Integer.parseInt(input_number.getText());
        if (customers == 0 || input_number.getText().equals("")) {
            System.out.println("Please enter the number of customers");
            return;
        }
        RandomTime.setCustomerNumber(customers);

        simulatorEngine = new Engine();
        simulatorEngine.start();
        simulatorEngine.join();

        records = recordDao.getAllRecords();
        Writer writer = new Writer(records);
        Thread thread = new Thread(writer);
        thread.start();
    }

    public class Writer implements Runnable {
        private List<Record> records;
        private TextArea textArea;
        private String string;

        public Writer(List<Record> records) {
            this.records = records;
            this.textArea = textArea;
            this.string = string;
        }

        @Override
        public void run() {
            for (Record r : records) {
                String servicePoint = r.getServicePoint();
                switch (servicePoint) {
                case "Queue1":
                    setStatue(queue1_status, r.getCustomers());
                    break;

                case "Pantti":
                    setStatue(pantti_status, r.getCustomers());
                    break;

                case "MARKET":
                    setStatue(market_status, r.getCustomers());
                    break;

                case "QUEUE2":
                    setStatue(queue2_status, r.getCustomers());
                    break;

                case "SELF_CHECKOUT":
                    setStatue(selfcheckout_status, r.getCustomers());
                    break;

                case "CASHIER":
                    setStatue(casher_status, r.getCustomers());
                    break;

                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}