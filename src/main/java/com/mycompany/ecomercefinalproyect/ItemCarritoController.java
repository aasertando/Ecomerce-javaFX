/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecomercefinalproyect;

import java.io.File;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
    private Button btnEliminar;

    private Runnable onEliminar;

    public void setDatos(String nombre, String detalle, String precioUnitario, String subtotal, Runnable onEliminar) {
        setDatos(nombre, detalle, precioUnitario, subtotal, null, onEliminar);
    }

    public void setDatos(String nombre, String detalle, String precioUnitario, String subtotal, String rutaImagen, Runnable onEliminar) {
        lblNombre.setText(nombre);
        lblDetalle.setText(detalle);
        lblPrecioUnitario.setText(precioUnitario);
        lblSubtotal.setText(subtotal);
        this.onEliminar = onEliminar;

        cargarImagen(imgProducto, rutaImagen);
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

    @FXML
    private void handleEliminar() {
        if (onEliminar != null) {
            onEliminar.run();
        }
    }
}
