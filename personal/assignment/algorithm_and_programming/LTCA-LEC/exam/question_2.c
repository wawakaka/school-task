#include <stdio.h>

int
main ()
{
  int maxTime;
  int i = 4;

  printf ("Masukkan waktu maksimal: ");
  scanf ("%d", &maxTime);

  while (i <= maxTime)
    {
      printf ("%d ", i);
      i += 4;
    }

  printf ("\n");
  return 0;
}
