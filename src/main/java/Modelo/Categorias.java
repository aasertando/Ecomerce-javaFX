package Modelo;

/**
 * Enum con todas las categorías disponibles en la tienda. Usar getNombre() para
 * obtener el String exacto que se guarda en JSON.
 */
public enum Categorias {

    ELECTRONICA("Electronica"),
    ROPA("Ropa"),
    HOGAR("Hogar"),
    DEPORTES("Deportes"),
    JUGUETES("Juguetes"),
    BELLEZA("Belleza"),
    ALIMENTOS("Alimentos");

    private final String nombre;

    Categorias(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna el nombre tal como se guarda en productos.json
     */
    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
