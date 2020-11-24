module yatzy.yatzy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires commons.io;

    opens ui.controller to javafx.fxml;
    opens core to java.sql;
    exports ui;
}
