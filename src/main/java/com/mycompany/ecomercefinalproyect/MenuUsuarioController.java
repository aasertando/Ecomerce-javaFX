package com.mycompany.ecomercefinalproyect;

import Modelo.Sesion;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class MenuUsuarioController {

    private AnchorPane principal;
    private Stage stagePrincipal;
    private Popup popupActual;

    public void inicializar(AnchorPane principal, Stage stagePrincipal, Popup popupActual) {
        this.principal = principal;
        this.stagePrincipal = stagePrincipal;
        this.popupActual = popupActual;
    }

    @FXML
    void irAPerfil(ActionEvent event) {
        cargarEnPrincipal("/com/mycompany/ecomercefinalproyect/PerfilUsuario.fxml");
    }

    @FXML
    void irAListaDeseados(ActionEvent event) {
        cargarEnPrincipal("/com/mycompany/ecomercefinalproyect/ListaDeseados.fxml");
    }

    @FXML
    void irAHistorial(ActionEvent event) {
        cargarEnPrincipal("/com/mycompany/ecomercefinalproyect/HistorialCompras.fxml");
    }

    @FXML
    void cerrarSesion(ActionEvent event) {
        if (popupActual != null) {
            popupActual.hide();
        }
        Sesion.cerrarSesion();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/mycompany/ecomercefinalproyect/Login.fxml"));
            stagePrincipal.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarEnPrincipal(String rutaFxml) {
        if (popupActual != null) {
            popupActual.hide();
        }
        try {
            var recurso = getClass().getResource(rutaFxml);
            if (recurso == null) {
                System.err.println("No se encontro el archivo FXML: " + rutaFxml);
                return;
            }
            AnchorPane nuevo = (AnchorPane) FXMLLoader.load(recurso);
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
