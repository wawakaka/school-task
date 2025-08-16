#include <stdio.h>
#include <stdlib.h>

int main(void) {
    int studentCount;
    int *studentScores;

    printf("Enter number of students: ");
    if (scanf("%d", &studentCount) != 1 || studentCount <= 0) {
        fprintf(stderr, "Invalid number of students\n");
        return 1;
    }
    
    studentScores = malloc(studentCount * sizeof(int));
    if (studentScores == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        return 1;
    }

    for (int i = 0; i < studentCount; i++) {
        printf("Enter score for student %d: ", i + 1);
        if (scanf("%d", &studentScores[i]) != 1) {
            fprintf(stderr, "Invalid score\n");
            free(studentScores);
            return 1;
        }
    }

    int total = 0;
    for (int i = 0; i < studentCount; i++) {
        total += studentScores[i];
    }

    float average = (float)total / studentCount;
    printf("Average = %.2f\n", average);

    free(studentScores);
    return 0;
}
