#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define FILE_NAME "students.csv"
#define TEMP_FILE "temp.csv"
#define LINE_SIZE 256

void addStudent();
void deleteStudent();
void searchStudent();

int main() {
    int choice;
    do {
        printf("\n=== Student Database Management ===\n");
        printf("1. Add Student\n");
        printf("2. Delete Student\n");
        printf("3. Search Student\n");
        printf("4. Exit\n");
        printf("Enter your choice: ");
        if (scanf("%d", &choice) != 1) {
            while (getchar() != '\n');
            printf("Invalid input. Try again.\n");
            continue;
        }
        getchar(); // clear newline
        switch (choice) {
            case 1: addStudent(); break;
            case 2: deleteStudent(); break;
            case 3: searchStudent(); break;
            case 4: printf("Exiting program.\n"); break;
            default: printf("Invalid choice. Try again.\n"); break;
        }
    } while (choice != 4);
    return 0;
}

void addStudent() {
    char regNo[20], name[100];
    int age;
    printf("Enter Registration Number: ");
    fgets(regNo, sizeof(regNo), stdin);
    regNo[strcspn(regNo, "\n")] = '\0';
    printf("Enter Name: ");
    fgets(name, sizeof(name), stdin);
    name[strcspn(name, "\n")] = '\0';
    printf("Enter Age: ");
    scanf("%d", &age);
    getchar(); // clear newline

    FILE *fp = fopen(FILE_NAME, "a");
    if (!fp) {
        perror("Failed to open file");
        return;
    }
    fprintf(fp, "%s,%s,%d\n", regNo, name, age);
    fclose(fp);
    printf("Student added successfully.\n");
}

void deleteStudent() {
    char targetRegNo[20];
    printf("Enter Registration Number to delete: ");
    fgets(targetRegNo, sizeof(targetRegNo), stdin);
    targetRegNo[strcspn(targetRegNo, "\n")] = '\0';

    FILE *infile = fopen(FILE_NAME, "r");
    FILE *outfile = fopen(TEMP_FILE, "w");
    if (!infile || !outfile) {
        perror("Failed to open file");
        if (infile) fclose(infile);
        if (outfile) fclose(outfile);
        return;
    }

    char line[LINE_SIZE];
    int found = 0;
    while (fgets(line, sizeof(line), infile)) {
        char regNo[20], name[100];
        int age;
        char *token = strtok(line, ",");
        if (token) strcpy(regNo, token);
        token = strtok(NULL, ",");
        if (token) strcpy(name, token);
        token = strtok(NULL, "\n");
        if (token) age = atoi(token);

        if (strcmp(regNo, targetRegNo) == 0) {
            found = 1;
        } else {
            fprintf(outfile, "%s,%s,%d\n", regNo, name, age);
        }
    }
    fclose(infile);
    fclose(outfile);

    if (found) {
        remove(FILE_NAME);
        rename(TEMP_FILE, FILE_NAME);
        printf("Student with regNo %s deleted successfully.\n", targetRegNo);
    } else {
        remove(TEMP_FILE);
        printf("Student with regNo %s not found.\n", targetRegNo);
    }
}

void searchStudent() {
    char targetRegNo[20];
    printf("Enter Registration Number to search: ");
    fgets(targetRegNo, sizeof(targetRegNo), stdin);
    targetRegNo[strcspn(targetRegNo, "\n")] = '\0';

    FILE *fp = fopen(FILE_NAME, "r");
    if (!fp) {
        perror("Failed to open file");
        return;
    }

    char line[LINE_SIZE];
    int found = 0;
    while (fgets(line, sizeof(line), fp)) {
        char regNo[20], name[100];
        int age;
        char *token = strtok(line, ",");
        if (token) strcpy(regNo, token);
        token = strtok(NULL, ",");
        if (token) strcpy(name, token);
        token = strtok(NULL, "\n");
        if (token) age = atoi(token);

        if (strcmp(regNo, targetRegNo) == 0) {
            printf("Found: regNo=%s, Name=%s, Age=%d\n", regNo, name, age);
            found = 1;
            break;
        }
    }
    if (!found) {
        printf("Student with regNo %s not found.\n", targetRegNo);
    }
    fclose(fp);
}
