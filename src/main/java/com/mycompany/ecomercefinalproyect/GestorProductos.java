/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecomercefinalproyect;

import Modelo.Productos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author garci
 */
public class GestorProductos {
    
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Type tipoListaProductos = new TypeToken<List<Productos>>() {}.getType();
    private final File archivo = new File("productos.json");

    // 1. Obtener todos los productos desde productos.json
    public List<Productos> obtenerTodos() {
        if (!archivo.exists() || archivo.length() == 0) {
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(archivo)) {
            List<Productos> productos = gson.fromJson(reader, tipoListaProductos);
            return productos != null ? productos : new ArrayList<>();
        } catch (IOException | JsonSyntaxException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // 2. Guardar la lista completa en productos.json
    public void guardarTodos(List<Productos> productos) {
        try (FileWriter writer = new FileWriter(archivo)) {
            gson.toJson(productos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 3. Agregar un nuevo producto y persistir en JSON
    public boolean agregarProducto(Productos nuevoProducto) {
        List<Productos> productos = obtenerTodos();

        for (Productos p : productos) {
            if (p.getNombre().equalsIgnoreCase(nuevoProducto.getNombre())) {
                return false; // Evita duplicados con el mismo nombre
            }
        }

        productos.add(nuevoProducto);
        guardarTodos(productos);
        return true;
    }

    // 4. Eliminar un producto por nombre y persistir en JSON
    public boolean eliminarProducto(String nombreProducto) {
        List<Productos> productos = obtenerTodos();
        boolean eliminado = productos.removeIf(p -> p.getNombre().equalsIgnoreCase(nombreProducto));

        if (eliminado) {
            guardarTodos(productos);
        }
        return eliminado;
    }
}
