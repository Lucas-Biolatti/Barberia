/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barberfolk;

import Entidades.Barbero;
import Entidades.Producto;
import Modelo.BarberoData;
import Modelo.Conexion;

import Modelo.ProductoData;
import Modelo.ServicioData;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author lbiolatti
 */
public class BarberFolk {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       Conexion c = new Conexion();
       ProductoData pd = new ProductoData(c);
       //pd.calcularStock(2);
     //  System.out.println(pd.buscarProducto("c"));
      //  System.out.println(pd.obtenerProductos());
      BarberoData bd = new BarberoData(c);
      LocalDate ld = LocalDate.of(2020,11,20);
      //Barbero b = new Barbero(1,"Facundo","Saez",ld,true);
      //bd.actualizarBarbero(b);
      ServicioData sd = new ServicioData(c);
        System.out.println(sd.obtenerDetallePorServicio("eda7174e"));
    }
    
}
