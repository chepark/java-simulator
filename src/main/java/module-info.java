module app.simulator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; //mysql
    requires de.jensd.fx.glyphs.fontawesome; // fontawesome

    opens app.simulator to javafx.fxml;
    exports app.simulator;
    exports app.simulator.Models;
    exports app.simulator.Controllers;
    exports app.simulator.Views;
    exports app.simulator.DB;
}