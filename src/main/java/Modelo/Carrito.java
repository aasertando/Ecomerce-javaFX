package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Carrito {

    private static final Carrito instancia = new Carrito();
    private final List<ItemCarritoCompra> items = new ArrayList<>();

    private Carrito() {
    }

    public static Carrito getInstancia() {
        return instancia;
    }

    public void agregarProducto(Productos producto) {
        if (producto == null) {
            return;
        }
        for (ItemCarritoCompra item : items) {
            if (item.getProducto() != null
                    && item.getProducto().getNombre().equalsIgnoreCase(producto.getNombre())) {
                item.incrementar();
                return;
            }
        }
        items.add(new ItemCarritoCompra(producto, 1));
    }

    public void eliminarProducto(Productos producto) {
        if (producto == null) {
            return;
        }
        items.removeIf(item -> item.getProducto() != null
                && item.getProducto().getNombre().equalsIgnoreCase(producto.getNombre()));
    }

    public List<ItemCarritoCompra> getItems() {
        return items;
    }

    public double calcularTotal() {
        double total = 0.0;
        for (ItemCarritoCompra item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public int getCantidadTotal() {
        int total = 0;
        for (ItemCarritoCompra item : items) {
            total += item.getCantidad();
        }
        return total;
    }

    public void vaciar() {
        items.clear();
    }
}
