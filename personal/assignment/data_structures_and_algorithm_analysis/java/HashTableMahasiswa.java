import java.util.LinkedList;

class HashTable { 
    private LinkedList<Entry>[] table; // Pakai generics biar aman warning
    private int size; 

    static class Entry { 
        int key; 
        String value; 

        Entry(int key, String value) { 
            this.key = key; 
            this.value = value; 
        } 
    } 

    @SuppressWarnings("unchecked") 
    HashTable(int size) { 
        this.size = size; 
        table = new LinkedList[size]; 
        for (int i = 0; i < size; i++) { 
            table[i] = new LinkedList<>(); 
        } 
    } 

    private int hashFunction(int key) { 
        return key % size; 
    } 

    void insert(int key, String value) { 
        int index = hashFunction(key); 
        table[index].add(new Entry(key, value)); 
    } 

    void display() { 
        for (int i = 0; i < size; i++) { 
            System.out.print("Index " + i + ": "); 
            if (table[i].isEmpty()) {
                System.out.println("null");
            } else {
                for (Entry entry : table[i]) { 
                    System.out.print("(NIM: " + entry.key + ", Nama: " + entry.value + ") -> "); 
                } 
                System.out.println("null"); 
            }
        } 
    } 

    // --- Implementasi method update ---
    void update(int key, String newValue) { 
        int index = hashFunction(key); 
        boolean found = false;

        // Loop list di bucket/index tersebut
        for (Entry entry : table[index]) {
            if (entry.key == key) {
                entry.value = newValue;
                found = true;
                System.out.println("Sukses update: NIM " + key + " menjadi " + newValue);
                break; // Stop loop kalau udah ketemu
            }
        }
        
        if (!found) {
            System.out.println("NIM " + key + " gak ditemukan buat update.");
        }
    } 

    // --- Implementasi method delete ---
    void delete(int key) { 
        int index = hashFunction(key); 
        Entry target = null;

        // Cari dulu entry-nya
        for (Entry entry : table[index]) {
            if (entry.key == key) {
                target = entry;
                break;
            }
        }

        // Kalau ketemu, hapus dari LinkedList
        if (target != null) {
            table[index].remove(target);
            System.out.println("Sukses delete: NIM " + key);
        } else {
            System.out.println("NIM " + key + " gak ditemukan buat delete.");
        }
    } 

    public static void main(String[] args) { 
        HashTable hashTable = new HashTable(10); 

        // Tambahkan beberapa data mahasiswa 
        hashTable.insert(20231001, "Kenshi Yonezu"); 
        hashTable.insert(20231002, "Hikaru Utada"); 
        // Tes collision (kalau size 10, key ini bakal masuk index sama kayak 20231001)
        hashTable.insert(20231011, "JANE DOE"); 

        System.out.println("=== Sebelum update dan delete ==="); 
        hashTable.display(); 
        System.out.println();

        // Uji fitur update() 
        System.out.println("=== Test Update ===");
        hashTable.update(20231011, "JANE DOE Updated");
        hashTable.update(20239999, "Ghost"); // Tes data yg gak ada
        System.out.println();

        // Uji fitur delete() 
        System.out.println("=== Test Delete ===");
        hashTable.delete(20231011); // Hapus Jane Smith
        System.out.println();

        // Tampilkan hasil akhir
        System.out.println("=== Setelah update dan delete ==="); 
        hashTable.display(); 
    } 
} 