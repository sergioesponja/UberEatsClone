package com.example.mainactivity.modelos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Pedido {
    private String id_cliente, id_comida, id_repartidor, costo_total;
    private String nombre_platillo;
    private String id;
    private boolean estatus_pago,estatus_pedido;
    private String fecha;

    public Pedido() {
    }

    public Pedido(String id_cliente, String id_comida, String id_repartidor, String costo_total, String nombre_platillo, String id, boolean estatus_pago, boolean estatus_pedido, String fecha) {
        this.id_cliente = id_cliente;
        this.id_comida = id_comida;
        this.id_repartidor = id_repartidor;
        this.costo_total = costo_total;
        this.nombre_platillo = nombre_platillo;
        this.id = id;
        this.estatus_pago = estatus_pago;
        this.estatus_pedido = estatus_pedido;
        this.fecha = fecha;
    }


    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getId_comida() {
        return id_comida;
    }

    public void setId_comida(String id_comida) {
        this.id_comida = id_comida;
    }

    public String getId_repartidor() {
        return id_repartidor;
    }

    public void setId_repartidor(String id_repartidor) {
        this.id_repartidor = id_repartidor;
    }

    public String getCosto_total() {
        return costo_total;
    }

    public void setCosto_total(String costo_total) {
        this.costo_total = costo_total;
    }

    public String getNombre_platillo() {
        return nombre_platillo;
    }

    public void setNombre_platillo(String nombre_platillo) {
        this.nombre_platillo = nombre_platillo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isEstatus_pago() {
        return estatus_pago;
    }

    public void setEstatus_pago(boolean estatus_pago) {
        this.estatus_pago = estatus_pago;
    }

    public boolean isEstatus_pedido() {
        return estatus_pedido;
    }

    public void setEstatus_pedido(boolean estatus_pedido) {
        this.estatus_pedido = estatus_pedido;
    }
}
