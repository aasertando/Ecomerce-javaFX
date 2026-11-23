/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecomercefinalproyect;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 *
 * @author garci
 */
public class ItemCarritoController {

    @FXML
    private ImageView imgProducto;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblDetalle;
    @FXML
    private Label lblPrecioUnitario;
    @FXML
    private Label lblSubtotal;
    @FXML
    private Button btnEliminar; // o Hyperlink, según uses en el FXML

    private Runnable onEliminar;

    public void setDatos(String nombre, String detalle, String precioUnitario, String subtotal, Runnable onEliminar) {
        lblNombre.setText(nombre);
        lblDetalle.setText(detalle);
        lblPrecioUnitario.setText(precioUnitario);
        lblSubtotal.setText(subtotal);
        this.onEliminar = onEliminar;
    }

    @FXML
    private void handleEliminar() {
        if (onEliminar != null) {
            onEliminar.run();
        }
    }
}
