package com.example.mainactivity.modelos;

public class Restaurante {
    private int id;
    private String nombre;
    private String latitud, longitud;

    public Restaurante() {
    }

    public Restaurante(int id, String nombre, String latitud, String longitud) {
        this.id = id;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public int getId() {
        return id;
    }
}
