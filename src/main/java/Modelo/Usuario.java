/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author garci
 */
public class Usuario {

    private int id;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
    private String ciudad;
    private String contraseña;
    private Carrito carrito;
    private ListaDeseos listaDeseos;

    public Usuario(int id, String nombre, String email, String telefono, String direccion, String ciudad, String contraseña, Carrito carrito, ListaDeseos listaDeseos) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.contraseña = contraseña;
        this.carrito = carrito;
        this.listaDeseos = listaDeseos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public ListaDeseos getListaDeseos() {
        return listaDeseos;
    }

    public void setListaDeseos(ListaDeseos listaDeseos) {
        this.listaDeseos = listaDeseos;
    }

}
