#include <stdio.h>

int
main ()
{
  int a, b, c;

  printf ("Masukkan bilangan bulat pertama: ");
  scanf ("%d", &a);
  printf ("Masukkan bilangan bulat kedua: ");
  scanf ("%d", &b);
  printf ("Masukkan bilangan bulat ketiga: ");
  scanf ("%d", &c);

  if (a >= b && a >= c)
    {
      printf ("Bilangan terbesar adalah %d\n", a);
    }
  else if (b >= a && b >= c)
    {
      printf ("Bilangan terbesar adalah %d\n", b);
    }
  else
    {
      printf ("Bilangan terbesar adalah %d\n", c);
    }

  return 0;
}
