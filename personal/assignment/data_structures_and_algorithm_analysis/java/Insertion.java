import java.util.Arrays;

public class Insertion {
    public static int[] insertX(
            int[] arr,
            int x,
            int pos) {
        int i;
        int arrSize = arr.length;
        int[] newArr = new int[arrSize + 1];
        for (i = 0; i < arrSize + 1; i++) {
            if (i < pos - 1)
                newArr[i] = arr[i];
            else if (i == pos - 1)
                newArr[i] = x;
            else
                newArr[i] = arr[i - 1];
        }
        return newArr;
    }

    // Driver code
    public static void main(String[] args) {
        int[] arr
                = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Initial Array:\n"
                + Arrays.toString(arr));
        int x = 50;
        int pos = 5;
        arr = insertX(arr, x, pos);
        System.out.println("\nArray with " + x
                + " inserted at position "
                + pos + ":\n"
                + Arrays.toString(arr));
    }
}
