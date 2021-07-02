/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author lbiolatti
 */
public class Conexion {
     private final String base="lucas_barberfolk";
    private final String url="jdbc:mysql://gator3140.hostgator.com:3306/"+base;
    private final String user="lucas_barberfolk";
    private final String pass="barberfolk2020";
    private Connection con;
    
    public Connection getConection(){
    try{
        Class.forName("org.mariadb.jdbc.Driver");
        con=(Connection)DriverManager.getConnection(url, user, pass);
    } catch(SQLException | ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"No se pudo conectar con la bd");
            }
    return con;
    }
}
