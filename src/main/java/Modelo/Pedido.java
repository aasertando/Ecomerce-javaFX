/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author aser
 */
public class Pedido {

    private int id;
    private Usuario u;
    private LocalDate fecha;
    private ArrayList<Producto> productos;
    private int total;
    private String informacion;
    private String metodoPago;

    public Pedido(int id, Usuario u, LocalDate fecha, ArrayList<Producto> productos, int total, String informacion, String metodoPago) {
        this.id = id;
        this.u = u;
        this.fecha = fecha;
        this.productos = productos;
        this.total = total;
        this.informacion = informacion;
        this.metodoPago = metodoPago;
    }

    public String infoUsuario() {

        return "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getU() {
        return u;
    }

    public void setU(Usuario u) {
        this.u = u;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
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

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

}
