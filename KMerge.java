/**
 * 
 */

public class KMerge {
	int[] numArr;
	int[] helperArr;

	public void sort(int[] numArr, int KPartitions) {
		this.numArr = numArr;
		this.helperArr = new int[this.numArr.length];
		for (int i = 0; i < numArr.length; i++) {
			this.helperArr[i] = this.numArr[i];
		}
		kWayMerge(this.numArr.length/KPartitions, 0, this.numArr.length, KPartitions);
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

	public void kWayMerge(int sizePartitions, int startPart, int endPart, int KPartitions) {

		//length of partition (n)
		int totalSize = endPart - startPart;
		
		for (int length = sizePartitions; length <= totalSize; length*=2){
			int i = 0;

			//either call kwaymerge on the partition, or two way merge with the partitions neighbor.
			while(i + length < totalSize) {

				int high;
				if(i+2*length < totalSize){
					high = i+2*length;
				}else{
					high = totalSize;
				}
				/**
				 * If the size of a given partition is less than k, we set the new k to the size of that partition
				 * for the recursive call.
				 */
				if (sizePartitions < KPartitions) {
					KPartitions = sizePartitions;
				}

				if (sizePartitions > 1){
					kWayMerge(Math.max(sizePartitions/KPartitions,1), i, high, KPartitions);
				}
				/**
				 * If the size of a partition is 1, we no longer need to recurse, begin two way merge with
				 * the partitions neighbor.
				*/

				/*

				*/
				twoWayMerge(i, i + length, high);
				i += 2*length;
			}
		}

	}
	
	public void twoWayMerge(int low, int mid, int high) {
		int i = low;
		int j = low;
		int k = mid;

		while (i < mid && k < high) {

			if (this.helperArr[i] <= this.helperArr[k]) {	
				this.numArr[j++] = this.helperArr[i++];

			}
			else if (this.helperArr[i] > this.helperArr[k]) {
				this.numArr[j++]= this.helperArr[k++];
			}
		}

		while (i < mid) {
			this.numArr[j++] = this.helperArr[i++];
		}

		while (k < high) {
			this.numArr[j++] = this.helperArr[k++];
		}

		while(low < high) {
			this.helperArr[low] = this.numArr[low++];

		}

	}
}
