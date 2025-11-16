package mdp.ingenieria.clinicagestion.model.ambulancia;

import mdp.ingenieria.clinicagestion.model.Ambulancia;

public class EstadoRegresando extends EstadoAbstracto {
    /**
     * Crea el estado Regresando asociado a la ambulancia dada.
     *
     * @param ambulancia ambulancia cuyo estado cambia
     */
    public EstadoRegresando(Ambulancia ambulancia) {
        super(ambulancia);
    }

    /**
     * @return true, ya que durante el regreso puede reasignarse a un traslado
     */
    @Override
    public boolean puedeTraslado() {
        return true;
    }

    /**
     * @return true, ya que durante el regreso puede reasignarse a una atención domiciliaria
     */
    @Override
    public boolean puedeAtencionDomicilio() {
        return true;
    }

    /**
     * Cambia a estado Atendiendo durante el regreso.
     */
    @Override
    public void solicitarAtencionDomicilio() {
        this.ambulancia.setEstado(new EstadoAtendiendo(this.ambulancia));
    }

    /**
     * Cambia a estado Trasladando durante el regreso.
     */
    @Override
    public void solicitarTraslado() {
        this.ambulancia.setEstado(new EstadoTrasladando(this.ambulancia));
    }

    /**
     * Al completarse el regreso, pasa a estado Disponible.
     */
    @Override
    public void retornoAutomatico() {
        this.ambulancia.setEstado(new EstadoDisponible(this.ambulancia));
    }

    /**
     * No puede solicitar mantenimiento mientras está regresando.
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
		return "Regresando";
	}
    
    
}
