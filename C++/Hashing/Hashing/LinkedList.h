#ifndef LINKEDLIST_H
#define LINKEDLIST_H

#include <iostream>
#include <string>
#include "Node.h"
using namespace std;

class LinkedList
{
private:
	Node *first;//Pointer to first node in list. 
	Node *current;//Pointer to current node in list. 

public:
	LinkedList();
	virtual ~LinkedList();
	void Add(string sWord);//Adds a node to the list. 
	void GoTo(int sPosition);//Navigate to a specified node in the list. 
	bool Search(string sWord);
	void DisplayList();
	void Substitute(string sWord);//Substitute a combo value in the list. 
	void Delete();//Delete the combo value in a node. 
	void Reset(int maxPositions);//Reset all nodes to a default value. 
	friend ostream& operator<<(ostream& output, LinkedList& list);//Display the list. 
	friend void SaveCombo(LinkedList& list);//Save the list to a file. 

};

#endif