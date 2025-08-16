#include <stdio.h>
#include <stdlib.h>

// insertion sort
void
insertionSort (int a[], int n)
{
  for (int i = 1; i < n; ++i)
    {
      int key = a[i];
      int j = i - 1;
      while (j >= 0 && a[j] > key)
        { // shift larger elements rightward
          a[j + 1] = a[j];
          --j;
        }
      a[j + 1] = key;
    }
}

// linear search
int
linearSearch (const int a[], int n, int key)
{
  for (int i = 0; i < n; ++i)
    if (a[i] == key)
      return i;
  return -1;
}

int
main (void)
{
  int n;

  printf ("Enter how many numbers you want to sort: ");
  if (scanf ("%d", &n) != 1 || n <= 0)
    {
      fprintf (stderr, "Invalid count.\n");
      return 1;
    }

  int *data = malloc (n * sizeof *data); // dynamic array
  if (!data)
    {
      perror ("malloc");
      return 1;
    }

  // read the numbers
  printf ("Enter %d integers separated by space/newline:\n", n);
  for (int i = 0; i < n; ++i)
    {
      if (scanf ("%d", &data[i]) != 1)
        {
          fprintf (stderr, "Bad input.\n");
          free (data);
          return 1;
        }
    }

  // sort
  insertionSort (data, n);

  // show sorted list
  printf ("\nSorted numbers: ");
  for (int i = 0; i < n; ++i)
    printf ("%d%c", data[i], i == n - 1 ? '\n' : ' ');

  // search
  int key;
  printf ("\nEnter a number to search for: ");
  if (scanf ("%d", &key) != 1)
    {
      fprintf (stderr, "Bad input.\n");
      free (data);
      return 1;
    }

  int pos = linearSearch (data, n, key);
  if (pos != -1)
    printf ("%d found at index %d\n",
            key, pos);
  else
    printf ("%d not found in the list.\n", key);

  free (data);
  return 0;
}
