package mdp.ingenieria.clinicagestion.model.ambulancia;

import mdp.ingenieria.clinicagestion.model.Ambulancia;

public class EstadoRegresandoTaller extends EstadoAbstracto{
    /**
     * Crea el estado RegresandoTaller asociado a la ambulancia dada.
     *
     * @param ambulancia ambulancia cuyo estado cambia
     */
    public EstadoRegresandoTaller(Ambulancia ambulancia) {
        super(ambulancia);
    }

    /**
     * No puede atender durante el regreso del taller.
     */
    @Override
    public void solicitarAtencionDomicilio() {
        // permanece en este estado
    }

    /**
     * No puede realizar traslados en este estado.
     */
    @Override
    public void solicitarTraslado() {
        // informa que no puede
    }

    /**
     * Al finalizar el regreso, pasa a estado Disponible.
     */
    @Override
    public void retornoAutomatico() {
        this.ambulancia.setEstado(new EstadoDisponible(this.ambulancia));
    }

    /**
     * No puede solicitar mantenimiento mientras regresa del taller.
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
		return "Regresando taller";
	}
}
