package com.mycompany.ecomercefinalproyect;

import Modelo.Productos;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ListaDeseadosController {

    @FXML
    private VBox contenedorDeseados;

    @FXML
    private Hyperlink linkSeguirComprando;

    @FXML
    public void initialize() {
        if (linkSeguirComprando != null) {
            linkSeguirComprando.setOnAction(e -> {
                // no-op: la navegación se gestiona desde el menú principal
            });
        }
        cargarDeseados();
    }

    private void cargarDeseados() {
        if (contenedorDeseados == null) {
            return;
        }
        contenedorDeseados.getChildren().clear();

        List<Productos> lista = GestorListaDeseados.obtenerProductos();
        if (lista.isEmpty()) {
            Label vacio = new Label("Aún no tienes productos en tu lista de deseados.");
            vacio.setStyle("-fx-text-fill: #6b7280; -fx-font-size: 14px;");
            contenedorDeseados.getChildren().add(vacio);
            return;
        }

        for (Productos p : lista) {
            try {
                javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                        getClass().getResource("/com/mycompany/ecomercefinalproyect/MoldeProductosListaDeseados.fxml")
                );
                javafx.scene.Parent fila = loader.load();
                MoldeProductosListaDeseadosController ctrl = loader.getController();
                ctrl.setDatos(
                        p.getNombre(),
                        p.getDescripcion(),
                        String.format("$%,.2f", p.getPrecio())
                );
                contenedorDeseados.getChildren().add(fila);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
    }
}
