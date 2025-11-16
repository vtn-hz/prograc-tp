package mdp.ingenieria.clinicagestion.model.ambulancia;

import mdp.ingenieria.clinicagestion.model.Ambulancia;

public class EstadoTrasladando extends EstadoAbstracto {
    /**
     * Crea el estado Trasladando asociado a la ambulancia dada.
     *
     * @param ambulancia ambulancia cuyo estado cambia
     */
    public EstadoTrasladando(Ambulancia ambulancia) {
        super(ambulancia);
    }

    /**
     * No puede iniciar una atención domiciliaria mientras traslada.
     */
    @Override
    public void solicitarAtencionDomicilio() {
        // permanece en este estado
    }

    /**
     * No puede iniciar otro traslado mientras está trasladando.
     */
    @Override
    public void solicitarTraslado() {
        // informa que no puede
    }

    /**
     * Al finalizar el traslado, pasa a estado Disponible.
     */
    @Override
    public void retornoAutomatico() {
        this.ambulancia.setEstado(new EstadoDisponible(this.ambulancia));
    }

    /**
     * No puede ingresar a mantenimiento mientras traslada.
     */
    @Override
    public void solicitarMantenimiento() {
        // infroma que no puede
    }

    /**
     * @return nombre del estado
     */
	@Override
	public String toString() {
		return "Trasladando";
	}   
}
