package sort;

/**
 * Created by xiongfeng on 16/6/23.
 */
public class MySort {
    static int[] bubbleSort1(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = i+1; j < arr.length - i; j++) {
                if (arr[j] > arr[j-1]) {
                    int temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        return arr;
    }

    static int[] quickSort(int[] arr) {
        int middle = arr[0];
        int i = 0;
        int j = arr.length - 1;


        while (i < j) {
            for (; i != j; j--) {
                if (arr[j] <= middle) {
                    arr[i] = arr[j];
                }
            }

            for (; i != j; i++) {
                if (arr[i] > middle) {
                    arr[j] = arr[i];
                }
            }

            arr[i] = middle;

        }
        return arr;
    }

    public static void main(String[] args) {
        int[] a = {8,9,2,7,6,3,4,1,5};

        quickSort(a);

        show(a);
    }

    static void show(int[] arr) {
        for(int a: arr) {
            System.out.println(a);
        }
    }
}
