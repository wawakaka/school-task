#include <stdio.h>

float
f (float aa, float bb)

{

  return ((float)aa + bb);
}

int
main ()

{

  int a;

  a = f (2.5, 4.61);

  printf ("%d", a);

  return 0;
}