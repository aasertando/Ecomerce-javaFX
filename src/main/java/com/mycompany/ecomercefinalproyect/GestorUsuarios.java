/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecomercefinalproyect;

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

/**
 *
 * @author aser
 */
public class GestorUsuarios {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Type tipoListaUsuario = new TypeToken<List<Usuario>>() {
    }.getType();
    private final File archivo = new File("usuarios.json");

    // 1. Leer la lista de usuarios desde usuarios.json
    public List<Usuario> obtenerTodos() {
        if (!archivo.exists()) {
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(archivo)) {
            List<Usuario> usuarios = gson.fromJson(reader, tipoListaUsuario);
            return usuarios != null ? usuarios : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // 2. Guardar la lista completa en usuarios.json
    private void guardarTodos(List<Usuario> usuarios) {
        try (FileWriter writer = new FileWriter(archivo)) {
            gson.toJson(usuarios, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 3. Registrar un nuevo usuario (evita nombres repetidos)
    public boolean registrarUsuario(Usuario nuevoUsuario) {
        List<Usuario> usuarios = obtenerTodos();

        for (Usuario u : usuarios) {
            if (u.getNombre().equalsIgnoreCase(nuevoUsuario.getNombre())) {
                return false;
            }
        }

        usuarios.add(nuevoUsuario);
        guardarTodos(usuarios);
        return true;
    }

    // 4. Validar login por usuario y contraseña
    public Usuario validarLogin(String usuarioIngresado, String contrasenaIngresada) {
        List<Usuario> usuarios = obtenerTodos();

        for (Usuario u : usuarios) {
            if (u.getNombre().equals(usuarioIngresado)
                    && u.getContraseña().equals(contrasenaIngresada)) {
                return u;
            }
        }
        return null;
    }
}
