package winter352_Assignment3;
//NAME : ALI SHER 
//STUDENT ID : 40255236

//NAME : Olgerta Gjyriqi
//student id : 40251045

public class ExpandableArray<K extends Comparable<K>, V> {
	
	
	private Entry<K, V>[] Array; 
	private int size; 
	private static final int DefaultCapacity = 10; 

	
	public ExpandableArray() {
		this(DefaultCapacity);
	}

	public ExpandableArray(int starting_size) {
		this.Array = (Entry<K, V>[]) new Entry[starting_size];
		this.size = 0;
		for (int i = 0; i < size; i++) {
			Array[i] = null;
		} 
	}

	
	public int size() {
		return size;
	}

	public int length() {
		return size;
	}

	

	public int Capacity() {
		return Array.length;
	}

	
	public boolean isEmpty() {
		return size <= 0;
	}

	
	public void clear() {
		for (int i = 0; i < size; i++)
			Array[i] = null;
		size = 0;
	}

	public void ensureCapacity() {
		if (size == Array.length-1) { // this is to ensure that 
			Entry<K, V>[] newQueueArray = (Entry<K, V>[]) new Entry[2 * (2 * size + 2)];
			System.arraycopy(Array, 0, newQueueArray, 0, size);
			Array = (Entry<K, V>[]) newQueueArray;
		}
	}

	
	public void swapIndex(int index1, int index2) {
		if (index1 < 0 || index1 >= Array.length || index2 < 0 || index2 >= Array.length) {
			throw new IndexOutOfBoundsException(
					"Index out of range for index1: "+ index1+" and index 2: "+ index2);
		}

		Entry<K, V> temp = Array[index1];
		Array[index1] = Array[index2];
		Array[index2] = temp;
		
		Array[index2].setIndex(index2);
		Array[index1].setIndex(index1);

	}

	

	public Entry<K, V> get(int index) {
		if (index < 0 || index >= Array.length)
			return null;
		return Array[index];
	}  

	

	public void set(int index, Entry<K, V> arg) {
		if (arg == null) {
			throw new IllegalArgumentException("arg can't be used for this type of removal");
		}
		if (index < 0 || index >= Array.length || arg == null)
			return;
		if (Array[index] == null) {
			size++;
		}  

		Array[index] = arg;
		Array[index].setIndex(index);
	}

	
	public void setNew(int index, K newK, V data) {
		if (index < 0 || index >= Array.length)
			return;
		Array[index] = new Entry<K, V>(newK, data);
		size++;
		Array[index].setIndex(index);

	}

	
	public Entry<K, V> remove(int index) {
		if (index < 0 || index >= Array.length)
			return null; 

		Entry<K, V> temp = new Entry<K, V>(Array[index].getKey(), Array[index].getValue());

		Array[index] = null;

		size--;

		return temp;
	}

	
	public void printArray(int size) {
		if (isEmpty()) {
			System.out.println("{}");
			return;
		}

		System.out.print("{ ");
		for (int i = 0; i < size; i++) {
			if (i > 0) {
				System.out.print(" | ");
			}
			if (Array[i] != null) {
				System.out.print(Array[i].print());
			} else {
				System.out.print("( , )");
			}
		}
		System.out.println(" }");
	}




}
