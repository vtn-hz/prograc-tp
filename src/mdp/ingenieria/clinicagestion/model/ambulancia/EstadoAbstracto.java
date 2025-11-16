package mdp.ingenieria.clinicagestion.model.ambulancia;

import mdp.ingenieria.clinicagestion.model.Ambulancia;

public abstract class EstadoAbstracto {
    protected Ambulancia ambulancia;

    /**
     * Inicializa el estado vinculándolo con la ambulancia correspondiente.
     *
     * <b>pre:</b> ambulancia no debe ser nula <br>
     * <b>post:</b> el estado queda asociado a la ambulancia indicada
     *
     * @param ambulancia instancia de la ambulancia
     */
    public EstadoAbstracto(Ambulancia ambulancia){
        this.ambulancia = ambulancia;
    }

    /**
     * Indica si en este estado es posible realizar un traslado.
     *
     * @return false por defecto; los estados concretos redefinen si corresponde
     */
    public boolean puedeTraslado(){ return false; }

    /**
     * Indica si en este estado es posible solicitar mantenimiento.
     *
     * @return false por defecto; los estados concretos redefinen si corresponde
     */
    public boolean puedeMantenimiento(){ return false; }

    /**
     * Indica si en este estado es posible realizar atención a domicilio.
     *
     * @return false por defecto; los estados concretos redefinen si corresponde
     */
    public boolean puedeAtencionDomicilio(){ return false; }

    /**
     * Solicita un traslado según las reglas del estado concreto.
     */
    public abstract void solicitarTraslado();

    /**
     * Gestiona el retorno automático de la ambulancia según el estado.
     */
    public abstract void retornoAutomatico();

    /**
     * Solicita el mantenimiento de la ambulancia según el estado concreto.
     */
    public abstract void solicitarMantenimiento();

    /**
     * Solicita una atención domiciliaria según el estado concreto.
     */
    public abstract void solicitarAtencionDomicilio();

    /**
     * Devuelve el nombre del estado.
     *
     * @return representación textual del estado
     */
    @Override
    public abstract String toString();
}
