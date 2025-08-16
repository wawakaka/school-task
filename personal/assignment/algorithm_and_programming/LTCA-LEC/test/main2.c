#include<stdio.h>

 

void main()

{

    int n=12;

    int f(int n);

    printf("%d",f(n));

}

int f(int n)

{

    if(n>0)

        return(n+f(n-4));

}