package mdp.ingenieria.clinicagestion.service;

import mdp.ingenieria.clinicagestion.model.persona.Asociado;
import mdp.ingenieria.clinicagestion.persistence.*;

import java.util.List;

public class AsociadoService {
    private final IAsociadoDAO dao = new AsociadoDAO();

    public void alta(Asociado a) {
        dao.agregarAsociado(AsociadoMapper.toDTO(a));
    }

    public void alta(AsociadoDTO a) {
        dao.agregarAsociado(a);
    }

    public void altaTabla(List<AsociadoDTO> l) {
        dao.agregarAsociados(l);
    }

    public boolean baja(String dni) {
        return dao.eliminarAsociado(dni);
    }

    public void bajaTabla() {
        dao.eliminarTodos();
    }

    public boolean existe(String dni) {
        return dao.existeAsociado(dni);
    }

    public AsociadoDTO obtener(String dni) {
        return dao.obtenerAsociado(dni);
    }

    public List<AsociadoDTO> listar() {
        return dao.listarAsociados();
    }

    public List<AsociadoDTO> listarRnd(int limit) {
        return dao.listarAsociadosRnd(limit);
    }

    public boolean actualizar(Asociado a) {
        return dao.actualizarAsociado(AsociadoMapper.toDTO(a));
    }
}