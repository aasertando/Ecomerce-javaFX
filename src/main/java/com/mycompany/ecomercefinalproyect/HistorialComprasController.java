package com.mycompany.ecomercefinalproyect;

import Modelo.Pedido;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class HistorialComprasController {

    @FXML
    private VBox contenedorPedidos;

    @FXML
    public void initialize() {
        cargarHistorial();
    }

    private void cargarHistorial() {
        if (contenedorPedidos == null) {
            return;
        }
        contenedorPedidos.getChildren().clear();

        List<Pedido> pedidos = GestorHistorialCompras.obtenerPorUsuario();
        if (pedidos.isEmpty()) {
            return;
        }

        int numero = pedidos.size();
        for (int i = pedidos.size() - 1; i >= 0; i--) {
            Pedido pedido = pedidos.get(i);

            StringBuilder productos = new StringBuilder();
            if (pedido.getItems() != null) {
                for (Modelo.ItemCarritoCompra item : pedido.getItems()) {
                    if (productos.length() > 0) {
                        productos.append(", ");
                    }
                    productos.append(item.getProducto().getNombre())
                            .append(" x")
                            .append(item.getCantidad());
                }
            }

            try {
                javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                        getClass().getResource("/com/mycompany/ecomercefinalproyect/MoldeHistorialCompra.fxml")
                );
                javafx.scene.Parent fila = loader.load();
                MoldeHistorialCompraController ctrl = loader.getController();
                ctrl.setDatos(
                        "#" + numero,
                        productos.toString(),
                        String.format("$%,.2f", pedido.getTotal())
                );
                contenedorPedidos.getChildren().add(fila);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }

            numero--;
        }
    }
}
