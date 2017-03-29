import java.util.Comparator;

public class CelestialDatabase {
	private List<Star> list;

	public CelestialDatabase(String type) {
		if (type.equals("array")) {
			list = new ArrayList<Star>();
		} else if (type.equals("linked")) {
			list = new LinkedList<Star>();
		}
	}

	public boolean add(Star s) {
		return list.add(s);
	}

	public Star find(String name) {
		for (int i = 0; i < list.size(); i++) {
			Star star = list.get(i);
			if (star.getName().contains(name)) {
				return star;
			}
			return null;
		}
		return null;
	}

	public Star findSun() {
		for (int i = 0; i < list.size(); i++) {
			Star star = list.get(i);
			if (star instanceof Sequence) {
				Sequence seq = (Sequence)star;
				if (seq.isSun() == true) {
					return star;
				}
			}
		}
		return null;
	}

	public Star remove(int index) {
		return list.remove(index);
	}

	public Star get(int index) {
		return list.get(index);
	}

	public void sort() {
		list.sort(true);
	}

	public Star[] getStarsByType(String type) {
		ArrayList<Star> starList = new ArrayList<Star>();
		if (type.equals("sequence")) {
			for (int i = 0; i < list.size(); i++) {
				Star star = list.get(i);
				if (star instanceof Sequence) {
					starList.add(star);
				}
			}
		} else if (type.equals("redgiant")) {
			for (int i = 0; i < list.size(); i++) {
				Star star = list.get(i);
				if (star instanceof RedGiant) {
					starList.add(star);
				}
			}
		} else if (type.equals("whitedwarf")) {
			for (int i = 0; i < list.size(); i++) {
				Star star = list.get(i);
				if (star instanceof WhiteDwarf) {
					starList.add(star);
				}
			}
		}
		Star[] starArray = new Star[starList.size()];
		for (int i = 0; i < starList.size(); i++) {
			starArray[i] = starList.get(i);
		}
		return starArray;
	}

	public Star getHeaviestStar() {
		if (list.isEmpty()) {
			return null;
		}
		double maxMass = 0;
		Star heaviestStar = null;
		for (int i = 0; i < list.size(); i++) {
			Star star = list.get(i);
			if (star.getMass() > maxMass) {
				maxMass = star.getMass();
				heaviestStar = star;
			}
		}
		return heaviestStar;
	}

	public Star getHeaviestRedGiant() {
		if (list.isEmpty()) {
			return null;
		}
		double maxMass = 0;
		Star heaviestRedGiant = null;
		for (int i = 0; i < list.size(); i++) {
			Star star = list.get(i);
			if (star instanceof RedGiant) {
				if (star.getMass() > maxMass) {
					maxMass = star.getMass();
					heaviestRedGiant = star;
				}
			}
		}
		return heaviestRedGiant;
	}

	public Star getLargestStar() {
		if (list.isEmpty()) {
			return null;
		}
		double maxSize = 0;
		Star largestStar = null;
		for (int i = 0; i < list.size(); i++) {
			Star star = list.get(i);
			if (star.getSize() > maxSize) {
				maxSize = star.getSize();
				largestStar = star;
			}
		}
		return largestStar;
	}

	public Star[] listBlackHoles() {
		ArrayList<Star> starList = new ArrayList<Star>();
		for (int i = 0; i < list.size(); i++) {
			Star star = list.get(i);
			if (star.isBlackHole() == true) {
				starList.add(star);
			}
		}
		
		if (starList.size() == 0) {
			return null;
		}

		Star[] array = new Star[starList.size()];
		
		for (int i = 0; i < starList.size(); i++) {
			array[i] = starList.get(i);
		}
		
		return array;
	}

	public Star[] getTopKheaviestStar(int k) {
		if (list.isEmpty()) {
			return null;
		}
		list.sort(false);
		/* in case number of stars is smaller than k, use actual length */
		if (list.size() < k) {
			k = list.size();
		}
		Star[] array = new Star[k];
		for (int i = 0; i < k; i++) {
			array[i] = list.get(i);
		}
		return array;
	}

	public Star[] getTopKLargestStar(int k) {
		if (list.isEmpty()) {
			return null;
		}
		SizeComparator comp = new SizeComparator();
		list.sort(comp);
		/* in case number of stars is smaller than k, use actual length */
		if (list.size() < k) {
			k = list.size();
		}
		Star[] array = new Star[k];
		for (int i = 0; i < k; i++) {
			array[i] = list.get(i);
		}
		return array;
	}

	public int[] countStars() {
		int numSequence = 0, numRedGiant = 0, numWhiteDwarf = 0;
		for (int i = 0; i < list.size(); i++) {
			Star star = list.get(i);
			if (star instanceof RedGiant) {
				numRedGiant++;
			} else if (star instanceof Sequence) {
				numSequence++;
			} else if (star instanceof WhiteDwarf) {
				numWhiteDwarf++;
			}
		}
		int[] ans = new int[3];
		ans[0] = numSequence;
		ans[1] = numRedGiant;
		ans[2] = numWhiteDwarf;
		return ans;
	}

	/* print the database */
	public void display() {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	public static void main(String[] args) {
		Star sun = new Sequence("Sun", 2, 430);
		Star sg = new RedGiant("SG", 200, 200);
		Star wd = new WhiteDwarf("WD", 100, 10);
		Star seq = new Sequence("Seq", 20, 4130);
		Star rg = new RedGiant("RG", 23, 2900);
		Star wd2 = new WhiteDwarf("WD2", 100, 100);

		CelestialDatabase db = new CelestialDatabase("array");

		db.list.add(sun);
		db.list.add(sg);
		db.list.add(wd);
		db.list.add(seq);
		db.list.add(rg);
		db.list.add(wd2);
		db.remove(4);
		db.display();
		System.out.println("findSun");
		System.out.println(db.findSun());
		
		System.out.println("Sort base on mass...");
		db.sort();
		db.display();
		
		
		System.out.println("getStarsByType");
		Star[] a = db.getStarsByType("redgiant");
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
		
		System.out.println(db.getHeaviestStar());
		System.out.println(db.getLargestStar());
		System.out.println(db.getHeaviestRedGiant());

		System.out.println("listBlackHoles");
		Star[] b = db.listBlackHoles();
		if (b != null) {
			for (int i = 0; i < b.length; i++) {
				System.out.println(b[i]);
			}
		}

		System.out.println("getTop10heaviestStar");
		Star[] c = db.getTopKheaviestStar(10);
		if (c != null) {
			for (int i = 0; i < c.length; i++) {
				System.out.println(c[i]);
			}
		}

		System.out.println("getTop10LargestStar");
		Star[] d = db.getTopKLargestStar(10);
		if (d != null) {
			for (int i = 0; i < d.length; i++) {
				System.out.println(d[i]);
			}
		}

		System.out.println("countStars");
		int[] count = db.countStars();
		for(int i = 0; i < count.length; i++) {
			System.out.print(count[i] + "\t");
		}
		System.out.println();
	}

}

/* a class implementing Comparator with decreasing size */
class SizeComparator implements Comparator<Star> {
	public int compare(Star s1, Star s2) {
		if (s1.getSize() < s2.getSize()) {
			return 1;
		} else if (s1.getSize() > s2.getSize()) {
			return -1;
		} else {
			return 0;
		}
	}
}