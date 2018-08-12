/*
 * unittest4.c
 * Testing supplyCount function.
 * 
 * Include in makefile:
 *
 * unittest4: unittest4.c dominion.o rngs.o testutils.o
 *      gcc -o unittest4 -g unittest4.c dominion.o rngs.o testutils.o $(CFLAGS)
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

void setSupply(struct gameState *game, int count) {
	for (int i = 0; i <= treasure_map; i++) {
		game->supplyCount[i] = count;
	} 
}


int main(int argc, char *argv[]) {
	struct gameState *game;
	game = newGame();
	int ret;
	int count;
	
	// test for empty
	count = 0;
	setSupply(game,count);
	for (int i = 0; i <= treasure_map; i++) {
		ret = supplyCount(i,game);
		assertLite(__LINE__,ret,count,1, NOISY_TEST);
	}
	
	// test for negative
	count = -1;
	setSupply(game,count);
	for (int i = 0; i <= treasure_map; i++) {
		ret = supplyCount(i,game);
		assertLite(__LINE__,ret,count,1, NOISY_TEST);
	}

	// test for positive
	count = 1;
	setSupply(game,count);
	for (int i = 0; i <= treasure_map; i++) {
		ret = supplyCount(i,game);
		assertLite(__LINE__,ret,count,1, NOISY_TEST);
	} 
	
	// test for extremely large
	count = 1024;
	setSupply(game,count);
	for (int i = 0; i <= treasure_map; i++) {
		ret = supplyCount(i,game);
		assertLite(__LINE__,ret,count,1, NOISY_TEST);
	} 

	printf("All tests complete.\n");

    return 0;
}


