public class LinkedList<T extends Comparable<T>> implements List<T> {
	private Node head, tail;
	private int size;

	public LinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	public boolean add(T element) {
		if (element == null) {
			return false;
		}
		Node newNode = new Node(element);
		if (head == null) {
			head = newNode;
		} else {
			tail.setNext(newNode);
		}
		tail = newNode;
		size++;
		return true;
	}

	public boolean add(int index, T element) {
		if (element == null || index < 0 || index >= size) {
			return false;
		}
		Node newNode = new Node(element);
		if (head == null) {
			head = newNode;
		} else {
			if (index == 0) {
				newNode.setNext(head);
				head = newNode;
			} else {
				Node current = head.getNext(), previous = head;
				while (index > 1 || (current != null && current.getNext() != null)) {
					current = current.getNext();
					previous = previous.getNext();
					index--;
				}
				newNode.setNext(current);
				previous.setNext(newNode);
			}
		}
		size++;
		return true;
	}

	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	public boolean contains(T element) {
		Node current = head;
		while (current != null) {
			if (current.getData().equals(element)) {
				return true;
			}
			current = current.getNext();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public T get(int index) {
		if (index < 0 || index >= size) {
			return null;
		} else {
			Node current = head;
			while (index > 0) {
				current = current.getNext();
				index--;
			}
			return (T)current.getData();
		}
	}

	public int indexOf(T element) {
		if (element != null) {
			int index = 0;
			Node current = head;
			while (current != null) {
				if (current.getData().equals(element)) {
					return index;
				}
				current = current.getNext();
				index++;
			}
		}
		return -1;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int lastIndexOf(T element) {
		if (element != null) {
			int index = -1, tempIndex = 0;
			Node current = head;
			while (current != null) {
				if (current.getData().equals(element)) {
					index = tempIndex;
				}
				current = current.getNext();
				tempIndex++;
			}
			return index;
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	public T set(int index, T element) {
		if (element == null || index < 0 || index >= size) {
			return null;
		} else {
			Node current = head;
			while (index > 0) {
				current = current.getNext();
				index--;
			}
			T oldElement = (T)current.getData();
			current.setData(element);
			return oldElement;
		}
	}

	public int size() {
		return size;
	}

	public void sort(boolean order) {
		head = mergeSort(head, order);
		Node current = head;
		while (current != null && current.getNext() != null) {
			current = current.getNext();
		}
		tail = current;
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

	@SuppressWarnings("unchecked")
	public T remove(int index) {
		if (index < 0 || index >= size) {
			return null;
		} else {
			T element;
			if (index == 0) {
				element = (T)head.getData();
				head = head.getNext();
			} else if (index == size - 1) {
				Node current = head;
				while (current != null && current.getNext() != tail) {
					current = current.getNext();
				}
				element = (T)tail.getData();
				tail = current;
				tail.setNext(null);
			} else {
				Node current = head.getNext(), previous = head;
				while (index > 1) {
					current = current.getNext();
					previous = previous.getNext();
					index--;
				}
				element = (T)current.getData();
				previous.setNext(current.getNext());
			}
		size--;
		return element;
		}
	}

	/* print the list */
	public void display() {
		Node current = head;
		while (current != null) {
			System.out.print(current.getData() + "\t");
			current = current.getNext();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(0,3);
		list.add(1);
		list.display();
		//System.out.println(list.get(2));
		//System.out.println(list.lastIndexOf(1));
		list.set(1, 4);
		list.display();
		//list.sort(true);
		//list.clear();
		/*
		list.display();
		for (int i = 0; i < 100; i++) {
			list.add((int)Math.floor(Math.random() * 100));
		}
		list.display();
		System.out.println("before sort");
		list.sort(false);
		list.display();
		*/
		ArrayList<String> l = new ArrayList<String>();
		l.add("hi");
		l.add("bye");
		l.add("hello");
		l.add("okay");
		l.add("chaoran");
		l.display();
		l.remove("bye");
		l.display();
		l.sort(false);
		l.display();
		l.set(1, "abc");
		l.display();
		l.clear();
		l.display();
	}

	private Node mergeSort(Node head, boolean order) {
		if (head != null && head.getNext() != null) {
            Node fast = head.getNext().getNext(), slow = head;
            while (fast != null && fast.getNext() != null) {
                fast = fast.getNext().getNext();
                slow = slow.getNext();
            }
            Node l2 = mergeSort(slow.getNext(), order);
            slow.setNext(null);
            Node l1 = mergeSort(head, order);
            return merge(l1, l2, order);
        } else {
            return head;
        }
	}

	@SuppressWarnings("unchecked")
	private Node merge(Node l1, Node l2, boolean order) {
		Node dummy = new Node(head.getData()), current = dummy;
		if (order == true) {
			while (l1 != null || l2 != null) {
				if (l2 == null || (l1 != null && ((T)l1.getData()).compareTo(((T)l2.getData())) <= 0)) {
					current.setNext(new Node((T)l1.getData()));
					l1 = l1.getNext();
				} else if (l1 == null || (l2 != null && ((T)l1.getData()).compareTo(((T)l2.getData())) > 0)) {
					current.setNext(new Node((T)l2.getData()));
					l2 = l2.getNext();
				}
				current = current.getNext();
			}
		} else {
			while (l1 != null || l2 != null) {
				if (l2 == null || (l1 != null && ((T)l1.getData()).compareTo(((T)l2.getData())) >= 0)) {
					current.setNext(new Node((T)l1.getData()));
					l1 = l1.getNext();
				} else if (l1 == null || (l2 != null && ((T)l1.getData()).compareTo(((T)l2.getData())) < 0)) {
					current.setNext(new Node((T)l2.getData()));
					l2 = l2.getNext();
				}
				current = current.getNext();
			}
		}		
		return dummy.getNext();
	}
}