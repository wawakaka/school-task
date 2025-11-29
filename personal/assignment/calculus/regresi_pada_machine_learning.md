### **Data yang diberikan:**

| Lama Belajar (X) | Nilai Ujian (Y) |
| ---------------- | --------------- |
| 1                | 60              |
| 2                | 65              |
| 3                | 75              |
| 4                | 80              |
| 5                | 85              |
| 6                | 90              |

Kita ingin mencari **persamaan regresi linear sederhana**:
$
Y = a + bX
$
dengan:

* ( a ) = intersep (titik potong)
* ( b ) = koefisien arah (kemiringan/slope)

### **Langkah 1: Hitung rata-rata X dan Y**

$
\bar{X} = \frac{1+2+3+4+5+6}{6} = \frac{21}{6} = 3.5
$
$
\bar{Y} = \frac{60+65+75+80+85+90}{6} = \frac{455}{6} = 75.83
$

### **Langkah 2: Hitung nilai b (kemiringan)**

Rumus:
$
b = \frac{\sum (X - \bar{X})(Y - \bar{Y})}{\sum (X - \bar{X})^2}
$

| X     | Y  | X−X̄ | Y−Ȳ   | (X−X̄)(Y−Ȳ) | (X−X̄)²  |
| ----- | -- | ---- | ------ | ------------ | -------- |
| 1     | 60 | -2.5 | -15.83 | 39.575       | 6.25     |
| 2     | 65 | -1.5 | -10.83 | 16.245       | 2.25     |
| 3     | 75 | -0.5 | -0.83  | 0.415        | 0.25     |
| 4     | 80 | 0.5  | 4.17   | 2.085        | 0.25     |
| 5     | 85 | 1.5  | 9.17   | 13.755       | 2.25     |
| 6     | 90 | 2.5  | 14.17  | 35.425       | 6.25     |
| **Σ** |    |      |        | **107.5**    | **17.5** |

$
b = \frac{107.5}{17.5} = 6.14
$

### **Langkah 3: Hitung nilai a (intersep)**

$
a = \bar{Y} - b\bar{X}
$
$
a = 75.83 - (6.14)(3.5) = 75.83 - 21.49 = 54.34
$

### **Persamaan Regresi Linear:**

$
\boxed{Y = 54.34 + 6.14X}
$


### **Interpretasi:**

* Setiap tambahan **1 jam waktu belajar per hari**, nilai ujian meningkat sekitar **6.14 poin**.
* Jika seorang siswa **tidak belajar sama sekali (X = 0)**, maka nilai ujian yang diprediksi adalah sekitar **54.34**.