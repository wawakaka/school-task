import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class ArrayListOperations {
    // Add element
    public static void add(ArrayList<Integer> list, int value) {
        list.add(value);
    }

    // Remove by index
    public static void removeByIndex(ArrayList<Integer> list, int index) {
        if (index >= 0 && index < list.size()) {
            list.remove(index);
        }
    }

    // Search (linear)
    public static int search(ArrayList<Integer> list, int target) {
        return list.indexOf(target); // returns -1 if not found
    }

    // Sort
    public static void sort(ArrayList<Integer> list) {
        Collections.sort(list);
    }

    // Traversal
    public static void traverse(ArrayList<Integer> list) {
        System.out.print("ArrayList Traversal: ");
        System.out.println(list);
    }
}


class ArrayOperations {
    // Traversal
    public static void traverse(int[] arr) {
        System.out.print("Array Traversal: ");
        System.out.println(Arrays.toString(arr));
    }

    // Linear Search
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    // Binary Search (asumsi array sudah terurut)
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    // Insert (mengembalikan array baru dengan ukuran +1)
    public static int[] insert(int[] arr, int index, int value) {
        if (index < 0 || index > arr.length) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        int[] newArr = new int[arr.length + 1];
        System.arraycopy(arr, 0, newArr, 0, index);
        newArr[index] = value;
        System.arraycopy(arr, index, newArr, index + 1, arr.length - index);
        return newArr;
    }

    // Delete (mengembalikan array baru dengan ukuran -1)
    public static int[] delete(int[] arr, int index) {
        if (index < 0 || index >= arr.length) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        int[] newArr = new int[arr.length - 1];
        System.arraycopy(arr, 0, newArr, 0, index);
        System.arraycopy(arr, index + 1, newArr, index, arr.length - index - 1);
        return newArr;
    }
}

public class Comparison {
    public static void main(String[] args) {
        // Ukuran data uji
        int size = 10000;
        int target = size / 2;
        int insertValue = -1;
        int insertIndex = size / 2;

        // === Inisialisasi data awal ===
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }

        ArrayList<Integer> arrayList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(i);
        }

        // === Traversal ===
        ArrayOperations.traverse(Arrays.copyOfRange(array, 0, Math.min(10, array.length)));
        ArrayListOperations.traverse(new ArrayList<>(arrayList.subList(0, Math.min(10, arrayList.size()))));

        // === Pencarian (Linear) ===
        long start, end;
        int result;

        start = System.nanoTime();
        result = ArrayOperations.linearSearch(array, target);
        end = System.nanoTime();
        System.out.printf("Pencarian %d dalam Array: Ditemukan di indeks %d%n", target, result);
        double timeArraySearch = (end - start) / 1_000_000.0;

        start = System.nanoTime();
        result = ArrayListOperations.search(arrayList, target);
        end = System.nanoTime();
        System.out.printf("Pencarian %d dalam ArrayList: Ditemukan di indeks %d%n", target, result);
        double timeListSearch = (end - start) / 1_000_000.0;

        // === Penyisipan ===
        start = System.nanoTime();
        int[] newArray = ArrayOperations.insert(array, insertIndex, insertValue);
        end = System.nanoTime();
        double timeArrayInsert = (end - start) / 1_000_000.0;

        start = System.nanoTime();
        arrayList.add(insertIndex, insertValue);
        end = System.nanoTime();
        double timeListInsert = (end - start) / 1_000_000.0;

        System.out.printf("Array setelah penyisipan elemen %d: %s...%n", insertValue,
                Arrays.toString(Arrays.copyOfRange(newArray, insertIndex - 2, insertIndex + 3)));
        System.out.printf("ArrayList setelah penyisipan elemen %d: %s...%n", insertValue,
                arrayList.subList(insertIndex - 2, insertIndex + 3));

        // === Penghapusan ===
        start = System.nanoTime();
        int[] deletedArray = ArrayOperations.delete(newArray, insertIndex);
        end = System.nanoTime();
        double timeArrayDelete = (end - start) / 1_000_000.0;

        start = System.nanoTime();
        arrayList.remove(insertIndex);
        end = System.nanoTime();
        double timeListDelete = (end - start) / 1_000_000.0;

        // === Pengurutan (ArrayList saja, karena array sudah terurut) ===
        // Acak dulu untuk pengujian sort
        Collections.shuffle(arrayList);
        start = System.nanoTime();
        ArrayListOperations.sort(arrayList);
        end = System.nanoTime();
        double timeListSort = (end - start) / 1_000_000.0;

        // === Tampilkan Perbandingan Waktu ===
        System.out.println("\n=== Perbandingan Waktu Eksekusi (ms) ===");
        System.out.printf("%-25s | %-15s | %-15s%n", "Operasi", "Array", "ArrayList");
        System.out.println("-----------------------------------------------");
        System.out.printf("%-25s | %-15.4f | %-15.4f%n", "Pencarian (Linear)", timeArraySearch, timeListSearch);
        System.out.printf("%-25s | %-15.4f | %-15.4f%n", "Penyisipan", timeArrayInsert, timeListInsert);
        System.out.printf("%-25s | %-15.4f | %-15.4f%n", "Penghapusan", timeArrayDelete, timeListDelete);
        System.out.printf("%-25s | %-15s | %-15.4f%n", "Pengurutan", "Tidak diuji (sudah urut)", timeListSort);
    }
}

