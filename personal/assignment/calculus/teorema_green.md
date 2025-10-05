## Gunakan Teorema Green

Gunakan teorema Green untuk menghitung integral garis:

$$
\oint_{C} (x^{3} + 2y)\,dx + (4x - 3y^{2})\,dy
$$

dengan \( C \) adalah elips:

$$
\frac{x^{2}}{a^{2}} + \frac{y^{2}}{b^{2}} = 1
$$

---

### Langkah-langkah Penyelesaian

1. Tentukan fungsi:
   $$
   P(x,y) = x^{3} + 2y, \quad Q(x,y) = 4x - 3y^{2}
   $$

2. Berdasarkan **Teorema Green**:

   $$
   \oint_{C} P\,dx + Q\,dy = \iint_{D} 
   \left( 
     \frac{\partial Q}{\partial x} - \frac{\partial P}{\partial y} 
   \right) dA
   $$

   di mana \( D \) adalah daerah yang dibatasi oleh kurva \( C \).

3. Hitung turunan parsial:
   $$
   \frac{\partial Q}{\partial x} = \frac{\partial}{\partial x}(4x - 3y^{2}) = 4
   $$
   $$
   \frac{\partial P}{\partial y} = \frac{\partial}{\partial y}(x^{3} + 2y) = 2
   $$

4. Maka:
   $$
   \frac{\partial Q}{\partial x} - \frac{\partial P}{\partial y} = 4 - 2 = 2
   $$

5. Substitusikan ke integral:
   $$
   \oint_{C} P\,dx + Q\,dy = \iint_{D} 2\,dA = 2 \cdot \text{Area}(D)
   $$

6. Luas daerah elips adalah:
   $$
   \text{Area}(D) = \pi a b
   $$

   Jadi:
   $$
   \oint_{C} (x^{3} + 2y)\,dx + (4x - 3y^{2})\,dy = 2 \pi a b
   $$

---

### Hasil Akhir

$$
\boxed{
\oint_{C} (x^{3} + 2y)\,dx + (4x - 3y^{2})\,dy = 2\pi a b
}
$$
