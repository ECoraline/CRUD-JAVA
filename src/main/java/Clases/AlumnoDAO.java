package Clases;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO {
    private Connection conexion;

    public AlumnoDAO() {
        Conexion con = new Conexion();
        this.conexion = con.estableceConexion();
    }

    public void insertar(AlumnoDTO alumno) throws SQLException {
        String sql = "INSERT INTO alumnos (nombre, promedio) VALUES (?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, alumno.getNombre());
            stmt.setDouble(2, alumno.getPromedio());
            stmt.executeUpdate();
        }
    }

    public List<AlumnoDTO> listar() throws SQLException {
        List<AlumnoDTO> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, promedio FROM alumnos";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                AlumnoDTO alumno = new AlumnoDTO(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getDouble("promedio")
                );
                lista.add(alumno);
            }
        }
        return lista;
    }

    public void actualizar(AlumnoDTO alumno) throws SQLException {
        String sql = "UPDATE alumnos SET nombre = ?, promedio = ? WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, alumno.getNombre());
            stmt.setDouble(2, alumno.getPromedio());
            stmt.setInt(3, alumno.getId());
            stmt.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM alumnos WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
