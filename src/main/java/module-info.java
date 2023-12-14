module app.simulator {

    requires java.sql; //mysql

    requires java.desktop;
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;

    opens app.simulator.controllers to javafx.fxml;

    exports app.simulator.views;
    exports app.simulator.controllers;
    exports app.simulator.database;
    exports app.simulator.models;
    exports app.simulator.types;
}