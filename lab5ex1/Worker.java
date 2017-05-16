package lab5ex1;

import java.util.Random;

public class Worker extends Thread {
	Buffer myBuffer;

	public Worker(Buffer myBuffer) {
		this.myBuffer = myBuffer;
	}

	@Override
	public void run() {
		Random rng = new Random();
		while (true) {
			try {
				sleep(rng.nextInt(200));
				myBuffer.useData();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("_");
		}
	}
}
