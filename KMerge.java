/**
 * 
 */
import java.util.Arrays;

public class KMerge {
	int[] numArr;
	int[] helperArr;

	public void sort(int[] numArr, int KPartitions) {
		this.numArr = numArr;
		this.helperArr = new int[this.numArr.length];
		for (int i = 0; i < numArr.length; i++) {
			this.helperArr[i] = this.numArr[i];
		}
		KWayMergeSort(KPartitions, 0, this.numArr.length - 1);
	}
	public static String toString(int[] arr){
		String s = "";
		int i;
		for (i = 0; i < arr.length; i++) {
			s += arr[i] + "\n";
		}
		return s;
	}

	/** 
	 * Given a partion in an array KWayMerge recursivley sorts the given partition.
	 * The input partition is split into K new partitions, and each of those partitions are passed
	 * back into KWayMerge. Once the partition size is equal to one, it begins a two way merge from bottom up.
	 * Suppose there are k sorted partitions of size n/k for the first k-1 partitions and size n%k for the last partition.
	 * the first two will be merged, then the next two, then the next two.... 
	 * Now there are k/2 partitions if k is even and k/2 + 1 if k is odd.
	 * This process is reapeated until there are 2 sorted partitions, they will merge and then we are done.
	 * If k is odd, the last partition will be merged last with the rest of the elements.
	 * @param startPart: start index of the partition
	 *		  endPart: end index of the partition
	 *		  KPartitions: number of partitions to split given partition into
	 * @return void
	 */

	public void KWayMergeSort(int KPartitions, int startPart, int endPart) {
		//length of partition (n)
		int totalSize = endPart - startPart + 1;
		int sizePartitions = Math.max(totalSize / KPartitions, 1);
		int sizeEndPartition = totalSize - (sizePartitions * (KPartitions - 1));

		if (sizePartitions > 1){
			for (int i = 0; i < KPartitions; i++) {
				int newEndPart;
				if (i == KPartitions - 1) {
					newEndPart = i*sizePartitions + startPart + sizeEndPartition - 1;
				}else{
					newEndPart = i*sizePartitions + startPart + sizePartitions - 1;
				}
				KWayMergeSort(Math.max(sizePartitions, KPartitions), 
								i*sizePartitions + startPart, 
								newEndPart);
			}
			KWayMerge(KPartitions, startPart, endPart);

		}else {
			if (sizeEndPartition > 1) {
				KWayMergeSort(KPartitions, endPart-sizeEndPartition, endPart);
				KWayMerge(KPartitions + sizeEndPartition, startPart, endPart);
			}else{
				KWayMerge(KPartitions, startPart, endPart);
				

			}
		}

	}
	
	public void KWayMerge(int KPartitions, int low, int high) {
		/**
		 * Setup: We need to merge numArr from the index low, to index high. 
		 * The size of this partition will be high - low + 1 (high and low are indices hence +1).  
		 * We will partition the given boundry in the array k times (KPartitions).
		 * Each partition will be of size n/k (high - low + 1)/KPartitions for the first k-1 partitions.
		 * The last partition will contain the rest of the elements (high - low + 1) - (KPartitions-1)sizePartitions.
		 */
		int totalSize = high - low + 1;
		int sizePartitions = Math.max(totalSize / KPartitions, 1);


		int sizeLastPartition = totalSize - sizePartitions*(KPartitions-1);
		//System.out.printf("Partitions:%d\nlow: %d\nhigh: %d\nsize: %d\n", KPartitions, low, high, totalSize);

		/**
		 * Consider the case when totalSize is less than KPartitions. This means that we would not be able the partition
		 * this part of the array into desired amount of partitions. What we can do is partition it into single
		 * element partitions. In this case we will just call this function again, with the same low/high and the number
		 * of one element partitions as k.
		*/
		if (totalSize < KPartitions) {
			KWayMerge(totalSize, low, high);
			return;
		}
		/**
		 * Plan: We now know where each partition is. 
		 * Lets start by keeping track of the first element in every partition. 
		 * If we can do this, then we can start to find the minimums of the first
		 * elements in all the sorted partitions, and we can begin to merge.
		 */

		int[] indices = new int[KPartitions];
		int count = low;
		int min;
		int currentPart;
		int minPosition;
		while (count <= high) {

			//iterate through first element in every partition and find the minimum
			min = Integer.MAX_VALUE;
			minPosition = 0;
			currentPart = low;
			for (int i = 0; i < KPartitions; i++){

				
				
				//The last partition could be smaller or bigger than the rest, if we are done merging elements
				//in the last partition, break the loop since we dont need to check it.
				if (i == KPartitions-1 && indices[i] == sizeLastPartition){
					break;
				}

				//If we are at the end of a partition, continue. We don't check this if we are in the last partition
				//This check was preformed above.
				if (indices[i] == sizePartitions && i != KPartitions - 1){
					currentPart += sizePartitions;
					continue;
				} 

				//we don't want to exceed any normal partition's boundry
				//if the last partition is larger we must accomidate for it
				

				//if the partition's current element is less than the min, then set it to be new min. 
				System.out.println("------");
				System.out.printf("Comparision\ncurrent min: %d\ncompared to: %d\n", min, helperArr[currentPart + indices[i]]);
				System.out.printf("i: %d\nindex at ith part: %d val:%d\n", i, indices[i], helperArr[currentPart + indices[i]]);
				System.out.printf("current part: %d\n", currentPart);
				if (helperArr[currentPart + indices[i]] < min) {
					min = helperArr[currentPart + (indices[i])];
					minPosition = i;
				}
				
				//move to next partition
				currentPart += sizePartitions;
				 
			}
			//update numArr and move the counter over.
			numArr[count++] = min;
			indices[minPosition]++;
			//System.out.println(min);
			//System.out.printf("help:   %s\n", Arrays.toString(helperArr));
			//System.out.printf("actual: %s\n", Arrays.toString(numArr));

		}

		for (int i = low; i <= high; i++) {
			helperArr[i] = numArr[i];
		}
	}
}


