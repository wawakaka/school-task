# Tugas Personal ke-2 - Computational Statistics

Tugas ini dikerjakan dengan bantuan Claude untuk:
- Mencari dan merangkum referensi
- Memberikan klarifikasi konsep atau penjelasan teknis
- Memperbaiki format penulisan

---

## Soal 1 - Grammar of Graphics dan Visualisasi

### Urutan Transformasi dalam Grammar of Graphics (GoG)

Grammar of Graphics (GoG) yang dikembangkan oleh Leland Wilkinson mendefinisikan visualisasi data sebagai sebuah **pipeline transformasi bertahap**. Urutan transformasi ini sangat krusial karena setiap tahap membangun di atas hasil tahap sebelumnya, sehingga kesalahan di tahap awal akan **berpropagasi** dan merusak akurasi visualisasi akhir.

**Pipeline GoG:**

```
Data -> Variable Set (Varset) -> Transformasi Statistik -> Geometri -> Koordinat -> Estetika
```

### Contoh: Pertumbuhan Penduduk Indonesia (2000-2025)

Misalkan kita memiliki data penduduk Indonesia per tahun.

**1. Data -> Varset**

Data mentah (tahun, jumlah penduduk) dikonversi menjadi variable set. Di sini kita memilih variabel mana yang akan divisualisasikan: `tahun` sebagai sumbu-x dan `jumlah_penduduk` sebagai sumbu-y.

**2. Transformasi Statistik**

Pada tahap ini, data dapat diagregasi atau ditransformasi. Misalnya, kita menghitung **laju pertumbuhan tahunan** (rate of change) daripada menampilkan angka absolut. Transformasi statistik ini mengubah makna visualisasi secara fundamental: grafik jumlah absolut menunjukkan tren naik yang landai, sedangkan grafik laju pertumbuhan bisa mengungkap bahwa pertumbuhan sebenarnya **melambat**.

**3. Transformasi Skala**

Pemilihan skala sangat memengaruhi interpretasi:

- **Skala linear:** Pertumbuhan penduduk terlihat mendatar di tahun-tahun awal dan curam di akhir (karena basis angka yang besar).
- **Skala logaritmik:** Pertumbuhan yang konstan secara persentase akan terlihat sebagai garis lurus, sehingga lebih mudah membandingkan laju pertumbuhan antar periode.

Jika kita salah memilih skala (misalnya menggunakan skala linear untuk data yang tumbuh eksponensial), visualisasi bisa **menyesatkan** karena perubahan kecil di awal periode akan tampak tidak signifikan padahal secara persentase cukup besar.

**4. Geometri**

Pemilihan bentuk geometris (titik, garis, batang) juga berpengaruh. Untuk data time-series seperti pertumbuhan penduduk, **garis (line)** lebih tepat karena menunjukkan kontinuitas temporal, sedangkan **bar chart** bisa memberikan kesan bahwa data bersifat diskrit dan independen antar tahun.

**5. Koordinat**

Sistem koordinat menentukan bagaimana data dipetakan ke ruang visual. Koordinat Cartesian adalah yang paling umum, tetapi untuk data siklikal (misalnya pola musiman), koordinat polar bisa lebih informatif. Kesalahan pemilihan koordinat dapat mengaburkan pola yang seharusnya terlihat jelas.

**6. Estetika**

Pemetaan warna, ukuran, dan bentuk ke variabel data. Misalnya, mewarnai garis berdasarkan wilayah (Jawa vs. luar Jawa) menambah dimensi informasi tanpa menambah sumbu.

### Kesimpulan

Urutan transformasi dalam GoG bersifat **deterministik dan berurutan**; mengubah urutan atau melewatkan satu tahap akan menghasilkan visualisasi yang berbeda dan berpotensi tidak akurat. Keunggulan pendekatan GoG adalah memberikan kerangka sistematis untuk memastikan setiap keputusan visualisasi dapat dilacak dan dipertanggungjawabkan.

---

## Soal 2 - Model Selection dan Regularisasi

### a) Perbedaan AIC dan BIC dalam Pemilihan Model

**AIC (Akaike Information Criterion)** dan **BIC (Bayesian Information Criterion)** sama-sama merupakan kriteria pemilihan model yang menyeimbangkan *goodness of fit* dengan kompleksitas model, tetapi dengan filosofi dan penalti yang berbeda.

| Aspek | AIC | BIC |
|-------|-----|-----|
| **Formula** | $AIC = -2 \ln(L) + 2k$ | $BIC = -2 \ln(L) + k \ln(n)$ |
| **Penalti** | Tetap: $2$ per parameter | Bergantung pada ukuran sampel: $\ln(n)$ per parameter |
| **Filosofi** | Mencari model yang paling baik **memprediksi** data baru (pendekatan information-theoretic) | Mencari model yang **paling mungkin benar** (pendekatan Bayesian) |
| **Kecenderungan** | Cenderung memilih model **lebih kompleks** | Cenderung memilih model **lebih sederhana** (parsimoni) |
| **Konsistensi** | Tidak konsisten: seiring $n \to \infty$, bisa tetap memilih model yang terlalu kompleks | Konsisten: seiring $n \to \infty$, akan memilih model yang benar jika termasuk dalam kandidat |

**Penjelasan kunci:** Pada BIC, penalti per parameter adalah $\ln(n)$, yang berarti ketika jumlah data $n > 8$ (karena $\ln(8) \approx 2.08$), BIC memberikan penalti **lebih berat** daripada AIC untuk setiap parameter tambahan. Semakin besar dataset, semakin besar perbedaan penalti ini, sehingga BIC semakin "ketat" dalam menolak parameter tambahan.

### b) Kapan Menggunakan Penalized Likelihood (Ridge Regression)?

Dalam situasi data besar dan kompleks, AIC/BIC memiliki **keterbatasan**:

**Gunakan AIC/BIC ketika:**

- Jumlah variabel prediktor (p) relatif kecil dibandingkan jumlah observasi (n)
- Tujuan utama adalah **pemilihan model** (memilih subset variabel terbaik)
- Model-model kandidat dapat dihitung secara eksplisit

**Gunakan Ridge Regression (atau penalized likelihood lainnya) ketika:**

1. **p mendekati atau melebihi n (high-dimensional data):** Ketika jumlah parameter besar (misalnya data genomik dengan ribuan gen), AIC/BIC harus membandingkan jumlah model yang sangat besar ($2^p$ kemungkinan subset). Ridge Regression secara simultan **menyusutkan (shrink)** semua koefisien menuju nol tanpa perlu enumerasi model.

2. **Multikolinearitas:** Ketika variabel-variabel prediktor saling berkorelasi tinggi, estimasi OLS menjadi tidak stabil (varians besar). Ridge menambahkan penalti $\lambda \sum \beta_j^2$ yang **menstabilkan** estimasi dengan mengorbankan sedikit bias, yaitu sebuah trade-off bias-variance yang menguntungkan.

3. **Pencegahan overfitting secara kontinu:** AIC/BIC melakukan pemilihan model secara **diskrit** (masuk atau keluar), sedangkan Ridge melakukan regularisasi secara **kontinu**, di mana setiap koefisien disusutkan secara proporsional terhadap kontribusinya. Ini menghasilkan model yang lebih robust terhadap variasi data baru.

4. **Tujuan prediksi, bukan interpretasi:** Ridge mempertahankan semua variabel (tidak melakukan seleksi), sehingga kurang cocok untuk interpretasi tetapi sering menghasilkan **prediksi yang lebih akurat** pada data baru.

**Catatan:** Jika diperlukan kombinasi regularisasi + seleksi variabel, **Elastic Net** (gabungan Ridge + Lasso) bisa menjadi pilihan yang lebih fleksibel.

---

## Soal 3 - Cross-validation dan Bootstrap

*Sumber: Advanced Resampling and Simulation Techniques in Statistical Analysis, Hal. 2-4*

### a) Perbedaan Mendasar Cross-validation dan Bootstrap

| Aspek | Cross-validation (CV) | Bootstrap |
|-------|----------------------|-----------|
| **Tujuan utama** | Mengestimasi **performa prediktif** model pada data baru | Mengestimasi **distribusi sampling** dari suatu statistik |
| **Mekanisme** | Membagi data menjadi fold training dan testing secara **tanpa pengembalian** (without replacement) | Mengambil sampel ulang **dengan pengembalian** (with replacement) dari data asli |
| **Prinsip** | Mensimulasikan skenario "data baru" dengan menyisihkan sebagian data untuk evaluasi | Mensimulasikan proses pengambilan sampel berulang dari populasi |
| **Output** | Estimasi error rate / akurasi model | Distribusi empiris dari statistik (mean, median, koefisien regresi, dll.) |
| **Penggunaan khas** | Memilih model terbaik, tuning hyperparameter | Membangun confidence interval, estimasi standard error, uji hipotesis |

**Perbedaan fundamental:** Cross-validation mengevaluasi *seberapa baik model akan bekerja pada data yang belum pernah dilihat*, sedangkan bootstrap mengevaluasi *seberapa stabil/akurat suatu estimasi statistik*. CV berfokus pada **model**, bootstrap berfokus pada **statistik**.

Dalam K-fold CV, setiap observasi muncul **tepat satu kali** di set testing. Pada bootstrap, beberapa observasi bisa muncul **berkali-kali** dalam satu sampel, dan sekitar 36.8% observasi ($1 - 1/e$) tidak muncul sama sekali, yang disebut *out-of-bag* samples.

### b) Contoh Bootstrap untuk Confidence Interval

**Kasus:** Kita memiliki data gaji 20 karyawan startup dan ingin membangun 95% confidence interval untuk **median** gaji.

**Data (dalam juta Rupiah):** {3, 4, 4, 5, 5, 5, 6, 6, 7, 7, 8, 8, 9, 10, 12, 15, 20, 25, 30, 50}

Perhatikan bahwa distribusi ini **right-skewed** (tidak normal) karena ada beberapa gaji yang sangat tinggi.

**Langkah Bootstrap:**

1. Ambil sampel acak **dengan pengembalian** sebanyak 20 observasi dari data asli -> hitung median
2. Ulangi langkah 1 sebanyak $B = 10.000$ kali
3. Kita mendapatkan 10.000 nilai median
4. **Percentile method:** Confidence interval 95% = persentil ke-2.5 dan ke-97.5 dari distribusi bootstrap

```
Iterasi 1: sampel = {5, 3, 8, 50, 7, 5, 6, 4, 9, 20, 7, 5, 12, 6, 4, 8, 5, 30, 7, 10} -> median = 7
Iterasi 2: sampel = {6, 15, 5, 8, 3, 25, 5, 7, 4, 6, 12, 9, 5, 50, 4, 7, 8, 5, 10, 20} -> median = 7
Iterasi 3: sampel = {30, 5, 4, 6, 50, 5, 3, 8, 7, 12, 4, 5, 9, 6, 7, 10, 5, 8, 25, 15} -> median = 7
...
(dan seterusnya 10.000 kali)
```

**Hasil bootstrap:** 95% CI untuk median $\approx [5, 10]$ juta Rupiah.

### Perbandingan dengan Metode Tradisional

Metode tradisional (parametrik) untuk confidence interval median mengasumsikan distribusi tertentu atau menggunakan rumus berdasarkan order statistics:

- **Metode normal approximation:** Mengasumsikan sampling distribution of median mendekati normal, lalu menggunakan rumus $CI = \text{median} \pm z \times SE$. Namun, untuk data yang sangat skewed seperti contoh di atas, asumsi normalitas **tidak terpenuhi**, sehingga CI yang dihasilkan bisa terlalu sempit atau asimetris ke arah yang salah.
- **Metode exact binomial:** Menggunakan order statistics, tetapi menghasilkan CI yang **sangat lebar** untuk sampel kecil dan hanya berlaku untuk kuantil, bukan statistik arbitrary.

### Keunggulan Bootstrap saat Distribusi Tidak Normal

1. **Tidak memerlukan asumsi distribusi:** Bootstrap bekerja langsung dari distribusi empiris data, sehingga secara otomatis menangkap skewness, heavy tails, dan bentuk distribusi apapun.
2. **Berlaku untuk statistik apapun:** Tidak hanya mean, tetapi juga bisa digunakan untuk median, rasio, koefisien korelasi, atau statistik kompleks lainnya yang tidak memiliki formula CI analitik.
3. **Menangkap asimetri:** CI bootstrap bisa **asimetris** (misalnya [5, 10] bukan simetris terhadap median 7.5), yang lebih realistis untuk data skewed. Metode normal selalu menghasilkan CI simetris yang bisa menyesatkan.
4. **Akurat untuk sampel kecil:** Ketika n kecil dan Central Limit Theorem belum berlaku, bootstrap tetap memberikan estimasi yang reasonable karena tidak bergantung pada asymptotics.
