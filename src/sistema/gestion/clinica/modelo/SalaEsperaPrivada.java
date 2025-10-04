package sistema.gestion.clinica.modelo;

public class SalaEsperaPrivada {
    private Paciente paciente;
    private SalaEsperaPrivada _instancia;

    public SalaEsperaPrivada getInstance() {
        if (this._instancia == null) {
            this._instancia = new SalaEsperaPrivada();
        }
        return this._instancia;
    }

    public void ocupar(Paciente paciente) {
        this.paciente = paciente;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void desocupar() {
        this.paciente = null;
    }

    public boolean estaOcupada() {
        return this.paciente != null;
    }
}
