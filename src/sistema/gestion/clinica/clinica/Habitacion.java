package sistema.gestion.clinica.clinica;

public abstract class Habitacion {
    protected double costoAsignacion;
    protected double costoAdicional;

    public Habitacion(double costoAsignacion, double costoAdicional) {
        this.costoAsignacion = costoAsignacion;
        this.costoAdicional = costoAdicional;
    }

    public double getCostoAsignacion() {
        return costoAsignacion;
    }
    public abstract double getCostoAdicional(int dias);

    public double getCostoTotal(int dias){
        return this.getCostoAsignacion()+this.getCostoAdicional(dias);
    }
}
