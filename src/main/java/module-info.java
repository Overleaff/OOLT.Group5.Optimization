module Optimization {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens controller to javafx.fxml;
    exports model;
    exports algorithm;
    exports view;
    exports controller;
}