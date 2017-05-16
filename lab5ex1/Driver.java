package lab5ex1;

public class Driver {
	public static void main(String[] args) {
		Buffer b = new Buffer();
		// 4 threads
		Sensor[] sensors = new Sensor[3];
		for (int i=0; i<3; i++){
			sensors[i] = new Sensor(b, i);
			sensors[i].start();
		}
		Worker w0 = new Worker(b);
		w0.start();
	}
}
