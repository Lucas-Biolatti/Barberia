/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Entidades.Barbero;
import Entidades.DetalleServicio;
import Entidades.Producto;
import Entidades.Servicio;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.util.UUID;
import javax.swing.JOptionPane;

/**
 *
 * @author lbiolatti
 */
public class ServicioData {
    private Connection con;
    
    public ServicioData(Conexion c){
        con = c.getConection();
    }
    
    public int ultimoID(){
    int ultValor=0;
        String ultimo= "SELECT MAX(idApp) FROM Servicio";
            try{
            PreparedStatement ps0 = con.prepareStatement(ultimo,Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps0.executeQuery();
            if(rs.next()){
                ultValor = rs.getInt("MAX(idApp)")+1;
            }
            ps0.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Fallo conexion ultNum");
            }
    return ultValor;
    }
    
    public void incluirServicio(Servicio s){
        
        String sql = "INSERT INTO `Servicio`(`idServicio`, `idApp`, `Fecha`, `Cliente`, `Estado`,idBarbero) VALUES (?,?,?,?,?,?)";
        
            try{
                PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1,s.getIdServicio());
                    ps.setInt(2, s.getIdApp());
                    ps.setDate(3, Date.valueOf(s.getFecha()));
                    ps.setString(4, s.getCliente());
                    ps.setBoolean(5, s.isEstado());
                    ps.setInt(6, s.getBarbero().getIdBarbero());
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Se agrego pedido con exito");
                    
                    ps.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "No se pudo agregar Servicio");
            }
    }
    
    public void actualizarServicio(Servicio s){
    String sql = "UPDATE `Servicio` SET`idBarbero`=?,`Fecha`=?,`Cliente`=?,`Estado`=? WHERE idApp=?";
    try{
                PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1,s.getBarbero().getIdBarbero());
                    ps.setDate(2, Date.valueOf(s.getFecha()));
                    ps.setString(3, s.getCliente());
                    ps.setBoolean(4, s.isEstado());
                    ps.setInt(5, s.getIdApp());
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Se actualizo Servicio Correctamente");
                    ps.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "No se pudo Actualizar Servicio");
            }
    
    }
    
    public Servicio buscarServicio(int idApp){
        BarberoData bd = new BarberoData(new Conexion());
        Barbero b;
        Servicio s=null;
        String sql="SELECT * FROM `Servicio` WHERE `idApp`=?";
        try{
        
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idApp);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                s=new Servicio();
                b = bd.buscarBarbero(rs.getInt("idBarbero"));
                s.setBarbero(b);
                s.setCliente(rs.getString("Cliente"));
                s.setEstado(rs.getBoolean("Estado"));
                s.setFecha(rs.getDate("Fecha").toLocalDate());
                s.setIdApp(rs.getInt("idApp"));
                s.setIdServicio(rs.getString("idServicio"));
                s.setImporte(rs.getDouble("Importe"));
               
            }
            ps.close();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo ejecutar la busqueda");
        }
        return s;
    }
   
    public Servicio buscarServicioID(String id){
        BarberoData bd = new BarberoData(new Conexion());
        
        Servicio s= null;
        String sql="SELECT * FROM `Servicio` WHERE `idServicio`=?";
        try{
        
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                s = new Servicio();
                Barbero b = bd.buscarBarbero(rs.getInt("idBarbero"));
                s.setBarbero(b);
                s.setCliente(rs.getString("Cliente"));
                s.setEstado(rs.getBoolean("Estado"));
                s.setFecha(rs.getDate("Fecha").toLocalDate());
                s.setIdApp(rs.getInt("idApp"));
                s.setIdServicio(rs.getString("idServicio"));
                s.setImporte(rs.getDouble("Importe"));
               
            }
            ps.close();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo ejecutar la busqueda");
        }
        return s;
    }
    
    public List<DetalleServicio> obtenerDetallePorServicio(String id){
        DetalleServicio ds=null;
        ProductoData pd = new ProductoData(new Conexion());
        
        List<DetalleServicio> lista = new ArrayList<>();
                
        String sql="SELECT * FROM DetalleServicio WHERE idServicio=?";
        
        try{
        
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ds = new DetalleServicio();
                ds.setCantidad(rs.getInt("Cantidad"));
                ds.setIdApp(rs.getString("idApp"));
                ds.setIdDetalle(rs.getInt("idDetalle"));
                Producto p = pd.buscarProducto(rs.getInt("idProducto"));
                ds.setProducto(p);
                Servicio s=this.buscarServicioID(id);
                ds.setServicio(s);
                lista.add(ds);
            
            }
            
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta de detalles");
        }
        return lista;
    }
    
    public List<Servicio> obtenerServiciosPendientes(){
    List<Servicio> lista = new ArrayList<>();
    Servicio s;
    String sql="SELECT * FROM `Servicio` WHERE `Estado`=0 ORDER BY idApp DESC ";
    try{
        PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = ps.executeQuery();
        BarberoData bd=new BarberoData(new Conexion());
        while(rs.next()){
            s=new Servicio();
            Barbero b = bd.buscarBarbero(rs.getInt("idBarbero"));
                s.setBarbero(b);
                s.setCliente(rs.getString("Cliente"));
                s.setEstado(rs.getBoolean("Estado"));
                s.setFecha(rs.getDate("Fecha").toLocalDate());
                s.setIdApp(rs.getInt("idApp"));
                s.setIdServicio(rs.getString("idServicio"));
                s.setImporte(rs.getDouble("Importe"));
                lista.add(s);
        }
        ps.close();
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, "No se pudo obtener los servicios pendientes");
    }
    return lista;
    }
    public double importe(String id){
    double importe=0;
        String sql = "SELECT SUM(Productos.PrecioUnit*DetalleServicio.Cantidad) AS total from Productos,DetalleServicio,Servicio WHERE Productos.CodigoProducto=DetalleServicio.idProducto and DetalleServicio.idServicio=Servicio.idServicio AND Servicio.idServicio=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
           if(rs.next()){
            
            importe = rs.getDouble("total");
            }
            
            ps.close();
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "No se pudo obtener el importe numeriico");
        }
        return importe;
    }
    
    public void caulcularImporte(String id){
        double importe=0;
        String sql = "SELECT SUM(Productos.PrecioUnit*DetalleServicio.Cantidad) AS total from Productos,DetalleServicio,Servicio WHERE Productos.CodigoProducto=DetalleServicio.idProducto and DetalleServicio.idServicio=Servicio.idServicio AND Servicio.idServicio=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
            
            importe = rs.getDouble("total");
            }
            ps.close();
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "No se pudo obtener el importe numeriico");
        }
        
        String sql2="UPDATE `Servicio` SET `Importe`=? WHERE `idServicio`=?";
        try{
        
            PreparedStatement ps = con.prepareStatement(sql2,Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, importe);
            ps.setString(2, id);
            ps.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo actualizar Valor de importe");
        }
    }
    
    /*public List<DetalleServicio> obtenerDetallesPorServicio(String id){
        List<DetalleServicio> lista = new ArrayList<>();
        DetalleServicio d;
        ProductoData pd = new ProductoData(new Conexion());
        ServicioData sd = new ServicioData(new Conexion());
        String sql = "SELECT * FROM `DetalleServicio` WHERE `idServicio`=?";                
        
        try{
        PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            d = new DetalleServicio();
            d.setCantidad(rs.getInt("Cantidad"));
            d.setIdApp(rs.getString("idApp"));
            d.setIdDetalle(rs.getInt("idDetalle"));
            
            Producto p= pd.buscarProducto(rs.getInt("idProducto"));
            d.setProducto(p);
            Servicio s = sd.buscarServicioID(id);
            
            d.setServicio(s);
            lista.add(d);
        }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo obtener lista de detalle");
        }
    
    return lista;
    }*/
    
}
