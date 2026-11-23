package com.mycompany.ecomercefinalproyect;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    private static final String USUARIO_VALIDO = "admin";
    private static final String CLAVE_VALIDA = "admin";

    private static final String PANTALLA_SIGUIENTE = "principalDuplica";

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private void ingresar() throws IOException {
        String usuario = txtUsuario.getText().trim();
        String clave = txtContrasena.getText();

        if (usuario.isEmpty() || clave.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Escribe tu usuario y tu contraseña.");
            return;
        }

        if (usuario.equals(USUARIO_VALIDO) && clave.equals(CLAVE_VALIDA)) {
            App.setRoot(PANTALLA_SIGUIENTE);
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Usuario o contraseña incorrectos.");
            txtContrasena.clear();
            txtContrasena.requestFocus();
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
