package mdp.ingenieria.clinicagestion.persistence;

import java.util.List;

public interface IAsociadoDAO {
    void agregarAsociado(AsociadoDTO asociado);
    boolean eliminarAsociado(String dni);
    boolean existeAsociado(String dni);
    AsociadoDTO obtenerAsociado(String dni);
    List<AsociadoDTO> listarAsociados();
    boolean actualizarAsociado(AsociadoDTO asociado);
}