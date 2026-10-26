module com.mycompany.ecomercefinalproyect {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.ecomercefinalproyect to javafx.fxml;
    exports com.mycompany.ecomercefinalproyect;
}
