import java.util.LinkedList;

class EmployeeHashTable {
    // Menggunakan raw type untuk array karena keterbatasan Java dengan Generic Arrays
    private LinkedList<Entry>[] table;
    private int size;

    static class Entry {
        int id;
        String name;

        Entry(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    @SuppressWarnings("unchecked")
    EmployeeHashTable(int size) {
        this.size = size;
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hashFunction(int id) {
        return id % size;
    }

    void insert(int id, String name) {
        int index = hashFunction(id);
        table[index].add(new Entry(id, name));
    }

    void display() {
        for (int i = 0; i < size; i++) {
            System.out.print("Index " + i + ": ");
            if (table[i].isEmpty()) {
                System.out.println("null");
            } else {
                for (Entry entry : table[i]) {
                    System.out.print("(ID: " + entry.id + ", Nama: " + entry.name + ") -> ");
                }
                System.out.println("null");
            }
        }
    }

    // --- TUGAS MAHASISWA: Implementasi method ini ---
    int getTotalEmployees() {
        int total = 0;
        // Loop ke setiap bucket (index) di hash table
        for (int i = 0; i < size; i++) {
            // Tambahkan ukuran LinkedList di index tersebut ke total
            if (table[i] != null) {
                total += table[i].size();
            }
        }
        return total;
    }

    public static void main(String[] args) {
        EmployeeHashTable employeeTable = new EmployeeHashTable(10);

        // Tambahkan beberapa data karyawan
        // Hash (101 % 10) = Index 1
        employeeTable.insert(101, "Budi Santoso");
        // Hash (102 % 10) = Index 2
        employeeTable.insert(102, "Dewi Lestari");
        // Hash (111 % 10) = Index 1 (Collision dengan Budi)
        employeeTable.insert(111, "Andi Wijaya"); 
        // Hash (105 % 10) = Index 5
        employeeTable.insert(105, "Siti Aminah");

        // 1. Tampilkan visualisasi Hash Table
        System.out.println("=== Struktur Hash Table ===");
        employeeTable.display();
        
        System.out.println(); // Spasi baris

        // 2. Tugas mahasiswa: Hitung total karyawan
        System.out.println("=== Statistik ===");
        int total = employeeTable.getTotalEmployees();
        System.out.println("Total karyawan yang terdaftar: " + total);
    }
}