public class KMerge {
    int[] numArr;
    int[] helperArr;
    /*
    public static void main(String[] args) {
    	int[] arr = {4,1, 2,5, 2,7, 3,8, 6,7,8,10,9};
    	KMerge merger = new KMerge();
    	merger.sort(arr, 5);
    }
    */

    public void sort(int[] numArr, int KPartitions) {
    	this.numArr = numArr;
    	this.helperArr = new int[this.numArr.length];
    	for (int i = 0; i < numArr.length; i++) {
    		this.helperArr[i] = this.numArr[i];
    	}
        kWayMerge(numArr.length/KPartitions, 0, this.numArr.length, KPartitions);
    }
    public static String toString(int[] arr){
    	String s = "";
    	int i;
    	for (i = 0; i < arr.length; i++) {
    		s += arr[i] + "\n";
    	}
    	return s;
    }
    
    public void kWayMerge(int sizePartitions, int startPart, int endPart, int KPartitions) {
    	int sizeArr = endPart - startPart;
    	int sizeLastPartition = sizeArr%KPartitions;
       	int length = sizePartitions;
       	int totalSize = sizeArr;

       	for (length = sizePartitions; length <= totalSize; length*=2){
       		int i = 0;

       		while(i + length < totalSize) {
       			
       			int high;
       			if(i+2*length < totalSize){
       				high = i+2*length;
       			}else{
       				high = totalSize;
       			}
       			if (sizePartitions < KPartitions) {
       				KPartitions = sizePartitions;
       			}
       			if (sizePartitions > 1){
       				kWayMerge(Math.max(sizePartitions/KPartitions, 1), i, high, KPartitions);
       			}
       			twoWayMerge(i, high, i + length);
       			i += 2*length;
       		}
       	}

    }
    public void twoWayMerge(int low, int high, int mid) {
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
