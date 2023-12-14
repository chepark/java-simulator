package app.simulator.controllers;

import app.simulator.dao.RecordDao;
import app.simulator.models.Record;
import app.simulator.services.Engine;
import app.simulator.util.timeUtil.RandomTime;
import app.simulator.views.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.List;

public class SimulatorController {
    public TextArea queue1_status;
    public TextArea pantti_status;
    public TextArea market_status;
    public TextArea queue2_status;
    public TextArea selfcheckout_status;
    public TextArea cashier_status;
    public Text simulator_status;
    public Button btn_play;
    public Button btn_stop;
    @FXML
    private TextField input_number;
    @FXML
    private Slider speed_slider;

    private Engine simulatorEngine;
    private List<Record> records;
    private RecordDao recordDao = new RecordDao();
    private Writer writer;
    private double speedRate = 1;

    public void setInitialFocus() {
        input_number.requestFocus();
    }

    public void setTextInput(TextArea textArea, String string) {
        textArea.clear();
        textArea.setText(string);
    }

    public void setText(Text text, String string) {
        text.setText(string);
    }

    public void handleSliderRelease(MouseEvent e) {
        speedRate = speed_slider.getValue();
        if (writer != null) {
            writer.setSpeed(speedRate);
        }
    }

    public void handleStopBtnClick(ActionEvent e) {
        try {
            if (writer != null) {
                writer.interrupt();
                setText(simulator_status, "Stopped");
            }
        } catch (SecurityException ex) {
            ex.printStackTrace();
        }
    }

    public void handlePlayBtnClick(ActionEvent e) throws InterruptedException {
        // reset Record table in DB
        recordDao.removeAllRecords();
        setText(simulator_status, "Running");

        // get input value from the UI
        int customers = Integer.parseInt(input_number.getText());
        if (customers == 0 || input_number.getText().equals("")) {
            System.out.println("Please enter the number of customers");
            return;
        }
        // set the number of customers to generate arrival time
        RandomTime.setCustomerNumber(customers);

        // simulator main logic engine starts
        // simulator logic result will be saved in DB
        simulatorEngine = new Engine();
        simulatorEngine.start();
        simulatorEngine.join();

        // once engine is done, get all accumulated records from DB and start writer
        records = recordDao.getAllRecords();
        writer = new Writer(records);
        writer.start();
    }

    // Write the records to the UI
    public class Writer extends Thread {
        private List<Record> records;
        // set up the speed to volatile to make sure the speed can be updated while the thread is running.
        // default speed to display the customers on the UI: 1000ms = 1s
        private double speed = 1000;
        public Writer(List<Record> records) {
            this.records = records;
        }

        public void setSpeed(double speedRate) {
            if (speedRate == 0) {
                speed = 4000;
            } else if (speedRate == 0.5) {
                speed = 3000;
            } else if (speedRate == 1.0) {
                speed = 2000;
            } else if (speedRate == 1.5) {
                speed = 1000;
            } else if (speedRate == 2.0) {
                speed = 500;
            }
        }

        @Override
        public void run() {
            for (Record r : records) {
                String servicePoint = r.getServicePoint();
                switch (servicePoint) {
                case "QUEUE1":
                    setTextInput(queue1_status, r.getCustomers());
                    break;
                case "PANTTI":
                    setTextInput(pantti_status, r.getCustomers());
                    break;
                case "MARKET":
                    setTextInput(market_status, r.getCustomers());
                    break;
                case "QUEUE2":
                    setTextInput(queue2_status, r.getCustomers());
                    break;
                case "SELF_CHECKOUT":
                    setTextInput(selfcheckout_status, r.getCustomers());
                    break;
                case "CASHIER":
                    setTextInput(cashier_status, r.getCustomers());
                    break;
                }
                try {
                    Thread.sleep((long) speed);
                    System.out.println("Speed: " + speed);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            setText(simulator_status, "Finished! Report in DB");
        }
    }
}