/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecomercefinalproyect;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author garci
 */
public class CarritoController implements Initializable {

    @FXML
    private Button btnConfirmarPedido;

    @FXML
    private VBox contenedorProductos;

    @FXML
    private Label lblSubtotal;

    @FXML
    private Label lblTotalPagar;

    @FXML
    private AnchorPane principal;

    // Lista temporal de prueba
    private final List<String[]> itemsPrueba = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("CarritoController inicializado. contenedorProductos = " + contenedorProductos);
        itemsPrueba.add(new String[]{"Smartphone X12", "128GB / 5G", "$799.00 c/u", "$799.00"});
        itemsPrueba.add(new String[]{"Audífonos BT", "Noise Cancelling", "$249.00 c/u", "$498.00"});
        refrescarListaProductos();
    }

    @FXML
    void mostrarCarrito(ActionEvent event) {
        refrescarListaProductos();
    }

    public void refrescarListaProductos() {
        contenedorProductos.getChildren().clear();

        for (String[] item : itemsPrueba) {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/mycompany/ecomercefinalproyect/ItemCarrito.fxml")
                );
                Parent fila = loader.load();

                ItemCarritoController itemCtrl = loader.getController();
                itemCtrl.setDatos(
                        item[0],
                        item[1],
                        item[2],
                        item[3],
                        () -> {
                            itemsPrueba.remove(item);
                            refrescarListaProductos();
                        }
                );

                contenedorProductos.getChildren().add(fila);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
