package com.mycompany.ecomercefinalproyect;

import Modelo.Carrito;
import Modelo.Productos;
import Modelo.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class PrincipalDuplicaController implements Initializable {

    @FXML
    private Button btnHeaderUsuario;

    @FXML
    private TextField inputHeaderBuscarProducto;

    @FXML
    private AnchorPane principal;

    private Popup menuPopup;
    private Usuario usuarioActual;
    
    //-----------------------------------------------------------------------------------------------------------------------
    //Cargar productos en la pantalla principal y productos
    @FXML
    private TilePane contenedorProductos;

    private final GestorProductos gestorProductos = new GestorProductos(); // Gestor de productos JSON

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarProductos(gestorProductos.obtenerTodos());
    }

    public void mostrarProductos(List<Productos> lista) {
        if (contenedorProductos == null) {
            return;
        }
        contenedorProductos.getChildren().clear();

        for (Productos p : lista) {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/mycompany/ecomercefinalproyect/TarjetaProducto.fxml")
                );
                Parent tarjeta = loader.load();

                TarjetaProductoController ctrl = loader.getController();
                String precioFormateado = String.format("$%,.2f", p.getPrecio());

                ctrl.setDatos(
                        p.getNombre(),
                        p.getDescripcion(),
                        precioFormateado,
                        p.getImagenURL(),
                        () -> {
                            Carrito.getInstancia().agregarProducto(p);
                            mostrarAlerta(
                                    Alert.AlertType.INFORMATION,
                                    "Carrito de compras",
                                    "¡" + p.getNombre() + " se agrego a tu carrito!"
                            );
                        },
                        () -> System.out.println("Favorito: " + p.getNombre())
                );

                contenedorProductos.getChildren().add(tarjeta);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    //-----------------------------------------------------------------------------------------------------------------------

    public void setUsuario(Usuario u) {
        this.usuarioActual = u;
    }

    @FXML
    void buscarProducto(ActionEvent event) {
        String texto = (inputHeaderBuscarProducto != null)
                ? inputHeaderBuscarProducto.getText().trim().toLowerCase()
                : "";

        if (texto.isEmpty()) {
            mostrarProductos(gestorProductos.obtenerTodos());
            return;
        }

        List<Productos> filtrados = new ArrayList<>();
        for (Productos p : gestorProductos.obtenerTodos()) {
            if ((p.getNombre() != null && p.getNombre().toLowerCase().contains(texto))
                    || (p.getDescripcion() != null && p.getDescripcion().toLowerCase().contains(texto))
                    || (p.getCategoria() != null && p.getCategoria().toLowerCase().contains(texto))) {
                filtrados.add(p);
            }
        }
        mostrarProductos(filtrados);
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
        try {
            AnchorPane nuevo = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/mycompany/ecomercefinalproyect/CategoriaAlimentos.fxml"));
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
    void mostrarCategoriaBelleza(ActionEvent event) {
        try {
            AnchorPane nuevo = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/mycompany/ecomercefinalproyect/CategoriaBelleza.fxml"));
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
    void mostrarCategoriaDeportes(ActionEvent event) {
        try {
            AnchorPane nuevo = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/mycompany/ecomercefinalproyect/CategoriaDeportes.fxml"));
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
        try {
            AnchorPane nuevo = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/mycompany/ecomercefinalproyect/CategoriaHogar.fxml"));
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
    void mostrarCategoriaJuguetes(ActionEvent event) {
        try {
            AnchorPane nuevo = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/mycompany/ecomercefinalproyect/CategoriaJuguetes.fxml"));
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

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
