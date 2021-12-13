package CSA;


public class Sort {
 
    public static void main(String args[]) {
    	int a[] = {5,4,1,2,7,9,8,10,3,6};
    	for (int i=0;i<10;i++) {
    		System.out.print(a[i]);
    		System.out.print(',');
    	}
    	insertSort(a);
    	System.out.println();
    	System.out.println("插入排序后：");
    	for (int i=0;i<10;i++) {
    		System.out.print(a[i]);
    		System.out.print(',');
    	}
    	System.out.println();
    	System.out.println("冒泡排序后：");
    	int a1[] = {5,4,1,2,7,9,8,10,3,6};
    	bubbleSort(a1);
    	for (int i=0;i<10;i++) {
    		System.out.print(a1[i]);
    		System.out.print(',');
    	}
    	System.out.println();
    	System.out.println("快速排序后：");
    	int a2[] = {5,4,1,2,7,9,8,10,3,6};
    	quickSort(a2,0,9);
    	for (int i=0;i<10;i++) {
    		System.out.print(a2[i]);
    		System.out.print(',');
    	}
    	System.out.println();
    	System.out.println("归并排序后：");
    	int a3[] = {5,4,1,2,7,9,8,10,3,6};
    	mergesort(a3,0,9);
    	for (int i=0;i<10;i++) {
    		System.out.print(a3[i]);
    		System.out.print(',');
    	}
    	System.out.println();
    	System.out.println("堆排序后：");
    	int a4[] = {5,4,1,2,7,9,8,10,3,6};
    	heapSort(a4);
    	for (int i=0;i<10;i++) {
    		System.out.print(a4[i]);
    		System.out.print(',');
    	}
    }
    public static int[] insertSort(int[] a){
    	int[] b = a;
        for(int i=1;i<a.length;i++){
            int temp = b[i];
            int j = i-1;   
            while (j>=0 && temp<b[j]){
                b[j+1] =b[j--];
                }
            b[j+1] = temp;     
        }
        return b;
    }
    public static int[] bubbleSort(int[] a){
        int temp;
        for (int i=0;i<a.length-1;i++){
            for (int j =i;j<a.length;j++){
                if(a[i]>a[j]) {
                	temp = a[i];
                	a[i] = a[j];
                	a[j] = temp;
                }
            }
        }
        return a;
    }
    public static int[] quickSort(int[] a, int low, int high){
        if (low < high){
            int middle = getMiddle(a,low,high);
            quickSort(a,0,middle-1);
            quickSort(a,middle+1,high);
        }
        return a;
    }

    public static int getMiddle(int[] a,int low, int high){
        int temp = a[low];
        while (low<high){
            while (low<high && a[high]>=temp){
                high--;
            }
            a[low]  = a[high];
            while (low<high && a[low]<=temp){
                low ++;
            }
            a[high] = a[low];
        }
        a[low] =temp;
        return low;
    }
    public static int[] mergesort(int[] a,int low,int high){
        int mid = (low+high)/2;
        if(low<high){
            mergesort(a,low,mid);
            mergesort(a,mid+1,high);
            merge(a,low,mid,high);
        }
        return a;
    }
     
    public static void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high-low+1];
        int i= low;
        int j = mid+1;
        int k=0;
        while(i<=mid && j<=high){
            if(a[i]<a[j]){
                temp[k++] = a[i++];
            }else{
                temp[k++] = a[j++];
            }
        } 
        while(i<=mid){
            temp[k++] = a[i++];
        }
        while(j<=high){
            temp[k++] = a[j++];
        }
        for(int x=0;x<temp.length;x++){
            a[x+low] = temp[x];
        }
    }
    public static void maxHeap(int arr[], int size, int index) {

        int leftNode = 2 * index + 1;
        int rightNode = 2 * index + 2;
        int max = index;
        if(leftNode < size && arr[leftNode] > arr[max]) {
            max = leftNode;
        }
 
        if(rightNode < size && arr[rightNode] > arr[max]) {
            max = rightNode;
        }

        if(max != index) {
            int tem = arr[index];
            arr[index] = arr[max];
            arr[max] = tem;
            maxHeap(arr, size, max);
        }
    }
 
    public static void heapSort(int arr[]) {
        int start = (arr.length - 1)/2;
        for(int i = start; i>=0; i--) {
            maxHeap(arr, arr.length, i);
        }
        for(int i = arr.length-1; i>0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            maxHeap(arr, i, 0);
        }
    }

}