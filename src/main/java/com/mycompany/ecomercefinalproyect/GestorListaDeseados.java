package com.mycompany.ecomercefinalproyect;

import Modelo.Productos;
import Modelo.Sesion;
import Modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorListaDeseados {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final Type tipoMapa = new TypeToken<Map<String, List<String>>>() {}.getType();
    private static final File archivo = new File("listaDeseados.json");

    private static Map<String, List<String>> cargarMapa() {
        if (!archivo.exists() || archivo.length() == 0) {
            return new HashMap<>();
        }
        try (FileReader reader = new FileReader(archivo)) {
            Map<String, List<String>> mapa = gson.fromJson(reader, tipoMapa);
            return mapa != null ? mapa : new HashMap<>();
        } catch (IOException | com.google.gson.JsonSyntaxException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    private static void guardarMapa(Map<String, List<String>> mapa) {
        try (FileWriter writer = new FileWriter(archivo)) {
            gson.toJson(mapa, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String claveUsuario() {
        Usuario u = Sesion.getUsuarioActual();
        if (u == null) {
            return "invitado";
        }
        if (u.getEmail() != null && !u.getEmail().isBlank()) {
            return u.getEmail();
        }
        return u.getNombre() != null ? u.getNombre() : "invitado";
    }

    public static List<String> obtenerNombres() {
        Map<String, List<String>> mapa = cargarMapa();
        List<String> nombres = mapa.get(claveUsuario());
        return nombres != null ? new ArrayList<>(nombres) : new ArrayList<>();
    }

    public static boolean contiene(Productos producto) {
        if (producto == null) {
            return false;
        }
        return contieneNombre(producto.getNombre());
    }

    public static boolean contieneNombre(String nombre) {
        if (nombre == null) {
            return false;
        }
        for (String n : obtenerNombres()) {
            if (n.equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    public static void alternar(Productos producto) {
        if (producto == null) {
            return;
        }
        Map<String, List<String>> mapa = cargarMapa();
        String clave = claveUsuario();
        List<String> nombres = mapa.computeIfAbsent(clave, k -> new ArrayList<>());

        boolean existia = nombres.removeIf(n -> n.equalsIgnoreCase(producto.getNombre()));
        if (!existia) {
            nombres.add(producto.getNombre());
        }
        if (nombres.isEmpty()) {
            mapa.remove(clave);
        }
        guardarMapa(mapa);
    }

    public static void quitar(Productos producto) {
        if (producto == null) {
            return;
        }
        Map<String, List<String>> mapa = cargarMapa();
        String clave = claveUsuario();
        List<String> nombres = mapa.get(clave);
        if (nombres != null) {
            nombres.removeIf(n -> n.equalsIgnoreCase(producto.getNombre()));
            if (nombres.isEmpty()) {
                mapa.remove(clave);
            }
            guardarMapa(mapa);
        }
    }

    public static List<Productos> obtenerProductos() {
        List<String> nombres = obtenerNombres();
        List<Productos> todos = new GestorProductos().obtenerTodos();
        List<Productos> resultado = new ArrayList<>();
        for (String nombre : nombres) {
            for (Productos p : todos) {
                if (p.getNombre() != null && p.getNombre().equalsIgnoreCase(nombre)) {
                    resultado.add(p);
                    break;
                }
            }
        }
        return resultado;
    }
}
