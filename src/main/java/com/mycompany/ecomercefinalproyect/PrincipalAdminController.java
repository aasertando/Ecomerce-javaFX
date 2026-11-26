package com.mycompany.ecomercefinalproyect;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class PrincipalAdminController {

    @FXML
    private Button btnHeaderAdministrador;

    @FXML
    private Button btnHeaderBuscar;

    @FXML
    private Button btnHeaderProductos;

    @FXML
    private TextField inputHeaderBuscarProducto;

    @FXML
    private AnchorPane principal;

    @FXML
    private void initialize() {
        cargarVista("/com/mycompany/ecomercefinalproyect/AdminProductos.fxml");
    }

    private void cargarVista(String rutaFxml) {
        try {
            var recurso = getClass().getResource(rutaFxml);
            if (recurso == null) {
                System.err.println("No se encontró el FXML: " + rutaFxml);
                return;
            }
            Parent vista = FXMLLoader.load(recurso);
            principal.getChildren().setAll(vista);

            AnchorPane.setTopAnchor(vista, 0.0);
            AnchorPane.setBottomAnchor(vista, 0.0);
            AnchorPane.setLeftAnchor(vista, 0.0);
            AnchorPane.setRightAnchor(vista, 0.0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
 
    private Popup menuPopup;

    @FXML
    void mostrarMenuAdmin(ActionEvent event) {
        if (menuPopup != null && menuPopup.isShowing()) {
            menuPopup.hide();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/ecomercefinalproyect/MenuAdmin.fxml"));
            Parent contenidoMenu = loader.load();

            Stage stage = (Stage) btnHeaderAdministrador.getScene().getWindow();

            menuPopup = new Popup();
            menuPopup.getContent().add(contenidoMenu);
            menuPopup.setAutoHide(true);

            MenuAdminController controller = loader.getController();
            controller.inicializar(stage, menuPopup);

            javafx.geometry.Bounds limitesBoton = btnHeaderAdministrador.localToScreen(btnHeaderAdministrador.getBoundsInLocal());
            double anchoMenu = 210.0;
            double posX = limitesBoton.getMaxX() - anchoMenu;
            double posY = limitesBoton.getMaxY() + 5;

            menuPopup.show(stage, posX, posY);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void buscarProducto(ActionEvent event) {
        String texto = (inputHeaderBuscarProducto != null) ? inputHeaderBuscarProducto.getText() : "";
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/mycompany/ecomercefinalproyect/AdminProductos.fxml")
            );
            Parent vista = loader.load();
            AdminProductosController ctrl = loader.getController();
            ctrl.filtrar(texto);

            principal.getChildren().setAll(vista);
            AnchorPane.setTopAnchor(vista, 0.0);
            AnchorPane.setBottomAnchor(vista, 0.0);
            AnchorPane.setLeftAnchor(vista, 0.0);
            AnchorPane.setRightAnchor(vista, 0.0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void mostrarAdmin(ActionEvent event) {
        // Aquí puedes cargar la vista principal del admin si tienes una
    }

    @FXML
    void mostrarProductos(ActionEvent event) {
        cargarVista("/com/mycompany/ecomercefinalproyect/AdminProductos.fxml");
    }
}