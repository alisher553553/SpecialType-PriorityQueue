package winter352_Assignment3;
//NAME : ALI SHER 
//STUDENT ID : 40255236

//NAME : Olgerta Gjyriqi
//student id : 40251045

public class Entry <K extends Comparable<K>, V> implements Comparable<Entry<K, V>>{
	private K key; 
	private V value; 
	private int index;
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	
	
	public Entry(K newKValue, V newVValue) {
		this.value = newVValue;
		this.key = newKValue;
	}

	
	public K getKey() {
		return key;
	}

        
	public V getValue() {
		return value;
	}

	
	public void setKey(K newKValue) {
		key = newKValue;
	}

	public void setValue(V newValue) {
		value = newValue;
	}

	

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		try {
			Entry<?, ?> other = (Entry<?, ?>) obj;
			return key.equals(other.key) && (value == null ? other.value == null : value.equals(other.value));
		} catch (ClassCastException e) {
			return false; 
		}
	}

	

	public String print() {
		return "(" + key.toString() + ',' + value.toString() + ")";
	}

	
	public int compareTo(Entry<K, V> other) {
		return this.key.compareTo(other.getKey());
	}
}
