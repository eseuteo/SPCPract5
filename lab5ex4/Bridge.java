package lab5ex4;

import java.util.concurrent.Semaphore;

public class Bridge {
	private int[] numCars;
	private Semaphore bridge;
	private Semaphore[] mutex = new Semaphore[2];
	
	/**
	 * Class constructor.
	 */
	public Bridge() {
		numCars = new int[2];
		bridge = new Semaphore(1, true);
		for (int i = 0; i < 2; i++) {
			mutex[i] = new Semaphore(1, true);
		}
	}

	/**
	 * A Car enters the Bridge.
	 * 
	 * @param direction		Direction where is heading the Car
	 */
	public void enterBridge(Direction direction) throws InterruptedException {
		int thisDir = direction == Direction.Left ? 0 : 1;
		
		mutex[thisDir].acquire();
		
		if (numCars[thisDir] == 0)
			bridge.acquire();
		numCars[thisDir]++;
		report("enter", direction);
		
		mutex[thisDir].release();
	}

	/**
	 * A Car exits the Bridge
	 * 
	 * @param direction		Direction where is heading the Car
	 */
	public void exitBridge(Direction direction) throws InterruptedException {
		int thisDir = direction == Direction.Left ? 0 : 1;
		
		mutex[thisDir].acquire();
		
		numCars[thisDir]--;
		report("exit", direction);
		if (numCars[thisDir] == 0)
			bridge.release();
		
		mutex[thisDir].release();
	}
	
	/**
	 * Prints on screen information about the entrance or exit of a Car
	 * 
	 * @param string		Description of the action done by the Car (enter or exit)
	 * @param direction		Direction where is heading the Car
	 */
	private void report(String string, Direction direction) {
		System.out.println("Car heading " + direction + " " + string + "ed \nHeading Left: " + numCars[0]
				+ " Heading Right: " + numCars[1]);	
	}
}
