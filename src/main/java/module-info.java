module app.simulator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; //mysql
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.desktop;
    requires mysql.connector.j;


    opens app.simulator.controllers to javafx.fxml;

    exports app.simulator;
    exports app.simulator.controllers;
    exports app.simulator.database;
    exports app.simulator.models;
    exports app.simulator.types;
    exports app.simulator.views;
}