#include <stdbool.h>
#include <stdio.h>

int
search (int array[], int arraySize, int key)
{
  for (int i = 0; i < arraySize; i++)
    {
      if (array[i] == key)
        {
          return i;
        }
    }
  return -1;
}

// int iterativeBinarySearch(int array[], int arraySize, int key){
//   for (int i = 0; i < arraySize; i++)
//     {
//       if (array[i] == key)
//         {
//           return i;
//         }
//     }
// }

void
swap (int *first, int *second)
{
  if (first == second)
    return;
  *first ^= *second;
  *second ^= *first;
  *first ^= *second;
}

void
bubbleSort (int array[], int arraySize)
{
  int leftPos, rightPos;
  bool swapped;
  for (leftPos = 0; leftPos < arraySize; leftPos++)
    {
      swapped = false;
      for (rightPos = 0; rightPos < arraySize - leftPos - 1; rightPos++)
        {
          if (array[rightPos] > array[rightPos + 1])
            {
              swap (&array[rightPos], &array[rightPos + 1]);
              swapped = true;
            }
        }
      if (swapped == false)
        break;
    }
}

void
printArray (int arr[], int size)
{
  int i;
  for (i = 0; i < size; i++)
    printf ("%d ", arr[i]);
}

int
main (int argc, char const *argv[])
{
  int array[] = { 2, 4, 0, 1, 9 };
  int arraySize = sizeof (array) / sizeof (array[0]);
  //   int result = search (array, arraySize, 1);
  //   (result == -1) ? printf ("Elemen is not present in array\n")
  //                  : printf ("Elemen is present at index %d\n", result);

  bubbleSort (array, arraySize);
  printf ("Sorted array: \n");
  printArray (array, arraySize);

  return 0;
}