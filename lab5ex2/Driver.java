package lab5ex2;

public class Driver {
	public static void main(String[] args) {
		ConveyorBelt cb = new ConveyorBelt();
		Robot r = new Robot(cb);
		r.start();
		for (int i=0; i<3; i++){
			Packager p = new Packager((i+1), cb);
			p.start();
		}
	}
}
