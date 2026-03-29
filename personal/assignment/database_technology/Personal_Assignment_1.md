### A. Soal Essay

**1. Apa itu Normalisasi Database?**
Normalisasi Database adalah proses penataan struktur *table* dan *column* di dalam *database*. Tujuannya terdiri dari dua hal:

* **Mengurangi redundansi:** Agar tidak terdapat data yang berulang atau terduplikasi tanpa alasan yang jelas.
* **Menjaga *data integrity* (integritas data):** Agar ketika dilakukan penambahan (*insert*), penyuntingan (*update*), maupun penghapusan (*delete*) data, tidak terjadi *error* atau anomali (misalnya terdapat data yang ikut terhapus padahal tidak seharusnya).

**2. Kapan database perlu di-normalisasi dan kapan tidak perlu?**

* **Kapan perlu di-normalisasi?** Normalisasi sangat diperlukan ketika membangun sistem yang bersifat transaksional atau OLTP (*Online Transaction Processing*). Contohnya adalah aplikasi kasir, *e-commerce*, atau sistem absensi karyawan. Pada sistem seperti ini, proses *insert*, *update*, dan *delete* data terjadi sangat sering dan cepat. Normalisasi membuat *database* lebih ringan dalam menulis data baru dan memastikan data selalu konsisten.
* **Kapan tidak perlu di-normalisasi (Denormalisasi)?**
Denormalisasi justru disarankan ketika membangun *Data Warehouse* atau sistem OLAP (*Online Analytical Processing*). Sistem ini berfokus pada *reporting* atau analisis data historis. Apabila data terlalu dinormalisasi, maka akan dibutuhkan sangat banyak proses *JOIN table* saat menjalankan *query*. Akibatnya, pemuatan *report* atau *dashboard* menjadi sangat lambat. Oleh karena itu, pada konteks ini kecepatan pembacaan data (*read speed*) lebih diutamakan daripada penghematan *storage*.

---

### B. Soal Case

Berdasarkan deskripsi studi kasus perusahaan pengolahan hasil laut, data dapat dipecah menjadi beberapa *Entity* dan *Relationship* untuk ERD, kemudian dipetakan ke *Relational Model*.

#### 1. Rancangan Entity-Relationship Diagram (ERD)

**A. Entities & Attributes:**

* **Karyawan:** `no_karyawan` (PK), `nama`, `alamat`, `no_hp`
* **Supplier:** `kode_supplier` (PK), `nama_supplier`, `penanggungjawab`, `no_telpon`
* **Bahan Makanan:** `nama_bahan` (PK) *(Catatan: Soal mensyaratkan nama yang unik, sehingga dijadikan Primary Key)*, `stok`
* **Penerimaan:** `no_penerimaan` (PK), `tanggal_penerimaan`
* **Penggunaan:** `no_penggunaan` (PK) *(ID ini ditambahkan untuk mempermudah pencatatan log setiap kali terjadi pemakaian)*, `tanggal_penggunaan`

**B. Relationships (Kardinalitas):**

* **Supplier (1) ke Penerimaan (M):** Satu *supplier* dapat mengirimkan banyak penerimaan, tetapi satu nomor penerimaan hanya berasal dari satu *supplier*.
* **Karyawan (1) ke Penerimaan (M):** Satu karyawan dapat menangani banyak penerimaan (pengiriman dari *supplier*).
* **Karyawan (1) ke Penggunaan (M):** Satu karyawan dapat mencatat banyak data penggunaan bahan.
* **Penerimaan (M) ke Bahan Makanan (M):** Satu nota penerimaan dapat berisi banyak bahan, dan satu bahan dapat terdapat di banyak nota penerimaan. *(Relasi ini bersifat Many-to-Many sehingga harus dipecah menggunakan tabel detail pada relational model, dengan atribut tambahan: `harga` dan `jumlah`)*.
* **Penggunaan (M) ke Bahan Makanan (M):** Satu log penggunaan dapat mengambil banyak bahan, dan satu bahan dapat digunakan di banyak log penggunaan. *(Relasi ini juga bersifat Many-to-Many, dengan atribut tambahan: `jumlah`)*.


[![](https://img.plantuml.biz/plantuml/svg/bLJRQkCm47tNLmn-AIoR7oYbbAMxB9I5qgNFOHghjcfv9AG99IJxxplooh6TsCts9MJENCxHaSPtaJ7mjhNg2dwQKWFN6ZOMOmIpSGJHEuPN5m05rKWwmFUdUx3EDQfEzHiJ9A6a_JbgS85hLpb22zQGZbrhtnZSbWrLAZQ6F0PiknhU-Todb2Osl8FY0SCEtv4Am0Xz07i5y0N8hPiUkOR8GKR2pStZm-sjv5Sh-I5iyPYJ6Aq0F4PaIEq7uCz0_NlhlJKwTDHzaAaRL-frxB4FoC-B9E4r8LNLbget4Vzo8eUrzOvc9Nt36cdz2nkaRCa4wNr9l2yfiQGiiciaPuZ7qn-rt8rfCOyUmi5lFs0BepcT3EsqiaJMabjqxSU_0GiNscL7Ekyrex7h4xbdw6NLNo4JBtjsBEaELsEeqdjwjGvPuZTP5wnddKpNEt8oXnDDFNRPoQ4oE_bffyPopj3BgZxjrAapL_2aBR9n56lZepgkyk6mMhdz-B5TGz5s2u9aYhd2GM4gt20Z5-gue4i3AoDXeKPugZpiPnvErz0QQneHCDsmF7ssXtKeqSldnME97LElz8nfSeJ_P1hrb2RzbxfCfUuqbUBuNm00)](https://editor.plantuml.com/uml/bLJRQkCm47tNLmn-AIoR7oYbbAMxB9I5qgNFOHghjcfv9AG99IJxxplooh6TsCts9MJENCxHaSPtaJ7mjhNg2dwQKWFN6ZOMOmIpSGJHEuPN5m05rKWwmFUdUx3EDQfEzHiJ9A6a_JbgS85hLpb22zQGZbrhtnZSbWrLAZQ6F0PiknhU-Todb2Osl8FY0SCEtv4Am0Xz07i5y0N8hPiUkOR8GKR2pStZm-sjv5Sh-I5iyPYJ6Aq0F4PaIEq7uCz0_NlhlJKwTDHzaAaRL-frxB4FoC-B9E4r8LNLbget4Vzo8eUrzOvc9Nt36cdz2nkaRCa4wNr9l2yfiQGiiciaPuZ7qn-rt8rfCOyUmi5lFs0BepcT3EsqiaJMabjqxSU_0GiNscL7Ekyrex7h4xbdw6NLNo4JBtjsBEaELsEeqdjwjGvPuZTP5wnddKpNEt8oXnDDFNRPoQ4oE_bffyPopj3BgZxjrAapL_2aBR9n56lZepgkyk6mMhdz-B5TGz5s2u9aYhd2GM4gt20Z5-gue4i3AoDXeKPugZpiPnvErz0QQneHCDsmF7ssXtKeqSldnME97LElz8nfSeJ_P1hrb2RzbxfCfUuqbUBuNm00)

#### 2. Mapping ke Relational Database (Relational Model)

*(Keterangan: **Bold** = Primary Key, Italic = Foreign Key)*

1. **Karyawan** (**no_karyawan**, nama, alamat, no_hp)
2. **Supplier** (**kode_supplier**, nama_supplier, penanggungjawab, no_telpon)
3. **Bahan_Makanan** (**nama_bahan**, stok)
4. **Penerimaan** (**no_penerimaan**, tanggal_penerimaan, *kode_supplier*, *no_karyawan*)
5. **Detail_Penerimaan** (***no_penerimaan***, ***nama_bahan***, harga, jumlah)
* *Catatan: `no_penerimaan` dan `nama_bahan` digabungkan menjadi satu Composite Primary Key.*


6. **Penggunaan** (**no_penggunaan**, tanggal_penggunaan, *no_karyawan*)
7. **Detail_Penggunaan** (***no_penggunaan***, ***nama_bahan***, jumlah)
* *Catatan: `no_penggunaan` dan `nama_bahan` digabungkan menjadi satu Composite Primary Key.*

---