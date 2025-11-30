public class Encapsulation {
    public static void main(String[] args) {
        Student student = new Student("Joko", 20, "2024001");
        student.displayInfo();
        System.out.println();

        System.out.println("--- Mengubah Data ---");
        student.setNama("Bagus");
        student.setUmur(21);
        student.setNilaiRataan(88);
        System.out.println();

        student.displayInfo();
        System.out.println();

        System.out.println("--- Mengakses Data Melalui Getter ---");
        System.out.println("Nama: " + student.getNama());
        System.out.println("NIM: " + student.getNim());
        System.out.println("Umur: " + student.getUmur());
        System.out.println("Nilai rata-rata: " + student.getNilaiRataRata());
        System.out.println("Grade: " + student.getGrade());
        System.out.println();

        System.out.println("--- Validasi Data ---");
        student.setNama("");
        student.setUmur(150);
        student.setNilaiRataan(150);
    }

    static public class Student {
        private String nama;
        private int umur;
        private double nilaiRataan;
        private String nim;

        public Student(String nama, int umur, String nim) {
            this.nama = nama;
            this.umur = umur;
            this.nim = nim;
            this.nilaiRataan = 0.0;
        }

        public String getNama() {
            return nama;
        }

        public int getUmur() {
            return umur;
        }

        public String getNim() {
            return nim;
        }

        public double getNilaiRataRata() {
            return nilaiRataan;
        }

        public void setNama(String nama) {
            if (nama != null && !nama.isEmpty()) {
                this.nama = nama;
                System.out.println("Nama berhasil diubah menjadi: " + nama);
            } else {
                System.out.println("Error: Nama tidak boleh kosong!");
            }
        }

        public void setUmur(int umur) {
            if (umur > 0 && umur <= 100) {
                this.umur = umur;
                System.out.println("Umur berhasil diubah menjadi: " + umur);
            } else {
                System.out.println("Error: Umur harus antara 1-100!");
            }
        }

        public void setNilaiRataan(double nilai) {
            if (nilai >= 0 && nilai <= 100) {
                this.nilaiRataan = nilai;
                System.out.println("Nilai rataan berhasil diubah menjadi: " + nilai);
            } else {
                System.out.println("Error: Nilai harus antara 0-100!");
            }
        }

        public void displayInfo() {
            System.out.println("===== Data Mahasiswa =====");
            System.out.println("Nama: " + nama);
            System.out.println("NIM: " + nim);
            System.out.println("Umur: " + umur);
            System.out.println("Nilai Rataan: " + nilaiRataan);
            System.out.println("========================");
        }

        public String getGrade() {
            if (nilaiRataan >= 85) {
                return "A";
            } else if (nilaiRataan >= 70) {
                return "B";
            } else if (nilaiRataan >= 60) {
                return "C";
            } else if (nilaiRataan >= 50) {
                return "D";
            } else {
                return "E";
            }
        }
    }
}
