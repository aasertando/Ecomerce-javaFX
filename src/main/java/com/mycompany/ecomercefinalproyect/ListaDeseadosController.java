package com.mycompany.ecomercefinalproyect;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

public class ListaDeseadosController implements Initializable {

    @FXML
    private TilePane contenedorDeseados;

    @FXML
    private Label lblVacio;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Por ahora no hay modelo de datos para favoritos.
        // Se muestra el mensaje vacío hasta que se implemente la persistencia.
        mostrarPlaceholder();
    }

    private void mostrarPlaceholder() {
        boolean hayItems = contenedorDeseados != null
                && !contenedorDeseados.getChildren().isEmpty();
        if (lblVacio != null) {
            lblVacio.setVisible(!hayItems);
            lblVacio.setManaged(!hayItems);
        }
    }
}
