package mdp.ingenieria.clinicagestion.util;

import java.lang.Thread;
import java.util.Random;

public class ThreadUtil {

	private static Random random;
	
	static { random = new Random(); }
	
	public static int SHORT 	= 1000;
	
	public static int MEDIUM 	= 1500;

	public static int LONG 		= 3000;
	
	public static void simulateTime ( int millis ) {
		assert millis > 0;
		try {
			Thread.sleep( millis  );
		} catch (InterruptedException e) {}
	}
	
	public static void simulateTimeRandom ( int millis ) {
		assert millis > 0;
		try {
			Thread.sleep( random.nextInt(millis) );
		} catch (InterruptedException e) {}
	}
	
	public static void simulateTimeMedio ( int millis ) {
		assert millis > 0;
		try {
			Thread.sleep( (millis + random.nextInt(millis)) / 2 );
		} catch (InterruptedException e) {}
	}
	
	

}
