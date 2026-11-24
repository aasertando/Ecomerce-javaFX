package com.mycompany.ecomercefinalproyect;

import Modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class registroController {

    @FXML
    private Button btnCrearCuenta;

    @FXML
    private TextField inputCiudad;

    @FXML
    private PasswordField inputContraseña;

    @FXML
    private TextField inputDireccion;

    @FXML
    private TextField inputEmail;

    @FXML
    private TextField inputNombre;

    @FXML
    private TextField inputTelefono;

    private final GestorUsuarios gestorUsuarios = new GestorUsuarios();

    @FXML
    void crearCuenta(ActionEvent event) {
        String nombre = inputNombre.getText().trim();
        String email = inputEmail.getText().trim();
        String telefono = inputTelefono.getText().trim();
        String direccion = inputDireccion.getText().trim();
        String ciudad = inputCiudad.getText().trim();
        String contraseña = inputContraseña.getText();

        if (nombre.isEmpty() || email.isEmpty() || telefono.isEmpty()
                || direccion.isEmpty() || ciudad.isEmpty() || contraseña.isEmpty()) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Campos incompletos",
                    "Por favor completa todos los campos."
            );
            return;
        }

        // Revisa que el orden de argumentos coincida con el constructor de tu clase Usuario
        Usuario u = new Usuario(
                nombre,
                email,
                telefono,
                direccion,
                ciudad,
                contraseña
        );

        if (gestorUsuarios.registrarUsuario(u)) {
            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Cuenta creada",
                    "El usuario se guardó en el JSON correctamente."
            );
            limpiarCampos();
        } else {
            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Usuario repetido",
                    "Ya existe un usuario con ese nombre."
            );
        }
    }

    private void limpiarCampos() {
        inputNombre.clear();
        inputEmail.clear();
        inputTelefono.clear();
        inputDireccion.clear();
        inputCiudad.clear();
        inputContraseña.clear();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
