package lab5ex2;

import java.util.concurrent.Semaphore;

public class ConveyorBelt {
	private final int SIZE = 10;
	private int[] belt = new int[SIZE];
	Semaphore space = new Semaphore(SIZE);
	Semaphore objects[] = new Semaphore[3];
	
	public ConveyorBelt(){
		for (int i = 0; i < 3; i++){
			objects[i] = new Semaphore(0);
		}
	}

	public void put(int product) throws InterruptedException {
		space.acquire();
		int beltPointer = nextFreePosition();
		System.out.println("Robot putting " + product + " at position " + beltPointer);
		belt[beltPointer] = product;
		objects[product-1].release();
	}

	public void take(int id) throws InterruptedException {
		objects[id-1].acquire();
		int i = findObject(id);
		System.out.println("Packager " + id + " taking object from position " + i);
		belt[i] = 0;
		space.release();
	}

	private int findObject(int id) {
		boolean found = false;
		int res = 0;
		while (!found && res <SIZE){
			found = belt[res] == id;
			res++;
		}
		
		return res-1;
	}

	private int nextFreePosition() {
		int res = 0;
		boolean found = false;
		while (!found && res < SIZE) {
			found = belt[res] == 0;
			res++;
		}
		
		return found ? res-1 : -1;
	}
}
