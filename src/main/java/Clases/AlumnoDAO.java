import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO {
    private Connection conexion;

    public AlumnoDAO() {
        this.conexion = ConexionDB.obtenerConexion(); // Tu clase de conexión
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
        String sql = "SELECT id
