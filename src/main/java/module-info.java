module Optimization {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens controller to javafx.fxml;
    exports model.individual;
    exports model.algorithm;
    exports model;
    exports view;
}