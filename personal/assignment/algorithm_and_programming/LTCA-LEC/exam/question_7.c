#include <stdio.h>

int
sum (int a, int b)
{
  return a + b;
}

int
main ()
{
  int (*ptr) (int, int) = sum;
  printf ("%d\n", ptr (3, 4));
}
