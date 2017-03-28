public class RedGiant extends Star {
	
	public RedGiant(String name, double mass, double size) {
		super(name, mass, size);
	}

	public boolean isSuperGiant() {
		if (getMass() > 100 && getSize() > 100) {
			return true;
		}
		return false;
	}

	public boolean isBlackHole() {
		return isSuperGiant();
	}

	public String toString() {
		String str = "< " + getName() + " >";
		str += ": A " + (isSuperGiant() == true ? "SuperGiant" : "RedGiant") + " with mass = < " + getMass() + " > KG; ";
		str += "Size = < " + getSize() + " > miles"; 
		return str;
	}

	public static void main(String[] args) {
		Star sg = new RedGiant("SG", 200, 200);
		System.out.println(sg);
	}
}