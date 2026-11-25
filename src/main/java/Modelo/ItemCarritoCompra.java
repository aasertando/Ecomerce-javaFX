package Modelo;

public class ItemCarritoCompra {
    private Productos producto;
    private int cantidad;

    public ItemCarritoCompra(Productos producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void incrementar() {
        this.cantidad++;
    }

    public double getSubtotal() {
        if (producto != null) {
            return producto.getPrecio() * cantidad;
        }
        return 0.0;
    }
}
