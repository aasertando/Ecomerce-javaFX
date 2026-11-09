package com.mycompany.ecomercefinalproyect;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class PrincipalAdminController {

    @FXML
    private Button btnHeaderAdministrador;

    @FXML
    private Button btnHeaderBuscar;

    @FXML
    private Button btnHeaderCompras;

    @FXML
    private Button btnHeaderProductos;

    @FXML
    private Button btnHeaderUsuarios;

    @FXML
    private TextField inputHeaderBuscarProducto;

    @FXML
    private AnchorPane principal;

    @FXML
    void buscarProducto(ActionEvent event) {

    }

    @FXML
    void mostrarAdmin(ActionEvent event) {

    }

    @FXML
    void mostrarCompras(ActionEvent event) {

        try {
            AnchorPane nuevo = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/mycompany/ecomercefinalproyect/AdminCompras.fxml"));
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
    void mostrarProductos(ActionEvent event) {

        try {
            AnchorPane nuevo = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/mycompany/ecomercefinalproyect/AdminProductos.fxml"));
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
    void mostrarUsuarios(ActionEvent event) {

        try {
            AnchorPane nuevo = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/mycompany/ecomercefinalproyect/AdminUsuarios.fxml"));
            principal.getChildren().setAll(nuevo);

            AnchorPane.setTopAnchor(nuevo, 0.0);
            AnchorPane.setBottomAnchor(nuevo, 0.0);
            AnchorPane.setLeftAnchor(nuevo, 0.0);
            AnchorPane.setRightAnchor(nuevo, 0.0);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
