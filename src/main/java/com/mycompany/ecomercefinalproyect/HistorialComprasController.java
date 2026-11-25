package com.mycompany.ecomercefinalproyect;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HistorialComprasController implements Initializable {

    @FXML
    private VBox contenedorHistorial;

    @FXML
    private Label lblVacio;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Por ahora no hay persistencia de pedidos confirmados.
        // Se muestra el mensaje vacío hasta que se implemente.
        mostrarPlaceholder();
    }

    private void mostrarPlaceholder() {
        boolean hayItems = contenedorHistorial != null
                && !contenedorHistorial.getChildren().isEmpty();
        if (lblVacio != null) {
            lblVacio.setVisible(!hayItems);
            lblVacio.setManaged(!hayItems);
        }
    }
}
