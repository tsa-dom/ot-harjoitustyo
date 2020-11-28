module yatzy.yatzy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires javafx.base;
    
    opens service.game to javafx.base;
    opens ui.controller to javafx.fxml;
    opens core to java.sql;
    exports ui;
}
