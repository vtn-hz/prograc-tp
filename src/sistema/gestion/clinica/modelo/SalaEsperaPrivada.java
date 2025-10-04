package sistema.gestion.clinica.modelo;

public class SalaEsperaPrivada {
    private Paciente paciente;
    private static SalaEsperaPrivada _instancia;

    public static SalaEsperaPrivada getInstance() {
        if (_instancia == null) {
            _instancia = new SalaEsperaPrivada();
        }
        return _instancia;
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
