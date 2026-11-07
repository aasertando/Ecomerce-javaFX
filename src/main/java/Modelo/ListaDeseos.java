/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author aser
 */
public class ListaDeseos {

    private ArrayList<Producto> productos;

    public boolean agregarProducto(Producto p) {

        return false;
    }

    public boolean eliminarProducto(int idProducto) {

        return false;
    }

    public ListaDeseos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

}
