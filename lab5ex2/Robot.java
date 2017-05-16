package lab5ex2;

import java.util.Random;

public class Robot extends Thread {
	private ConveyorBelt cb;
	
	public Robot(ConveyorBelt cb) {
		this.cb = cb;
	}

	@Override
	public void run() {
		Random rnd = new Random();
		while(true){
			try {
				sleep(rnd.nextInt(1000));
				int product = rnd.nextInt(3) + 1;
				cb.put(product);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
