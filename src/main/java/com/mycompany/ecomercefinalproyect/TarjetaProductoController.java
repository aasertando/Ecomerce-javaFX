/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecomercefinalproyect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author garci
 */
public class TarjetaProductoController {

    @FXML 
    private ImageView imgProducto;
    @FXML
    private Button btnFavorito;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblDetalle;
    @FXML
    private Label lblPrecio;
    @FXML
    private Button btnAgregarCarrito;

    private Runnable onAgregarCarrito;
    private Runnable onFavorito;

    public void setDatos(String nombre, String detalle, String precio, String rutaImagen,
            Runnable onAgregarCarrito, Runnable onFavorito) {
        lblNombre.setText(nombre);
        lblDetalle.setText(detalle);
        lblPrecio.setText(precio);
        this.onAgregarCarrito = onAgregarCarrito;
        this.onFavorito = onFavorito;

        if (rutaImagen != null && !rutaImagen.isBlank()) {
            var url = getClass().getResource(rutaImagen);
            if (url != null) {
                imgProducto.setImage(new Image(url.toExternalForm()));
            }
        }
    }

    @FXML
    private void handleAgregarCarrito(ActionEvent event) {
        if (onAgregarCarrito != null) {
            onAgregarCarrito.run();
        }
    }

    @FXML
    private void handleFavorito(ActionEvent event) {
        if (onFavorito != null) {
            onFavorito.run();
        }
    }
}
