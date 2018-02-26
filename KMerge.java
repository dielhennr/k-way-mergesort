
import java.util.Arrays;

/**
 * A class that sorts an input array using K-Way Merge Sort.  
 */
public class KMerge {

	//input array and a copy for merging
	int[] numArr;
	int[] helperArr;
	
	/**
	 * @function Sort
	 * @purpose instantiate data attributes and start K-Way Merge Sort 
	 * @param numArr: int array to be sorted
	 * 		  kPartitions: Number of partitions to partition numArr into
	 */

	public void sort(int[] numArr, int kPartitions) {
		this.numArr = numArr;
		this.helperArr = new int[this.numArr.length];
		for (int i = 0; i < numArr.length; i++) {
			this.helperArr[i] = this.numArr[i];
		}
		KWayMergeSort(kPartitions, 0, this.numArr.length - 1);
	}

	/**
	 * @function To String
	 * @purpose Create a string representation of an array where each element is on its own line.
	 *          Used to write sorted array to a file.
	 * @param arr: integer array to be converted to string
	 */
	public static String toString(int[] arr){
		String s = "";
		int i;
		for (i = 0; i < arr.length; i++) {
			s += arr[i] + "\n";
		}
		return s;
	}

	/** 
	 * @function K Way Merge Sort
	 * @purpose recursivley sort a boundry of an array
	 * Given a partion in an array KWayMerge recursivley sorts the given partition.
	 * The input partition is split into K new partitions, and each of those partitions are passed
	 * back into KWayMergeSort. Once the partition size is less than k, the elements in the partition will be sorted with.
	 * k way merge where the new k is the size of the partition.
	 * Suppose there are k sorted partitions of size n/k for the first k-1 partitions and size n - (k-1) *(n/k) 
	 * for the last partition.
	 * We will split up the boundry into k partitions, call KWayMergeSort on each partition, 
	 * and then KWayMerge the partitions.
	 * @param startPart: start index of the partition
	 *		  endPart: end index of the partition
	 *		  kPartitions: number of partitions to split given partition into
	 */

	private void KWayMergeSort(int kPartitions, int startPart, int endPart) {
		//length of boundry (n)
		int totalSize = endPart - startPart + 1;
		int sizePartitions = Math.max(totalSize / kPartitions, 1);
		int sizeEndPartition = totalSize - (sizePartitions * (kPartitions - 1));

		//We must recurse until the size of each partition is 1
		if (sizePartitions > 1){
			/**
			 * Need to pass each partition back into KWayMergeSort. i*sizePartitions + startPart - 1;
			 * will give low index of a partition. The high index will be dependent on wether or not 
			 * the partition is the last partition. If it is not, then we just add a size of a regular partition 
			 * to the low index to get the high index. If it is then we add the size of the last partition.
			 */
			for (int i = 0; i < kPartitions; i++) {


				int newEndPart;
				if (i == kPartitions - 1) {
					newEndPart = i*sizePartitions + startPart + sizeEndPartition - 1;
				}else{
					newEndPart = i*sizePartitions + startPart + sizePartitions - 1;
				}
				//Recurse for each partition
				KWayMergeSort(Math.max(sizePartitions, kPartitions), 
								i*sizePartitions + startPart, 
								newEndPart);
			}
			

		}else /*if the size of the partitions is 1*/ {
			//if the size of the end partition is greater than one, recurse with the last partition 
			//before merging partitions
			if (sizeEndPartition > 1) {
				KWayMergeSort(kPartitions, endPart-sizeEndPartition, endPart);
			}
		}
		//Once we are done recursing, start merging partitions.
		KWayMerge(kPartitions, startPart, endPart);
		System.out.println(Arrays.toString(numArr));

	}

	/**
	 * @function K Way Merge
	 * @purpose Merge K sorted partitions of an array.
	 * @setup We need to merge numArr from the index low, to index high. 
	 * The size of this partition will be high - low + 1 (high and low are indices hence +1).  
	 * We will partition the given boundry in the array k times (kPartitions).
	 * Each partition will be of size n/k (high - low + 1)/kPartitions for the first k-1 partitions.
	 * The last partition will contain the rest of the elements (high - low + 1) - (kPartitions-1)sizePartitions.
	 * @param kPartitions: number of partitions to partition given boundry into
	 *        low: low index of boundry 
	 *		  high: high index of boundry
	 */
	
	private void KWayMerge(int kPartitions, int low, int high) {
		
		int totalSize = high - low + 1;
		int sizePartitions = Math.max(totalSize / kPartitions, 1);
		int sizeLastPartition = totalSize - sizePartitions*(kPartitions-1);

		/**
		 * Consider the case when totalSize is less than kPartitions. This means that we would not be able the partition
		 * this part of the array into desired amount of partitions. What we can do is partition it into n (totalSize) 
		 * single element partitions. In this case we will just call this function again, with the same low/high and the number
		 * of one element partitions as k.
		*/
		if (totalSize < kPartitions) {
			KWayMerge(totalSize, low, high);
			System.out.println(Arrays.toString(numArr));
			return;
		}
		/**
		 * Plan: We now know where the begining of every partition is. 
		 * Lets start by keeping track of the first element in every partition. 
		 * If we can do this, then we can start to find the minimums of the first
		 * elements in all the sorted partitions, and we can begin to merge.
		 */

		int[] indices = new int[kPartitions];

		 
		int count = low;
		int min;
		int minPosition;
		int currentPart;
		//while there are still elements to merge
		while (count <= high) {

			//iterate through first element in every partition and find the minimum
			min = Integer.MAX_VALUE;
			minPosition = 0;
			currentPart = low;
			for (int i = 0; i < kPartitions; i++){

				
				
				//The last partition could be smaller or bigger than the rest, if we are done merging elements
				//in the last partition, break the loop since we dont need to check it.
				if (i == kPartitions-1 && indices[i] == sizeLastPartition){
					break;
				}

				//If we are at the end of a partition, continue. Don't want to exceed any normal partition's boundry
				//We don't check this if we are in the last partition
				//This check was preformed above.
				if (indices[i] == sizePartitions && i != kPartitions - 1){
					currentPart += sizePartitions;
					continue;
				} 
				
				//if the partition's current element is less than the min, then set it to be new min. 
				if (helperArr[currentPart + indices[i]] < min) {
					min = helperArr[currentPart + (indices[i])];
					minPosition = i;
				}
				
				//move to next partition
				currentPart += sizePartitions;
				 
			}
			//update numArr and move the counter over.
			numArr[count++] = min;
			//update the index of the partition
			indices[minPosition]++;
		}

		//copy changes to numArr into helperArr.
		for (int i = low; i <= high; i++) {
			helperArr[i] = numArr[i];
		}
	}
}


