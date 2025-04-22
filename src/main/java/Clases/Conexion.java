/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Coraline
 */
public class Conexion {
    Connection conectar;
    
    String usuario = System.getenv("MYSQL_USER");
    String contra = System.getenv("MYSQL_PASSWORD");
    String db = "usuarios";
    String ip = "localhost";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+db;
    
    public Connection estableceConexion(){
        try{
            conectar = DriverManager.getConnection(cadena,usuario,contra);
            //JOptionPane.showMessageDialog(null, "conexion correcta");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "problemas en la coneccion: "+ e.getMessage());
        }
        return conectar;
    }
}
