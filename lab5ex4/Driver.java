package lab5ex4;

import java.util.Random;

public class Driver {
	final static int NUMCARS = 100;
	
	public static void main(String[] args) {
		Bridge bridge = new Bridge();
		Car car[] = new Car[NUMCARS];
		int aux;
		Random rng = new Random();
		
		for (int i=0; i<NUMCARS; i++){
			aux = rng.nextInt(2);
			car[i] = aux == 0 ? new Car(Direction.Left, bridge) : new Car(Direction.Right, bridge);
			car[i].start();
		}
	}
}
