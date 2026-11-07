/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author garci
 */
public class Carrito {

    private ArrayList<Producto> productos;
    private int total;

    public boolean agregarProducto(Producto p) {

        return false;
    }

    public boolean eliminarProducto(int idProducto) {

        return false;
    }

    public int calcularSubtotal() {

        return 0;
    }

    public Carrito(ArrayList<Producto> productos, int total) {
        this.productos = productos;
        this.total = total;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
