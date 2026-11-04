module com.mycompany.ecomercefinalproyect {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.ecomercefinalproyect to javafx.fxml;
    exports com.mycompany.ecomercefinalproyect;
}
