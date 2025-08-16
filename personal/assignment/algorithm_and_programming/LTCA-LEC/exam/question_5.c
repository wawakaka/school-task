#include <ctype.h>
#include <stdio.h>
#include <string.h>

int
main ()
{
  char kalimat[256];

  printf ("Masukkan sebuah kalimat: ");
  fgets (kalimat, sizeof (kalimat), stdin);

  for (int i = 0; i < strlen (kalimat); i++)
    {
      kalimat[i] = tolower (kalimat[i]);
    }

  printf ("Hasil: %s", kalimat);

  return 0;
}
