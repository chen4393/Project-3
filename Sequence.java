public class Sequence extends Star {
	
	public Sequence(String name, double mass, double size) {
		super(name, mass, size);
	}

	public boolean isBlackHole() {
		if (getMass() > 1000 && getSize() < 50) {
			return true;
		}
		return false;
	}

	public String toString() {
		String str = "< " + getName() + " >";
		str += ": A Sequence Star with mass = < " + getMass() + " > KG; ";
		str += "Size = < " + getSize() + " > miles"; 
		return str;
	}

	public boolean isSun() {
		if (getMass() == 2 && getSize() == 430) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		Sequence sun = new Sequence("Sun", 20000, 10);
		System.out.println(sun);
	}
}