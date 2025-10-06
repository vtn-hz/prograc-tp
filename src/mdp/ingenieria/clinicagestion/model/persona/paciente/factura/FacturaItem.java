package mdp.ingenieria.clinicagestion.model.persona.paciente.factura;

/**
 * Representa un ítem de factura asociado a la atención médica.
 */
public class FacturaItem {
	
    private int numeroMatriculaMedico;
    private String nyaMedico;
    private String especialidadMedico;
    private double honorario;

    /**
     * Contructor de un item vacio
     * <b>post:</b> el ítem queda creado con valores por defecto
     */
    public FacturaItem() {}

    /**
     * Constructor de un ítem con los datos del médico y el honorario base.
     *
     * <b>pre:</b> el nombre y la especialidad no deben ser nulos ni vacíos; la matrícula debe ser válida; el honorario debe ser >= 0 <br>
     * <b>post:</b> el ítem queda inicializado para su uso en la factura con los datos especificados
     *
     * @param numeroMatriculaMedico     número de matrícula del médico
     * @param nyaMedico                 nombre y apellido del médico
     * @param especialidadMedico        especialidad del médico
     * @param honorario                 honorario base del médico
     */
    public FacturaItem(
        int numeroMatriculaMedico, String nyaMedico, 
        String especialidadMedico, double honorario
    ) {
        this.numeroMatriculaMedico = numeroMatriculaMedico;        
        this.nyaMedico = nyaMedico;
        this.especialidadMedico = especialidadMedico;
        this.honorario = honorario;
    }

    /** @return número de matrícula del médico */
    public int getNumeroMatriculaMedico() {
        return numeroMatriculaMedico;
    }

    /** @return nombre y apellido del médico */
    public String getNyaMedico() {
        return nyaMedico;
    }

    /** @return especialidad del médico */
    public String getEspecialidadMedico() {
        return especialidadMedico;
    }

    /** @return honorario base del médico */
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

    /**
     * Calcula el subtotal del ítem aplicando un 20% adicional sobre el honorario base.
     *
     * <b>pre:</b> el honorario base debe estar definido (no negativo) <br>
     * <b>post:</b> devuelve el subtotal con el recargo aplicado
     *
     * @return subtotal del ítem (honorario * 1.2)
     */
    public double getSubtotal() {
        return honorario * 1.2;
    }

    /**
     * Formatea y acorta un texto
     *
     * <b>pre:</b> max debe ser mayor que cero, el texto no puede ser null o vacio <br>
     * <b>post:</b> retorna el texto original o truncado con "..." si excede el máximo
     *
     * @param texto     cadena a formatear
     * @param max       cantidad máxima de caracteres
     * @return texto formateado
     */
    /** move to utils */
    private String rowFormat(String texto, int max) {
        if (texto == null) return "";
        if (texto.length() <= max) return texto;
        if (max <= 3) return "...";
        return texto.substring(0, max - 3) + "...";
    }

    /**
     * Devuelve una representación textual alineada del ítem.
     * <b>post:</b> devuelve una línea formateada apta para listados en factura
     *
     * @return cadena alineada con los datos del ítem
     */
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
