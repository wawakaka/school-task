#include <stdio.h>

int
factorial (int n)
{
  if (n == 0 || n == 1)
    {
      return 1;
    }
  else
    {
      return n * factorial (n - 1);
    }
}

int
main ()
{
  int n;

  printf ("Masukkan angka: ");
  scanf ("%d", &n);

  if (n < 0)
    {
      printf ("Tolong masukan bilangan bulat positif.\n");
    }
  else
    {
      printf ("%d! = %d\n", n, factorial (n));
    }

  return 0;
}
