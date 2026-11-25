package com.mycompany.ecomercefinalproyect;

import Modelo.Categorias;
import Modelo.Productos;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class AdminProductosController implements Initializable {

    // Columna izquierda: Catálogo de productos
    @FXML
    private VBox contenedorProductos;

    // Columna derecha: Formulario Añadir producto
    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<String> cbCategoria;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private Button btnSubirImagen;

    private final GestorProductos gestorProductos = new GestorProductos();
    private String rutaImagenSeleccionada = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (cbCategoria != null) {
            List<String> nombresCategoria = new ArrayList<>();
            for (Categorias c : Categorias.values()) {
                nombresCategoria.add(c.getNombre());
            }
            cbCategoria.setItems(FXCollections.observableArrayList(nombresCategoria));
        }
        if (contenedorProductos != null) {
            contenedorProductos.setSpacing(10);
        }
        cargarCatalogoAdmin();
    }

    // Refresca la lista completa de productos
    public void cargarCatalogoAdmin() {
        renderizarProductos(gestorProductos.obtenerTodos());
    }

    // Filtra productos por nombre, categoría o descripción
    public void filtrar(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            cargarCatalogoAdmin();
            return;
        }
        String filtro = texto.trim().toLowerCase();
        List<Productos> filtrados = new ArrayList<>();
        for (Productos p : gestorProductos.obtenerTodos()) {
            if ((p.getNombre() != null && p.getNombre().toLowerCase().contains(filtro))
                    || (p.getCategoria() != null && p.getCategoria().toLowerCase().contains(filtro))
                    || (p.getDescripcion() != null && p.getDescripcion().toLowerCase().contains(filtro))) {
                filtrados.add(p);
            }
        }
        renderizarProductos(filtrados);
    }

    // Renderiza una lista de productos en el contenedor
    private void renderizarProductos(List<Productos> productos) {
        if (contenedorProductos == null) {
            return;
        }
        contenedorProductos.getChildren().clear();

        for (Productos p : productos) {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/mycompany/ecomercefinalproyect/ItemCarrito.fxml")
                );
                Parent fila = loader.load();

                ItemCarritoController ctrl = loader.getController();
                String precioFormateado = String.format("$%,.2f", p.getPrecio());

                ctrl.setDatos(
                        p.getNombre(),
                        p.getDescripcion(),
                        "Categoría: " + p.getCategoria(),
                        precioFormateado,
                        p.getImagenURL(),
                        () -> {
                            if (gestorProductos.eliminarProducto(p.getNombre())) {
                                cargarCatalogoAdmin();
                            }
                        }
                );

                contenedorProductos.getChildren().add(fila);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void subirImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen del producto");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Imágenes (JPG, PNG)", "*.jpg", "*.jpeg", "*.png")
        );

        File archivoSeleccionado = fileChooser.showOpenDialog(btnSubirImagen.getScene().getWindow());
        if (archivoSeleccionado == null) {
            return;
        }

        long maxBytes = 5L * 1024L * 1024L;
        if (archivoSeleccionado.length() > maxBytes) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Archivo muy pesado",
                    "La imagen supera el límite máximo de 5 MB."
            );
            return;
        }

        rutaImagenSeleccionada = archivoSeleccionado.toURI().toString();
        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Imagen cargada",
                "Imagen seleccionada: " + archivoSeleccionado.getName()
        );
    }

    @FXML
    void publicarProducto(ActionEvent event) {
        String nombre = txtNombre.getText() != null ? txtNombre.getText().trim() : "";
        String categoria = cbCategoria.getValue();
        String precioTexto = txtPrecio.getText() != null ? txtPrecio.getText().trim().replace(",", ".") : "";
        String descripcion = txtDescripcion.getText() != null ? txtDescripcion.getText().trim() : "";

        if (nombre.isEmpty() || categoria == null || categoria.isBlank()
                || precioTexto.isEmpty() || descripcion.isEmpty()
                || rutaImagenSeleccionada.isBlank()) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Campos incompletos",
                    "Completa todos los campos obligatorios (*) y selecciona una imagen."
            );
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioTexto);
            if (precio <= 0) {
                mostrarAlerta(
                        Alert.AlertType.WARNING,
                        "Precio inválido",
                        "El precio debe ser mayor que 0."
                );
                return;
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Precio inválido",
                    "Ingresa un valor numérico válido para el precio (ej. 1299.00)."
            );
            return;
        }

        Productos nuevo = new Productos(
                nombre,
                categoria,
                10,
                precio,
                descripcion,
                rutaImagenSeleccionada
        );

        if (gestorProductos.agregarProducto(nuevo)) {
            limpiarCampos();
            cargarCatalogoAdmin();
            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Producto publicado",
                    "El producto se guardó en productos.json."
            );
        } else {
            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Duplicado",
                    "Ya existe un producto con ese nombre."
            );
        }
    }

    private void limpiarCampos() {
        if (txtNombre != null) {
            txtNombre.clear();
        }
        if (cbCategoria != null) {
            cbCategoria.getSelectionModel().clearSelection();
            cbCategoria.setValue(null);
        }
        if (txtPrecio != null) {
            txtPrecio.clear();
        }
        if (txtDescripcion != null) {
            txtDescripcion.clear();
        }
        rutaImagenSeleccionada = "";
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
