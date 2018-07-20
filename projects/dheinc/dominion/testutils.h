#ifndef _TESTUTILS_H
#define _TESTUTILS_H

// equal: 0 for !=, 1 for ==, 2 for <, 3 for >, 4 for <=, 5 for >=
int assertLite(int line, int left, int right, int equal);

int testCompare(const void* a, const void* b);

void resetForTest(struct gameState *game, struct gameState *pre, int testCard);

void printHand(struct gameState *game);

void printDiscard(struct gameState *game) ;

void printDeck(struct gameState *game);

#endif
