#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Pegawai {
    char nip[20];
    char nama[50];
    char alamat[100];
    char no_hp[15];
    char jabatan[30];
    char golongan[3];
    int gaji_pokok;
    int jam_lembur;
    int total_gaji;
};

int getGajiPokok(const char *golongan) {
    if (strcasecmp(golongan, "D1") == 0) return 2500000;
    else if (strcasecmp(golongan, "D2") == 0) return 2000000;
    else if (strcasecmp(golongan, "D3") == 0) return 1500000;
    else return 0;
}

int getGajiLemburPerJam(char golongan[]) {
    if (strcasecmp(golongan, "D1") == 0) return 10000;
    else if (strcasecmp(golongan, "D2") == 0) return 5000;
    else if (strcasecmp(golongan, "D3") == 0) return 2500;
    else return 0;
}

void validasiGajiPokok(int gaji_pokok) {
     if (gaji_pokok == 0) {
        printf("Error: Golongan tidak valid\n");
        return;
    }
}

int main() {
    struct Pegawai pgw;

    // Input 1 - Data pegawai
    printf("Input Data Pegawai:\n");
    printf("NIP : ");
    scanf("%s", pgw.nip);

    printf("Nama : ");
    scanf(" %[^\n]", pgw.nama);

    printf("Alamat : ");
    scanf(" %[^\n]", pgw.alamat);

    printf("No HP : ");
    scanf("%s", pgw.no_hp);

    printf("Jabatan : ");
    scanf(" %[^\n]", pgw.jabatan);

    printf("Golongan (D1/D2/D3) : ");
    scanf("%s", pgw.golongan);

    // Validasi dan hitung gaji pokok
    pgw.gaji_pokok = getGajiPokok(pgw.golongan);
    validasiGajiPokok(pgw.gaji_pokok);

    // Output Input 1
    printf("\n--- Data Pegawai ---\n");
    printf("NIP       = %s\n", pgw.nip);
    printf("Nama      = %s\n", pgw.nama);
    printf("Alamat    = %s\n", pgw.alamat);
    printf("No HP     = %s\n", pgw.no_hp);
    printf("Jabatan   = %s\n", pgw.jabatan);
    printf("Golongan  = %s\n", pgw.golongan);
    printf("Gaji      = %d\n", pgw.gaji_pokok);

    // Input 2 - Lembur
    printf("\nInput Lembur Pegawai:\n");
    printf("NIP : %s\n", pgw.nip);

    printf("Golongan : %s\n", pgw.golongan);

    printf("Lembur (Jam) : ");
    scanf("%d", &pgw.jam_lembur);

    // Hitung total gaji
    pgw.total_gaji = pgw.gaji_pokok + (getGajiLemburPerJam(pgw.golongan) * pgw.jam_lembur);

    // Output Input 2
    printf("\n--- Rekap Gaji Bulanan ---\n");
    printf("NIP                  = %s\n", pgw.nip);
    printf("Golongan             = %s\n", pgw.golongan);
    printf("Lembur               = %d Jam\n", pgw.jam_lembur);
    printf("Total Gaji Bulan ini = %d\n", pgw.total_gaji);

    return 0;
}