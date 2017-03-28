public class ArrayList<T extends Comparable<T>> implements List<T> {
	private T[] a;
	private int size = 0;	// actual length

	@SuppressWarnings("unchecked")
	public ArrayList() {
		int capacity = 2;
		a = (T[]) new Comparable[capacity];
		//System.out.println(a.length);
	}

	public boolean add(T element) {
		int capacity = a.length;	// physical length
		if (element != null) {
			if (size >= capacity) {
				a = grow();	// grow and copy
			}
			a[size++] = element;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean add(int index, T element) {
		if (element == null || index < 0 || index >= size) {
			return false;
		} else {
			int capacity = a.length;	// physical length
			if (size >= capacity) {
				a = grow();
			}			
			/* shift the element currently at that position and any subsequent elements */
			for (int i = size - 1; i >= index; i--) {
				a[i + 1] = a[i];
			}
			a[index] = element;
			size++;
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	public void clear() {
		int capacity = 2;
		a = (T[]) new Comparable[capacity];
		//System.out.println(a.length);
		size = 0;
	}

	public boolean contains(T element) {
		for (int i = 0; i < size; i++) {
			if (a[i].equals(element)) {
				return true;
			}
		}
		return false;
	}

	public T get(int index) {
		if (index < 0 || index >= size) {
			return null;
		} else {
			return a[index];
		}
	}

	public int indexOf(T element) {
		if (element != null) {
			for (int i = 0; i < size; i++) {
				if (a[i].equals(element)) {
					return i;
				}
			}
		}
		return -1;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int lastIndexOf(T element) {
		if (element != null) {
			for (int i = size - 1; i >= 0; i--) {
				if (a[i].equals(element)) {
					return i;
				}
			}
		}
		return -1;
	}

	public T set(int index, T element) {
		if (element == null || index < 0 || index >= size) {
			return null;
		} else {
			T oldElement = a[index];
			a[index] = element;
			return oldElement;
		}
	}

	public int size() {
		return size;
	}

	public void sort(boolean order) {
		mergeSort(a, 0, size - 1, order);
	}

	public boolean remove(T element) {
		int index = indexOf(element);	// first instance index
		System.out.println("first instance index: " + index);
		if (index != -1) {
			remove(index);
			return true;
		} else {
			return false;
		}
	}

	public T remove(int index) {
		if (index < 0 || index >= size) {
			return null;
		} else {
			T element = a[index];
			size--;
			for (int i = index; i < size; i++) {
				a[i] = a[i + 1];
			}
			//a[index] = null;
			return element;
		}
	}

	/* print the list */
	public void display() {
		for (int i = 0; i < size; i++) {
			System.out.print(a[i] + "\t");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(1);
		list.add(3);
		list.add(2, 2);
		//list.clear();
		list.add(5);
		list.add(4);
		list.display();
		//list.sort(true);
		list.remove((Integer)2);
		list.display();
		list.clear();
		System.out.println(list.size());
		/*
		for (int i = 0; i < 100; i++) {
			list.add((int)Math.floor(Math.random() * 100));
		}
		list.display();
		list.sort(true);
		System.out.println("Sorting");
		list.display();
		*/
		ArrayList<String> l = new ArrayList<String>();
		l.add("hi");
		l.add("bye");
		l.add("hello");
		l.add("okay");
		l.add("chaoran");
		l.display();
		l.remove(1);
		l.display();
		l.sort(false);
		l.display();
		l.set(1, "abc");
		l.display();
		l.clear();
		l.display();
	}

	@SuppressWarnings("unchecked")
	/* twice the length of the original array and copy everything to the new array */
	private T[] grow() {
		int capacity = a.length;	// physical length
		capacity *= 2;	// grow
		T[] temp = (T[]) new Comparable[capacity];
		for (int i = 0; i < size; i++) {
			temp[i] = a[i];
		}
		return temp;
	}

	public void mergeSort(T[] arr, int from, int to, boolean order) {
		if (from < to) {
			/* Split the original array into two sub-arrays */
			int mid = (from + to) / 2;
			/* Sort these two sub-array separately */
			mergeSort(arr, from, mid, order);
			mergeSort(arr, mid + 1, to, order);
			/* Merge the two sorted sub-array */
			merge(arr, from, mid, to, order);
		}
	}

	@SuppressWarnings("unchecked")
	private void merge(T[] arr, int from, int mid, int to, boolean order) {
		int n = to - from + 1;
		int n1 = mid - from + 1;
		int n2 = to - mid;
		/* Create two sub-arrays according to the partition point
		 * and copy the contents into them */
		T[] arr1 = (T[]) new Comparable[n1];
		T[] arr2 = (T[]) new Comparable[n2];
		for (int i = 0; i < n1; i++) {
			arr1[i] = arr[from + i];
		}
		for (int j = 0; j < n2; j++) {
			arr2[j] = arr[mid + 1 + j];
		}

		int i = 0, j = 0;
		/* increasing when order is true, decreasing when false */
        if (order == true) {
			for (int k = from; k <= to; k++) {
				/* if arr2 is finished going through OR the element in arr1 is less than arr2,
				 * use the element in arr1 */
				if (j >= arr2.length || (i < arr1.length && arr1[i].compareTo(arr2[j]) <= 0)) {
				    arr[k] = arr1[i];
				    i++;
				}
				/* if arr1 is finished going through OR the element in arr1 is greater than arr2,
				 * use the element in arr2 */
				else if (i >= arr1.length || (j < arr2.length && arr1[i].compareTo(arr2[j]) > 0)){
					arr[k] = arr2[j];
					j++;
				}
				//System.out.println(k + ": " + arr[k]);
			}
        } else {
			for (int k = from; k <= to; k++) {
				/* if arr2 is finished going through OR the element in arr1 is greater than arr2,
				 * use the element in arr1 */
				if (j >= arr2.length || (i < arr1.length && arr1[i].compareTo(arr2[j]) >= 0)) {
				    arr[k] = arr1[i];
				    i++;
				}
				/* if arr1 is finished going through OR the element in arr1 is less than arr2,
				 * use the element in arr2 */
				else if (i >= arr1.length || (j < arr2.length && arr1[i].compareTo(arr2[j]) < 0)){
					arr[k] = arr2[j];
					j++;
				}
			}
		}
	}
}