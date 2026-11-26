package com.mycompany.ecomercefinalproyect;

import Modelo.Carrito;
import Modelo.ItemCarritoCompra;
import Modelo.Pedido;
import Modelo.Sesion;
import Modelo.Usuario;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class CarritoController implements Initializable {

    @FXML
    private VBox contenedorProductos;

    @FXML
    private Label lblSubtotal;

    @FXML
    private Label lblTotalPagar;

    @FXML
    private Button btnConfirmarPedido;

    @FXML
    private RadioButton rbEfectivo;

    @FXML
    private RadioButton rbNequi;

    @FXML
    private ToggleGroup grupoPago;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refrescarListaProductos();
    }

    public void refrescarListaProductos() {
        if (contenedorProductos == null) {
            return;
        }
        contenedorProductos.getChildren().clear();

        List<ItemCarritoCompra> items = Carrito.getInstancia().getItems();

        if (items.isEmpty()) {
            if (lblSubtotal != null) {
                lblSubtotal.setText("$0.00");
            }
            if (lblTotalPagar != null) {
                lblTotalPagar.setText("$0.00");
            }
            return;
        }

        for (ItemCarritoCompra item : items) {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/mycompany/ecomercefinalproyect/ItemCarrito.fxml")
                );
                Parent fila = loader.load();

                ItemCarritoController itemCtrl = loader.getController();
                String precioUnitario = String.format("$%,.2f c/u", item.getProducto().getPrecio());
                String subtotal = String.format("$%,.2f", item.getSubtotal());

                itemCtrl.setDatos(
                        item.getProducto().getNombre(),
                        item.getProducto().getDescripcion(),
                        precioUnitario,
                        subtotal,
                        item.getProducto().getImagenURL(),
                        () -> {
                            Carrito.getInstancia().eliminarProducto(item.getProducto());
                            refrescarListaProductos();
                        }
                );

                contenedorProductos.getChildren().add(fila);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        double total = Carrito.getInstancia().calcularTotal();
        String totalFormateado = String.format("$%,.2f", total);
        if (lblSubtotal != null) {
            lblSubtotal.setText(totalFormateado);
        }
        if (lblTotalPagar != null) {
            lblTotalPagar.setText(totalFormateado);
        }
    }

    @FXML
    void confirmarPedido(ActionEvent event) {
        if (Carrito.getInstancia().getItems().isEmpty()) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Carrito vacio",
                    "No tienes productos en el carrito para confirmar."
            );
            return;
        }

        String metodoPago = "Efectivo contra entrega";
        if (rbNequi != null && rbNequi.isSelected()) {
            metodoPago = "Nequi";
        }

        double total = Carrito.getInstancia().calcularTotal();
        String totalStr = String.format("$%,.2f", total);

        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Pedido Confirmado",
                "¡Gracias por tu compra!\nMetodo de pago: " + metodoPago + "\nTotal pagado: " + totalStr
        );

        // Registrar en historial de compras
        Usuario usuarioActual = Sesion.getUsuarioActual();
        String usuario = (usuarioActual != null)
                ? (usuarioActual.getEmail() != null && !usuarioActual.getEmail().isBlank()
                        ? usuarioActual.getEmail() : usuarioActual.getNombre())
                : "invitado";
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        List<ItemCarritoCompra> itemsCopia = new ArrayList<>(Carrito.getInstancia().getItems());
        Pedido pedido = new Pedido(usuario, fecha, metodoPago, total, itemsCopia);
        GestorHistorialCompras.agregar(pedido);

        Carrito.getInstancia().vaciar();
        refrescarListaProductos();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
