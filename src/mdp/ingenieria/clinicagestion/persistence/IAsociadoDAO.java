package mdp.ingenieria.clinicagestion.persistence;

import java.util.List;

public interface IAsociadoDAO {
    void agregarAsociado(AsociadoDTO asociado);
    void agregarAsociados(List<AsociadoDTO> asociado);

    boolean eliminarAsociado(String dni);
    void eliminarTodos();

    List<AsociadoDTO> listarAsociados();
    List<AsociadoDTO> listarAsociadosRnd(int limit);

    AsociadoDTO obtenerAsociado(String dni);
    boolean existeAsociado(String dni);
    boolean actualizarAsociado(AsociadoDTO asociado);
}