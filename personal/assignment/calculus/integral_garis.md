## Penyelesaian

Diketahui sebuah kawat berbentuk heliks dengan parameterisasi:

$$
x = 3\cos t, \quad y = 3\sin t, \quad z = 4t, \quad 0 \le t \le \pi
$$

dan kerapatan kawat diberikan oleh:

$$
\delta(x, y, z) = kz
$$

Kita diminta untuk mencari massa kawat tersebut.

---

### 1. Rumus Umum Massa

Massa total kawat dapat dihitung dengan integral garis:

$$
M = \int_C \delta(x, y, z) \, ds
$$

dengan

$$
ds = |r'(t)| \, dt
$$

---

### 2. Menentukan Kerapatan dalam Parameter \(t\)

Dari parameterisasi \(z = 4t\), maka:

$$
\delta(t) = k \cdot z = k(4t) = 4kt
$$

---

### 3. Menentukan Panjang Elemen Busur

Turunan posisi terhadap \(t\):

$$
r'(t) = (-3\sin t, \, 3\cos t, \, 4)
$$

Sehingga panjang vektor turunan:

$$
|r'(t)| = \sqrt{(-3\sin t)^2 + (3\cos t)^2 + 4^2}
$$

$$
|r'(t)| = \sqrt{9(\sin^2 t + \cos^2 t) + 16} = \sqrt{25} = 5
$$

---

### 4. Substitusi ke dalam Integral Massa

$$
M = \int_0^{\pi} \delta(t) \, |r'(t)| \, dt
$$

$$
M = \int_0^{\pi} (4kt)(5) \, dt = 20k \int_0^{\pi} t \, dt
$$

---

### 5. Hitung Integral

$$
M = 20k \left[ \frac{t^2}{2} \right]_0^{\pi} = 20k \cdot \frac{\pi^2}{2} = 10k\pi^2
$$

---

### **Hasil Akhir**

$$
\boxed{M = 10k\pi^2}
$$
