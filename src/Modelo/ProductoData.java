/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Entidades.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author lbiolatti
 */
public class ProductoData {
    Connection con;
    
    public ProductoData(Conexion c){
    con=c.getConection();
    }
    
    public void insertarProducto(Producto p){
    String sql="INSERT INTO `Productos`(`CodigoProducto`, `Categoria`, `Descripcion`, `PrecioUnit`, `StockMin`, `Estado`) VALUES (?,?,?,?,?,?);";
    
    try{
    PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
    ps.setInt(1, p.getIdProducto());
    ps.setString(2,p.getCategoria());
    ps.setString(3,p.getDescripcion());
    ps.setDouble(4,p.getPrecioUnitario());
    ps.setInt(5,p.getStockMin());
    ps.setBoolean(6, p.isEstado());
    ps.executeUpdate();
    ps.close();
        
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, "No se pudo acceder a la BD");
    }
    
    }
    
    public void actualizarProducto(Producto p){
        String sql="UPDATE `Productos` SET `Descripcion`=?,`Categoria`=?,`PrecioUnit`=?,`StockMin`=?,`Estado`=? WHERE CodigoProducto=?";
        try{
    PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
    ps.setInt(6, p.getIdProducto());
    ps.setString(2,p.getCategoria());
    ps.setString(1,p.getDescripcion());
    ps.setDouble(3,p.getPrecioUnitario());
    ps.setInt(4,p.getStockMin());
    ps.setBoolean(5, p.isEstado());
    ps.executeUpdate();
    ps.close();
        
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, "No se pudo acceder a la BD");
    }
    }
    
    public List<Producto> obtenerProductos(String categoria){
    List<Producto> lista = new ArrayList<>();
    Producto p=null;
    
    String sql="SELECT * FROM `Productos` WHERE `Estado`=1 and Categoria=?;";
    try{
        PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, categoria);
        ResultSet rs = ps.executeQuery();
            while(rs.next()){
                p=new Producto();
                p.setIdProducto(rs.getInt("CodigoProducto"));
                p.setCategoria(rs.getString("Categoria"));
                p.setDescripcion(rs.getString("Descripcion"));
                p.setPrecioUnitario(rs.getDouble("PrecioUnit"));
                p.setStockMin(rs.getInt("StockMin"));
                p.setEstado(rs.getBoolean("Estado"));
                this.calcularStock(p.getIdProducto());
                p.setStock(rs.getInt("Stock"));
                lista.add(p);
            }
            ps.close();
    
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, "No se pudo conectar con la BD Producto");
    
    }
    return lista;
    
    }
    
    public Producto buscarProducto(String desc){
    Producto p=null;
    //List<Producto> lista = new ArrayList<>();
    
    String sql="SELECT * FROM `Productos` WHERE `Descripcion`LIKE ? ";
    try{
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,desc);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                p=new Producto();
                p.setIdProducto(rs.getInt("CodigoProducto"));
                p.setCategoria(rs.getString("Categoria"));
                p.setDescripcion(rs.getString("Descripcion"));
                p.setPrecioUnitario(rs.getDouble("PrecioUnit"));
                p.setStockMin(rs.getInt("StockMin"));
                p.setEstado(rs.getBoolean("Estado"));
                this.calcularStock(p.getIdProducto());
                p.setStock(rs.getInt("Stock"));
                //lista.add(p);
                
            }
            ps.close();
    
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, "Fallo la conexion");
    }
    return p;
    }
    
    public Producto buscarProducto(int desc){
    Producto p=null;
    //List<Producto> lista = new ArrayList<>();
    
    String sql="SELECT * FROM `Productos` WHERE `CodigoProducto`= ? ";
    try{
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,desc);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                p=new Producto();
                p.setIdProducto(rs.getInt("CodigoProducto"));
                p.setCategoria(rs.getString("Categoria"));
                p.setDescripcion(rs.getString("Descripcion"));
                p.setPrecioUnitario(rs.getDouble("PrecioUnit"));
                p.setStockMin(rs.getInt("StockMin"));
                p.setEstado(rs.getBoolean("Estado"));
                this.calcularStock(p.getIdProducto());
                p.setStock(rs.getInt("Stock"));
                //lista.add(p);
                
            }
            ps.close();
    
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, "Fallo la conexion");
    }
    return p;
    }
    
    public void calcularStock(int idProducto){
    int entradas=0;
    int salidas=0;
    int Stock=0;
    String sql ="SELECT sum(Entrada.Cantidad) AS Entradas FROM Entrada,Productos WHERE Productos.CodigoProducto=? AND Productos.CodigoProducto=Entrada.idProducto";
    String sql1="SELECT sum(DetalleServicio.Cantidad) AS Salidas FROM DetalleServicio,Productos WHERE Productos.CodigoProducto=? AND Productos.CodigoProducto=DetalleServicio.idProducto";
    try{
        PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, idProducto);
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
        entradas=rs.getInt("Entradas");
        }
        ps.close();
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, "Fallo la Conexion Entrada");
    }
    try{
        PreparedStatement ps1= con.prepareStatement(sql1,Statement.RETURN_GENERATED_KEYS);
        ps1.setInt(1, idProducto);
        ResultSet rs1=ps1.executeQuery();
        if(rs1.next()){
            salidas=rs1.getInt("Salidas");
        }
        ps1.close();
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, "Fallo la conexion Salida");
    }
    Stock = entradas-salidas;
    
    String sql2="UPDATE `Productos` SET `Stock`=? WHERE CodigoProducto=?";
    try{
        PreparedStatement ps2 = con.prepareStatement(sql2,Statement.RETURN_GENERATED_KEYS);
        ps2.setInt(1, Stock);
        ps2.setInt(2,idProducto);
        ps2.executeUpdate();
        ps2.close();
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, "Fallo la Conexion Stock");
    }
    }
    
    public void darDeBaja(String desc){
        String sql="UPDATE `Productos` SET `Estado`=0 WHERE Descripcion=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, desc);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se dio de baja con Exito");
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo conectar la bd");
        }
    
    }
    
        public void darDeAlta(String desc){
        String sql="UPDATE `Productos` SET `Estado`=1 WHERE Descripcion=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, desc);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se dio de Alta con Exito");
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo conectar la bd");
        }
    
    }
        
        public void ActualizarPrecio(int id, double cant){
        String sql="UPDATE `Productos` SET `PrecioUnit`=? WHERE CodigoProducto=?";
        try{
        PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        ps.setDouble(1, cant);
        ps.setInt(2, id);
        ps.executeUpdate();
        ps.close();
        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Fallo la conexion con la BD");
        }
        
        }
        
        public List<Producto> obtenerProductosNoSeleccionados(String idServicio){
            List<Producto> lista = new ArrayList<>();
            Producto p = null;
            String sql = "SELECT * FROM `Productos` WHERE CodigoProducto NOT IN(SELECT idProducto FROM DetalleServicio WHERE DetalleServicio.idServicio=?)";
            try{
                
                PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, idServicio);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                p = new Producto();
                p.setCategoria(rs.getString("Categoria"));
                p.setDescripcion(rs.getString("Descripcion"));
                p.setEstado(rs.getBoolean("Estado"));
                p.setIdProducto(rs.getInt("CodigoProducto"));
                p.setPrecioUnitario(rs.getDouble("PrecioUnit"));
                p.setStockMin(rs.getInt("StockMin"));
                lista.add(p);
                }
                ps.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "No se pudo obtener los productos");
            }
            return lista;
        }
        
       public List<String> obtenerCategorias(){
            List<String> lista=new ArrayList<>();
            
            String sql="SELECT DISTINCT Categoria AS cat FROM `Productos` WHERE 1";
            try{
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
             lista.add(rs.getString("cat"));
              }
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null, "No se pudo obtener listado de categorias");
            }
            return lista;
       }
       public List<Producto> obtenerProductoPorCategoria(String cat){
       List<Producto> lista = new ArrayList<>();
            Producto p = null;
            String sql = "SELECT * FROM `Productos` WHERE `Categoria` = ?";
            try{
                
                PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, cat);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                p = new Producto();
                
                p.setCategoria(rs.getString("Categoria"));
                p.setDescripcion(rs.getString("Descripcion"));
                p.setEstado(rs.getBoolean("Estado"));
                p.setIdProducto(rs.getInt("CodigoProducto"));
                p.setPrecioUnitario(rs.getDouble("PrecioUnit"));
                p.setStockMin(rs.getInt("StockMin"));
                lista.add(p);
                }
                ps.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "No se pudo obtener los productos");
            }
            return lista;
        }
       
       


}
    

