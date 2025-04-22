/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import com.mysql.cj.protocol.Resultset;
import com.tarea.logincrud.Crud;

import java.sql.ResultSet;
import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Coraline
 */
public class CLogin {
    public void validaUsuario(JTextField usuario, JPasswordField contra){

        try {
            ResultSet rs = null;
            PreparedStatement ps = null;
            String sql = "SELECT * FROM usuarios WHERE nombre = ? AND contraseña = ?;";

            Clases.Conexion opbjetoConexion = new Clases.Conexion();
            ps = opbjetoConexion.estableceConexion().prepareStatement(sql);

            ps.setString(1, usuario.getText());
            ps.setString(2, String.valueOf(contra.getPassword()));
            //ps.setString(2, contra.getText());
            rs = ps.executeQuery();

            if (rs.next()) {
                
                // Aquí puedes agregar el código para abrir la ventana principal
                Crud objetoMenu = new Crud();
                objetoMenu.setVisible(true);
                objetoMenu.setLocationRelativeTo(null);
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
}
