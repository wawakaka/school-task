#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <uuid/uuid.h>

// Konstanta batas maksimum panjang string dan jumlah data
#define MAX_STR_LEN 256
#define MAX_BOOKS 64
#define MAX_HISTORIES 64
#define BOOK_FILE_PATH "databuku.txt"
#define HISTORY_FILE_PATH "datahistory.txt"
#define UUID_STRLEN 36
#define UUID_BUF (UUID_STRLEN + 1)

// Struktur data history penjualan
struct history{
     char kode_transaksi[UUID_BUF];
     char *kode_buku;
     char *nama_buku;
     char *jumlah;
     char *total_harga;
     char *tanggal;
};

struct history histories[MAX_HISTORIES]; // Array penyimpanan history penjualan

// Struktur data buku
struct book{
     char kode[UUID_BUF];
     char *namaBuku;
     char *jenisBuku;
     char *harga;
};

struct book books[MAX_BOOKS]; // Array penyimpanan data buku

// ============================
// Menampilkan daftar menu utama
int showMenu() {
     int menu;
     char *listMenu[] = {"Input", "View History", "View Buku", "Delete History", "Delete Buku", "Exit"};

     printf("===== MENU APLIKASI TOKO BUKU =====\n");
     for(int i = 0; i < 6; i++) {
          printf("%d. %s\n", i + 1, listMenu[i]);
     }
     printf("\n");

     // Validasi input menu dari user
     do {
          printf("Pilih Menu (1-6): ");

          // Validasi input harus angka
          if (scanf("%d", &menu) != 1) {
               printf("Error: Input harus angka.\n");
               while (getchar() != '\n'); // bersihkan buffer input
               continue;
          }

          // remove trailing newline
          int ch; while ((ch = getchar()) != '\n' && ch != EOF) {}

          // Validasi range angka
          if (menu < 1 || menu > 6) {
               printf("Error: Menu tidak valid. Silakan masukkan angka 1-6.\n");
          } else {
               printf("Menu Pilihan    : %s\n", listMenu[menu-1]);
               printf("===================================\n\n");
          }
     } while (menu < 1 || menu > 6);

     return menu;
}

static char *read_line_alloc(const char *prompt) {
    char buf[MAX_STR_LEN];

    printf("%s",prompt);

    if (!fgets(buf, sizeof buf, stdin)) return NULL;
    buf[strcspn(buf, "\n")] = '\0';

    char *p = malloc(strlen(buf) + 1);
    if (!p) return NULL;
    strcpy(p, buf);
    return p;
}

// ============================
// Menu input buku
int input(void) {
    char *bookName = NULL;
    char *bookType = NULL;
    char *price    = NULL;
    FILE *file     = NULL;

    // ask all input one by one or go to cleanup if there is any error
    if (!(bookName = read_line_alloc("Masukan nama buku: "))) goto cleanup;
    if (!(bookType = read_line_alloc("Masukan tipe buku: "))) goto cleanup;
    if (!(price    = read_line_alloc("Masukan harga: ")))     goto cleanup;

    file = fopen(BOOK_FILE_PATH, "a");
    if (!file) goto cleanup;

    uuid_t binuuid;
    char uuid_str[UUID_BUF];
    // generate binary uuid
    uuid_generate_random(binuuid);
    // convert generated binary uuid into lower case string
    uuid_unparse_lower(binuuid, uuid_str);

    if (fprintf(file, "%s,%s,%s,%s\n", uuid_str, bookName, bookType, price) < 0)
        goto cleanup;

    if (fclose(file) != 0) { file = NULL; goto cleanup; }
    file = NULL;

cleanup:
    if (file) fclose(file);
    free(bookName);
    free(bookType);
    free(price);
    return 0;
}

// ============================
// Menu melihat data history penjualan
int viewHistory() {
     FILE *datahistory;
     char buffer[MAX_STR_LEN];
     char *temp;
     int i = 0;

     // Membuka file history
     datahistory = fopen(HISTORY_FILE_PATH, "r");
     if (datahistory == NULL) {
          printf("Error membuka file datahistory.txt!\n");
          return 1;
     }

     // Header tabel
     printf("Daftar History Penjualan:\n");
     printf("---------------------------------------------------------------------------------------------------\n");
     printf("No | Kode Transaksi | Kode Buku | Nama Buku                     | Jumlah | Total Harga | Tanggal\n");
     printf("---------------------------------------------------------------------------------------------------\n");

     // Membaca data dari file baris per baris
     while (fgets(buffer, sizeof(buffer), datahistory) != NULL) {
          // Menghapus newline di akhir baris
          if ((strlen(buffer) > 0) && (buffer[strlen(buffer) - 1] == '\n')) {
               buffer[strlen(buffer) - 1] = '\0';
          }

          // Parsing setiap kolom dengan strtok dan menyimpannya ke struct
          temp = strtok(buffer, ",");
          if (temp != NULL) {
               strncpy(histories[i].kode_transaksi, temp, sizeof(histories[i].kode_transaksi));
          }  

          temp = strtok(NULL, ",");
          histories[i].kode_buku = temp ? strdup(temp) : strdup("N/A");

          temp = strtok(NULL, ",");
          histories[i].nama_buku = temp ? strdup(temp) : strdup("N/A");

          temp = strtok(NULL, ",");
          histories[i].jumlah = temp ? strdup(temp) : strdup("N/A");

          temp = strtok(NULL, ",");
          histories[i].total_harga = temp ? strdup(temp) : strdup("N/A");

          temp = strtok(NULL, ",");
          histories[i].tanggal = temp ? strdup(temp) : strdup("N/A");

          // Menampilkan hasil yang sudah disimpan
          printf("%-3d| %-15s| %-10s| %-30s| %-7s| Rp.%-9s| %s\n",
               i + 1,
               histories[i].kode_transaksi,
               histories[i].kode_buku,
               histories[i].nama_buku,
               histories[i].jumlah,
               histories[i].total_harga,
               histories[i].tanggal
          );

          i++;

          // Cegah melebihi batas array
          if (i >= MAX_HISTORIES) {
               break;
          }
     }

     fclose(datahistory);
     return 0;
}

// ============================
// Menu melihat data buku
int viewBuku() {
     FILE *databuku;
     char buffer[MAX_STR_LEN];
     char *temp;
     int i = 0;

     // Membuka file buku
     databuku = fopen(BOOK_FILE_PATH, "r");
     if (databuku == NULL) {
          printf("Error membuka file databuku.txt!\n");
          return 1;
     }

     // Header tabel
     printf("Daftar Buku:\n");
     printf("------------------------------------------------------------------------------------------------------\n");
     printf("No | Kode                                | Nama Buku                     | Jenis               | Harga\n");
     printf("------------------------------------------------------------------------------------------------------\n");

     // Membaca data dari file baris per baris
     while (fgets(buffer, sizeof(buffer), databuku) != NULL) {
          // Hapus newline di akhir baris
          if ((strlen(buffer) > 0) && (buffer[strlen(buffer) - 1] == '\n')) {
               buffer[strlen(buffer) - 1] = '\0';
          }

          // Parsing setiap kolom dengan strtok dan menyimpannya ke struct
          temp = strtok(buffer, ",");
          if (temp != NULL) {
               strncpy(books[i].kode, temp, sizeof(books[i].kode));
          }  

          temp = strtok(NULL, ",");
          books[i].namaBuku = temp ? strdup(temp) : strdup("N/A");

          temp = strtok(NULL, ",");
          books[i].jenisBuku = temp ? strdup(temp) : strdup("N/A");

          temp = strtok(NULL, ",");
          books[i].harga = temp ? strdup(temp) : strdup("N/A");

          // Menampilkan hasil yang sudah disimpan
          printf("%-3d| %-5s| %-30s| %-20s| Rp.%s\n",
               i + 1,
               books[i].kode,
               books[i].namaBuku,
               books[i].jenisBuku,
               books[i].harga
          );

          i++;

          // Cegah melebihi batas array
          if (i >= MAX_BOOKS) {
               break;
          }
     }

     fclose(databuku);
     return 0;
}

// ============================
// Menu menghapus data history penjualan
int deleteHistory() {
    FILE *fp = fopen(HISTORY_FILE_PATH, "r");
    if (!fp) {
        printf("Error membuka file datahistory.txt!\n");
        return 1;
    }

    // 1) Baca semua baris & tampilkan daftar
    char *lines[MAX_HISTORIES];
    int count = 0;
    char buf[MAX_STR_LEN];

    printf("Daftar History Penjualan: \n");
    printf("-------------------------------------------------------------------------------------------\n");
    while (count < MAX_HISTORIES && fgets(buf, sizeof buf, fp)) {
        printf("%2d. %s", count + 1, buf);
        lines[count] = strdup(buf);
        if (!lines[count]) {
            for (int i = 0; i < count; ++i) free(lines[i]);
            fclose(fp);
            printf("Memori tidak cukup.\n");
            return 1;
        }
        count++;
    }
    fclose(fp);

    if (count == 0) {
        printf("Tidak ada data untuk dihapus.\n");
        return 0;
    }

    // 2) Input index dengan validasi
    int idx = 0;
    for (;;) {
        printf("\nMasukkan index yang akan dihapus (1-%d): ", count);
        char ibuf[64];
        if (!fgets(ibuf, sizeof ibuf, stdin)) {
            for (int i = 0; i < count; ++i) free(lines[i]);
            return 1;
        }
        char *endp = NULL;
        long selectedIndex = strtol(ibuf, &endp, 10);
        while (*endp == ' ' || *endp == '\t') endp++;
        if (endp == ibuf || (*endp != '\n' && *endp != '\0')) { puts("Error: input harus angka."); continue; }
        if (selectedIndex < 1 || selectedIndex > count) { puts("Error: index di luar jangkauan."); continue; }
        idx = (int)selectedIndex;
        break;
    }

    // 3) Tulis ulang file yang sama (overwrite tanpa entry yang dihapus)
    fp = fopen("datahistory.txt", "w"); // truncate file
    if (!fp) {
        for (int k = 0; k < count; ++k) free(lines[k]);
        printf("Gagal membuka file untuk menulis.\n");
        return 1;
    }

    for (int i = 0; i < count; ++i) {
        if (i == idx - 1) continue;   // skip baris yang dihapus
        fputs(lines[i], fp);
    }
    if (fclose(fp) != 0) {
        for (int k = 0; k < count; ++k) free(lines[k]);
        printf("Gagal menyimpan perubahan.\n");
        return 1;
    }

    for (int k = 0; k < count; ++k) free(lines[k]);

    printf("Data Successfully delete..\n");
    return 0;
}

// ============================
// Menu menghapus data buku
int deleteBuku() {
    FILE *fp = fopen(BOOK_FILE_PATH, "r");
    if (!fp) {
        printf("Error membuka file %s!\n", BOOK_FILE_PATH);
        return 1;
    }

    // 1) Baca semua baris & tampilkan daftar
    char *lines[MAX_BOOKS];
    int count = 0;
    char buf[MAX_STR_LEN];

    printf("Daftar Buku: \n");
    printf("------------------------------------------------------------\n");
    while (count < MAX_BOOKS && fgets(buf, sizeof buf, fp)) {
        printf("%2d. %s", count + 1, buf);
        lines[count] = strdup(buf);
        if (!lines[count]) {
            for (int k = 0; k < count; ++k) free(lines[k]);
            fclose(fp);
            printf("Memori tidak cukup.\n");
            return 1;
        }
        count++;
    }
    fclose(fp);

    if (count == 0) {
        printf("Tidak ada data untuk dihapus.\n");
        return 0;
    }

    // 2) Input index dengan validasi
    int idx = 0;
    for (;;) {
        printf("\nMasukkan index yang akan dihapus (1-%d): ", count);
        char ibuf[64];
        if (!fgets(ibuf, sizeof ibuf, stdin)) {
            for (int k = 0; k < count; ++k) free(lines[k]);
            return 1;
        }
        char *endp = NULL;
        long v = strtol(ibuf, &endp, 10);
        while (*endp == ' ' || *endp == '\t') endp++;
        if (endp == ibuf || (*endp != '\n' && *endp != '\0')) { puts("Error: input harus angka."); continue; }
        if (v < 1 || v > count) { puts("Error: index di luar jangkauan."); continue; }
        idx = (int)v;
        break;
    }

    // 3) Tulis ulang file yang sama
    fp = fopen(BOOK_FILE_PATH, "w"); 
    if (!fp) {
        for (int k = 0; k < count; ++k) free(lines[k]);
        printf("Gagal membuka file untuk menulis.\n");
        return 1;
    }

    for (int i = 0; i < count; ++i) {
        if (i == idx - 1) continue;   // skip baris yang dihapus
        fputs(lines[i], fp);
    }
    if (fclose(fp) != 0) {
        for (int k = 0; k < count; ++k) free(lines[k]);
        printf("Gagal menyimpan perubahan.\n");
        return 1;
    }

    for (int k = 0; k < count; ++k) free(lines[k]);

    printf("Data Successfully delete..\n");
    return 0;
}

// ============================
// Fungsi utama (entry point)
int main() {
     int pilihan = showMenu(); // Tampilkan menu dan ambil pilihan user

     // Arahkan ke fungsi sesuai pilihan
     switch (pilihan) {
          case 1: input(); break;
          case 2: viewHistory(); break;
          case 3: viewBuku(); break;
          case 4: deleteHistory(); break;
          case 5: deleteBuku(); break;
          case 6: 
               printf("Terima kasih telah menggunakan aplikasi.\n");
               break;
     }

     return 0;
}