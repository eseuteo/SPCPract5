package lab5ex4;

import java.util.Random;

public class Car extends Thread {
	private Direction direction;
	private Bridge bridge;
	
	public Car(Direction direction, Bridge bridge){
		this.direction = direction;
		this.bridge = bridge;
	}
	
	@Override
	public void run() {
		Random rng = new Random();
		try {
			sleep(rng.nextInt(1000));
			bridge.enterBridge(direction);
			sleep(1000);
			bridge.exitBridge(direction);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
