/*
 * cardtest1.c
 *
 * Card being tested:	Smithy
 *
 * Card's Function:		Draw three cards from player's deck to their hand
 *
 * 
 * Include in makefile:
 *
 * cardtest1: cardtest1.c dominion.o rngs.o testutils.o
 *      gcc -o cardtest1 -g cardtest1.c dominion.o rngs.o testutils.o $(CFLAGS)
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
	struct gameState *pre;
	game = newGame();
	pre = newGame();

	int ret;
	int smithyPos;
	int preCardCount;
	int cardCount;

	printf("Testing Smithy\n");
	printf("0: Good return on cardEffect\n");
	printf("1: Check for memory changes to players\n");
	printf("2: Check if player has same number of cards total\n");
	printf("3: Check if discard works\n");
	printf("4: Check if 3 cards were drawn properly\n");
	printf("5: Confirm supply did not change\n");
	printf("6: Check for score not changing\n\n");

	// test 1 - confirm other players were not affected
	//	- no new cards added, no cards removed etc
	resetForTest(game, pre, smithy);
	smithyPos = game->handCount[0]-1;
	ret = cardEffect(smithy,-1,-1,-1,game,smithyPos,NULL);
#if NOISY_TEST
		printf("\nChecking good return on cardEffect...\n");
#endif	
	assertLite(__LINE__,ret,0,1, NOISY_TEST);
	
	for(int i = 1; i < 4;i++) {
#if NOISY_TEST
		printf("\nTest 1: Checking memory for changes to player %d.\n",i+1);
#endif
		assertLite(__LINE__,memcmp(game->hand[i],pre->hand[i],sizeof(int)* (int) pre->handCount[i]),0,1, NOISY_TEST);   
		assertLite(__LINE__,memcmp(game->deck[i],pre->deck[i],sizeof(int)* (int) pre->deckCount[i]),0,1, NOISY_TEST);
		assertLite(__LINE__,memcmp(game->discard[i],pre->discard[i],sizeof(int)* (int) pre->discardCount[i]),0,1, NOISY_TEST);
	}

	// test 2 - confirm player has same number of cards (none removed from game or added)
	resetForTest(game, pre, smithy);
	smithyPos = game->handCount[0]-1;
		ret = cardEffect(smithy,-1,-1,-1,game,smithyPos,NULL);
#if NOISY_TEST
		printf("\nChecking good return on cardEffect...\n");
#endif	
	assertLite(__LINE__,ret,0,1, NOISY_TEST);
	
#if NOISY_TEST
	printf("\nTest 2: Checking if player 0 still has same number of cards total (deck + hand + discard)\n");
#endif	
	preCardCount = pre->deckCount[0] + pre->handCount[0] + pre->discardCount[0];
	cardCount = game->deckCount[0] + game->handCount[0] + game->discardCount[0];  
	assertLite(__LINE__,preCardCount,cardCount,1, NOISY_TEST);	

	// test 3 - is smithy card discarded properly?
	//	- card leaves hand
	//	- card ends up in discard
	resetForTest(game, pre, smithy);
	smithyPos = game->handCount[0]-1;
		ret = cardEffect(smithy,-1,-1,-1,game,smithyPos,NULL);
#if NOISY_TEST
		printf("\nChecking good return on cardEffect...\n");
#endif	
	assertLite(__LINE__,ret,0,1, NOISY_TEST);
	
#if NOISY_TEST
	printf("\nTest 3: Checking if discard count was increased\n");
#endif
	assertLite(__LINE__,(game->discardCount[0] - pre->discardCount[0]),1,1, NOISY_TEST); // assert new discardCount - old is equal to 1
#if NOISY_TEST
	printf("\nTest 3: Checking if smithy is the latest card in discard\n");
#endif
	assertLite(__LINE__,(*game->discard[game->discardCount[0]-1]),smithy,1, NOISY_TEST); // assert the latest card in the discard pile is smithy

#if NOISY_TEST
		printf("\nTest 3: Checking if smithy is still in hand\n");
#endif
	for (int i = 0; i < game->handCount[0];i++){
		assertLite(__LINE__,(*game->hand[i]),smithy,0, NOISY_TEST); // confirm smithy is no longer in the hand
	}

	// test 4 - were 3 cards added to the hand and removed from the deck
	//	- deckCount drops by 3 cards
	//	- handCount goes up by 2 cards (accounting for smithy being removed)
	resetForTest(game, pre, smithy);
	smithyPos = game->handCount[0]-1;
		ret = cardEffect(smithy,-1,-1,-1,game,smithyPos,NULL);
#if NOISY_TEST
		printf("\nChecking good return on cardEffect...\n");
#endif	
	assertLite(__LINE__,ret,0,1, NOISY_TEST);

#if NOISY_TEST
	printf("\nTest 4: Checking for deckCount decrease by three...\n");
#endif
	assertLite(__LINE__,(pre->deckCount[0] - game->deckCount[0]),3,1, NOISY_TEST);
#if NOISY_TEST
	printf("\nTest 4: Checking for handCount increase by two...\n");
#endif
	assertLite(__LINE__,(game->handCount[0] - pre->handCount[0]),2,1, NOISY_TEST);
 
	// test 5 - ensure supply was not affected
	// memory state of supply is same now as before
	resetForTest(game, pre, smithy);
	smithyPos = game->handCount[0]-1;
		ret = cardEffect(smithy,-1,-1,-1,game,smithyPos,NULL);
#if NOISY_TEST
		printf("\nChecking good return on cardEffect...\n");
#endif	
	assertLite(__LINE__,ret,0,1, NOISY_TEST);

#if NOISY_TEST
	printf("\nTest 5: Checking for no change in supply...\n");
#endif
	assertLite(__LINE__,memcmp(game->supplyCount,pre->supplyCount,(sizeof(int)*(treasure_map+1))),0,1, NOISY_TEST);

	// test 6 - confirm scores for all players are unchanged
	resetForTest(game, pre, smithy);
	smithyPos = game->handCount[0]-1;
		ret = cardEffect(smithy,-1,-1,-1,game,smithyPos,NULL);
#if NOISY_TEST
		printf("\nChecking good return on cardEffect...\n");
#endif	
	assertLite(__LINE__,ret,0,1, NOISY_TEST);

	int preScore;
	int postScore;
	for(int i = 0; i < 4;i++) {
#if NOISY_TEST
		printf("\nTest 6: Checking score unchanged for player %d.\n",i+1);
#endif
		preScore = scoreFor(i,pre);
		postScore = scoreFor(i,game);
		assertLite(__LINE__,preScore,postScore,1, NOISY_TEST);
	}

	printf("All tests complete.\n");

    return 0;
}


