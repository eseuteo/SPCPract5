package lab5ex4;

import java.util.concurrent.Semaphore;

public class Bridge {
	private Semaphore[] enteringSemaphore = new Semaphore[2];
	private Semaphore[] mutex = new Semaphore[2];
	private int[] headingCounter = new int[2];

	public Bridge() {
		for (int i = 0; i < 2; i++) {
			enteringSemaphore[i] = new Semaphore(1, true);
			mutex[i] = new Semaphore(1, true);
		}
	}

	public void enterBridge(Direction direction) throws InterruptedException {
		int thisDirection = direction == Direction.Left ? 0 : 1;
		int antiDirection = (thisDirection + 1) % 2;
		if (headingCounter[thisDirection] == 0){
			enteringSemaphore[thisDirection].acquire();
			enteringSemaphore[antiDirection].acquire();
		}
		mutex[thisDirection].acquire();
		headingCounter[thisDirection]++;
		System.out.println("Car heading " + direction + " entered\nHeading Left: " + headingCounter[0] + " Heading Right: " + headingCounter[1]);
		mutex[thisDirection].release();
	}

	public void exitBridge(Direction direction) throws InterruptedException {
		int thisDirection = direction == Direction.Left ? 0 : 1;
		int antiDirection = (thisDirection + 1) % 2;
		mutex[thisDirection].acquire();
		headingCounter[thisDirection]--;
		if (headingCounter[thisDirection] == 0){
			enteringSemaphore[antiDirection].release();
			enteringSemaphore[thisDirection].release();
		}
		System.out.println("Car heading " + direction + " exited\nHeading Left: " + headingCounter[0] + " Heading Right: " + headingCounter[1]);
		mutex[thisDirection].release();
	}
}
