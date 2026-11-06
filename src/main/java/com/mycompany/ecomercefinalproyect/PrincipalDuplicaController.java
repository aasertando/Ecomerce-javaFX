package com.mycompany.ecomercefinalproyect;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class PrincipalDuplicaController {

    @FXML
    private Button btnHeaderAlimentos;

    @FXML
    private Button btnHeaderBelleza;

    @FXML
    private Button btnHeaderBuscar;

    @FXML
    private Button btnHeaderCarrito;

    @FXML
    private Button btnHeaderDeportes;

    @FXML
    private Button btnHeaderElectronica;

    @FXML
    private Button btnHeaderHogar;

    @FXML
    private Button btnHeaderJuguetes;

    @FXML
    private Button btnHeaderRopa;

    @FXML
    private Button btnHeaderUsuario;

    @FXML
    private TextField inputHeaderBuscarProducto;

    @FXML
    private AnchorPane principal;

    @FXML
    void buscarProducto(ActionEvent event) {

    }

    @FXML
    void mostrarCarrito(ActionEvent event) {

    }

    @FXML
    void mostrarCategoriaAlimentos(ActionEvent event) {

    }

    @FXML
    void mostrarCategoriaBelleza(ActionEvent event) {

    }

    @FXML
    void mostrarCategoriaDeportes(ActionEvent event) {

    }

    @FXML
    void mostrarCategoriaElectronica(ActionEvent event) {

        try {
            AnchorPane nuevo = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/mycompany/ecomercefinalproyect/CategoriaElectronica.fxml"));
            principal.getChildren().setAll(nuevo);

            AnchorPane.setTopAnchor(nuevo, 0.0);
            AnchorPane.setBottomAnchor(nuevo, 0.0);
            AnchorPane.setLeftAnchor(nuevo, 0.0);
            AnchorPane.setRightAnchor(nuevo, 0.0);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void mostrarCategoriaHogar(ActionEvent event) {

    }

    @FXML
    void mostrarCategoriaJuguetes(ActionEvent event) {

    }

    @FXML
    void mostrarCategoriaRopa(ActionEvent event) {

        try {
            AnchorPane nuevo = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/mycompany/ecomercefinalproyect/CategoriaRopa.fxml"));
            principal.getChildren().setAll(nuevo);

            AnchorPane.setTopAnchor(nuevo, 0.0);
            AnchorPane.setBottomAnchor(nuevo, 0.0);
            AnchorPane.setLeftAnchor(nuevo, 0.0);
            AnchorPane.setRightAnchor(nuevo, 0.0);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void mostrarUsuario(ActionEvent event) {

    }

}
