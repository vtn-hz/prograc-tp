package mdp.ingenieria.clinicagestion.service;

import mdp.ingenieria.clinicagestion.model.persona.Asociado;
import mdp.ingenieria.clinicagestion.persistence.*;

import java.util.List;

public class AsociadoService {
    private final IAsociadoDAO dao = new AsociadoDAO();

    /**
     * Da de alta un asociado del modelo convirtiéndolo a DTO.
     * <b>pre:</b> a no debe ser nulo <br>
     * @param a objeto Asociado a registrar
     */
    public void alta(Asociado a) {
        assert a != null;
        dao.agregarAsociado(AsociadoMapper.toDTO(a));
    }

    /**
     * Da de alta un asociado recibidos directamente como DTO.
     * <b>pre:</b> a no debe ser nulo <br>
     * @param a asociado a registrar
     */
    public void alta(AsociadoDTO a) {
        assert a != null;
        dao.agregarAsociado(a);
    }

    /**
     * Inserta un conjunto de asociados en bloque.
     * <b>pre:</b> la lista List<AsociadoDTO> no debe estar vacia <br>
     * @param l lista de asociados a registrar
     */
    public void altaTabla(List<AsociadoDTO> l) {
        assert !l.isEmpty();
        dao.agregarAsociados(l);
    }

    /**
     * Elimina un asociado por su DNI.
     *
     * @param dni identificador del asociado
     * @return true si se eliminó, false si no existe
     */
    public boolean baja(String dni) {
        return dao.eliminarAsociado(dni);
    }

    /**
     * Elimina todos los registros de asociados.
     */
    public void bajaTabla() {
        dao.eliminarTodos();
    }

    /**
     * Verifica si existe un asociado con el DNI proporcionado.
     *
     * @param dni identificador a consultar
     * @return true si existe, false en caso contrario
     */
    public boolean existe(String dni) {
        return dao.existeAsociado(dni);
    }

    /**
     * Obtiene un asociado por DNI.
     *
     * @param dni identificador buscado
     * @return el DTO correspondiente, o null si no se encontró
     */
    public AsociadoDTO obtener(String dni) {
        return dao.obtenerAsociado(dni);
    }

    /**
     * Lista todos los asociados registrados.
     *
     * @return lista completa de asociados
     */
    public List<AsociadoDTO> listar() {
        return dao.listarAsociados();
    }

    /**
     * Lista una cantidad aleatoria de asociados.
     *
     * @param limit cantidad máxima de elementos
     * @return lista con hasta limit asociados en orden aleatorio
     */
    public List<AsociadoDTO> listarRnd(int limit) {
        return dao.listarAsociadosRnd(limit);
    }

    /**
     * Cuenta todos los asociados registrados.
     *
     * @return cantidad de registros
     */
    public int contar() {
        return dao.contarAsociados();
    }

    /**
     * Actualiza los datos de un asociado del modelo.
     *
     * @param a asociado a actualizar
     * @return true si se modificó algún registro, false si no existe
     */
    public boolean actualizar(Asociado a) {
        return dao.actualizarAsociado(AsociadoMapper.toDTO(a));
    }
}