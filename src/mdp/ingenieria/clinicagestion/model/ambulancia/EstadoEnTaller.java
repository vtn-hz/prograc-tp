package mdp.ingenieria.clinicagestion.model.ambulancia;

import mdp.ingenieria.clinicagestion.model.Ambulancia;

public class EstadoEnTaller extends EstadoAbstracto{
    /**
     * Crea el estado EnTaller asociado a la ambulancia dada.
     *
     * @param ambulancia ambulancia cuyo estado cambia
     */
    public EstadoEnTaller(Ambulancia ambulancia) {
        super(ambulancia);
    }

    /**
     * @return true porque ya está en mantenimiento
     */
    @Override
    public boolean puedeMantenimiento(){
        return true;
    }

    /**
     * No puede realizar atención domiciliaria en este estado.
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
     * No cambia de estado durante un retorno automático.
     */
    @Override
    public void retornoAutomatico() {
        // permanece en este estado
    }

    /**
     * Cambia el estado a RegresandoTaller tras finalizar el mantenimiento.
     */
    @Override
    public void solicitarMantenimiento() {
        this.ambulancia.setEstado(new EstadoRegresandoTaller(this.ambulancia));
    }

    /**
     * @return nombre del estado
     */
	@Override
	public String toString() {
		return "En taller";
	}
}
