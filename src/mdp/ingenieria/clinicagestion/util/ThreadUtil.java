package mdp.ingenieria.clinicagestion.util;

import java.lang.Thread;
import java.util.Random;

public class ThreadUtil {

	private static Random random;
	
	static { random = new Random(); }
	
	public static int SHORT 	= 1000;
	
	public static int MEDIUM 	= 1500;

	public static int LONG 		= 3000;

    /**
     * Pausa el hilo por el tiempo indicado.
     *
     * <b>pre:</b> millis > 0 <br>
     * <b>post:</b> el hilo se detiene exactamente millis milisegundos
     *
     * @param millis duración de la pausa
     */
	public static void simulateTime ( int millis ) {
		assert millis > 0;
		try {
			Thread.sleep( millis  );
		} catch (InterruptedException e) {}
	}

    /**
     * Pausa el hilo por un valor aleatorio entre 0 y millis.
     *
     * <b>pre:</b> millis > 0 <br>
     * <b>post:</b> el hilo se detiene un tiempo aleatorio dentro del rango
     *
     * @param millis límite superior del tiempo aleatorio
     */
	public static void simulateTimeRandom ( int millis ) {
		assert millis > 0;
		try {
			Thread.sleep( random.nextInt(millis) );
		} catch (InterruptedException e) {}
	}

    /**
     * Pausa el hilo por el promedio entre millis y un valor aleatorio menor a millis.
     *
     * <b>pre:</b> millis > 0 <br>
     * <b>post:</b> el hilo se detiene un tiempo cercano al promedio esperado
     *
     * @param millis valor base máximo para medir el promedio
     */

    public static void simulateTimeMedio ( int millis ) {
		assert millis > 0;
		try {
			Thread.sleep( (millis + random.nextInt(millis)) / 2 );
		} catch (InterruptedException e) {}
	}
	
	

}
