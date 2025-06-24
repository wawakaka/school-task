# Personal Assignment Practicum

## Tugas 1: Format Input and Output / Operator, Operand, and Arithmetic

```c
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

```

## Tugas 2: Program Control: Selection and Repetition / Pointers and Array

```c
#include <stdio.h>

const int INPUT_SIZE = 5;

void
maxScore (int *input)
{
  int max = input[0];
  for (int index = 0; index < INPUT_SIZE; index++)
    {
      if (input[index] > max)
        {
          max = input[index];
        }
    }
  printf ("maximum scrore are %d\n", max);
}

void
minScore (int *input)
{
  int min = input[0];
  for (int index = 0; index < INPUT_SIZE; index++)
    {
      if (input[index] < min)
        {
          min = input[index];
        }
    }
  printf ("minimum scrore are %d\n", min);
}

void
averageScore (int *input)
{
  int total = 0;
  for (int index = 0; index < INPUT_SIZE; index++)
    {
      total = total + input[index];
    }
  float average = (float)total / INPUT_SIZE;
  printf ("average scrore are %.1f\n", average);
}

int
main ()
{
  int input[INPUT_SIZE];
  int result;
  printf ("Enter five test score (integers & space separated) => ");
  scanf ("%d %d %d %d %d", &input[0], &input[1], &input[2], &input[3],
         &input[4]);
  maxScore (input);
  minScore (input);
  averageScore (input);
  return 0;
}
```
