#include <stdio.h>

int
main ()
{
  int inputDay = 0;
  printf ("Enter how many days of temperatures you want to input (1-7) => ");
  scanf ("%d", &inputDay);
  if (inputDay < 1 || inputDay > 7)
    {
      printf ("Input %d is invalid. exit", inputDay);
      return 0;
    }

  int temperature[inputDay];
  for (int i = 0; i < inputDay; i++)
    {
      printf ("Input temperature %d ", i);
      scanf ("%d", &temperature[i]);
    }
  int *ptr;
  ptr = temperature;
  int average;

  // for (int i = 0; i < inputDay; i++)
  // {
  //     printf ("index %d = %d \n", i, *(ptr + i));
  //     average += *(ptr + i);
  // }
  int index = 0;
  while (index < inputDay)
    {
      printf ("index %d = %d \n", index, *(ptr + index));
      average += *(ptr + index);
      index++;
    }
  average = average / inputDay;

  printf ("average = %d \n", average);

  if (average < 20)
    {
      printf ("COLD");
    }
  else if (average < 30)
    {
      printf ("WARM");
    }
  else
    {
      printf ("HOT");
    }

  return 0;
}
