# Personal Assignment 3 - Computational Statistics

Tugas ini dikerjakan dengan bantuan Claude untuk:
- Mencari dan merangkum referensi
- Memberikan klarifikasi konsep atau penjelasan teknis
- Memperbaiki format penulisan

---

## Soal 1: Eksplorasi Teknik Smoothing

### Perbedaan Utama Kernel Smoothers vs Smoothing Splines

**Kernel Smoothers** dan **Smoothing Splines** keduanya merupakan metode nonparametrik untuk menghaluskan data, namun memiliki filosofi dan mekanisme yang berbeda.

#### 1. Kernel Smoothers (Pendekatan Bobot Lokal)

Kernel smoother bekerja dengan cara **memberikan bobot lokal** pada observasi di sekitar titik yang ingin diestimasi. Formula umumnya:

$$\hat{m}(x) = \frac{\sum_{i=1}^{n} K\left(\frac{x - x_i}{h}\right) y_i}{\sum_{i=1}^{n} K\left(\frac{x - x_i}{h}\right)}$$

di mana:
- $K(\cdot)$ adalah fungsi kernel (misal Gaussian, Epanechnikov)
- $h$ adalah **bandwidth** yang mengontrol lebar jendela lokal

**Analogi:** Kernel smoother ibarat **kuas kecil** yang disapukan di atas data. Ukuran kuas (bandwidth) menentukan seberapa halus sapuan tersebut. Kuas kecil → detail tajam tapi berisik; kuas besar → halus tapi kehilangan detail.

**Contoh Aplikasi:**
- Estimasi kepadatan harga saham harian (density estimation).
- Analisis pola suhu lokal di wilayah geografis.
- Smoothing sinyal sensor IoT.

#### 2. Smoothing Splines (Pendekatan Penalti Kelengkungan)

Smoothing splines meminimalkan fungsi objektif yang mengombinasikan **kecocokan data** dengan **penalti kelengkungan**:

$$\min_{f} \sum_{i=1}^{n} (y_i - f(x_i))^2 + \lambda \int (f''(x))^2 \, dx$$

di mana $\lambda$ adalah parameter penalti yang mengontrol trade-off antara fit dan smoothness.

**Analogi:** Smoothing splines ibarat **karet gelang** yang direntangkan melalui titik-titik data. Karet tersebut berusaha menyentuh semua titik (fit), tetapi secara alami menolak lengkungan yang ekstrem (penalti $f''$). Nilai $\lambda$ besar → karet kaku (sangat halus, hampir linear); $\lambda$ kecil → karet lentur (hampir interpolasi).

**Contoh Aplikasi:**
- Pemodelan kurva pertumbuhan anak (growth curves).
- Analisis tren data makroekonomi (GDP, inflasi).
- Kurva dose-response dalam farmakologi.

### Perbandingan Ringkas

| Aspek | Kernel Smoothers | Smoothing Splines |
|-------|------------------|-------------------|
| Prinsip | Bobot lokal (local averaging) | Optimisasi global dengan penalti |
| Parameter tuning | Bandwidth $h$ | Parameter smoothing $\lambda$ |
| Komputasi | Relatif ringan per titik | Memerlukan solusi sistem linear |
| Perilaku di batas | Bias di boundary | Lebih baik di boundary |
| Kontinuitas | Tergantung kernel | Otomatis smooth (turunan kedua kontinu) |

### Kapan Menggunakan Masing-Masing?

**Gunakan Kernel Smoothers bila:**
- Data besar dan butuh estimasi titik tertentu saja.
- Ingin interpretasi lokal yang sederhana.
- Tidak memerlukan fungsi hasil yang sangat halus secara turunan.
- Eksplorasi awal (misal density estimation).

**Gunakan Smoothing Splines bila:**
- Diperlukan fungsi global yang halus dan turunannya bermakna.
- Data memiliki struktur tren keseluruhan.
- Akan digunakan untuk interpolasi/prediksi di banyak titik.
- Kualitas smoothing di area boundary penting.

---

## Soal 2: Semiparametric Model & Nuisance Parameters

### Pengaruh Nuisance Parameter terhadap Efisiensi Estimasi β

Dalam model semiparametrik, parameter dibagi menjadi dua:
- **Parameter utama** ($\beta$): parameter finite-dimensional yang menjadi fokus inferensi.
- **Nuisance parameter** ($\eta$): parameter tambahan (sering berdimensi tak hingga, seperti fungsi baseline hazard di Cox model atau fungsi error density) yang tidak langsung menarik tetapi harus diperhitungkan.

Model umum:
$$Y = X^\top \beta + g(Z) + \varepsilon$$

di mana $g(\cdot)$ adalah fungsi nonparametrik (nuisance).

#### Dampak Nuisance Parameter

1. **Peningkatan variansi estimator $\hat{\beta}$**: Karena $\eta$ juga harus diestimasi dari data, informasi yang tersedia untuk $\beta$ berkurang. Efisiensi estimator $\beta$ umumnya lebih rendah dibanding jika $\eta$ diketahui.

2. **Bias bila diabaikan**: Jika kita menganggap $\eta$ tetap atau mengabaikannya, estimasi $\hat{\beta}$ dapat menjadi **bias dan tidak konsisten**.

3. **Efisiensi semiparametrik**: Ada batas bawah teoretis (semiparametric efficiency bound) untuk variansi $\hat{\beta}$ yang dipengaruhi oleh struktur $\eta$.

**Ilustrasi "Faktor Tak Terlihat":**
Bayangkan memprediksi nilai ujian mahasiswa ($Y$) berdasarkan jam belajar ($X$, parameter utama $\beta$). Namun ada **faktor tak terlihat** seperti kualitas tidur atau kondisi psikologis ($g(Z)$). Jika faktor ini diabaikan:
- Estimasi efek jam belajar menjadi bias (misalnya, overestimasi karena confounding).
- Prediksi kurang akurat.

Sebaliknya, bila dimodelkan secara nonparametrik, kita harus "membayar" dengan data lebih banyak untuk mengestimasinya, sehingga variansi $\hat{\beta}$ bertambah.

### Konsep Profile Likelihood

**Profile likelihood** adalah teknik untuk mengeliminasi nuisance parameter dengan cara memaksimalkannya terhadap $\eta$ untuk setiap nilai $\beta$:

$$L_p(\beta) = \max_{\eta} L(\beta, \eta) = L(\beta, \hat{\eta}(\beta))$$

di mana $\hat{\eta}(\beta)$ adalah estimator $\eta$ saat $\beta$ dianggap tetap.

Langkah-langkah:
1. Untuk setiap kandidat $\beta$, cari $\hat{\eta}(\beta)$ yang memaksimalkan likelihood.
2. Substitusi kembali untuk mendapatkan $L_p(\beta)$.
3. Maksimalkan $L_p(\beta)$ terhadap $\beta$ untuk memperoleh $\hat{\beta}$.

### Mengapa Profile Likelihood Sering Digunakan?

1. **Reduksi dimensi**: Mengubah masalah optimisasi tinggi (bersama $\beta, \eta$) menjadi optimisasi pada $\beta$ saja.
2. **Inferensi yang valid**: Profile likelihood memberikan confidence interval dan uji hipotesis yang akurat, bahkan ketika $\eta$ berdimensi tinggi.
3. **Efisiensi asimtotik**: Di banyak kasus (misal Cox Proportional Hazards), profile likelihood menghasilkan estimator yang mencapai semiparametric efficiency bound.
4. **Praktis dan fleksibel**: Dapat dikombinasikan dengan teknik numerik seperti EM atau iteratif.
5. **Interpretabilitas**: Memfokuskan perhatian peneliti pada parameter yang bermakna secara ilmiah.

**Contoh klasik:** Pada Cox Proportional Hazards, baseline hazard $\lambda_0(t)$ dianggap nuisance dan dieliminasi via partial likelihood (bentuk khusus profile likelihood), sehingga hanya $\beta$ (koefisien kovariat) yang diestimasi.

---

## Soal 3: Statistik Bayesian dan ABC (Approximate Bayesian Computation)

### a. Prinsip Kerja ABC

Approximate Bayesian Computation (ABC) adalah metode inferensi Bayesian yang digunakan ketika **likelihood $p(y|\theta)$ sulit atau tidak mungkin dihitung secara eksplisit** (likelihood-free inference), namun kita mampu **mensimulasikan data** dari model.

#### Algoritma Dasar ABC (Rejection Sampling)

1. **Sample** parameter dari prior: $\theta^* \sim \pi(\theta)$.
2. **Simulasi** data dari model: $y^* \sim p(y|\theta^*)$.
3. **Hitung statistik ringkasan** $s(y^*)$ dan $s(y_{obs})$ (misal mean, varians, kuantil).
4. **Hitung jarak**: $d = \rho(s(y^*), s(y_{obs}))$, misal jarak Euclidean.
5. **Terima** $\theta^*$ jika $d < \epsilon$ (threshold/toleransi), tolak jika tidak.
6. Ulangi banyak kali → sampel yang diterima membentuk aproksimasi posterior $\pi(\theta | d < \epsilon) \approx \pi(\theta | y_{obs})$.

#### Peran Kunci

- **Simulasi**: Menggantikan evaluasi likelihood. Kita hanya butuh generator data dari model.
- **Statistik ringkasan $s(\cdot)$**: Mereduksi dimensi data (idealnya sufficient statistic).
- **Threshold $\epsilon$**:
  - $\epsilon \to 0$: aproksimasi makin akurat tapi penerimaan sangat rendah (komputasi mahal).
  - $\epsilon$ besar: lebih banyak sampel diterima tapi posterior lebih kasar (bias).
  - Ada trade-off antara akurasi dan efisiensi komputasi.

Variasi ABC lanjutan: ABC-MCMC, ABC-SMC (Sequential Monte Carlo), dan ABC dengan neural networks (NN-ABC) untuk efisiensi lebih tinggi.

### b. Keunggulan ABC dan Konteks Penggunaan

#### Keunggulan dibanding Pendekatan Konvensional

1. **Likelihood-free**: Bekerja tanpa ekspresi likelihood yang eksplisit — cocok untuk model kompleks seperti simulasi agent-based, model stokastik, atau sistem dinamis.
2. **Fleksibilitas model**: Mendukung model yang sangat rumit (non-linear, non-Gaussian, hierarki kompleks).
3. **Intuitif**: Konsep "simulasi lalu bandingkan" mudah dipahami dan diimplementasikan.
4. **Paralelisasi**: Simulasi independen mudah diparalelkan pada cluster/GPU.
5. **Skalabilitas model**: Dapat dipakai untuk inferensi pada model yang evaluasi likelihood-nya $O(n^3)$ atau lebih.

#### Konteks yang Cocok

ABC paling berguna ketika:

- **Likelihood intractable** tetapi simulasi data dari model mudah.
- **Model generatif kompleks** (simulator hitam).
- **Data memiliki struktur non-standar** (jaringan, gambar, data spasial).
- Peneliti ingin memasukkan **prior knowledge** Bayesian.

#### Contoh Aplikasi: Biologi & Genetika Populasi

**Coalescent models**: Dalam genetika populasi, kita ingin mengestimasi parameter demografis seperti:
- Ukuran populasi efektif ($N_e$)
- Laju migrasi antar populasi
- Waktu divergensi spesies

Likelihood untuk data genom (misal SNP dari ribuan individu) **sangat sulit dihitung** karena melibatkan integrasi atas semua kemungkinan silsilah coalescent. Namun, kita bisa **mensimulasikan** genealogi dan data DNA dengan mudah menggunakan simulator seperti `ms` atau `msprime`.

Prosedur ABC:
1. Sample $\theta = (N_e, \text{migrasi}, \text{waktu divergensi})$ dari prior.
2. Simulasikan data SNP.
3. Hitung statistik ringkasan: heterozygosity ($\pi$), Tajima's D, $F_{ST}$.
4. Bandingkan dengan data observasi; terima jika dekat.

Aplikasi lain:
- **Epidemiologi**: Estimasi $R_0$ pada model penyebaran penyakit stokastik.
- **Ekologi**: Dinamika populasi predator-prey.
- **Astrofisika**: Model pembentukan galaksi.
- **Ekonomi**: Agent-based models pasar keuangan.

### Ringkasan

ABC membuka pintu inferensi Bayesian untuk model-model kompleks yang sebelumnya tidak dapat dianalisis, dengan "harga" berupa aproksimasi yang dikontrol oleh pilihan statistik ringkasan dan threshold $\epsilon$.
