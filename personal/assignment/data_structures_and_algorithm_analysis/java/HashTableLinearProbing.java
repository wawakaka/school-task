import java.util.Arrays;

public class HashTableLinearProbing {
    int size;
    Integer[] table; // Pakai Integer biar bisa set null

    public HashTableLinearProbing(int size) {
        this.size = size;
        this.table = new Integer[size];
    }

    // Fungsi Hash
    private int hashFunction(int key) {
        return key % size;
    }

    // Method Insert dengan Linear Probing
    public void insert(int key) {
        int index = hashFunction(key);
        int originalIndex = index;
        
        // Linear Probing: Kalau slot tidak null, geser ke sebelah
        while (table[index] != null) {
            System.out.println("Collision di index " + index + " untuk data " + key + ", geser...");
            index = (index + 1) % size; // Pindah ke next index (wrap around)
            
            // Safety break: Kalau balik ke index awal, berarti tabel full
            if (index == originalIndex) {
                System.out.println("Tabel Penuh! Tidak bisa input " + key);
                return;
            }
        }
        
        // Ketemu slot kosong
        table[index] = key;
        System.out.println("Data " + key + " masuk di index " + index);
    }

    public void printTable() {
        System.out.println("\nIsi Tabel Hash:");
        System.out.println(Arrays.toString(table));
    }

    public static void main(String[] args) {
        // Init tabel size 8 sesuai soal
        HashTableLinearProbing ht = new HashTableLinearProbing(8);
        
        int[] dataInput = {72, 27, 36, 45, 63, 82, 94, 105};
        
        System.out.println("--- Mulai Insert Data ---");
        for (int k : dataInput) {
            ht.insert(k);
        }
        
        ht.printTable();
    }
}