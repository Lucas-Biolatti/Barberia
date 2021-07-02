/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Entidades.DetalleServicio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JOptionPane;

/**
 *
 * @author lbiolatti
 */
public class DetalleServicioData {
    private Connection con;
    
    public DetalleServicioData(Conexion c){
        con=c.getConection();
    }
    
    public void agregarDetalle(DetalleServicio ds){
    
    String sql = "INSERT INTO `DetalleServicio`(`idProducto`, `idServicio`, `Cantidad`, `idApp`) VALUES (?,?,?,?)";
    try{
        PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ds.getProducto().getIdProducto());
            ps.setString(2, ds.getServicio().getIdServicio());
            ps.setInt(3, ds.getCantidad());
            ps.setString(4, UUID.randomUUID().toString().substring(0, 8));
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                ds.setCantidad(rs.getInt("idDetalle"));
            }
           
            ps.close();
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null,"No se pudo agregar detalle");
    }
    }
    
    public void eliminarDetalle(int id){
    
        String sql = "DELETE FROM `DetalleServicio` WHERE ?";
        
        try{
        
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
            
            ps.close();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el detalle");
        }
    }
    
    public void modificarCantidad(int id, int cant){
        
        String sql = "UPDATE `DetalleServicio` SET `Cantidad`=? WHERE idDetalle=?";
        
        try{
        
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cant);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo modificar la cantidad");
        }
    }
    
}
