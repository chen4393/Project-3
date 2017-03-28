public abstract class Star implements Comparable<Star> {
	private String name;
	private double mass;  /* x 10^30 KGs i.e. 1 unit of = 10^30 KGs */
	private double size;  /* x 1000 miles. 1 unit of size = 1000 miles. */

	public Star(String name, double mass, double size) {
		this.name = name;
		this.mass = mass;
		this.size = size;
	}

	/* Abstract method: Return true if star can become blackhole. */
	abstract public boolean isBlackHole();

	/* Abstract method: Return string description of the star. */
	abstract public String toString();

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public double getMass() {
		return this.mass;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public double getSize() {
		return this.size;
	}

	public boolean equals(Star star) {
		return (this.name.equals(star.getName()) 
			&& this.mass == star.getMass() 
			&& this.size == star.getSize());
	}

	/*
	 * Compare two star by their mass. 
	 */
	public int compareTo(Star star) {
		if (this.mass > star.getMass())
			return 1;
		if (this.mass < star.getMass())
			return -1;
		else return 0;
	}
}
