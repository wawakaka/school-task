#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define NAME_LEN 100

typedef struct
{
  char name[NAME_LEN];
  int price;
  int stock;
} Product;

int
main (void)
{
  int n;

  /* Ask how many products */
  printf ("Enter number of products: ");
  if (scanf ("%d", &n) != 1 || n <= 0)
    {
      fprintf (stderr, "Invalid number.\n");
      return 1;
    }

  /* Allocate array dynamically (safer for larger n) */
  Product *items = malloc (n * sizeof (Product));
  if (!items)
    {
      perror ("malloc");
      return 1;
    }

  /* Read each product */
  for (int i = 0; i < n; ++i)
    {
      printf ("\nProduct %d\n", i + 1);

      /* Clear newline left by previous scanf */
      getchar ();

      printf ("- Name  : ");
      fgets (items[i].name, NAME_LEN, stdin);
      /* Remove trailing newline from fgets */
      items[i].name[strcspn (items[i].name, "\n")] = '\0';

      printf ("- Price : ");
      if (scanf ("%d", &items[i].price) != 1)
        {
          fprintf (stderr, "Invalid price.\n");
          free (items);
          return 1;
        }

      printf ("- Stock : ");
      if (scanf ("%d", &items[i].stock) != 1)
        {
          fprintf (stderr, "Invalid stock.\n");
          free (items);
          return 1;
        }
    }

  /* Find the product with the highest price */
  int idx_max = 0;
  for (int i = 1; i < n; ++i)
    {
      if (items[i].price > items[idx_max].price)
        {
          idx_max = i;
        }
    }

  /* Output the result */
  printf ("\nProduct with the highest price:\n");
  printf ("Name : %s\n", items[idx_max].name);
  printf ("Price: %d\n", items[idx_max].price);
  printf ("Stock: %d\n", items[idx_max].stock);

  /* Clean up */
  free (items);
  return 0;
}
