#include <ctype.h>
#include <stdio.h>

int
main ()
{
  FILE *file;
  char ch;
  int count = 0;

  file = fopen ("angka.txt", "r");
  if (file == NULL)
    {
      perror ("Gagal membuka file");
      return 1;
    }

  while ((ch = fgetc (file)) != EOF)
    {
      if (isdigit (ch))
        {
          count++;
        }
    }

  fclose (file);

  printf ("Jumlah digit: %d\n", count);

  return 0;
}
