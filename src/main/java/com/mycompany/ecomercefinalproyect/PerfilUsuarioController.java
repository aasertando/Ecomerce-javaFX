package com.mycompany.ecomercefinalproyect;

import Modelo.Sesion;
import Modelo.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PerfilUsuarioController implements Initializable {

    @FXML
    private TextField casillaNombreUsuario;

    @FXML
    private TextField casillaIDUsuario;

    @FXML
    private TextField casillaCorreoUsuario;

    @FXML
    private TextField casillaTelefonoUsuario;

    @FXML
    private TextField casillaCiudadUsuario;

    @FXML
    private TextField casillaDirecciondUsuario;

    @FXML
    private Button btnEditarInfo;

    @FXML
    private Button btnGuardarInfo;

    private final GestorUsuarios gestorUsuarios = new GestorUsuarios();
    private boolean modoEdicion = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatosUsuario();
        setModoEdicion(false);
    }

    private void cargarDatosUsuario() {
        Usuario u = Sesion.getUsuarioActual();
        if (u != null) {
            String nombre = u.getNombre() != null ? u.getNombre() : "";
            casillaNombreUsuario.setText(nombre);
            casillaCorreoUsuario.setText(u.getEmail() != null ? u.getEmail() : "");
            casillaTelefonoUsuario.setText(u.getTelefono() != null ? u.getTelefono() : "");
            casillaCiudadUsuario.setText(u.getCiudad() != null ? u.getCiudad() : "");
            casillaDirecciondUsuario.setText(u.getDireccion() != null ? u.getDireccion() : "");

            casillaIDUsuario.setText(nombre);
        }
    }

    @FXML
    private void editarInfo(ActionEvent event) {
        modoEdicion = !modoEdicion;
        setModoEdicion(modoEdicion);
        if (modoEdicion) {
            casillaNombreUsuario.requestFocus();
        } else {
            cargarDatosUsuario();
        }
    }

    private void setModoEdicion(boolean editable) {
        this.modoEdicion = editable;

        configurarCampo(casillaNombreUsuario, editable);
        configurarCampo(casillaCorreoUsuario, editable);
        configurarCampo(casillaTelefonoUsuario, editable);
        configurarCampo(casillaCiudadUsuario, editable);
        configurarCampo(casillaDirecciondUsuario, editable);

        if (casillaIDUsuario != null) {
            casillaIDUsuario.setEditable(false);
        }

        if (btnEditarInfo != null) {
            btnEditarInfo.setText(editable ? "Cancelar" : "Editar información");
        }

        if (btnGuardarInfo != null) {
            btnGuardarInfo.setVisible(editable);
            btnGuardarInfo.setManaged(editable);
        }
    }

    private void configurarCampo(TextField campo, boolean editable) {
        if (campo == null) {
            return;
        }
        campo.setEditable(editable);
        if (editable) {
            if (!campo.getStyleClass().contains("casilla-editable")) {
                campo.getStyleClass().add("casilla-editable");
            }
        } else {
            campo.getStyleClass().remove("casilla-editable");
        }
    }

    @FXML
    private void guardarInfo(ActionEvent event) {
        Usuario usuarioActual = Sesion.getUsuarioActual();
        if (usuarioActual == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No hay una sesión de usuario activa.");
            return;
        }

        String nuevoNombre = casillaNombreUsuario.getText().trim();
        String nuevoCorreo = casillaCorreoUsuario.getText().trim();
        String nuevoTelefono = casillaTelefonoUsuario.getText().trim();
        String nuevaCiudad = casillaCiudadUsuario.getText().trim();
        String nuevaDireccion = casillaDirecciondUsuario.getText().trim();

        if (nuevoNombre.isEmpty() || nuevoCorreo.isEmpty() || nuevoTelefono.isEmpty()
                || nuevaCiudad.isEmpty() || nuevaDireccion.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos incompletos", "Por favor completa todos los campos de información personal.");
            return;
        }

        Usuario actualizado = new Usuario(
                nuevoNombre,
                nuevoCorreo,
                nuevoTelefono,
                nuevaDireccion,
                nuevaCiudad,
                usuarioActual.getContraseña()
        );

        String nombreOriginal = usuarioActual.getNombre();

        if (gestorUsuarios.actualizarUsuario(nombreOriginal, actualizado)) {
            Sesion.setUsuarioActual(actualizado);
            if (casillaIDUsuario != null) {
                casillaIDUsuario.setText(nuevoNombre);
            }
            setModoEdicion(false);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Perfil actualizado", "Tu información personal se guardó correctamente.");
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error al guardar", "No se pudo actualizar la información en usuarios.json.");
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
