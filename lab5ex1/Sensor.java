package lab5ex1;

import java.util.Random;

public class Sensor extends Thread {
	Buffer myBuffer;
	int id;

	public Sensor(Buffer myBuffer, int id) {
		this.myBuffer = myBuffer;
		this.id = id;
	}

	@Override
	public void run() {
		Random r = new Random();
		while (true) {
			try {
				sleep(r.nextInt(100));
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			int data = r.nextInt(10);
			try {
				myBuffer.fillData(data, id);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
