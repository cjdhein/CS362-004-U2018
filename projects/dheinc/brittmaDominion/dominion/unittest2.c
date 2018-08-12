/*
 * unittest2.c
 * Testing shuffle function.
 * 
 * Include in makefile:
 *
 * unittest2: unittest2.c dominion.o rngs.o testutils.o
 *      gcc -o unittest2 -g unittest2.c dominion.o rngs.o testutils.o $(CFLAGS)
 *
 */

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"
#include "testutils.h"

// set NOISY_TEST to 0 to remove printfs from output
#define NOISY_TEST 1

#define TEST_DECK_SIZE 150

void setup(struct gameState *game, int deckNum) {
	
	// add cards to deck
	game->deckCount[deckNum] = 0;

	int card = 0;	
	int i;
	for (i = 0; i < TEST_DECK_SIZE; i++) {
		game->deckCount[deckNum]++;
		game->deck[deckNum][i] = card;
		
		if (card < 26)
			card++;
		else
			card = 0;
	}
		
}

// Test to ensure same cards are in the deck and deckCount is the same
void test1(struct gameState *game, int deckNum){

	int ret;
	int preSize = game->deckCount[deckNum];
	
	// sort for easier reading and comparing
	qsort ((void*)(game->deck[deckNum]), game->deckCount[deckNum], sizeof(int), testCompare);

	// create and copy to preDeck deck
	int preDeck[TEST_DECK_SIZE];
	memcpy(preDeck,game->deck[deckNum],TEST_DECK_SIZE*sizeof(int));

	// Begin shuffle & test
	
#if (NOISY_TEST == 1)
	printf("Testing for card count, card order, and which cards are in deck over multiple iterations...\n");
#endif
	
	for (int i = 0; i < 50; i++) {
		shuffle(deckNum,game);
		
		//	confirm same number of cards post shuffle
		assertLite(__LINE__,preSize,game->deckCount[deckNum],1, NOISY_TEST);
			

		//	compare memory to ensure that it is different post-shuffle
		//		this shows that the cards have changed their order (next step shows same cards exist)
		ret = memcmp(game->deck[deckNum],preDeck,sizeof(preDeck));
		assertLite(__LINE__,ret,0,0, NOISY_TEST);
		
		//	sort shuffled deck to bring it back to starting order
		//		this allows us to show the same cards are in the deck pre and post shuffle
		qsort ((void*)(game->deck[deckNum]), game->deckCount[deckNum], sizeof(int), testCompare);
		ret = memcmp(game->deck[deckNum],preDeck,sizeof(preDeck));
		assertLite(__LINE__,ret,0,1, NOISY_TEST);
	}

}

int main(int argc, char *argv[]) {
	time_t t;
	srand((unsigned) time(&t));
	struct gameState *game;
	game = newGame();
	int ret;

	// setup a multi-card deck and test
	setup(game,0);
	test1(game,0);	

	// set a deck to a single card and test return value (should be 0)
	printf("Testing shuffle on single card deck...\n");
	game->deck[1][0] = 1;
	game->deckCount[1] = 1;
	ret = shuffle(1,game);
	assertLite(__LINE__,ret,0,1, NOISY_TEST);

	// set a deck to zero and test return value (should be -1)
	printf("Testing shuffle on zero card deck...\n");
	game->deckCount[2] = 0;
	ret = shuffle(2,game);
	assertLite(__LINE__,ret,-1,1, NOISY_TEST);
	
	// set a deck to -20 and test return value (should be -1)
	printf("Testing shuffle on negative size deck...\n");
	game->deckCount[3] = -20;
	ret = shuffle(3,game);
	assertLite(__LINE__,ret,-1,1, NOISY_TEST);

    return 0;
}


