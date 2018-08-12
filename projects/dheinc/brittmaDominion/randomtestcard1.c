/*
 * randomtestcard1.c
 *
 * Card being tested:	Smithy
 *
 * Card's Function:		Draw three cards from player's deck to their hand
 *
 * 
 * Include in makefile:
 *
 * randomtestcard1: randomtestcard1.c dominion.o rngs.o testutils.o
 *      gcc -o randomtestcard1 -g randomtestcard1.c dominion.o rngs.o testutils.o $(CFLAGS)
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
#include <limits.h>
// set NOISY_TEST to 0 to remove printfs from output
#define NOISY_TEST 0

// number of tests
#define TEST_COUNT 7

// number of iterations
#define TEST_ITERATIONS 30000


void runTests(int* testResults);

int main(int argc, char *argv[]) {
	srand(time(0));

	printf("Testing Smithy\n");
	printf("0: Good return on cardEffect\n");
	printf("1: Check for memory changes to players\n");
	printf("2: Check if player has same number of cards total\n");
	printf("3: Check if discard works\n");
	printf("4: Check if 3 cards were drawn properly\n");
	printf("5: Confirm supply did not change\n");
	printf("6: Check for score not changing\n\n");

	int* testResults = malloc(sizeof(int) * TEST_COUNT);
	memset(testResults,0,sizeof(int) * TEST_COUNT);

	for(int count = 0; count < TEST_ITERATIONS; count++) {

		runTests(testResults);
	}

	printf("All tests complete.\n");
	
	for (int i = 0; i < TEST_COUNT;i++) {
		printf("Test %d fail count: %d\n",i,testResults[i]);
	}

    return 0;
}

void runTests(int* testResults){
	int ret;
	int assertRes;

	int testFail = 0;

	int smithyPos;
	int preCardCount;
	int cardCount;

	struct gameState *game = newGame();
	struct gameState *pre = newGame();

	smithyPos = resetForRandTest(game, pre, smithy);
	ret = cardEffect(smithy,-1,-1,-1,game,smithyPos,NULL);
#if NOISY_TEST
		printf("\nTest 0 - Checking good return on cardEffect...\n");
#endif	
	assertRes=assertLite(__LINE__,ret,0,1,NOISY_TEST);

	if(assertRes){testResults[0] += 1;}


	// test 1 - confirm other players were not affected
	//	- no new cards added, no cards removed etc
	for(int i = 0; i < game->numPlayers;i++) {
		if (i != game->whoseTurn){
#if NOISY_TEST
			printf("\nTest 1: Checking memory for changes to player %d.\n",i+1);
#endif
			assertRes=assertLite(__LINE__,memcmp(game->hand[i],pre->hand[i],sizeof(int)* (int) pre->handCount[i]),0,1,NOISY_TEST);   
			if(assertRes){
				printf("\nPlayer %d has memory change in hand.",i); testFail = 1;}
			assertRes=assertLite(__LINE__,memcmp(game->deck[i],pre->deck[i],sizeof(int)* (int) pre->deckCount[i]),0,1,NOISY_TEST);
			if(assertRes){
				printf("\nPlayer %d has memory change in deck.",i); testFail = 1;}
			assertRes=assertLite(__LINE__,memcmp(game->discard[i],pre->discard[i],sizeof(int)* (int) pre->discardCount[i]),0,1,NOISY_TEST);
			if(assertRes){
				printf("\nPlayer %d has memory change in discard.",i); testFail = 1;}
		}
	}
	testResults[1] += testFail;
	testFail = 0;
	// test 2 - confirm player has same number of cards (none removed from game or added)
	
#if NOISY_TEST
	printf("\nTest 2: Checking if player still has same number of cards total (deck + hand + discard)\n");
#endif	
	preCardCount = pre->deckCount[pre->whoseTurn] + pre->handCount[pre->whoseTurn] + pre->discardCount[pre->whoseTurn];
	cardCount = game->deckCount[game->whoseTurn] + game->handCount[game->whoseTurn] + game->discardCount[game->whoseTurn];  
	assertRes=assertLite(__LINE__,preCardCount,cardCount,1,NOISY_TEST);	
	if(assertRes){testResults[2] += 1;}

	// test 3 - is smithy card discarded properly?
	//	- card leaves hand
	//	- card ends up in discard
	
#if NOISY_TEST
	printf("\nTest 3: Checking if discard count was increased\n");
#endif
	assertRes=assertLite(__LINE__,(game->discardCount[game->whoseTurn] - pre->discardCount[pre->whoseTurn]),1,1,NOISY_TEST); // assert new discardCount - old is equal to 1
	if(assertRes){testFail = 1;}
#if NOISY_TEST
	printf("\nTest 3: Checking if smithy is the latest card in discard\n");
#endif
	assertRes=assertLite(__LINE__,(game->discard[game->whoseTurn][game->discardCount[game->whoseTurn]-1]),smithy,1,NOISY_TEST); // assert the latest card in the discard pile is smithy
	if(assertRes){testFail = 1;}

#if NOISY_TEST
		printf("\nTest 3: Checking if smithy is still in hand\n");
#endif
	assertRes=assertLite(__LINE__,(game->hand[game->whoseTurn][smithyPos]),smithy,0,NOISY_TEST); // confirm smithy is no longer in the hand
	if(assertRes){testFail = 1;}

	testResults[3] += testFail;
	testFail = 0;

	// test 4 - were 3 cards added to the hand and removed from the deck
	//	- deckCount drops by 3 cards
	//	- handCount goes up by 2 cards (accounting for smithy being removed)

#if NOISY_TEST
	printf("\nTest 4: Checking for deckCount decrease by three...\n");
#endif
	assertRes=assertLite(__LINE__,(pre->deckCount[pre->whoseTurn] - game->deckCount[game->whoseTurn]),3,1,NOISY_TEST);
	if(assertRes){testFail = 1;}
#if NOISY_TEST
	printf("\nTest 4: Checking for handCount increase by two...\n");
#endif
	assertRes=assertLite(__LINE__,(game->handCount[game->whoseTurn] - pre->handCount[pre->whoseTurn]),2,1,NOISY_TEST);
	if(assertRes){testFail = 1;}
 
	testResults[4] += testFail;
	testFail = 0;

	
	// test 5 - ensure supply was not affected
	// memory state of supply is same now as before

#if NOISY_TEST
	printf("\nTest 5: Checking for no change in supply...\n");
#endif
	assertRes=assertLite(__LINE__,memcmp(game->supplyCount,pre->supplyCount,(sizeof(int)*(treasure_map+1))),0,1,NOISY_TEST);

	testResults[5] += assertRes;
	// test 6 - confirm scores for all players are unchanged

	int preScore;
	int postScore;
	for(int i = 0; i < game->numPlayers; i++) {
#if NOISY_TEST
		printf("\nTest 6: Checking score unchanged for player %d.\n",i+1);
#endif
		preScore = scoreFor(i,pre);
		postScore = scoreFor(i,game);
		assertRes=assertLite(__LINE__,preScore,postScore,1,NOISY_TEST);

		if(assertRes){testFail = 1;}
	}

	testResults[6] += testFail;

	free(game);
	free(pre);

}

