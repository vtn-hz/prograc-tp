package mdp.ingenieria.clinicagestion.util;

import java.lang.Thread;
import java.util.Random;

public class ThreadUtil {

	private static Random random;
	
	static { random = new Random(); }
	
	public static int SHORT 	= 2000;
	
	public static int MEDIUM 	= 5000;

	public static int LONG 		= 10000;
	
	public static void simulateTime ( int millis ) {
		try {
			Thread.sleep( random.nextInt( millis ) );
		} catch (InterruptedException e) {}
	}

}
