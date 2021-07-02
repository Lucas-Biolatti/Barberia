/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author lbiolatti
 */
public class DetalleServicio {
    private int idDetalle;
    private Producto producto;
    private Servicio servicio;
    private int cantidad;
    private String idApp;

    public DetalleServicio() {
    }

    public DetalleServicio(Producto producto, Servicio servicio, int cantidad, String idApp) {
        this.producto = producto;
        this.servicio = servicio;
        this.cantidad = cantidad;
        this.idApp = idApp;
    }
    
    

    public DetalleServicio(Producto producto, Servicio servicio, int cantidad) {
        this.producto = producto;
        this.servicio = servicio;
        this.cantidad = cantidad;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getIdApp() {
        return idApp;
    }

    public void setIdApp(String idApp) {
        this.idApp = idApp;
    }

    @Override
    public String toString() {
        return idDetalle + "-" + producto + "-" + servicio + "-" + cantidad + "-" + idApp;
    }
    
    
}
