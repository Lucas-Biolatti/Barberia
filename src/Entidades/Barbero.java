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
public class Barbero {
    private int idBarbero;
    private String nombre;
    private String apellido;
    private LocalDate fIngreso;
    private boolean estado;
    private String foto;

    public Barbero(String nombre, String apellido, LocalDate fIngreso, boolean estado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fIngreso = fIngreso;
        this.estado = estado;
    }

    public Barbero(int idBarbero, String nombre, String apellido, LocalDate fIngreso, boolean estado) {
        this.idBarbero = idBarbero;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fIngreso = fIngreso;
        this.estado = estado;
    }

   
    

    public Barbero() {
    }

    public int getIdBarbero() {
        return idBarbero;
    }

    public void setIdBarbero(int idBarbero) {
        this.idBarbero = idBarbero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getfIngreso() {
        return fIngreso;
    }

    public void setfIngreso(LocalDate fIngreso) {
        this.fIngreso = fIngreso;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return nombre + ", "+ apellido;
    }
    
    
    
}
