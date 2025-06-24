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
