package mdp.ingenieria.clinicagestion.model.persona.paciente.factura;

public class FacturaItem {
	
    private int numeroMatriculaMedico;
    private String nyaMedico;
    private String especialidadMedico;
    private double honorario;

    public FacturaItem() {}

    public FacturaItem(
        int numeroMatriculaMedico, String nyaMedico, 
        String especialidadMedico, double honorario
    ) {
        this.numeroMatriculaMedico = numeroMatriculaMedico;        
        this.nyaMedico = nyaMedico;
        this.especialidadMedico = especialidadMedico;
        this.honorario = honorario;
    }

    public int getNumeroMatriculaMedico() {
        return numeroMatriculaMedico;
    }

    public String getNyaMedico() {
        return nyaMedico;
    }

    public String getEspecialidadMedico() {
        return especialidadMedico;
    }

    public double getHonorario() {
        return honorario;
    }

    public void setNumeroMatriculaMedico(int numeroMatriculaMedico) {
        this.numeroMatriculaMedico = numeroMatriculaMedico;
    }

    public void setNyaMedico(String nyaMedico) {
        this.nyaMedico = nyaMedico;
    }

    public void setEspecialidadMedico(String especialidadMedico) {
        this.especialidadMedico = especialidadMedico;
    }

    public void setHonorario(double honorario) {
        this.honorario = honorario;
    }

    public double getSubtotal() {
        return honorario * 1.2;
    }

    /** move to utils */
    private String rowFormat(String texto, int max) {
        if (texto == null) return "";
        if (texto.length() <= max) return texto;
        if (max <= 3) return "...";
        return texto.substring(0, max - 3) + "...";
    }

    @Override
    public String toString() {
        String nombre = "Nombre Médico: " + this.rowFormat(nyaMedico, 12);
        String especialidad = "Especialidad: " + especialidadMedico;
        String etiquetaSubtotal = "Subtotal:";
        String montoSubtotal = String.format("$ %.2f", getSubtotal());

        // 70 caracteres máximo
        return String.format(
            "%-28s %-23s %10s %8s",
            nombre, especialidad, etiquetaSubtotal, montoSubtotal
        );
    }
}
