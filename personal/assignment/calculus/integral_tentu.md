# Penyelesaian Soal Integral Tentu

## Soal 1
Diketahui \(f(x)=x-2\). Tentukan luas daerah arsiran pada interval \([0,6]\).

Titik potong dengan sumbu \(x\):
$
x-2=0 \Rightarrow x=2
$

Karena tanda berubah di \(x=2\), luas total adalah:
$
L = \int_{0}^{2} |x-2|\,dx + \int_{2}^{6} |x-2|\,dx.
$

Pada \([0,2]\), \(|x-2| = 2-x\); pada \([2,6]\), \(|x-2| = x-2\). Maka
$
L = \int_{0}^{2} (2-x)\,dx + \int_{2}^{6} (x-2)\,dx.
$

Hitung masing-masing:
$
\int_{0}^{2} (2-x)\,dx 
= \left[\,2x - \tfrac{x^2}{2}\,\right]_{0}^{2} 
= \left(2(2) - \tfrac{2^2}{2}\right) - \left(2(0) - \tfrac{0^2}{2}\right) 
= (4 - 2) - 0 = 2,
$

$
\int_{2}^{6} (x-2)\,dx
= \left[\,\tfrac{x^2}{2} - 2x\,\right]_{2}^{6}
= \left(\tfrac{36}{2} - 12\right) - \left(\tfrac{4}{2} - 4\right)
= (18 - 12) - (2 - 4) 
= 6 - (-2) = 8.
$


Sehingga:
$
L = 2 + 8 = 10.
$

**Jawaban Soal 1:** $L=10$.

---

## Soal 2
Diketahui
$
\int_{-10}^{6} f(x)\,dx = 23,\qquad \int_{-10}^{6} g(x)\,dx = -9.
$
Tentukan:
$
\int_{-10}^{6} \big(2f(x) - 4g(x)\big)\,dx.
$

Gunakan linearitas integral:
$
\int_{-10}^{6} \big(2f(x) - 4g(x)\big)\,dx
= 2\int_{-10}^{6} f(x)\,dx - 4\int_{-10}^{6} g(x)\,dx.
$

Substitusi:
$
= 2(23) - 4(-9) = 46 + 36 = 82.
$

**Jawaban Soal 2:** 82.
