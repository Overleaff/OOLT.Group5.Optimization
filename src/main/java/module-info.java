module Optimization {
    requires javafx.controls;
    requires javafx.fxml;

    opens controller to javafx.fxml;
    exports model.individual;
    exports model.algorithm;
    exports model;
    exports view;
}