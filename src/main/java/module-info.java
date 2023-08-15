module com.onix.login_fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.onix.login_fx to javafx.fxml;
    exports com.onix.login_fx;
}