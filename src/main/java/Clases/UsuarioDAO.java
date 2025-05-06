/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Coraline
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private Connection conectar;

    public UsuarioDAO() {
        Conexion conexion = new Conexion();
        conectar = conexion.estableceConexion();
    }

    // Crear
    public boolean insertarUsuario(UsuarioDTO usuario) {
        if (existeUsuario(usuario.getNombre())) {
            System.out.println("El usuario ya existe");
            return false;
        }

        String sql = "INSERT INTO usuarios (nombre, contraseña) VALUES (?, ?)";
        try (PreparedStatement pst = conectar.prepareStatement(sql)) {
            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getContraseña());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    // Leer
    public List<UsuarioDTO> obtenerUsuarios() {
        List<UsuarioDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Statement st = conectar.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                UsuarioDTO usuario = new UsuarioDTO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("contraseña")
                );
                lista.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener usuarios: " + e.getMessage());
        }
        return lista;
    }

    // Actualizar
    public boolean actualizarUsuarioPorIdONombre(UsuarioDTO usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, contraseña = ? WHERE id = ? OR nombre = ?";
        try (PreparedStatement pst = conectar.prepareStatement(sql)) {
            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getContraseña());
            pst.setInt(3, usuario.getId());
            pst.setString(4, usuario.getNombre());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    // Eliminar
    public boolean eliminarUsuarioPorIdONombre(int id, String nombre) {
        String sql = "DELETE FROM usuarios WHERE id = ? OR nombre = ?";
        try (PreparedStatement pst = conectar.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.setString(2, nombre);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    public boolean existeUsuario(String nombre) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE nombre = ?";
        try (PreparedStatement pst = conectar.prepareStatement(sql)) {
            pst.setString(1, nombre);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar usuario: " + e.getMessage());
        }
        return false;
    }

}