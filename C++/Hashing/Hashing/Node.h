#ifndef NODE_H
#define NODE_H

#include <iostream>
#include <string>
using namespace std;

class Node
{
public:
	string word;//Dictionary word.		
	Node *next;//Pointer to next node in the list. 

	Node();
	virtual ~Node();
};

#endif