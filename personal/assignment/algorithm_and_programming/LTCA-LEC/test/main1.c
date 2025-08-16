#include <stdio.h>

    typedef struct p

    {

        int x, y;

    }k;

    int main()

    {

        struct p p = {1, 2};

        k k1 = p;

        printf("%d\n", k1.x);

    }