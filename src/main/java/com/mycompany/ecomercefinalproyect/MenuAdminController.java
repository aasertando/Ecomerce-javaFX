/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecomercefinalproyect;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class MenuAdminController {

    private Stage stagePrincipal;
    private Popup popupActual;

    public void inicializar(Stage stagePrincipal, Popup popupActual) {
        this.stagePrincipal = stagePrincipal;
        this.popupActual = popupActual;
    }

    @FXML
    void cerrarSesion(ActionEvent event) {
        if (popupActual != null) {
            popupActual.hide();
        }
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/mycompany/ecomercefinalproyect/Login.fxml"));
            stagePrincipal.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}