
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "dominion.h"
#include "testutils.h"



// equal: 0 for !=, 1 for ==, 2 for <, 3 for >, 4 for <=, 5 for >=
int assertLite(int line, int left, int right, int equal,int loud) {
	//printf("left: %d, right: %d",left,right);
	switch (equal){
		case 0://!=
			if(left != right){
				return 0;
			} else if(loud) {
				printf("TEST FAILED. %d == %d at line %d\n",left,right, line);
			}
			break;
		case 1://==
			if(left == right){
				return 0;
			} else if(loud) {
				printf("TEST FAILED. %d != %d at line %d\n",left,right, line);
			}
			break;
		case 2:
			if(left < right){
				return 0;
			} else if(loud) {
				printf("TEST FAILED. %d is not < %d at line %d\n",left,right, line);
			}
			break;
		case 3:
			if(left > right){
				return 0;
			} else if(loud) {
				printf("TEST FAILED. %d is not > %d at line %d\n",left,right, line);
			}
			break;
		case 4:
			if(left <= right){
				return 0;
			} else if(loud) {
				printf("TEST FAILED. %d is not <= %d at line %d\n",left,right, line);
			}
			break;
		case 5:
			if(left >= right){
				return 0;
			} else if(loud) {
				printf("TEST FAILED. %d is not >= %d at line %d\n",left,right, line);
			}
			break;
		default:
			return -1;
	}
	return 1;
}

int testCompare(const void* a, const void* b) {
  if (*(int*)a > *(int*)b)
    return 1;
  if (*(int*)a < *(int*)b)
    return -1;
  return 0;
}

void resetForTest(struct gameState *game, struct gameState *pre, int testCard) {

	memset(game,0,sizeof(struct gameState));

	// set the kingdom cards being used
	int k[10] = {smithy, gardens, great_hall, village, minion, mine, cutpurse, sea_hag, tribute, adventurer};	
	// initialize the game
	initializeGame(4,k,23,game);
	
	// give first player the test card in their hand
	game->hand[0][game->handCount[0]] = testCard;
	game->handCount[0]++;

	// store initial state in pre
	memset(pre,0,sizeof(struct gameState));
	memcpy(pre,game,sizeof(struct gameState));
	
}

int resetForRandTest(struct gameState *game, struct gameState *pre, int testCard) {
	memset(game,0,sizeof(struct gameState));
	free(game);
	memset(pre,0,sizeof(struct gameState));
	free(pre);

	pre = newGame();

	// initialize the game
	game = randomState(testCard);

	
	// give player the test card in their hand
	int cardPos = rand() % game->handCount[game->whoseTurn] - 1;
	game->hand[game->whoseTurn][cardPos] = testCard;

	// store initial state in pre
	memcpy(pre,game,sizeof(struct gameState));

	return cardPos;

}

void printDeck(struct gameState *game) {
	printf("Deck:\n");
	for(int i = 0; i < game->deckCount[0];i++){
		printf("%d, ",game->deck[0][i]);
	}
	printf("\n");
}

void printDiscard(struct gameState *game) {
	printf("Discard:\n");
	for(int i = 0; i < game->discardCount[0];i++){
		printf("%d, ",game->discard[0][i]);
	}
	printf("\n");
}

void printHand(struct gameState *game) {
	printf("Hand:\n");
	for(int i = 0; i < game->handCount[0];i++){
		printf("%d, ",game->hand[0][i]);
	}
	printf("\n");
}

int* randomKingdom(int testCard) {
	int* kdom = malloc(10 * sizeof(int));

	int card;
	int valid = 0;

	kdom[0] = testCard;

	for(int i = 1; i < 10; i++) {
		while(!valid){
			card = (rand() % 21) + 7;
			valid = 1;
			for(int j = 0; j < i;j++) {
				if (kdom[j] == card)
					valid = 0;
			}

		}
		kdom[i] = card;
		valid = 0;
	}

	return kdom;
	//printf("kdom:%d, %d, %d, %d, %d, %d, %d, %d, %d, %d\n", kdom[0], kdom[1], kdom[2], kdom[3], kdom[4], kdom[5], kdom[6], kdom[7], kdom[8], kdom[9]);

}

struct gameState* randomState(int testCard) {
	struct gameState* game = newGame();
	int card;
	int numPlayers = rand() % 3 + 2;	
	game->numPlayers = numPlayers;

	int* k = randomKingdom(testCard);

	//set number of Curse cards
	game->supplyCount[curse] = rand() % 10 + 1;

	game->supplyCount[estate] = rand() % 8+1;
	game->supplyCount[duchy] = rand() % 8+1;
	game->supplyCount[province] = rand() % 8+1;

	//set number of Treasure cards
	game->supplyCount[copper] = rand() % 60 +10;
	game->supplyCount[silver] = rand() % 40 +5;
	game->supplyCount[gold] = rand() % 30 + 4;	
	
	//set kingdom cards
	for (int i = adventurer; i < treasure_map; i++) {
		for(int j = 0; j < 10; j++) {
			if (k[j] == i){
				game->supplyCount[i] = rand() % 50 + 1;
			} else {
				game->supplyCount[i] = -1;
			}
		}
	}

	// generate decks
	for(int i = 0; i < numPlayers; i++) {
		game->deckCount[i] = 0;
		int deckSize = rand() % 100 + 10;
		for(int j = 0; j < deckSize;j++) {
			card = rand() % 17;
			if (card < 10)
				card = k[card];
			else
				card = card - 10;
			game->deck[i][game->deckCount[i]] = card;	
			game->deckCount[i]++;
		}

		shuffle(i,game);
	}

	//generate hands
	for(int i = 0; i < numPlayers; i++) {
		game->handCount[i] = 0;
		int handSize = rand() % 50 + 3;

		for(int j = 0; j < handSize; j++) {
			card = rand() % 17;
			if (card < 10)
				card = k[card];
			else
				card = card - 10;

			game->hand[i][game->handCount[i]] = card;
			game->handCount[i]++;
		}
	}
	
	//generate discard
	for(int i = 0; i < numPlayers; i++) {
		game->discardCount[i] = 0;
		int discardSize = rand() % 25;

		for(int j = 0; j < discardSize; j++) {
			card = rand() % 17;
			if (card < 10)
				card = k[card];
			else
				card = card - 10;

			game->discard[i][game->discardCount[i]] = card;
			game->discardCount[i]++;
		}
	}

	// pick who will play test card
	int testPlayer = rand() % numPlayers;
	game->whoseTurn = testPlayer;

	return game;	
}

