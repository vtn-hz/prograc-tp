package mdp.ingenieria.clinicagestion.model;

public class AmbulanciaMock {
	
	// Definimos los estados como constantes para que sea legible
	public static final int ESTADO_DISPONIBLE = 0;
	public static final int ESTADO_EN_DOMICILIO = 1;
	public static final int ESTADO_EN_TRASLADO_MANT = 2;
	
	// El estado inicial es 0 (DISPONIBLE), como pediste.
	private int state = ESTADO_DISPONIBLE;

	public AmbulanciaMock() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Método de espera genérico.
	 * Todos los hilos esperan aquí si la ambulancia NO está disponible.
	 */
	private synchronized void esperarSiEstaOcupada(String solicitante) throws InterruptedException {
		String hilo = Thread.currentThread().getName();
		
		// Esta es la lógica clave:
		// Espera MIENTRAS (while) el estado NO SEA (state !=) DISPONIBLE (0).
        while (state != ESTADO_DISPONIBLE) {
            try {
            	System.out.println(hilo + ": ...ESPERANDO para " + solicitante + " (Estado actual=" + state + ", se necesita 0).");
                wait();
                System.out.println(hilo + ": ...despertó para " + solicitante + ". Re-chequeando estado.");
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt(); 
                // Relanzamos la interrupción para que el método que nos llamó se entere.
                throw e; 
            }
        }
	}
	
	 public synchronized void solicitarAtencionDomicilio(){
		String hilo = Thread.currentThread().getName();
		System.out.println(hilo + ": INTENTA solicitar Domicilio. (State actual=" + state + ")");
		
        try {
        	// 1. Espera a que esté disponible
			esperarSiEstaOcupada("Domicilio");
			
			// 2. Si salimos de esperarSiEstaOcupada, es que state == 0.
			// ¡La tenemos! La marcamos como ocupada.
			System.out.println(hilo + ": OBTUVO Domicilio. Poniendo state = " + ESTADO_EN_DOMICILIO);
			state = ESTADO_EN_DOMICILIO;
			// No notificamos a nadie (notifyAll), porque acabamos de tomar el recurso.
			
		} catch (InterruptedException e) {
			System.out.println(hilo + ": Fue interrumpido mientras esperaba Domicilio.");
		}
    }

    public synchronized void solicitarTraslado(){
    	String hilo = Thread.currentThread().getName();
    	System.out.println(hilo + ": INTENTA solicitar Traslado. (State actual=" + state + ")");

        try {
        	// 1. Espera a que esté disponible
			esperarSiEstaOcupada("Traslado");
			
			// 2. ¡La tenemos!
			System.out.println(hilo + ": OBTUVO Traslado. Poniendo state = " + ESTADO_EN_TRASLADO_MANT);
			state = ESTADO_EN_TRASLADO_MANT;
			
		} catch (InterruptedException e) {
			System.out.println(hilo + ": Fue interrumpido mientras esperaba Traslado.");
		}
    }

    public synchronized void solicitarMantenimiento(){
    	String hilo = Thread.currentThread().getName();
    	System.out.println(hilo + ": INTENTA solicitar Mantenimiento. (State actual=" + state + ")");

    	try {
        	// 1. Espera a que esté disponible
			esperarSiEstaOcupada("Mantenimiento");
			
			// 2. ¡La tenemos! (Reusamos el estado de Traslado)
			System.out.println(hilo + ": OBTUVO Mantenimiento. Poniendo state = " + ESTADO_EN_TRASLADO_MANT);
			this.state = ESTADO_EN_TRASLADO_MANT;
			
		} catch (InterruptedException e) {
			System.out.println(hilo + ": Fue interrumpido mientras esperaba Mantenimiento.");
		}
    }

    /**
     * ¡Este es el método más importante!
     * Libera la ambulancia y despierta a todos los que esperan.
     */
    public synchronized void retornoAutomatico(){
    	String hilo = Thread.currentThread().getName();
    	System.out.println(hilo + ": >>> RETORNO AUTOMATICO. Poniendo state = 0 (Favorable).");
    	this.state = ESTADO_DISPONIBLE;
        notifyAll(); // ¡Despierta a TODOS los hilos en wait()!
    }
}