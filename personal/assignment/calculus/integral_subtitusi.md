## Soal Integral

### 1. $\int x^3 (x^4 + 5)^3 dx$

Misal:
$
u = x^4 + 5 \quad \Rightarrow \quad du = 4x^3 dx \quad \Rightarrow \quad x^3 dx = \tfrac{1}{4}du
$

Maka:
$
\int x^3 (x^4 + 5)^3 dx = \tfrac{1}{4} \int u^3 du
$

$
= \tfrac{1}{4} \cdot \tfrac{u^4}{4} + C = \tfrac{1}{16}(x^4+5)^4 + C
$

**Hasil:**
$
\int x^3 (x^4 + 5)^3 dx = \frac{1}{16}(x^4+5)^4 + C
$

---

### 2. $\int_{2}^{3} x\sqrt{x-2}\, dx$

Substitusi:
$
u = x-2 \quad \Rightarrow \quad du = dx, \quad x = u+2
$

Batas baru:
$
x=2 \Rightarrow u=0, \quad x=3 \Rightarrow u=1
$

Sehingga:
$
\int_{2}^{3} x\sqrt{x-2}\, dx = \int_{0}^{1} (u+2)\sqrt{u}\, du
$

$
= \int_{0}^{1} \big(u^{3/2} + 2u^{1/2}\big) du
$

$
= \left[\tfrac{2}{5}u^{5/2} + \tfrac{4}{3}u^{3/2}\right]_{0}^{1}
$

$
= \tfrac{2}{5} + \tfrac{4}{3} = \tfrac{26}{15}
$

**Hasil:**
$
\int_{2}^{3} x\sqrt{x-2}\, dx = \frac{26}{15}
$
