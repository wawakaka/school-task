#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <uuid/uuid.h>

#define MAX_STR_LEN 256
#define MAX_BOOKS 64
#define MAX_HISTORIES 64
#define BOOK_FILE_PATH "databuku.txt"

static char *
read_line_alloc (const char *prompt)
{
  char buf[256];

  printf ("%s", prompt);

  if (!fgets (buf, sizeof buf, stdin))
    return NULL;
  buf[strcspn (buf, "\n")] = '\0';

  char *p = malloc (strlen (buf) + 1);
  if (!p)
    return NULL;
  strcpy (p, buf);
  return p;
}

int
main ()
{
  char *bookName = NULL;
  char *bookType = NULL;
  char *price = NULL;
  FILE *file = NULL;

  // Ask inputs (one by one)
  if (!(bookName = read_line_alloc ("Masukan nama buku: ")))
    goto cleanup;
  if (!(bookType = read_line_alloc ("Masukan tipe buku: ")))
    goto cleanup;
  if (!(price = read_line_alloc ("Masukan harga: ")))
    goto cleanup;

  // Open file (append)
  file = fopen (BOOK_FILE_PATH, "a");
  if (!file)
    goto cleanup;

  // UUID
  uuid_t binuuid;
  char uuid_str[37]; // 36 + '\0'
  uuid_generate_random (binuuid);
  uuid_unparse_lower (binuuid, uuid_str);

  // Write as CSV: uuid,name,type,price
  if (fprintf (file, "%s,%s,%s,%s\n", uuid_str, bookName, bookType, price) < 0)
    goto cleanup;

  if (fclose (file) != 0)
    {
      file = NULL;
      goto cleanup;
    }
  file = NULL;

cleanup:
  if (file)
    fclose (file);
  free (bookName);
  free (bookType);
  free (price);
  return 0;
  return 0;
}