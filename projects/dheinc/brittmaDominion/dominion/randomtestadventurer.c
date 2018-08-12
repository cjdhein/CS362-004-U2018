/*
 * randomtestadventurer.c
 *
 * Card being tested:	Adventurer
 *
 * Card's Function:		Draw cards from deck until 2 treasure cards are revealed.
 *					Place them in your hand and discard the other revealed cards.
 *
 * 
 * Include in makefile:
 *
 * randomtestadventurer: randomtestadventurer.c dominion.o rngs.o testutils.o
 *      gcc -o randomtestadventurer -g randomtestadventurer.c dominion.o rngs.o testutils.o $(CFLAGS)
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

int countTreasure(struct gameState *game);
void runTests(int* testResults);

int main(int argc, char *argv[]) {
	srand(time(0));

	printf("Testing Adventurer\n");
	printf("0: Good return on cardEffect\n");
	printf("1: Check for memory changes to players\n");
	printf("2: Check if player has same number of cards total\n");
	printf("3: Check if discard works\n");
	printf("4: Check if 2 treasure cards added\n");
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
int countTreasure(struct gameState *game) {
	int count = 0;
	int card;
	for(int i = 0; i < game->handCount[0]; i++) {
		card = game->hand[0][i];
		switch(card){
			case copper:
			case silver:
			case gold:
				count++;
			default:
				break;
		}
	}
	return count;
}

void runTests(int* testResults){
	int ret;
	int assertRes;

	int testFail = 0;

	int adventurerPos;
	int preCardCount;
	int cardCount;

	struct gameState *game = newGame();
	struct gameState *pre = newGame();

	adventurerPos = resetForRandTest(game, pre, adventurer);
	ret = cardEffect(adventurer,-1,-1,-1,game,adventurerPos,NULL);
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
			if(assertRes){testFail = 1;}
			assertRes=assertLite(__LINE__,memcmp(game->deck[i],pre->deck[i],sizeof(int)* (int) pre->deckCount[i]),0,1,NOISY_TEST);
			if(assertRes){testFail = 1;}
			assertRes=assertLite(__LINE__,memcmp(game->discard[i],pre->discard[i],sizeof(int)* (int) pre->discardCount[i]),0,1,NOISY_TEST);
			if(assertRes){testFail = 1;}
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

	// test 3 - is adventurer card discarded properly?
	//	- card leaves hand
	//	- card ends up in discard
	
#if NOISY_TEST
	printf("\nTest 3: Checking if discard count was increased\n");
#endif
	assertRes=assertLite(__LINE__,(game->discardCount[game->whoseTurn] - pre->discardCount[pre->whoseTurn]),1,1,NOISY_TEST); // assert new discardCount - old is equal to 1
	if(assertRes){testFail = 1;}
#if NOISY_TEST
	printf("\nTest 3: Checking if adventurer is the latest card in discard\n");
#endif
	assertRes=assertLite(__LINE__,(game->discard[game->whoseTurn][game->discardCount[game->whoseTurn]-1]),adventurer,1,NOISY_TEST); // assert the latest card in the discard pile is adventurer
	if(assertRes){testFail = 1;}

#if NOISY_TEST
		printf("\nTest 3: Checking if adventurer is still in hand\n");
#endif
	assertRes=assertLite(__LINE__,(game->hand[game->whoseTurn][adventurerPos]),adventurer,0,NOISY_TEST); // confirm adventurer is no longer in the hand
	if(assertRes){testFail = 1;}

	testResults[3] += testFail;
	testFail = 0;

	// test 4 - were two treasure cards added to player's hand?
	//	- handCount up by one (account for discarding adventurer)
	//	- count of old treasure cards is two less than count of new

#if NOISY_TEST
	printf("\nTest 4: Checking if handcount up by one\n");
#endif
	assertRes=assertLite(__LINE__,(game->handCount[game->whoseTurn] - pre->handCount[pre->whoseTurn]),1,1,NOISY_TEST);
	if(assertRes){testFail = 1;}
#if NOISY_TEST
	printf("\nTest 4: Checking if count of treasures is up by 2\n");
#endif

	assertRes = assertLite(__LINE__,countTreasure(game) - countTreasure(pre),2,1,NOISY_TEST);

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

