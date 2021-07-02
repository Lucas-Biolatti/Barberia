
package Modelo;

import Entidades.Barbero;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


public class BarberoData {
    Connection con;
    
    public BarberoData(Conexion c){
        con=c.getConection();
    }
    
    public void ingresarBarbero(Barbero b){
    
        String sql="INSERT INTO `Barberos`(`Nombre`, `Apellido`, `FechaIngreso`, `Estado`) VALUES (?,?,?,?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, b.getNombre());
            ps.setString(2,b.getApellido());
            ps.setDate(3, Date.valueOf(b.getfIngreso()));
            ps.setBoolean(4,b.isEstado());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                b.setIdBarbero(rs.getInt("idBarbero"));
                
            }
            JOptionPane.showMessageDialog(null, "Se Agrego Barbero con exito el id es:"+b.getIdBarbero());
            ps.close();
        }catch(SQLException e){
        
            JOptionPane.showMessageDialog(null,"No se Pudo Ingresar Barbero");
        }
    }
    
    public void actualizarBarbero(Barbero b){
    String sql = "UPDATE `Barberos` SET `Nombre`=?,`Apellido`=?,`FechaIngreso`=?,Estado=? WHERE idBarbero=?";
    
    try{
    
        PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, b.getNombre());
        ps.setString(2, b.getApellido());
        ps.setDate(3, Date.valueOf(b.getfIngreso()));
        ps.setInt(5, b.getIdBarbero());
        ps.setBoolean(4,b.isEstado());
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Se Actualizo el Barbero"+b.getIdBarbero()+" con exito");
        ps.close();
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, "No se pudo Actualizar Barbero");
    }
    
    }
    public void darDeBaja(int id){
    String sql="UPDATE `Barberos` SET `Estado`=0 WHERE `idBarbero`=?";
    
    try{
    
        PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, id);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Se dio de baja correctamente");
        ps.close();
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, "No se pudo dar de baja al Barbero");
    }
    
    }
    
      public void darDeAlta(int id){
    String sql="UPDATE `Barberos` SET `Estado`=1 WHERE `idBarbero`=?";
    
    try{
    
        PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, id);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Se dio de Alta correctamente");
        ps.close();
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, "No se pudo dar de Alta al Barbero");
    }
    
    }
      
      public Barbero buscarBarbero(int id){
          Barbero b=null;
      String sql="SELECT * FROM `Barberos` WHERE idBarbero=?";
      
      try{
      
          PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
          ps.setInt(1, id);
          ResultSet rs = ps.executeQuery();
          if(rs.next()){
          b = new Barbero();
          b.setIdBarbero(rs.getInt("idBarbero"));
          b.setNombre(rs.getString("Nombre"));
          b.setApellido(rs.getString("Apellido"));
          b.setfIngreso(rs.getDate("FechaIngreso").toLocalDate());
          b.setEstado(rs.getBoolean("Estado"));
          
          }
          ps.close();
      }catch(SQLException e){
          JOptionPane.showMessageDialog(null, "No se pudo encontrar barbero");
      }
      return b;
      }
      
      public List<Barbero> obtenerBarberos(){
          List<Barbero> lista = new ArrayList<>();
          Barbero b=null;
      String sql = "SELECT * FROM `Barberos` WHERE Estado=1";
      
        try{
        PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
        b = new Barbero();
        b.setIdBarbero(rs.getInt("idBarbero"));
        b.setNombre(rs.getString("Nombre"));
        b.setApellido(rs.getString("Apellido"));
        b.setfIngreso(rs.getDate("FechaIngreso").toLocalDate());
        b.setEstado(rs.getBoolean("Estado"));
        lista.add(b);
        }
       
        ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo obtener la lista de los Barberos");
        }
        return lista;
      }
    
}
