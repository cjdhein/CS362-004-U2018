/*
 * unittest3.c
 * Testing whoseTurn function.
 * 
 * Include in makefile:
 *
 * unittest3: unittest3.c dominion.o rngs.o testutils.o
 *      gcc -o unittest3 -g unittest3.c dominion.o rngs.o testutils.o $(CFLAGS)
 *
 */

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"
#include "testutils.h"

// set NOISY_TEST to 0 to remove printfs from output
#define NOISY_TEST 1



int main(int argc, char *argv[]) {
	struct gameState *game;
	game = newGame();
	int ret;
	int turn;
		
	turn = 1;
	game->whoseTurn = turn;
	ret = whoseTurn(game);
	assertLite(__LINE__,ret,turn,1, NOISY_TEST);

	turn = 2;
	game->whoseTurn = turn;
	ret = whoseTurn(game);
	assertLite(__LINE__,ret,turn,1, NOISY_TEST);

	turn = 3;
	game->whoseTurn = turn;
	ret = whoseTurn(game);
	assertLite(__LINE__,ret,turn,1, NOISY_TEST);

	turn = 0;
	game->whoseTurn = turn;
	ret = whoseTurn(game);
	assertLite(__LINE__,ret,turn,1, NOISY_TEST); 

	turn = -1;
	game->whoseTurn = turn;
	ret = whoseTurn(game);
	assertLite(__LINE__,ret,turn,1, NOISY_TEST);

	turn = 99;
	game->whoseTurn = turn;
	ret = whoseTurn(game);
	assertLite(__LINE__,ret,turn,1, NOISY_TEST);

	printf("All tests complete.\n");

    return 0;
}


