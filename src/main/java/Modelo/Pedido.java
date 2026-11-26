package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private String usuario;
    private String fecha;
    private String metodoPago;
    private double total;
    private List<ItemCarritoCompra> items;

    public Pedido() {
        this.items = new ArrayList<>();
    }

    public Pedido(String usuario, String fecha, String metodoPago, double total, List<ItemCarritoCompra> items) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.total = total;
        this.items = items != null ? items : new ArrayList<>();
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<ItemCarritoCompra> getItems() {
        return items;
    }

    public void setItems(List<ItemCarritoCompra> items) {
        this.items = items != null ? items : new ArrayList<>();
    }
}
