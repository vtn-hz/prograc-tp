package mdp.ingenieria.clinicagestion.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsociadoDAO implements IAsociadoDAO {
    private final ConnectionManager cm = ConnectionManager.getInstance();

    /**
     * Inserta un nuevo asociado en la base de datos.
     *
     * <b>pre:</b> a no debe ser nulo y debe contener campos válidos (dni único) <br>
     * <b>post:</b> se inserta un registro en la tabla asociado; si el DNI existe, se lanza una excepción
     *
     * @param a datos del asociado a agregar
     */
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

    /**
     * Inserta una lista de asociados usando un batch.
     *
     * <b>pre:</b> lista no debe ser nula ni vacía; cada asociado debe tener DNI válido <br>
     * <b>post:</b> se intentan insertar todos los registros; si hay conflicto (por ejemplo, DNI duplicado),
     * se lanza una excepción
     *
     * @param lista lista de asociados a insertar
     */
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

    /**
     * Elimina un asociado por DNI.
     *
     * <b>pre:</b> dni no debe ser nulo ni vacío <br>
     * <b>post:</b> si existía, se elimina el registro y se devuelve true; en caso contrario se devuelve false
     *
     * @param dni identificador del asociado
     * @return true si se eliminó algún registro, false si no existía
     */
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

    /**
     * Elimina todos los asociados de la tabla.
     *
     * <b>post:</b> la tabla asociado queda vacía
     */
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

    /**
     * Lista todos los asociados ordenados por nombre y apellido.
     *
     * <b>post:</b> se devuelve una lista (posiblemente vacía) con todos los registros de la tabla
     *
     * @return lista de AsociadoDTO
     */
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

    /**
     * Lista asociados en orden aleatorio, limitado por el parámetro.
     *
     * <b>pre:</b> limit >= 0 <br>
     * <b>post:</b> se devuelve una lista de longitud menor o igual a limit
     *
     * @param limit cantidad máxima de registros a devolver
     * @return lista de AsociadoDTO en orden aleatorio
     */
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

    /**
     * Cuenta la cantidad total de asociados.
     *
     * <b>post:</b> se devuelve la cantidad de registros; en caso de error se devuelve 0
     *
     * @return número de filas en la tabla asociado
     */
    @Override
    public int contarAsociados() {
        String sql = "SELECT COUNT(*) FROM asociado";
        try (Connection c = cm.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error contando asociados", e);
        }
        return 0;
    }

    /**
     * Verifica si existe un asociado con el DNI dado.
     *
     * <b>pre:</b> dni no debe ser nulo ni vacío <br>
     * <b>post:</b> se devuelve true si hay al menos un registro con ese DNI
     *
     * @param dni identificador a verificar
     * @return true si existe, false en caso contrario
     */
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

    /**
     * Obtiene un asociado a partir de su DNI.
     *
     * <b>pre:</b> dni no debe ser nulo ni vacío <br>
     * <b>post:</b> se devuelve un AsociadoDTO si existe, o null si no se encontró ningún registro
     *
     * @param dni identificador del asociado a buscar
     * @return asociado encontrado o null
     */
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

    /**
     * Actualiza los datos de un asociado ya existente.
     *
     * <b>pre:</b> a no debe ser nulo y su DNI debe existir en la base <br>
     * <b>post:</b> se actualizan los campos no clave; se devuelve true si se modificó algún registro
     *
     * @param a datos del asociado a actualizar
     * @return true si se actualizó, false si no se encontró el DNI
     */
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