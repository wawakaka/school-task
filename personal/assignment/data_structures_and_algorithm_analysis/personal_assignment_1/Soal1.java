//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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