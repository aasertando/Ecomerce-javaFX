/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecomercefinalproyect;

import java.io.File;
import java.net.URL;
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

    public void setFavoritoActivo(boolean activo) {
        if (btnFavorito != null) {
            btnFavorito.setText(activo ? "\u2665" : "\u2661");
        }
    }

    private void cargarImagen(ImageView imageView, String ruta) {
        if (imageView == null || ruta == null || ruta.isBlank()) {
            return;
        }
        try {
            URL recurso = getClass().getResource(ruta);
            if (recurso != null) {
                imageView.setImage(new Image(recurso.toExternalForm()));
            } else {
                String uri = (ruta.startsWith("file:") || ruta.startsWith("http:") || ruta.startsWith("https:"))
                        ? ruta
                        : new File(ruta).toURI().toString();
                imageView.setImage(new Image(uri));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDatos(String nombre, String detalle, String precio, String rutaImagen,
            Runnable onAgregarCarrito, Runnable onFavorito) {
        lblNombre.setText(nombre);
        lblDetalle.setText(detalle);
        lblPrecio.setText(precio);
        this.onAgregarCarrito = onAgregarCarrito;
        this.onFavorito = onFavorito;

        cargarImagen(imgProducto, rutaImagen);
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
