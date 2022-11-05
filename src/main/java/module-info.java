module indexify {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;



//    opens application to javafx.fxml;
    opens application;
    exports application;
    opens indexifyDB;
}