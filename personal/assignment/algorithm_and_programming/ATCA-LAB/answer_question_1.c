#include <stdio.h>

void
addition (int *inputOne, int *inputTwo, int *inputThree)
{
  int result = *inputOne + *inputTwo + *inputThree;
  printf ("result of %d + %d + %d = %d \n", *inputOne, *inputTwo, *inputThree,
          result);
}

void
substraction (int *inputOne, int *inputTwo, int *inputThree)
{
  int result = *inputOne - *inputTwo - *inputThree;
  printf ("result of %d - %d - %d = %d \n", *inputOne, *inputTwo, *inputThree,
          result);
}

void
multiplication (int *inputOne, int *inputTwo, int *inputThree)
{
  int result = *inputOne * *inputTwo * *inputThree;
  printf ("result of %d * %d * %d = %d \n", *inputOne, *inputTwo, *inputThree,
          result);
}

void
division (int *inputOne, int *inputTwo, int *inputThree)
{
  if (*inputTwo == 0 || *inputThree == 0)
    {
      printf ("division() error cannot divided by 0\n");
      return;
    }
  int result = *inputOne / *inputTwo / *inputThree;
  printf ("result of %d / %d / %d = %d (integer) \n", *inputOne, *inputTwo,
          *inputThree, result);
}

void
divisionInFloat (int *inputOne, int *inputTwo, int *inputThree)
{
  if (*inputTwo == 0 || *inputThree == 0)
    {
      printf ("divisionInFloat() error cannot divided by 0.0\n");
      return;
    }
  float result = (float)*inputOne / *inputTwo / *inputThree;
  printf ("result of %d / %d / %d = %.1f (float) \n", *inputOne, *inputTwo,
          *inputThree, result);
}

void
average (int *inputOne, int *inputTwo, int *inputThree)
{
  int result = (*inputOne + *inputTwo + *inputThree) / 3;
  printf ("average of three input (%d, %d, %d) are %d \n", *inputOne,
          *inputTwo, *inputThree, result);
}

int
main ()
{
  int inputOne, inputTwo, inputThree;
  float result;
  printf ("Enter three integers (space separated) => ");
  scanf ("%d %d %d", &inputOne, &inputTwo, &inputThree);
  addition (&inputOne, &inputTwo, &inputThree);
  substraction (&inputOne, &inputTwo, &inputThree);
  multiplication (&inputOne, &inputTwo, &inputThree);
  division (&inputOne, &inputTwo, &inputThree);
  divisionInFloat (&inputOne, &inputTwo, &inputThree);
  average (&inputOne, &inputTwo, &inputThree);
  return 0;
}
