/*
 * unittest1.c
 * Testing isGameOver function.
 * 
 * Include in makefile:
 *
 * unittest1: unittest1.c dominion.o rngs.o testutils.o
 *      gcc -o unittest1 -g unittest1.c dominion.o rngs.o testutils.o $(CFLAGS)
 *
 */

#include <string.h>
#include <stdio.h>
#include <time.h>
#include <assert.h>
#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"
#include "testutils.h"

// set NOISY_TEST to 0 to remove printfs from output
#define NOISY_TEST 1

void test2(struct gameState* game){
		for(int i=0; i <= treasure_map;i++){
			game->supplyCount[i] = -1;
		}			
}

void test3(struct gameState* game){
		for(int i=0; i <= treasure_map;i++){
			game->supplyCount[i] = 1;
		}			
}

void test4(struct gameState* game) {
		for(int i=0; i <= treasure_map;i++){
			game->supplyCount[i] = -1;
		}			
		game->supplyCount[province] = 0;
}

void test5(struct gameState* game) {
		for(int i=0; i <= treasure_map;i++){
			game->supplyCount[i] = -1;
		}			
		game->supplyCount[curse] = 0;
		game->supplyCount[estate] = 0;
		game->supplyCount[treasure_map] = 0;	
}


int main(int argc, char *argv[]) {
		struct gameState *game;
		game = newGame();
		int ret;
		
		
		//Test all at 0. Expected return 1
		ret = isGameOver(game);
#if (NOISY_TEST == 1)
		printf("\nTest 1 - Testing all supply at 0. Expected return 1.\n");
		printf("Actual return %d\n",ret);
#endif		
		assertLite(__LINE__, ret,1,1, NOISY_TEST);
			
		
		//Test all at -1. Expected return 0
		test2(game);
		ret = isGameOver(game);
#if (NOISY_TEST == 1)
		printf("\nTest 2 - Testing all supply at -1. Expected return 0.\n");
		printf("Actual return %d\n",ret);
#endif				
		assertLite(__LINE__, ret,0,1, NOISY_TEST);
		
		//Test all at 1. Expected return 0
		test3(game);
		ret = isGameOver(game);
#if (NOISY_TEST == 1)
		printf("\nTest 3 - Testing all supply at 1. Expected return 0.\n");
		printf("Actual return %d\n\n",ret);
#endif				
		assertLite(__LINE__, ret,0,1, NOISY_TEST);

		//Test province at 0. Expected return 1
		test4(game);
		ret = isGameOver(game);
#if (NOISY_TEST == 1)
		printf("\nTest 4 - Testing province supply at 0. Expected return 1.\n");
		printf("Actual return %d\n",ret);
#endif				
		assertLite(__LINE__, ret,1,1, NOISY_TEST);
		
		//Test curse(0), estate(1) and treasure_map(26) at 0. Expected return 1
		test5(game);
		ret = isGameOver(game);
#if (NOISY_TEST == 1)
		printf("\nTest 5 - Testing curse(0), estate(1) and treasure_map(26) at 0. Expected return 1.\n");
		printf("Actual return %d\n",ret);
#endif				
		assertLite(__LINE__, ret,1,1, NOISY_TEST);
	
		printf("Testing complete.\n");	
    return 0;
}


