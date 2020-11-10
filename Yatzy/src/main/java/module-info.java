module yatzy.yatzy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens uicontroller to javafx.fxml;
    opens manager to java.sql;
    opens game to javafx.fxml;
    exports ui;
}
