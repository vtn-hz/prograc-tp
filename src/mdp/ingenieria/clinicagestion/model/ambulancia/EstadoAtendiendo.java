package mdp.ingenieria.clinicagestion.model.ambulancia;

import mdp.ingenieria.clinicagestion.model.Ambulancia;

public class EstadoAtendiendo extends EstadoAbstracto{
    /**
     * Crea el estado Atendiendo asociado a la ambulancia dada.
     *
     * @param ambulancia ambulancia cuyo estado cambia
     */
    public EstadoAtendiendo(Ambulancia ambulancia) {
        super(ambulancia);
    }

    /**
     * Continúa atendiendo; no cambia de estado.
     */
    @Override
    public void solicitarAtencionDomicilio() {
        // permanece en este estado
    }

    /**
     * No se puede solicitar un traslado mientras se está atendiendo.
     */
    @Override
    public void solicitarTraslado() {
        // informa que no puede
    }

    /**
     * Finaliza la atención y pasa al estado Regresando.
     */
    @Override
    public void retornoAutomatico() {
        this.ambulancia.setEstado(new EstadoRegresando(this.ambulancia));
    }

    /**
     * No se puede solicitar mantenimiento mientras se está atendiendo.
     */
    @Override
    public void solicitarMantenimiento() {
        // informa que no puede
    }

    /**
     * @return nombre del estado
     */
	@Override
	public String toString() {
		return "Atendiendo";
	}

}
