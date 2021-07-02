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
public class Producto {
    private int idProducto;
    private String categoria;
    private String descripcion;
    private double precioUnitario;
    private int stockMin;
    private boolean estado;
    private int stock;

    public Producto() {
    }

    public Producto(int idProducto, String categoria, String descripcion, double precioUnitario, int stockMin, boolean estado) {
        this.idProducto = idProducto;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.stockMin = stockMin;
        this.estado = estado;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    
    
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getStockMin() {
        return stockMin;
    }

    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
    

   
    
}
