package com.mycompany.ecomercefinalproyect;

import Modelo.Usuario;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private TextField txtContrasena;

    private final GestorUsuarios gestorUsuarios = new GestorUsuarios();

    @FXML
    private void ingresar() throws IOException {
        String usuario = txtUsuario.getText().trim();
        String contrasena = txtContrasena.getText();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Campos incompletos",
                    "Por favor ingresa tu usuario y contraseña."
            );
            return;
        }

        if (usuario.equals("admin") && contrasena.equals("admin")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PrincipalAdmin.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) txtUsuario.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            return;
        }

        Usuario u = gestorUsuarios.validarLogin(usuario, contrasena);

        if (u == null) {
            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Acceso denegado",
                    "Usuario o contraseña incorrectos."
            );
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("PrincipalDuplica.fxml"));
        Parent root = loader.load();

        PrincipalDuplicaController controller = loader.getController();
        controller.setUsuario(u);

        Stage stage = (Stage) txtUsuario.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void irARegistro() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/ecomercefinalproyect/registro.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) txtUsuario.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
