package AlgoAnalizOdev;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.Random;

class SortingClass {
    private static void Max_Heapify(int[]arr,int i, int n){
        int largest =i; // Selected the root
        int left = 2*i+1; // left node
        int right = 2*i+2; // right node

        if(left<=n  && arr[left]>arr[i]) largest = left; // Changes the largest node if left is the bigger than root.
        if(right<=n && arr[right]>arr[largest]) largest=right;// Changes the largest node if right is the bigger than root.
        if(largest != i){ // If largest was changed. Changes the root and calls the recursion.
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            Max_Heapify(arr,largest,n); // The recursion for heapify.
        }
    }

    private static void Build_Max_Heap(int[] arr,int n){
        for (int i = n/2  ; i >=0 ; i--) {
            Max_Heapify(arr,i,n); // Calls the heapify from the middle node to first node in array for building max heap.
        }
    }
    public static int[] heapSort(int[] arrayToSort){
        Build_Max_Heap(arrayToSort,arrayToSort.length-1); //Builds the max heap
        for (int i =arrayToSort.length-1 ; i >0 ; i--) {
            // Max value is the first node every step for max heapify.
            // So, the max value swaps with the last node in decreasing.
            int temp= arrayToSort[0];
            arrayToSort[0] = arrayToSort[i];
            arrayToSort[i] = temp;
            // Calls max-heapify, so that the first node is the largest.
            Max_Heapify(arrayToSort,0,i-1);
        }
        // Returns the sorted array.
        return  arrayToSort;
    }

    public static ArrayList<Integer> insertionSort(ArrayList<Integer> arr){
        for (int i = 0; i < arr.size(); i++) {
            int key = arr.get(i);
            int j = i - 1;
            //Moves elements of arr[0..i-1], that are greater than key.
            while (j >= 0 && arr.get(j) > key) {
                arr.set(j + 1,arr.get(j));
                j = j - 1;
            }
            arr.set((j + 1), key);
        }
        // Returns the sorted array.
        return arr;
    }
    public static int[] bucketSort(int[] arrayToSort,int n){
        int max_value= arrayToSort[0]; //Finds the max value in array.
        for (int i = 0; i < arrayToSort.length; i++) {
            if(arrayToSort[i] >max_value) max_value = arrayToSort[i];
        }
        int bucket_size = max_value/n; // Defines the bucket size for data insertion in the buckets.
        ArrayList<Integer>[] buckets = new ArrayList[n]; // Generates buckets with n size.

        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>(); // Initialize the bucket for insertion.
            for (int j = 0; j < arrayToSort.length; j++) {
                if(arrayToSort[j] <= (bucket_size*(i+1))+1&& arrayToSort[j] >(bucket_size)*(i)){
                    buckets[i].add(arrayToSort[j]);  // If A[i] is the wanted value. Adds to the bucket.
                }
            }
        }
        // Sorts each buckets.
        for (int i = 0; i < n; i++) {
            buckets[i] = insertionSort(buckets[i]);
        }
        // Concatenate all buckets into arrayToSort
        int count = 0;
        for (int i = 0; i < n; i++) {
            for(Integer data: buckets[i]){
                arrayToSort[count] = data;
                count++;
            }
        }
        return arrayToSort;
    }

    private static void swap(int[] arr, int i, int j) {
        //Swaps the A[i],A[j]
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static int[] dualPivotQuickSort(int[] arrayToSort,int low,int high){
        if(high > low){
            // If the chosen low, and high indexes is not A[high]>A[low]. It will be swapped.
            if (arrayToSort[low] > arrayToSort[high])
                swap(arrayToSort, low, high);
            int lp = arrayToSort[low], rp = arrayToSort[high]; // low and high values selected.

            int l = low + 1, g = high - 1, k = l; // For partition l and q are the positions left and right.
            while (k <= g) { // k is the selection point. when k is equal highest point it will be soon.
                if (arrayToSort[k] < lp) {
                    // If selection is the lower than low. swap A[k] with A[low].
                    swap(arrayToSort, k, l);
                    // Then, increased lower index.
                    ++l;
                }
                else if (arrayToSort[k] >= rp) {
                    // If selection is the bigger than high. swaps the correct position A[k]. A[high]'s index will be decreased.
                    while (arrayToSort[g] > rp && k < g) --g;
                    swap(arrayToSort, k, g);
                    --g;
                    if (arrayToSort[k] < lp) {
                        swap(arrayToSort, k, l);
                        ++l;
                    }
                }
                ++k;
            }
            --l;++g;

            // Swaps pivots to final place
            swap(arrayToSort, low, l); swap(arrayToSort, high, g);

            // Recursively sort partitions.
            dualPivotQuickSort(arrayToSort, low, l - 1);
            dualPivotQuickSort(arrayToSort, l + 1, g - 1);
            dualPivotQuickSort(arrayToSort, g + 1, high);
        }
        return arrayToSort;
    }
    public static int[] generated_arr(int choice,int arraySize){
        // Generates the array wanted size and choices {1000,10000,100000}.
        Random rnd = new Random();
        int[] arr = new int[arraySize];
        switch (choice){
            case 0:
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = 30;
                }
                break;
            case 1:
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = rnd.nextInt();
                }
                break;
            case 2:
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = i;
                }
                break;
            case 3:
                int j= 0;
                for (int i = arr.length-1; i >=0 ; i--) {
                    arr[j] = i;
                    j++;
                }
                break;
        }
        return arr;
    }
    public static void main(String[] args){
        //Test the sorting algorithms.

        //Screen and tests.
        System.out.println("                     /      EQUAL INTEGERS     #   RANDOM INTEGERS     #   INCREASING INTEGERS    #  DECREASING INTEGERS  /");
        System.out.println("                     /   1k    /  10k / 100k  #  1k  / 10k  / 100k    #  1k  /  10k  /   100k    # 1k  /  10k  /  100k    /");
        System.out.print("1-)HeapSort          / ");


        //Generates the arrList with ArrayList for heapsort.
        ArrayList<int[]> arrList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j =1000; j <100001 ;j*=10) {
                arrList.add(generated_arr(i,j));
            }
        }
        int count = 0;
        for (int[] arr : arrList) {
            count++;
            // Time calculations
            long startTime = System. currentTimeMillis();
            heapSort(arr);
            long estimatedTime = System. currentTimeMillis() - startTime;

            //Prints
            System.out.print("  "+estimatedTime+"    ");
            if(count%3==0) System.out.print("#");
            else System.out.print("/");
        }

        //Generates the arrList with ArrayList for DualPivotQuickSort.
        count = 0;
        arrList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j =1000; j <100001 ;j*=10) {
                arrList.add(generated_arr(i,j));
            }
        }
        System.out.println();
        System.out.print("2-)DualPivotQuickSort/  ");
        for (int[] arr : arrList) {
            count++;

            // Time calculations
            long startTime = System. currentTimeMillis();
            dualPivotQuickSort(arr,0, arr.length-1);
            long estimatedTime = System. currentTimeMillis() - startTime;

            //Prints
            System.out.print(" "+estimatedTime+"    ");
            if(count%3==0) System.out.print(" # ");
            else System.out.print("/");
        }

        //Generates the arrList with ArrayList for BuckedSort.
        count = 0;
        arrList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j =1000; j <100001 ;j*=10) {
                arrList.add(generated_arr(i,j));
            }
        }
        System.out.println();
        System.out.print("3-)BuckedSort        /  ");
        for (int[] arr : arrList) {
            count++;

            // Time calculations
            long startTime = System. currentTimeMillis();
            bucketSort(arr, 5);
            long estimatedTime = System. currentTimeMillis() - startTime;

            //Prints
            System.out.print(" "+estimatedTime+"    ");
            if(count%3==0) System.out.print("#  ");
            else System.out.print("/");
        }

    }
}
