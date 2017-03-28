public static class WhiteDwarf {

	public WhiteDwarf(String name, double mass, double size) {
		super(name, mass, size);
	}

	public boolean isBlackHole() {
		return false;
	}

	public String toString() {
		String str = "< " + getName() + " >";
		str += ": A WhiteDwarf with mass = < " + getMass() + " > KG; ";
		str += "Size = < " + getSize() + " > miles"; 
		return str;
	}
}