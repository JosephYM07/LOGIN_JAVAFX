module com.onix.login_fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens onix.login_fx to javafx.fxml;
    exports onix.login_fx;
}