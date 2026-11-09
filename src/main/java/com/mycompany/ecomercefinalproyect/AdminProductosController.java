package com.mycompany.ecomercefinalproyect;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class AdminProductosController {

    @FXML
    private TextField inputNombre;

    @FXML
    private ComboBox comboCategoria;

    @FXML
    private TextField inputPrecio;

    @FXML
    private TextField inputDescripcion;

    @FXML
    private Button btnSubirFoto;

    String foto;

    void handleSubirImagen() {

        FileChooser seleccionador = new FileChooser();
        seleccionador.setTitle("Seleccione foto del producto");
        seleccionador.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );
        
        //para que se abra en si y se guarde, y se abra con el botón de padre
        File archivo = seleccionador.showOpenDialog(btnSubirFoto.getScene().getWindow());
        
        
        
        

    }

    void handleCrearProducto() {

    }

}
