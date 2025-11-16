package mdp.ingenieria.clinicagestion.persistence;

import java.util.List;

public interface IAsociadoDAO {
    /**
     * Inserta un asociado en la base de datos.
     *
     * @param asociado datos del asociado a agregar
     */
    void agregarAsociado(AsociadoDTO asociado);

    /**
     * Inserta una lista de asociados en la base de datos.
     *
     * @param asociado lista de asociados a agregar
     */
    void agregarAsociados(List<AsociadoDTO> asociado);

    /**
     * Elimina un asociado por su DNI.
     *
     * @param dni identificador del asociado
     * @return true si se eliminó algún registro, false en caso contrario
     */
    boolean eliminarAsociado(String dni);

    /**
     * Elimina todos los asociados.
     */
    void eliminarTodos();

    /**
     * Lista todos los asociados.
     *
     * @return lista completa de asociados, posiblemente vacía
     */
    List<AsociadoDTO> listarAsociados();

    /**
     * Lista asociados en orden aleatorio, limitado por el parámetro.
     *
     * @param limit cantidad máxima de asociados a devolver
     * @return lista de asociados, con tamaño menor o igual a limit
     */
    List<AsociadoDTO> listarAsociadosRnd(int limit);

    /**
     * Cuenta la cantidad total de asociados.
     *
     * @return número de registros en la tabla de asociados
     */
    int contarAsociados();

    /**
     * Obtiene un asociado por su DNI.
     *
     * @param dni identificador del asociado
     * @return asociado encontrado o null si no existe
     */
    AsociadoDTO obtenerAsociado(String dni);

    /**
     * Verifica si existe un asociado con el DNI indicado.
     *
     * @param dni identificador a consultar
     * @return true si existe, false en caso contrario
     */
    boolean existeAsociado(String dni);

    /**
     * Actualiza los datos de un asociado existente.
     *
     * @param asociado datos a actualizar
     * @return true si se modificó algún registro, false si no se encontró el DNI
     */
    boolean actualizarAsociado(AsociadoDTO asociado);
}