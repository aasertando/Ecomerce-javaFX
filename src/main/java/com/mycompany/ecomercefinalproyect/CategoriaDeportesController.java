package com.mycompany.ecomercefinalproyect;

import Modelo.Carrito;
import Modelo.Productos;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.TilePane;

public class CategoriaDeportesController implements Initializable {

    @FXML
    private TilePane contenedorProductos;

    private final GestorProductos gestorProductos = new GestorProductos();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarProductosPorCategoria("Deportes");
    }

    private void cargarProductosPorCategoria(String categoria) {
        if (contenedorProductos == null) {
            return;
        }
        contenedorProductos.getChildren().clear();

        List<Productos> productos = gestorProductos.obtenerTodos();
        for (Productos p : productos) {
            if (p.getCategoria() != null && p.getCategoria().equalsIgnoreCase(categoria)) {
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
                                mostrarAlerta(Alert.AlertType.INFORMATION, "Carrito de compras", "¡" + p.getNombre() + " se agrego a tu carrito!");
                            },
                            () -> System.out.println("Favorito: " + p.getNombre())
                    );

                    contenedorProductos.getChildren().add(tarjeta);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
