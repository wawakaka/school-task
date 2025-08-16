#include <stdio.h>
#include <stdlib.h>

#define SOURCE_FILE "angka.txt"
#define ODD_FILE "ganjil.txt"
#define EVEN_FILE "genap.txt"

int readNumbers(const char *filePath, int **odd, int *oddCount, int **even, int *evenCount);
int writeToFiles(const char *filePath, int *numbers, int numberCount);

int main() {
    int *odd = NULL, *even = NULL;
    int oddCount = 0, evenCount = 0;

    if (readNumbers(SOURCE_FILE, &odd, &oddCount, &even, &evenCount) != 0) {
        return 1;
    }

    writeToFiles(EVEN_FILE, even, evenCount);
    writeToFiles(ODD_FILE, odd, oddCount);

    free(odd);
    free(even);

    return 0;
}

int readNumbers(const char *filePath, int **odd, int *oddCount, int **even, int *evenCount) {
    FILE *file = fopen(filePath, "r");
    if (file == NULL) {
        perror("error opening source file");
        return 1;
    }

    int number;
    while (fscanf(file, "%d", &number) == 1) {
        if (number % 2 == 0) {
            int *temp = realloc(*even, (*evenCount + 1) * sizeof(int));
            if (!temp) { perror("realloc failed"); fclose(file); return 1; }
            *even = temp;
            (*even)[(*evenCount)++] = number;
        } else {
            int *temp = realloc(*odd, (*oddCount + 1) * sizeof(int));
            if (!temp) { perror("realloc failed"); fclose(file); return 1; }
            *odd = temp;
            (*odd)[(*oddCount)++] = number;
        }
    }
    fclose(file);
    return 0;
}

int writeToFiles(const char *filePath, int *numbers, int numberCount) {
    FILE *file = fopen(filePath, "w");
    if (file == NULL) {
        perror("error opening file");
        return 1;
    }
    for (int i = 0; i < numberCount; i++) {
        fprintf(file, "%d ", numbers[i]);
    }
    fclose(file);
    return 0;
}
