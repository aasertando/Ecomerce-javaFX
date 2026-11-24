package com.mycompany.ecomercefinalproyect;

import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;

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
    
    private Popup menuPopup;

    @FXML
    void buscarProducto(ActionEvent event) {

    }

    @FXML
    void mostrarCarrito(ActionEvent event) {
        
        try {
            AnchorPane nuevo = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/mycompany/ecomercefinalproyect/Carrito.fxml"));
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
        // Si el menú ya está abierto, lo cierra al volver a hacer clic
        if (menuPopup != null && menuPopup.isShowing()) {
            menuPopup.hide();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/ecomercefinalproyect/MenuUsuario.fxml"));
            Parent contenidoMenu = loader.load();

            Stage stage = (Stage) btnHeaderUsuario.getScene().getWindow();

            menuPopup = new Popup();
            menuPopup.getContent().add(contenidoMenu);
            menuPopup.setAutoHide(true); // Se cierra solo al hacer clic fuera

            // Le pasamos el AnchorPane 'principal' y el 'stage' al controlador del menú
            MenuUsuarioController controller = loader.getController();
            controller.inicializar(principal, stage, menuPopup);

            // Ubicar el menú justo debajo y alineado a la derecha del botón btnHeaderUsuario
            Bounds limitesBoton = btnHeaderUsuario.localToScreen(btnHeaderUsuario.getBoundsInLocal());
            double anchoMenu = 250.0; 
            double posX = limitesBoton.getMaxX() - anchoMenu;
            double posY = limitesBoton.getMaxY() + 5;

            menuPopup.show(stage, posX, posY);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
