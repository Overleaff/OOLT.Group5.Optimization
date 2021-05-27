module Optimization {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;

    opens controller to javafx.fxml;
    opens model to javafx.fxml;
    exports model;
}