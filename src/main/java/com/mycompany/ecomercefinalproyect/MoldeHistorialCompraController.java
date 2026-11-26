package com.mycompany.ecomercefinalproyect;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MoldeHistorialCompraController {

    @FXML
    private Label Pedido;

    @FXML
    private Label Productos;

    @FXML
    private Label Total;

    public void setDatos(String pedido, String productos, String total) {
        if (Pedido != null) {
            Pedido.setText(pedido);
        }
        if (Productos != null) {
            Productos.setText(productos);
        }
        if (Total != null) {
            Total.setText(total);
        }
    }
}
