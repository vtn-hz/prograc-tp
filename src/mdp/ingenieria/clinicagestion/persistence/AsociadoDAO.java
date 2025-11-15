package mdp.ingenieria.clinicagestion.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsociadoDAO implements IAsociadoDAO {
    private final ConnectionManager cm = ConnectionManager.getInstance();

    @Override
    public void agregarAsociado(AsociadoDTO a) {
        String sql = "INSERT INTO asociado(dni, nya, telefono, ciudad, direccion) VALUES(?,?,?,?,?)";
        try (Connection c = cm.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, a.getDni());
            ps.setString(2, a.getNya());
            ps.setString(3, a.getTelefono());
            ps.setString(4, a.getCiudad());
            ps.setString(5, a.getDireccion());
            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new RuntimeException("DNI duplicado: " + a.getDni(), e);
        } catch (SQLException e) {
            throw new RuntimeException("Error insertando asociado " + a.getDni(), e);
        }
    }

    @Override
    public void agregarAsociados(List<AsociadoDTO> lista) {
        String sql = "INSERT INTO asociado(dni, nya, telefono, ciudad, direccion) VALUES(?,?,?,?,?)";

        try (Connection c = cm.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            for (AsociadoDTO a : lista) {
                ps.setString(1, a.getDni());
                ps.setString(2, a.getNya());
                ps.setString(3, a.getTelefono());
                ps.setString(4, a.getCiudad());
                ps.setString(5, a.getDireccion());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (BatchUpdateException e) {
            throw new RuntimeException("Error en inserción por lote (posible DNI duplicado)", e);
        } catch (SQLException e) {
            throw new RuntimeException("Error insertando lista de asociados", e);
        }
    }

    @Override
    public boolean eliminarAsociado(String dni) {
        String sql = "DELETE FROM asociado WHERE dni = ?";
        try (Connection c = cm.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, dni);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando asociado " + dni, e);
        }
    }

    @Override
    public void eliminarTodos() {
        String sql = "DELETE FROM asociado";
        try (Connection c = cm.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando todos los asociados", e);
        }
    }

    @Override
    public boolean existeAsociado(String dni) {
        String sql = "SELECT 1 FROM asociado WHERE dni = ?";
        try (Connection c = cm.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
        } catch (SQLException e) {
            throw new RuntimeException("Error verificando asociado " + dni, e);
        }
    }

    @Override
    public AsociadoDTO obtenerAsociado(String dni) {
        String sql = "SELECT dni, nya, telefono, ciudad, direccion FROM asociado WHERE dni = ?";
        try (Connection c = cm.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new AsociadoDTO(
                        rs.getString("dni"),
                        rs.getString("nya"),
                        rs.getString("telefono"),
                        rs.getString("ciudad"),
                        rs.getString("direccion")
                    );
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo asociado " + dni, e);
        }
    }

    @Override
    public List<AsociadoDTO> listarAsociados() {
        String sql = "SELECT dni, nya, telefono, ciudad, direccion FROM asociado ORDER BY nya";
        List<AsociadoDTO> list = new ArrayList<>();
        try (Connection c = cm.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new AsociadoDTO(
                    rs.getString("dni"),
                    rs.getString("nya"),
                    rs.getString("telefono"),
                    rs.getString("ciudad"),
                    rs.getString("direccion")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando asociados", e);
        }
        return list;
    }

    @Override
    public List<AsociadoDTO> listarAsociadosRnd(int limit) {
        String sql = "SELECT dni, nya, telefono, ciudad, direccion FROM asociado ORDER BY RAND() LIMIT ?";
        List<AsociadoDTO> list = new ArrayList<>();
        try (Connection c = cm.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, limit);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new AsociadoDTO(
                        rs.getString("dni"),
                        rs.getString("nya"),
                        rs.getString("telefono"),
                        rs.getString("ciudad"),
                        rs.getString("direccion")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando asociados", e);
        }
        return list;
    }

    @Override
    public boolean actualizarAsociado(AsociadoDTO a) {
        String sql = "UPDATE asociado SET nya = ?, telefono = ?, ciudad = ?, direccion = ? WHERE dni = ?";
        try (Connection c = cm.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, a.getNya());
            ps.setString(2, a.getTelefono());
            ps.setString(3, a.getCiudad());
            ps.setString(4, a.getDireccion());
            ps.setString(5, a.getDni());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando asociado " + a.getDni(), e);
        }
    }
}