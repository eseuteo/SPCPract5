package lab5ex1;

import java.util.concurrent.Semaphore;

public class Buffer {
	static int[] buffer = new int[3];
	int counter = 0;
	StringBuilder sb = new StringBuilder();

	Semaphore thereIsSpace = new Semaphore(0, true);
	Semaphore thereAreData = new Semaphore(0, true);
	Semaphore mutEx = new Semaphore(1, true);

	public void useData() throws InterruptedException {
		thereAreData.acquire();
		sb = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			sb.append(buffer[i]);
			if (i < 2) {
				sb.append(", ");
			}
		}
		System.out.println(sb.toString());
		mutEx.acquire();
		buffer = new int[3];
		counter = 0;
		mutEx.release();
		for (int i = 0; i < 3; i++) {
			thereIsSpace.release();
		}
	}

	public void fillData(int data, int id) throws InterruptedException {
		mutEx.acquire();
		buffer[id] = data;
		counter++;
		System.out.println("Sensor " + id + " puts data " + data);
		if (counter == 3) {
			thereAreData.release();
		}
		mutEx.release();
		thereIsSpace.acquire();
	}

}
