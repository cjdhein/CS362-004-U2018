/*
 * cardtest3.c
 *
 * Card being tested:	Village
 *
 * Card's Function:		Draws a single card and gives player 2 additional actions.
 *
 * 
 * Include in makefile:
 *
 * cardtest3: cardtest3.c dominion.o rngs.o testutils.o
 *      gcc -o cardtest3 -g cardtest3.c dominion.o rngs.o testutils.o $(CFLAGS)
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
	int villagePos;
	int preCardCount;
	int cardCount;	
	int ret;
	
	printf("Testing Village\n");
	printf("0: Good return on cardEffect\n");
	printf("1: Check for memory changes to players\n");
	printf("2: Check if player has same number of cards total\n");
	printf("3: Check if discard works\n");
	printf("4: Check if action count increased by 2\n");
	printf("5: Check if card was drawn properly\n");
	printf("6: Confirm supply did not change\n");
	printf("7: Check for score not changing\n\n");

	// test 1 - confirm other players were not affected
	//	- no new cards added, no cards removed etc
	resetForTest(game, pre, village);
	villagePos = game->handCount[0]-1;
	ret = cardEffect(village,-1,-1,-1,game,villagePos,NULL);
#if NOISY_TEST
		printf("\nChecking good return on cardEffect...\n");
#endif	
	assertLite(__LINE__,ret,0,1, NOISY_TEST);
		
	// loop through players 2-4
	for(int i = 1; i < 4;i++) {
#if NOISY_TEST
		printf("\nChecking memory for changes to player %d.\n",i+1);
#endif
		assertLite(__LINE__,memcmp(game->hand[i],pre->hand[i],sizeof(int)* (int) pre->handCount[i]),0,1, NOISY_TEST);   
		assertLite(__LINE__,memcmp(game->deck[i],pre->deck[i],sizeof(int)* (int) pre->deckCount[i]),0,1, NOISY_TEST);
		assertLite(__LINE__,memcmp(game->discard[i],pre->discard[i],sizeof(int)* (int) pre->discardCount[i]),0,1, NOISY_TEST);
	}	

	// test 2 - confirm player has same number of cards in hand+deck+discard
	resetForTest(game, pre, village);
	villagePos = game->handCount[0]-1;
	ret = cardEffect(village,-1,-1,-1,game,villagePos,NULL);
#if NOISY_TEST
		printf("\nChecking good return on cardEffect...\n");
#endif	
	assertLite(__LINE__,ret,0,1, NOISY_TEST);
	
#if NOISY_TEST
	printf("\nChecking if player 0 still has same number of cards total (deck + hand + discard)\n");
#endif	
	preCardCount = pre->deckCount[0] + pre->handCount[0] + pre->discardCount[0];
	cardCount = game->deckCount[0] + game->handCount[0] + game->discardCount[0];  
	assertLite(__LINE__,preCardCount,cardCount,1, NOISY_TEST);

	// test 3 - is village card discarded properly?
	//	- card leaves hand
	//	- card ends up in discard
	resetForTest(game, pre, village);
	villagePos = game->handCount[0]-1;
	ret = cardEffect(village,-1,-1,-1,game,villagePos,NULL);
#if NOISY_TEST
		printf("\nChecking good return on cardEffect...\n");
#endif	
	assertLite(__LINE__,ret,0,1, NOISY_TEST);
	
#if NOISY_TEST
	printf("\nChecking if discard count was increased\n");
#endif
	assertLite(__LINE__,(game->discardCount[0] - pre->discardCount[0]),1,1, NOISY_TEST); // assert new discardCount - old is equal to 1
#if NOISY_TEST
	printf("\nChecking if village is the latest card in discard\n");
#endif
	assertLite(__LINE__,(*game->discard[game->discardCount[0]-1]),village,1, NOISY_TEST); // assert the latest card in the discard pile is village

#if NOISY_TEST
		printf("\nChecking if village is still in hand\n");
#endif 
	for (int i = 0; i < game->handCount[0];i++){
		assertLite(__LINE__,(*game->hand[i]),village,0, NOISY_TEST); // confirm village is no longer in the hand
	}


	// test 4 - was the player's action count increased by 2?
	resetForTest(game, pre, village);
	villagePos = game->handCount[0]-1;
	ret = cardEffect(village,-1,-1,-1,game,villagePos,NULL);
#if NOISY_TEST
		printf("\nChecking good return on cardEffect...\n");
#endif	
	assertLite(__LINE__,ret,0,1, NOISY_TEST);
	
#if NOISY_TEST
		printf("\nChecking if numActions increased by 2...\n");
#endif
	assertLite(__LINE__,(game->numActions - pre->numActions),2,1, NOISY_TEST);

	// test 5 - was a card added to the hand and removed from the deck
	//	- deckCount drops by 1 card
	//	- handCount stays the same (accounting for village being removed)
	resetForTest(game, pre, village);
	villagePos = game->handCount[0]-1;
	ret = cardEffect(village,-1,-1,-1,game,villagePos,NULL);
#if NOISY_TEST
		printf("\nChecking good return on cardEffect...\n");
#endif	
	assertLite(__LINE__,ret,0,1, NOISY_TEST);

#if NOISY_TEST
	printf("\nChecking for deckCount decrease by one...\n");
#endif
	assertLite(__LINE__,(pre->deckCount[0] - game->deckCount[0]),1,1, NOISY_TEST);
#if NOISY_TEST
	printf("\nChecking for handCount increase by zero...\n");
#endif
	assertLite(__LINE__,(game->handCount[0] - pre->handCount[0]),0,1, NOISY_TEST);
 
	// test 6 - ensure supply was not affected
	// memory state of supply is same now as before
	resetForTest(game, pre, village);
	villagePos = game->handCount[0]-1;
	ret = cardEffect(village,-1,-1,-1,game,villagePos,NULL);
#if NOISY_TEST
		printf("\nChecking good return on cardEffect...\n");
#endif	
	assertLite(__LINE__,ret,0,1, NOISY_TEST);

#if NOISY_TEST
	printf("\nChecking for no change in supply...\n");
#endif
	assertLite(__LINE__,memcmp(game->supplyCount,pre->supplyCount,(sizeof(int)*(treasure_map+1))),0,1, NOISY_TEST);

	// test 7 - confirm scores for all players are unchanged
	resetForTest(game, pre, village);
	villagePos = game->handCount[0]-1;
	ret = cardEffect(village,-1,-1,-1,game,villagePos,NULL);
#if NOISY_TEST
		printf("\nChecking good return on cardEffect...\n");
#endif	
	assertLite(__LINE__,ret,0,1, NOISY_TEST);


	for(int i = 0; i < 4;i++) {
#if NOISY_TEST
		printf("\nChecking score unchanged for player %d.\n",i+1);
#endif
		assertLite(__LINE__,scoreFor(i,game),scoreFor(i,pre),1, NOISY_TEST);
	}



	return 0;
}


