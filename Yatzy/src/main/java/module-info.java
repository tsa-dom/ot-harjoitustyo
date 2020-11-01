module yatzy.yatzy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens uicontroller to javafx.fxml;
    exports ui;
}
