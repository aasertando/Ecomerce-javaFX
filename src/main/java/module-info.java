module com.mycompany.ecomercefinalproyect {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires com.google.gson;

    opens com.mycompany.ecomercefinalproyect to javafx.fxml;
    opens Modelo to com.google.gson;

    exports com.mycompany.ecomercefinalproyect;
    exports Modelo;
}
