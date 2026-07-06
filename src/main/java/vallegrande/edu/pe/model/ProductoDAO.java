package vallegrande.edu.pe.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoDAO {

    private Connection conexion;

    // Constructor
    public ProductoDAO() {
        conexion = ConexionBD.getConexion();
    }

    // INSERTAR
    public boolean insertar(Producto producto) {

        String sql = "INSERT INTO producto(nombre, descripcion, tipo, disponible, id_categoria) VALUES (?, ?, ?, ?, ?)";

        try {

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setString(3, producto.getTipo());
            ps.setBoolean(4, producto.isDisponible());
            ps.setInt(5, producto.getIdCategoria());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Error al insertar: " + e.getMessage());
            return false;
        }
    }

    // LISTAR
    public ArrayList<Producto> listar() {

        ArrayList<Producto> lista = new ArrayList<>();

        String sql = "SELECT * FROM producto";

        try {

            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Producto producto = new Producto();

                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setTipo(rs.getString("tipo"));
                producto.setDisponible(rs.getBoolean("disponible"));
                producto.setIdCategoria(rs.getInt("id_categoria"));

                lista.add(producto);
            }

        } catch (SQLException e) {

            System.out.println("Error al listar: " + e.getMessage());
        }

        return lista;
    }

    // ACTUALIZAR
    public boolean actualizar(Producto producto) {

        String sql = "UPDATE producto SET nombre = ?, descripcion = ?, tipo = ?, disponible = ?, id_categoria = ? WHERE id_producto = ?";

        try {

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setString(3, producto.getTipo());
            ps.setBoolean(4, producto.isDisponible());
            ps.setInt(5, producto.getIdCategoria());
            ps.setInt(6, producto.getIdProducto());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR (TE FALTABA ESTO)
    public boolean eliminar(int idProducto) {

        String sql = "DELETE FROM producto WHERE id_producto = ?";

        try {

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setInt(1, idProducto);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }
}
