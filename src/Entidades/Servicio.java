/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.time.LocalDate;

/**
 *
 * @author lbiolatti
 */
public class Servicio {
    private String idServicio;
    private int idApp;
    private Barbero barbero;
    private LocalDate fecha;
    private double importe;
    private String cliente;
    private boolean estado;

    public Servicio(Barbero barbero, LocalDate fecha, double importe, String cliente, boolean estado) {
        this.barbero = barbero;
        this.fecha = fecha;
        this.importe = importe;
        this.cliente = cliente;
        this.estado = estado;
    }

    public Servicio() {
    }

    public Servicio(Barbero barbero, LocalDate fecha, String cliente, boolean estado) {
        this.barbero = barbero;
        this.fecha = fecha;
        this.cliente = cliente;
        this.estado = estado;
    }
    
    public Servicio(LocalDate fecha, String cliente, boolean estado) {
        this.fecha = fecha;
        this.cliente = cliente;
        this.estado = estado;
    }

    public Servicio(String idServicio, int idApp, Barbero barbero, LocalDate fecha, String cliente, boolean estado) {
        this.idServicio = idServicio;
        this.idApp = idApp;
        this.barbero = barbero;
        this.fecha = fecha;
        this.cliente = cliente;
        this.estado = estado;
    }
    

    
    
    public Servicio(String idServicio, int idApp, LocalDate fecha, String cliente, boolean estado) {
        this.idServicio = idServicio;
        this.idApp = idApp;
        this.fecha = fecha;
        this.cliente = cliente;
        this.estado = estado;
    }
    
    

    public String getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }

    public int getIdApp() {
        return idApp;
    }

    public void setIdApp(int idApp) {
        this.idApp = idApp;
    }

    public Barbero getBarbero() {
        return barbero;
    }

    public void setBarbero(Barbero barbero) {
        this.barbero = barbero;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return cliente + " - "+ estado;
    }
    
    
    
}
