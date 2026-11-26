package com.mycompany.ecomercefinalproyect;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MoldeProductosListaDeseadosController {

    @FXML
    private Label Producto;

    @FXML
    private Label Descripcion;

    @FXML
    private Label Precio;

    public void setDatos(String producto, String descripcion, String precio) {
        if (Producto != null) {
            Producto.setText(producto);
        }
        if (Descripcion != null) {
            Descripcion.setText(descripcion);
        }
        if (Precio != null) {
            Precio.setText(precio);
        }
    }
}
