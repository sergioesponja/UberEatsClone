package com.example.mainactivity.modelos;

public class Comida {
    private int id, idRestaurante, precio;
    private String nombre, ingredientes, n_restaurante;

    public Comida() {
    }

    public Comida(int id, int idRestaurante, int precio, String nombre, String ingredientes) {
        this.id = id;
        this.idRestaurante = idRestaurante;
        this.precio = precio;
        this.nombre = nombre;
        this.ingredientes = ingredientes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(int idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getN_restaurante() {
        return n_restaurante;
    }

    public void setN_restaurante(String n_restaurante) {
        this.n_restaurante = n_restaurante;
    }
}
