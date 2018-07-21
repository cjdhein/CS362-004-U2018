#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<time.h>

char inputChar()
{
	int asciiVals[9] = {91, 40, 123, 125, 41, 93, 32, 97, 120};
	int randInt;
	randInt = rand() % 9;

	return asciiVals[randInt];
}

char *inputString()
{
	int asciiVals[4] = {114, 101, 115, 116};
	int size = 5;
	int randInt;
	// TODO: rewrite this function
	
	char *str;
	str = malloc(sizeof(char) * size);

	for (int i = 0; i < size; i++) {
		randInt = rand() % 4;
		str[i] = asciiVals[randInt];
	}

	return str;
}

void testme()
{
	int tcCount = 0;
	char *s;
	char c;
	int state = 0;
	while (1) {
		tcCount++;
		c = inputChar();
		s = inputString();
		printf("Iteration %d: c = %c, s = %s, state = %d\n", tcCount, c, s, state);

		if (c == '[' && state == 0) state = 1;
		if (c == '(' && state == 1) state = 2;
		if (c == '{' && state == 2) state = 3;
		if (c == ' '&& state == 3) state = 4;
		if (c == 'a' && state == 4) state = 5;
		if (c == 'x' && state == 5) state = 6;
		if (c == '}' && state == 6) state = 7;
		if (c == ')' && state == 7) state = 8;
		if (c == ']' && state == 8) state = 9;
		if (s[0] == 'r' && s[1] == 'e'
			 && s[2] == 's' && s[3] == 'e'
			 && s[4] == 't' && s[5] == '\0'
			 && state == 9)	{
				printf("error ");
				exit(200);
		}
	}
}


int main(int argc, char *argv[])
{
	srand(time(NULL));
	testme();
	//printf("%s\n",inputString());
	return 0;
}