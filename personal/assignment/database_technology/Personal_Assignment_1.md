### A. Soal Essay

**1. Apa itu Normalisasi Database?**
Normalisasi database itu intinya adalah proses merapikan struktur *table* dan *column* di dalam *database*. Tujuannya ada dua:

* **Mengurangi redundansi:** Biar nggak ada data yang berulang-ulang atau diduplikat tanpa alasan yang jelas.
* **Menjaga *data integrity* (integritas data):** Biar kalau kita mau nambahin (*insert*), ngedit (*update*), atau ngapus (*delete*) data, nggak terjadi *error* atau anomali (misalnya ada data yang ikut kehapus padahal nggak seharusnya).

**2. Kapan database perlu di-normalisasi dan kapan tidak perlu?**

* **Kapan perlu di-normalisasi?** Sangat perlu waktu kita bikin sistem yang transaksional atau OLTP (*Online Transaction Processing*). Contohnya aplikasi kasir, *e-commerce*, atau sistem absensi karyawan. Di sistem kayak gini, proses *insert, update,* dan *delete* data terjadi sangat sering dan cepat. Normalisasi bikin *database* lebih enteng buat nulis data baru dan mastiin datanya selalu konsisten.
* **Kapan tidak perlu di-normalisasi (Denormalisasi)?**
Tidak perlu atau malah disarankan buat di-denormalisasi kalau kita bikin *Data Warehouse* atau sistem OLAP (*Online Analytical Processing*). Sistem ini fokusnya buat *reporting* atau analisis data sejarah. Kalau datanya terlalu di-normalisasi, kita bakal butuh banyak banget proses *JOIN table* pas ngejalanin *query*. Akibatnya, *loading report* atau *dashboard* bakal jadi lambat banget. Jadi, di sini kecepatan baca data (*read speed*) lebih penting daripada ngehemat *storage*.

---

### B. Soal Case

Dari deskripsi studi kasus perusahaan pengolahan hasil laut, kita bisa mecah datanya jadi beberapa *Entity* dan *Relationship* untuk ERD, lalu nge-*map* ke *Relational Model*.

#### 1. Rancangan Entity-Relationship Diagram (ERD)

**A. Entities & Attributes:**

* **Karyawan:** `no_karyawan` (PK), `nama`, `alamat`, `no_hp`
* **Supplier:** `kode_supplier` (PK), `nama_supplier`, `penanggungjawab`, `no_telpon`
* **Bahan Makanan:** `nama_bahan` (PK) *(Note: Soal minta nama yang unik, jadi kita jadikan Primary Key)*, `stok`
* **Penerimaan:** `no_penerimaan` (PK), `tanggal_penerimaan`
* **Penggunaan:** `no_penggunaan` (PK) *(Kita tambahkan ID ini biar gampang nyatet log tiap kali ada pemakaian)*, `tanggal_penggunaan`

**B. Relationships (Kardinalitas):**

* **Supplier (1) ke Penerimaan (M):** Satu *supplier* bisa ngirim banyak penerimaan, tapi satu nomor penerimaan pasti cuma dari satu *supplier*.
* **Karyawan (1) ke Penerimaan (M):** Satu karyawan bisa nanganin banyak penerimaan (pengiriman dari *supplier*).
* **Karyawan (1) ke Penggunaan (M):** Satu karyawan bisa nyatet banyak data penggunaan bahan.
* **Penerimaan (M) ke Bahan Makanan (M):** Satu nota penerimaan bisa berisi banyak bahan, dan satu bahan bisa ada di banyak nota penerimaan. *(Ini relasi Many-to-Many, harus dipecah pakai tabel detail di relational model nanti, dengan atribut tambahan: `harga` dan `jumlah`)*.
* **Penggunaan (M) ke Bahan Makanan (M):** Satu log penggunaan bisa ngambil banyak bahan, dan satu bahan bisa dipakai di banyak log penggunaan. *(Ini juga Many-to-Many, dengan atribut tambahan: `jumlah`)*.


[![](https://img.plantuml.biz/plantuml/svg/bLJRQkCm47tNLmn-AIoR7oYbbAMxB9I5qgNFOHghjcfv9AG99IJxxplooh6TsCts9MJENCxHaSPtaJ7mjhNg2dwQKWFN6ZOMOmIpSGJHEuPN5m05rKWwmFUdUx3EDQfEzHiJ9A6a_JbgS85hLpb22zQGZbrhtnZSbWrLAZQ6F0PiknhU-Todb2Osl8FY0SCEtv4Am0Xz07i5y0N8hPiUkOR8GKR2pStZm-sjv5Sh-I5iyPYJ6Aq0F4PaIEq7uCz0_NlhlJKwTDHzaAaRL-frxB4FoC-B9E4r8LNLbget4Vzo8eUrzOvc9Nt36cdz2nkaRCa4wNr9l2yfiQGiiciaPuZ7qn-rt8rfCOyUmi5lFs0BepcT3EsqiaJMabjqxSU_0GiNscL7Ekyrex7h4xbdw6NLNo4JBtjsBEaELsEeqdjwjGvPuZTP5wnddKpNEt8oXnDDFNRPoQ4oE_bffyPopj3BgZxjrAapL_2aBR9n56lZepgkyk6mMhdz-B5TGz5s2u9aYhd2GM4gt20Z5-gue4i3AoDXeKPugZpiPnvErz0QQneHCDsmF7ssXtKeqSldnME97LElz8nfSeJ_P1hrb2RzbxfCfUuqbUBuNm00)](https://editor.plantuml.com/uml/bLJRQkCm47tNLmn-AIoR7oYbbAMxB9I5qgNFOHghjcfv9AG99IJxxplooh6TsCts9MJENCxHaSPtaJ7mjhNg2dwQKWFN6ZOMOmIpSGJHEuPN5m05rKWwmFUdUx3EDQfEzHiJ9A6a_JbgS85hLpb22zQGZbrhtnZSbWrLAZQ6F0PiknhU-Todb2Osl8FY0SCEtv4Am0Xz07i5y0N8hPiUkOR8GKR2pStZm-sjv5Sh-I5iyPYJ6Aq0F4PaIEq7uCz0_NlhlJKwTDHzaAaRL-frxB4FoC-B9E4r8LNLbget4Vzo8eUrzOvc9Nt36cdz2nkaRCa4wNr9l2yfiQGiiciaPuZ7qn-rt8rfCOyUmi5lFs0BepcT3EsqiaJMabjqxSU_0GiNscL7Ekyrex7h4xbdw6NLNo4JBtjsBEaELsEeqdjwjGvPuZTP5wnddKpNEt8oXnDDFNRPoQ4oE_bffyPopj3BgZxjrAapL_2aBR9n56lZepgkyk6mMhdz-B5TGz5s2u9aYhd2GM4gt20Z5-gue4i3AoDXeKPugZpiPnvErz0QQneHCDsmF7ssXtKeqSldnME97LElz8nfSeJ_P1hrb2RzbxfCfUuqbUBuNm00)

#### 2. Mapping ke Relational Database (Relational Model)

*(Keterangan: **Bold** = Primary Key, Italic = Foreign Key)*

1. **Karyawan** (**no_karyawan**, nama, alamat, no_hp)
2. **Supplier** (**kode_supplier**, nama_supplier, penanggungjawab, no_telpon)
3. **Bahan_Makanan** (**nama_bahan**, stok)
4. **Penerimaan** (**no_penerimaan**, tanggal_penerimaan, *kode_supplier*, *no_karyawan*)
5. **Detail_Penerimaan** (***no_penerimaan***, ***nama_bahan***, harga, jumlah)
* *Note: `no_penerimaan` dan `nama_bahan` gabung jadi satu Composite Primary Key.*


6. **Penggunaan** (**no_penggunaan**, tanggal_penggunaan, *no_karyawan*)
7. **Detail_Penggunaan** (***no_penggunaan***, ***nama_bahan***, jumlah)
* *Note: `no_penggunaan` dan `nama_bahan` gabung jadi satu Composite Primary Key.*

---