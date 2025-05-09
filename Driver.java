package winter352_Assignment3;
//NAME : ALI SHER 
//STUDENT ID : 40255236

//NAME : Olgerta Gjyriqi
//student id : 40251045

public class Driver {
	public static void main(String[] args) {
		// 1. Initialization Tests
		System.out.println("\n--- Initialization Tests ---");
		APQ<Integer, String> maxHeap = new APQ<>();
		APQ<Integer, String> minHeap = new APQ<>(APQ.HeapType.Min);
		System.out.println("Max Heap and Min Heap Initialized Successfully!");

		// 2. Insert Tests
		System.out.println("\n--- Insert Tests ---");
		maxHeap.insert(10, "A");
		maxHeap.insert(20, "B");
		maxHeap.insert(15, "C");
		maxHeap.insert(5, "D");
		maxHeap.insert(30, "E");
		System.out.println("Max Heap after insertions:");
		maxHeap.printPriorityQueue();

		
		
		minHeap.insert(50, "X");
		minHeap.insert(40, "Y");
		minHeap.insert(60, "Z");
		minHeap.insert(35, "W");
		System.out.println("Min Heap after insertions:");
		minHeap.printPriorityQueue();

		// 3. Remove Tests
		System.out.println("\n--- Remove Tests ---");
		System.out.println("Removing top element from Max Heap:");
		maxHeap.removeTop();
		maxHeap.printPriorityQueue();

		System.out.println("Removing specific element (Key = 15) from Max Heap:");
		maxHeap.remove(15);
		maxHeap.printPriorityQueue();

		System.out.println("Removing top element from Min Heap:");
		minHeap.removeTop();
		minHeap.printPriorityQueue();

		// 4. Toggle Heap Type
		System.out.println("\n--- Toggle Heap Type ---");
		maxHeap.toggle();
		System.out.println("Max Heap toggled to Min Heap:");
		maxHeap.printPriorityQueue();

		minHeap.toggle();
		System.out.println("Min Heap toggled to Max Heap:");
		minHeap.printPriorityQueue();

		// 5. Replace Key and Value Tests
		System.out.println("\n--- Replace Key and Value Tests ---");
		System.out.println("Replacing key (20 -> 25) in Max Heap:");
		maxHeap.replaceKey(new Entry<>(20, "B"), 25);
		maxHeap.printPriorityQueue();

		System.out.println("Replacing value ('Z' -> 'Updated') in Min Heap:");
		minHeap.replaceValue(new Entry<>(60, "Z"), "Updated");
		minHeap.printPriorityQueue();

		// 6. Capacity Expansion Tests
		System.out.println("\n--- Capacity Expansion Tests ---");
		APQ<Integer, String> dynamicHeap = new APQ<>(5);
		for (int i = 1; i <= 10; i++) {
			dynamicHeap.insert(i, "Val" + i);
		}
		System.out.println("Dynamic Heap after exceeding initial capacity:");
		dynamicHeap.printPriorityQueue();

		// 7. Edge Cases
		System.out.println("\n--- Edge Cases ---");
		APQ<Integer, String> emptyHeap = new APQ<>();
		System.out.println("Removing from an empty heap:");
		emptyHeap.removeTop();

		System.out.println("Toggling an empty heap:");
		emptyHeap.toggle();

		System.out.println("Inserting duplicate keys:");
		emptyHeap.insert(10, "A");
		emptyHeap.insert(10, "B");
		emptyHeap.printPriorityQueue();

		System.out.println("Removing non-existing key (99):");
		emptyHeap.remove(99);

		
		System.out.println("\n--- PeekAt(n) Clean Test ---");

		APQ<Integer, String> peekHeap = new APQ<>(APQ.HeapType.Min);
		peekHeap.insert(9, "Nine");
		peekHeap.insert(3, "Three");
		peekHeap.insert(5, "Five");
		peekHeap.insert(7, "Seven");
		

		peekHeap.printPriorityQueue();

		try {
		    for (int i = 0; i < peekHeap.size(); i++) {
		        System.out.println("Peek at index " + i + ": " + peekHeap.peekAt(i).print());
		    }
		} catch (IndexOutOfBoundsException e) {
		    System.out.println("Error: " + e.getMessage());
		}

		
		//9.MErge otherAPQ
		System.out.println("\n--- Merge Test ---");

		APQ<Integer, String> mergeHeap1 = new APQ<>(APQ.HeapType.Min);
		mergeHeap1.insert(5, "Five");
		mergeHeap1.insert(1, "One");
		mergeHeap1.insert(3, "Three");

		APQ<Integer, String> mergeHeap2 = new APQ<>(APQ.HeapType.Min);
		mergeHeap2.insert(2, "Two");
		mergeHeap2.insert(4, "Four");

		System.out.println("MergeHeap1 before merge:");
		mergeHeap1.printPriorityQueue();

		System.out.println("MergeHeap2 to be merged:");
		mergeHeap2.printPriorityQueue();

		mergeHeap1.merge(mergeHeap2);

		System.out.println("MergeHeap1 after merge (should include all entries):");
		mergeHeap1.printPriorityQueue();

		System.out.println("\n=== Testing Complete ===");


	}
}
