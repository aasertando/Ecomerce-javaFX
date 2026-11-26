package com.mycompany.ecomercefinalproyect;

import Modelo.Pedido;
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
import java.util.List;

public class GestorHistorialCompras {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final Type tipoLista = new TypeToken<List<Pedido>>() {}.getType();
    private static final File archivo = new File("historialCompras.json");

    public static List<Pedido> obtenerTodos() {
        if (!archivo.exists() || archivo.length() == 0) {
            return new ArrayList<>();
        }
        try (FileReader reader = new FileReader(archivo)) {
            List<Pedido> lista = gson.fromJson(reader, tipoLista);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException | com.google.gson.JsonSyntaxException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static void guardarTodos(List<Pedido> lista) {
        try (FileWriter writer = new FileWriter(archivo)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void agregar(Pedido pedido) {
        if (pedido == null) {
            return;
        }
        List<Pedido> lista = obtenerTodos();
        lista.add(pedido);
        guardarTodos(lista);
    }

    public static List<Pedido> obtenerPorUsuario() {
        String clave = claveUsuario();
        List<Pedido> resultado = new ArrayList<>();
        for (Pedido p : obtenerTodos()) {
            if (p.getUsuario() == null || p.getUsuario().equalsIgnoreCase(clave)) {
                resultado.add(p);
            }
        }
        return resultado;
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
}
