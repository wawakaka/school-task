```java
public class Soal1 {
    public static void main(String[] args) {
        // Membuat array untuk menyimpan barang dengan kapasitas 10
        Barang[] inventaris = new Barang[10];

        // Menambahkan barang ke dalam array
        inventaris[0] = new Barang("Pensil", 50, 200);
        inventaris[1] = new Barang("Buku", 30, 15000);
        inventaris[2] = new Barang("Penghapus", 20, 500);

        // Menampilkan semua barang di inventaris
        System.out.println("Inventaris Toko:");
        int nomor = 1;
        for (Barang barang : inventaris) {
            if (barang != null) {
                System.out.println(nomor + ". " + barang);
                nomor++;
            }
        }
    }


    // Kelas Barang
    static class Barang {
        private String nama;
        private int jumlahStok;
        private int harga;

        // Constructor
        public Barang(String nama, int jumlahStok, int harga) {
            this.nama = nama;
            this.jumlahStok = jumlahStok;
            this.harga = harga;
        }

        // Method toString untuk menampilkan info barang
        @Override
        public String toString() {
            return "Nama: " + nama + ", Jumlah Stok: " + jumlahStok + ", Harga: " + harga;
        }
    }
}
```

```java
import java.util.Scanner;

public class Soal2 {
    private static Barang[] inventaris = new Barang[10];
    private static int jumlahBarang = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Menambahkan barang ke dalam array
        tambahBarang("Pensil", 50, 200);
        tambahBarang("Buku", 30, 15000);
        tambahBarang("Penghapus", 20, 500);

        // Menampilkan inventaris awal
        System.out.println("=== Inventaris Awal ===");
        tampilkanInventaris();

        // Update stok
        System.out.print("\nMasukkan nama barang yang ingin diupdate: ");
        String namaUpdate = scanner.nextLine();
        System.out.print("Masukkan jumlah stok baru: ");
        int stokBaru = scanner.nextInt();
        updateStok(namaUpdate, stokBaru);

        // Tampilkan inventaris setelah update
        System.out.println("\nInventaris setelah diupdate:");
        tampilkanInventaris();

        // Cari barang
        scanner.nextLine(); // Membersihkan buffer input
        System.out.print("\nMasukkan nama barang yang dicari: ");
        String namaCari = scanner.nextLine();
        System.out.println("Hasil pencarian:");
        cariBarang(namaCari);

        scanner.close();
    }

    // Metode untuk menambahkan barang ke inventaris
    private static void tambahBarang(String nama, int jumlahStok, int harga) {
        if (jumlahBarang < inventaris.length) {
            inventaris[jumlahBarang] = new Barang(nama, jumlahStok, harga);
            jumlahBarang++;
        } else {
            System.out.println("Inventaris penuh!");
        }
    }

    // Metode untuk menampilkan semua barang di inventaris
    private static void tampilkanInventaris() {
        int nomor = 1;
        for (int i = 0; i < jumlahBarang; i++) {
            System.out.println(nomor + ". " + inventaris[i]);
            nomor++;
        }
    }

    // Metode untuk update stok barang berdasarkan nama
    private static void updateStok(String nama, int jumlahBaru) {
        boolean ditemukan = false;
        for (int i = 0; i < jumlahBarang; i++) {
            if (inventaris[i].getNama().equalsIgnoreCase(nama)) {
                inventaris[i].setJumlahStok(jumlahBaru);
                System.out.println("Stok barang '" + nama + "' berhasil diupdate!");
                ditemukan = true;
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("Barang '" + nama + "' tidak ditemukan!");
        }
    }

    // Metode untuk mencari barang berdasarkan nama
    private static void cariBarang(String nama) {
        boolean ditemukan = false;
        for (int i = 0; i < jumlahBarang; i++) {
            if (inventaris[i].getNama().equalsIgnoreCase(nama)) {
                System.out.println(inventaris[i]);
                ditemukan = true;
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("Barang '" + nama + "' tidak ditemukan!");
        }
    }

    // Kelas Barang
    static class Barang {
        private String nama;
        private int jumlahStok;
        private int harga;

        // Constructor
        public Barang(String nama, int jumlahStok, int harga) {
            this.nama = nama;
            this.jumlahStok = jumlahStok;
            this.harga = harga;
        }

        // Getter untuk nama
        public String getNama() {
            return nama;
        }

        // Getter untuk jumlahStok
        public int getJumlahStok() {
            return jumlahStok;
        }

        // Setter untuk jumlahStok
        public void setJumlahStok(int jumlahStok) {
            this.jumlahStok = jumlahStok;
        }

        // Getter untuk harga
        public int getHarga() {
            return harga;
        }

        // Method toString untuk menampilkan info barang
        @Override
        public String toString() {
            return "Nama: " + nama + ", Jumlah Stok: " + jumlahStok + ", Harga: " + harga;
        }
    }
}
```