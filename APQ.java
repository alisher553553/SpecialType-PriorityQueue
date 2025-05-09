package winter352_Assignment3;
//NAME : ALI SHER 
//STUDENT ID : 40255236

//NAME : Olgerta Gjyriqi
//student id : 40251045

public class APQ <K extends Comparable<K>, V>{
	
	private ExpandableArray<K, V> expandingArray;
	private static final int DefaultCapacity = 10;
	private int last_added = 0;
	private int size = 0;

	
	private HeapType currentHeapType = HeapType.Max;

	
	public static enum HeapType {
		Max, Min
	}

	
	public APQ() {
		this(HeapType.Max, DefaultCapacity); 
	} 
	public APQ(HeapType heapType) { 
		this(heapType, DefaultCapacity);
	}

	public APQ(int startingSize) { 
		this(HeapType.Max, startingSize);
	}

	public APQ(HeapType heapType, int startingSize) { // Main Class Constructor
		this.expandingArray = new ExpandableArray<>(startingSize);
		this.currentHeapType = heapType;
	}

	
	public int size() {
		return expandingArray.size();
	}

	public int length() {
		return expandingArray.size();
	}

	public int capacity() {
		return expandingArray.Capacity();
	}

	
	public boolean isEmpty() {
		return expandingArray.isEmpty();
	}

	
	public void clear() {
		expandingArray.clear();
		last_added = 0;
		size = 0;
	}

	
	private void ensureCapacity() {
		expandingArray.ensureCapacity();
	}

	

	private int getParent(int index) {

		if (index < 0 || index > expandingArray.length() - 1) {
			return -1;
		}
		int new_index = (index - 1) / 2;

		if (new_index < 0 || new_index > expandingArray.length() - 1 || expandingArray.get(new_index) == null) {
			return -1;
		} else {
			return new_index;
		}
	}

	
	private int getLeftChild(int index) {
		if (index < 0 || index > expandingArray.length() - 1) {
			return -1;
		}
		int new_index = 2 * index + 1;

		if (new_index < 0 || new_index > expandingArray.length() - 1 || expandingArray.get(new_index) == null) {
			return -1;
		} else {
			return new_index;
		}
	}

	

	private int getRightChild(int index) {
		
		if (index < 0 || index > expandingArray.length() - 1) {
			return -1;
		}

		int new_index = 2 * index + 2;

		if (new_index < 0 || new_index >= expandingArray.length() - 1 || expandingArray.get(new_index) == null) {
			return -1;
		} else {
			return new_index;
		}
	}

	

	private Boolean parentAvailable(int index) {
		if (index < 0 || index > expandingArray.length() - 1) {
			return false;
		}

		int parentIndex = (index - 1) / 2;

		return expandingArray.get(parentIndex) == null;
	}

	

	private Boolean leftChildAvailable(int index) {
		if (index < 0 || index > expandingArray.length() - 1) {
			return false;
		}

		int leftChildIndex = 2 * index + 1;

		return expandingArray.get(leftChildIndex) == null;
	}

	

	private Boolean rightChildAvailable(int index) {
		if (index < 0 || index > expandingArray.length() - 1) {
			return false;
		}

		int rightChildIndex = 2 * index + 2;

		return expandingArray.get(rightChildIndex) == null;
	}

	

	private Boolean hasParent(int index) {
		return (this.getParent(index) >= 0);
	}

	
	private boolean hasLeftChild(int index) {
		return (getLeftChild(index) > 0);
	}


	

	private Boolean hasRightChild(int index) {
		return (getRightChild(index) > 0);
	}

	

	private int linearSearch(K target) {

		for (int i = 0; i < expandingArray.Capacity(); i++) {

			if (expandingArray.get(i) != null && expandingArray.get(i).getKey() == target) {
				return i;
			}
		}
		return -1;
	}

	

	private int linearSearch(Entry<K, V> target) {

		if (target == null)
			return -1;
		for (int i = 0; i < expandingArray.Capacity(); i++) {

			if (expandingArray.get(i) != null)
				if (expandingArray.get(i).equals(target)) {
					return i;
				}
		}
		return -1;
	}

	

	public String state() {
		return currentHeapType.toString();
	}

	

	public void toggle() {

		if (currentHeapType == HeapType.Max) {
			currentHeapType = HeapType.Min;
		} else {
			currentHeapType = HeapType.Max;
		}
		heapSortAndInsert();
	}

	

	private void heapsortAndInsert() {
	    Entry<K, V>[] temp = (Entry<K, V>[]) new Entry[size];
	    int count = 0;

	    // Collect valid entries
	    while (!isEmpty()) {
	        Entry<K, V> removed = removeTop();
	        if (removed != null) {
	            temp[count++] = removed;
	        }
	    }

	    // Reinsert using new heap mode
	    for (int i = 0; i < count; i++) {
	        insert(temp[i].getKey(), temp[i].getValue());
	    }
	}

	


	public Entry<K, V> peekAt(int n) {
	    // Bounds check
	    if (n < 0) {
	        throw new IndexOutOfBoundsException("Index " + n + " is out of bounds.");
	    }

	    // Step 1: Copy non-null entries from expandingArray to temp[]
	    Entry<K, V>[] temp = (Entry<K, V>[]) new Entry[size];
	    int count = 0;

	    for (int i = 0; i < expandingArray.length(); i++) {
	        Entry<K, V> entry = expandingArray.get(i);
	        if (entry != null) {
	            temp[count++] = entry;
	        }
	    }

	    // Step 2: Perform selection sort on valid entries only (0 to count-1)
	    for (int i = 0; i < count - 1; i++) {
	        int selected = i;
	        for (int j = i + 1; j < count; j++) {
	            if (currentHeapType == HeapType.Min) {
	                if (temp[j].getKey().compareTo(temp[selected].getKey()) < 0) {
	                    selected = j;
	                }
	            } else {
	                if (temp[j].getKey().compareTo(temp[selected].getKey()) > 0) {
	                    selected = j;
	                }
	            }
	        }
	        Entry<K, V> tempEntry = temp[i];
	        temp[i] = temp[selected];
	        temp[selected] = tempEntry;
	    }

	    // Step 3: Validate final n and return
	    if (n >= count) {
	        throw new IndexOutOfBoundsException("Index " + n + " is out of bounds.");
	    }
	    return temp[n];
	}


	public Entry<K, V> removeTop() {
	    if (!isEmpty() && last_added >= 0) {
	        Entry<K, V> top = expandingArray.get(0);
	        Entry<K, V> last = expandingArray.get(last_added);

	        // Safety check: avoid calling getKey on null
	        if (top == null || last == null) {
	            return null;
	        }

	        size--;

	        if (size == 0) {
	            expandingArray.remove(0);
	            last_added = 0;
	            return top;
	        } else {
	            expandingArray.swapIndex(0, last_added);
	            expandingArray.remove(last_added);
	            last_added--;
	            downHeap(0);
	            return top;
	        }
	    } else {
	        return null;
	    }
	}
	private void heapSortAndInsert() {
	    Entry<K, V>[] temp = (Entry<K, V>[]) new Entry[size];
	    int count = 0;

	    // Collect valid entries
	    while (!isEmpty()) {
	        Entry<K, V> removed = removeTop();
	        if (removed != null) {
	            temp[count++] = removed;
	        }
	    }

	    // Reinsert using new heap mode
	    for (int i = 0; i < count; i++) {
	        insert(temp[i].getKey(), temp[i].getValue());
	    }
	}


	public void merge(APQ<K, V> otherAPQ) {
	    if (otherAPQ == null || otherAPQ.isEmpty()) {
	        return;
	    }

	    for (int i = 0; i < otherAPQ.expandingArray.length(); i++) {
	        Entry<K, V> entry = otherAPQ.expandingArray.get(i);
	        if (entry != null) {
	            this.insert(entry.getKey(), entry.getValue());
	        }
	    }
	}


	public Entry<K, V> remove(K e) {

		if (e == expandingArray.get(0).getKey()) {
			return removeTop();
		}
		int indexfound = linearSearch(e);
		if (indexfound >= 0 && expandingArray.get(indexfound) != null) {

			if (indexfound == last_added) {
				Entry<K, V> test = expandingArray.get(indexfound);
				expandingArray.remove(last_added);

				if (expandingArray.get(last_added - 1) != null) {
					--last_added;
				} else {
					if (this.hasParent(last_added)) {
						last_added = this.getParent(last_added);
					}
				}

				return test;

			}

			if (expandingArray.get(last_added) != null) {

				Entry<K, V> test = expandingArray.get(indexfound);

				expandingArray.swapIndex(indexfound, last_added);
				expandingArray.remove(last_added);

				if (expandingArray.get(last_added - 1) != null) {
					--last_added;
				} else {
					if (this.hasParent(last_added)) {
						last_added = this.getParent(last_added);
					}
				}
				downHeap(upHeap(downHeap(indexfound)));

				return test;
			}

		}
		return null;

	}

	

	public Entry<K, V> remove(Entry<K, V> e) {

		if (e == null) { // if the target is null
			return null;
		} else { // if it isnt
			if (e.compareTo(expandingArray.get(0)) == 0) { 
				return removeTop();
			} else // if this is not the top element then :
			{
				int indexfound = linearSearch(e);
				if (indexfound >= 0 && expandingArray.get(indexfound) != null) { 
					if (indexfound == last_added) { 
						Entry<K, V> test = expandingArray.get(indexfound);
						expandingArray.remove(last_added);

						if (expandingArray.get(last_added - 1) != null) {
							--last_added;
						} else {
							if (this.hasParent(last_added)) {
								last_added = this.getParent(last_added);
							}
						}
						size--;
						return test;
					} else { 

						if (expandingArray.get(last_added) != null) {
							Entry<K, V> test = expandingArray.get(indexfound);

							expandingArray.swapIndex(indexfound, last_added);
							expandingArray.remove(last_added);

							if (expandingArray.get(last_added - 1) != null) {
								--last_added;
							} else {
								if (this.hasParent(last_added)) {
									last_added = this.getParent(last_added);
								}
							}
							
							downHeap(indexfound); 

							size--;
							return test;
						}
					}
				}
			}
		}
		return null;
	}

	

	public Entry<K, V> insert(K key, V value) {
		ensureCapacity(); 

		if (expandingArray.get(size) == null) { 
			expandingArray.set(size, new Entry<K, V>(key, value)); 
			last_added = size; 
		} else { 
			int current = -1; 
			if (leftChildAvailable(size)) {
				current = getLeftChild(size);
			} else if (rightChildAvailable(size)) {
				current = getRightChild(size);
			}

			if (current >= 0) {
				expandingArray.set(current, new Entry<K, V>(key, value)); 
																				

				last_added = current; 
			}
		}

		size++; 

		if (size > 1)
			upHeap(last_added);

		return expandingArray.get(size - 1); 
	}

	
	public Entry<K, V> top() {
		return expandingArray.get(0);
	}

	

	public K replaceKey(Entry<K, V> e, K newKey) {

		int indexfound = linearSearch(e); // O(n)
		if (indexfound >= 0) {
			if (expandingArray.get(indexfound) != null) {
				K old = expandingArray.get(indexfound).getKey();
				expandingArray.get(indexfound).setKey(newKey);

				downHeap(upHeap(downHeap(indexfound)));
				upHeap(last_added);
				return old;
			}
		}
		return null;
	}

	
	public V replaceValue(Entry<K, V> e, V value) {
		int indexfound = linearSearch(e); // O(n)
		if (indexfound >= 0) {
			if (expandingArray.get(indexfound) != null) {
				V old = expandingArray.get(indexfound).getValue();
				expandingArray.get(indexfound).setValue(value);
				return old;
			}
		}
		return null;
	}



	private int upHeap(int startIndex) {
		int temp = startIndex; 

		while (temp > 0) { 
			if (temp == startIndex)
				System.out.println("Up Heap : ");

			int parent = getParent(temp); 
			if (parent >= 0) { 

				if (expandingArray.get(parent) != null && expandingArray.get(temp) != null) { 
					System.out.print("        ");
					printPriorityQueue(); 
					// null
					if (currentHeapType == HeapType.Max) { 
						if (expandingArray.get(parent).getKey().compareTo(expandingArray.get(temp).getKey()) < 0) {
							expandingArray.swapIndex(parent, temp);
							temp = parent; 
							System.out.print("        ");
							printPriorityQueue();
							continue; 
						}
					} else if (currentHeapType == HeapType.Min) { 
						if (expandingArray.get(parent).getKey().compareTo(expandingArray.get(temp).getKey()) > 0) {
							expandingArray.swapIndex(parent, temp); 
							temp = parent; 
							System.out.print("        ");
							printPriorityQueue();
							continue; 
						}
					}
				}
			} else { 
				break; 
			}
			temp = parent; 
		}
		return temp;
	}


	private int downHeap(int endIndex) {
		int currentEntry = endIndex; 
		while (currentEntry >= endIndex && currentEntry <= last_added) { 

			if (currentEntry == endIndex)
				System.out.println("Down Heap : ");

			int child = -1; 

			if (hasLeftChild(currentEntry) && hasRightChild(currentEntry)) {
				if (currentHeapType == HeapType.Max) {
					child = (expandingArray.get(getLeftChild(currentEntry)).getKey()
							.compareTo(expandingArray.get(getRightChild(currentEntry)).getKey()) > 0)
									? getLeftChild(currentEntry)
									: getRightChild(currentEntry);
				} else if (currentHeapType == HeapType.Min) {
					child = (expandingArray.get(getLeftChild(currentEntry)).getKey()
							.compareTo(expandingArray.get(getRightChild(currentEntry)).getKey()) < 0)
									? getLeftChild(currentEntry)
									: getRightChild(currentEntry);
				}

			} else if (!hasLeftChild(currentEntry) && hasRightChild(currentEntry)) {
				child = getRightChild(currentEntry);
			} else if (hasLeftChild(currentEntry) && !hasRightChild(currentEntry)) {
				child = getLeftChild(currentEntry);
			} else if (!hasLeftChild(currentEntry) && !hasRightChild(currentEntry)) {
				break; 
			}
			
			if (child >= 0) { 
				if (expandingArray.get(child) != null && expandingArray.get(currentEntry) != null) {

					System.out.print("        ");
					printPriorityQueue();
					if (currentHeapType == HeapType.Max) {
						if (expandingArray.get(child).getKey()
								.compareTo(expandingArray.get(currentEntry).getKey()) > 0) {
							expandingArray.swapIndex(child, currentEntry);
							currentEntry = child;

							System.out.print("        ");
							printPriorityQueue();
							continue; 
						} else {
							break; 
						}
					} else if (currentHeapType == HeapType.Min) {
						if (expandingArray.get(child).getKey()
								.compareTo(expandingArray.get(currentEntry).getKey()) < 0) {
							expandingArray.swapIndex(child, currentEntry);
							currentEntry = child;

							System.out.print("        ");
							printPriorityQueue();
							continue; 
						} else {
							break; 
						}
					}
				}
			}
			currentEntry = child; 
		}
		return currentEntry;
	}

	
	public void printPriorityQueue() {
		expandingArray.printArray(size);
	}

}
