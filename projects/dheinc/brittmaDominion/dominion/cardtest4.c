/*
 * cardtest4.c
 *
 * Card being tested:	great_hall
 *
 * Card's Function:		Draws a single card and gives player 1 additional action; also worth 1 VP.
 *
 * 
 * Include in makefile:
 *
 * cardtest4: cardtest4.c dominion.o rngs.o testutils.o
 *      gcc -o cardtest4 -g cardtest4.c dominion.o rngs.o testutils.o $(CFLAGS)
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
	int great_hallPos;
	int preCardCount;
	int cardCount;	
	int ret;
#if NOISY_TEST
	printf("Testing Great Hall\n");
#endif

	// test 1 - confirm Great Hall is worth 1 victory point
	//	- get score with no cards and after adding great hall
	game->hand[0][0] = great_hall;
#if NOISY_TEST
		printf("\nChecking for player score of 1 more than pre\n");
#endif	
	assertLite(__LINE__,(scoreFor(0,game) - scoreFor(0,pre)), 1, 1, NOISY_TEST);

	// test 2 - confirm other players were not affected
	//	- no new cards added, no cards removed etc
	resetForTest(game, pre, great_hall);
	great_hallPos = game->handCount[0]-1;
	ret = cardEffect(great_hall,-1,-1,-1,game,great_hallPos,NULL);
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

	// test 3 - confirm player has same number of cards in hand+deck+discard
	resetForTest(game, pre, great_hall);
	great_hallPos = game->handCount[0]-1;
	ret = cardEffect(great_hall,-1,-1,-1,game,great_hallPos,NULL);
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

	// test 4 - is great_hall card discarded properly?
	//	- card leaves hand
	//	- card ends up in discard
	resetForTest(game, pre, great_hall);
	great_hallPos = game->handCount[0]-1;
	ret = cardEffect(great_hall,-1,-1,-1,game,great_hallPos,NULL);
#if NOISY_TEST
		printf("\nChecking good return on cardEffect...\n");
#endif	
	assertLite(__LINE__,ret,0,1, NOISY_TEST);
	
#if NOISY_TEST
	printf("\nChecking if discard count was increased\n");
#endif
	assertLite(__LINE__,(game->discardCount[0] - pre->discardCount[0]),1,1, NOISY_TEST); // assert new discardCount - old is equal to 1
#if NOISY_TEST
	printf("\nChecking if great_hall is the latest card in discard\n");
#endif
	assertLite(__LINE__,(*game->discard[game->discardCount[0]-1]),great_hall,1, NOISY_TEST); // assert the latest card in the discard pile is great_hall

#if NOISY_TEST
		printf("\nChecking if great_hall is still in hand\n");
#endif 
	for (int i = 0; i < game->handCount[0];i++){
		assertLite(__LINE__,(*game->hand[i]),great_hall,0, NOISY_TEST); // confirm great_hall is no longer in the hand
	}


	// test 5 - was the player's action count increased by 1?
	resetForTest(game, pre, great_hall);
	great_hallPos = game->handCount[0]-1;
	ret = cardEffect(great_hall,-1,-1,-1,game,great_hallPos,NULL);
#if NOISY_TEST
		printf("\nChecking good return on cardEffect...\n");
#endif	
	assertLite(__LINE__,ret,0,1, NOISY_TEST);
	
#if NOISY_TEST
		printf("\nChecking if numActions increased by 1...\n");
#endif
	assertLite(__LINE__,(game->numActions - pre->numActions),1,1, NOISY_TEST);

	// test 6 - was a card added to the hand and removed from the deck
	//	- deckCount drops by 1 card
	//	- handCount stays the same (accounting for great_hall being removed)
	resetForTest(game, pre, great_hall);
	great_hallPos = game->handCount[0]-1;
	ret = cardEffect(great_hall,-1,-1,-1,game,great_hallPos,NULL);
#if NOISY_TEST
		printf("\nChecking good return on cardEffect...\n");
#endif	
	assertLite(__LINE__,ret,0,1, NOISY_TEST);
	

#if NOISY_TEST
	printf("\nChecking for deckCount decreasse by 1...\n");
#endif
	assertLite(__LINE__,(pre->deckCount[0] - game->deckCount[0]),1,1, NOISY_TEST);
#if NOISY_TEST
	printf("\nChecking for handCount being the same...\n");
#endif
	assertLite(__LINE__,(game->handCount[0] - pre->handCount[0]),0,1, NOISY_TEST);
 
	// test 7 - ensure supply was not affected
	// memory state of supply is same now as before
	resetForTest(game, pre, great_hall);
	great_hallPos = game->handCount[0]-1;
	ret = cardEffect(great_hall,-1,-1,-1,game,great_hallPos,NULL);
#if NOISY_TEST
		printf("\nChecking good return on cardEffect...\n");
#endif	
	assertLite(__LINE__,ret,0,1, NOISY_TEST);

#if NOISY_TEST
	printf("\nChecking for no change in supply...\n");
#endif
	assertLite(__LINE__,memcmp(game->supplyCount,pre->supplyCount,(sizeof(int)*(treasure_map+1))),0,1, NOISY_TEST);

	// test 8 - confirm scores for all players are unchanged
	resetForTest(game, pre, great_hall);
	great_hallPos = game->handCount[0]-1;
	ret = cardEffect(great_hall,-1,-1,-1,game,great_hallPos,NULL);
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


