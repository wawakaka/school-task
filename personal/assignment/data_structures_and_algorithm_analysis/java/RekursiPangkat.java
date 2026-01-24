public class RekursiPangkat {

    // Fungsi rekursif untuk menghitung x pangkat n
    public static int hitungPangkat(int x, int n) {
        // Base case: jika pangkat adalah 0, hasil selalu 1
        if (n == 0) {
            return 1;
        } else {
            // Recursive step: x * x^(n-1)
            return x * hitungPangkat(x, n - 1);
        }
    }

    public static void main(String[] args) {
        int basis = 2;
        int eksponen = 5;

        int hasil = hitungPangkat(basis, eksponen);

        System.out.println(basis + " pangkat " + eksponen + " adalah: " + hasil);
    }
}