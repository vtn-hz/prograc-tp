package mdp.ingenieria.clinicagestion.model.ambulancia;

import mdp.ingenieria.clinicagestion.model.Ambulancia;

public class EstadoDisponible extends EstadoAbstracto {
    /**
     * Crea el estado Disponible asociado a la ambulancia dada.
     *
     * @param ambulancia ambulancia cuyo estado cambia
     */
    public EstadoDisponible(Ambulancia ambulancia) {
        super(ambulancia);
    }

    /**
     * @return true porque en este estado se puede realizar un traslado
     */
    @Override
    public boolean puedeTraslado(){
        return true;
    }

    /**
     * @return true porque en este estado se puede solicitar mantenimiento
     */
    @Override
    public boolean puedeMantenimiento(){
        return true;
    }

    /**
     * @return true porque en este estado se puede realizar atención domiciliaria
     */
    @Override
    public boolean puedeAtencionDomicilio(){
        return true;
    }

    /**
     * Cambia el estado a Atendiendo.
     */
    @Override
    public void solicitarAtencionDomicilio() {
        this.ambulancia.setEstado(new EstadoAtendiendo(this.ambulancia) );
    }

    /**
     * Cambia el estado a Trasladando.
     */
    @Override
    public void solicitarTraslado() {
        this.ambulancia.setEstado(new EstadoTrasladando(this.ambulancia) );
    }

    /**
     * No provoca cambios; la ambulancia ya está disponible.
     */
    @Override
    public void retornoAutomatico() {
        // permanece en este estado
    }

    /**
     * Cambia el estado a EnTaller.
     */
    @Override
    public void solicitarMantenimiento() {
        this.ambulancia.setEstado(new EstadoEnTaller(this.ambulancia) );
    }

    /**
     * @return nombre del estado
     */
	@Override
	public String toString() {
		return "Disponible";
	}
    
}
