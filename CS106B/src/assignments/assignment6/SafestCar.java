package assignments.assignment6;

public class SafestCar implements Comparable<SafestCar>{
	
	private String name;
	private int weight;
	private int numAirbgs;

	SafestCar(String name, int weight, int numAirbags) {
		this.name = name;
		this.weight = weight;
		this.numAirbgs = numAirbags;
	}
	
	public String toString() {
		return "[" + name + ", " + weight + ", " + numAirbgs + "]";
	}
	
	public static void main(String[] args) {
		SafestCar car1 = new SafestCar("Audi", 1400, 8);
		SafestCar car2 = new SafestCar("BMW", 1200, 8);
		SafestCar car3 = new SafestCar("Ford", 1800, 6);
		
		SafestCar[] carArray = new SafestCar[3];
		
		carArray[0] = car1;
		carArray[1] = car2;
		carArray[2] = car3;
		
		System.out.println(GenericMethods.getMax(carArray));
		
	}

	@Override
	public int compareTo(SafestCar other) {
		if(this.numAirbgs > other.numAirbgs) return 1;
		else if(this.numAirbgs < other.numAirbgs) return -1;
		else return this.weight - other.weight;
	}

}
