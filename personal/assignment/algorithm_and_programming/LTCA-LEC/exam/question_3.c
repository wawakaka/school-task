#include <stdio.h>

int
main ()
{
  float nilai, max;
  float *ptr = &nilai;

  printf ("Masukkan 5 nilai rasa: ");
  for (int i = 0; i < 5; i++)
    {
      scanf ("%f", ptr);

      if (i == 0 || *ptr > max)
        {
          max = *ptr;
        }
    }

  printf ("Skor rasa tertinggi: %.1f\n", max);
  return 0;
}
