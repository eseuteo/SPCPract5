package lab5ex2;

import java.util.Random;

public class Packager extends Thread {
	private ConveyorBelt cb;
	private int id;
	
	public Packager(int id, ConveyorBelt cb) {
		this.id = id;
		this.cb = cb;
	}

	@Override
	public void run() {
		Random rnd = new Random();
		while(true){
			try {
				cb.take(id);
				sleep(rnd.nextInt(500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
