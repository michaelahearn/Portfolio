#include "LinkedList.h"
#include "Node.h"
#include <conio.h>
#include <iostream>
using namespace std;

LinkedList::LinkedList()
{
	first = NULL;//The first node in the list.
	current = NULL;//The node that is currently "selected", and thus eligible for deletion, substitution, etc.
}

LinkedList::~LinkedList()
{

}

void LinkedList::Add(string sWord)//Adds a node to the end of the list.
{

	Node *node = new Node(); //Create a new node.
	node->word = sWord; //Set the node direction and combo to the submitted values. 	

	if (first == NULL)//If this is the first node being tracked by the list. 
	{
		first = node;//Set the list's "first" pointer to the pointer of the new node. 
	}

	else
	{
		Node *currNode = first;//Create a pointer that will be used to determine the end of the list. 
		Node *prevNode = NULL;//Create a pointer to keep track of the node preceding the current node.  

		while (currNode != NULL)//Repeat the following until current node becomes null, and the end of the list has been reached. 
		{
			prevNode = currNode;//Previous node changes to current node's value.   
			currNode = currNode->next;//Current node moves on to next location in the list. 
		}

		if (prevNode != NULL)//Check to determine that the prevNode pointer contains a valid address, which should be the last node in the list.
		{
			prevNode->next = node;//Change the next pointer of the list's last node to the newly-created node's pointer. This makes it the new last node. 
		}
	}

	current = first;//Set current node to first node in list. 
}

void LinkedList::GoTo(int sPosition)//Changes the "current" node to a specified position in the list.
{
	current = first;//Start with the first node in the list.

	for (int i = 1; i < sPosition; i++)//Iterate through the nodes until the submitted node is reached.
	{
		if (current->next != NULL)
		{
			current = current->next;
		}

		else
		{

		}

	}

}

bool LinkedList::Search(string sWord)
{

	if (first != NULL)//If the first node in the list is not null. 
	{		
		current = first;//Set the current pointer to the first node in the list.
		bool loop = true;//Set loop to continue through entire list.
		while (loop == true)
		{
			if (current->next == NULL)//If there are no remaining nodes in the list after the one currently being searched.
			{
				if (current->word == sWord)//If the current word is equal to the word being searched
				{
					return true;//Stop searching and return true
				}
				loop = false;//Stop looping
			}
			else//There are still nodes remaining in the list.
			{
				if (current->word == sWord)//If the current word is equal to the word being searched. 
				{
					return true;//Stop searching and return true
				}
				current = current->next;//Move the current pointer to the next available node
			}
		}		
	}

	return false;//If this point is reached, there are no words at the submitted index, so it's not in the dictionary.
	
}

//Used to display all existing node items in the entire linked list. 
void LinkedList::DisplayList()
{
	if (first != NULL)
	{		
		current = first;
		bool loop = true;
		while (loop == true)
		{
			if (current->next == NULL)
			{
				cout << current->word << " ";
				cout << endl;
				loop = false;
			}
			else
			{
				cout << current->word << " ";
				current = current->next;
			}
		}
	}
	else
	{
		cout << endl;
	}
}

//Unused link list methods from previous assignment
//void LinkedList::Substitute(string sWord)//Sets combo of current node to submitted value. 
//{
//	current->word = sWord;
//}
//
//void LinkedList::Delete()//Sets combo of current node to 0.
//{
//	current->word = "";
//}
//
//void LinkedList::Reset(int maxPositions)//Resets combo value of all nodes to 0.
//{
//
//	Node *resetNode = first;//Start with the first node.
//
//	for (int i = 0; i < maxPositions; i++)//Cycle through all the nodes, resetting each combo to 0.
//	{
//		resetNode->word = "";
//		resetNode = resetNode->next;
//	}
//
//	current = first;
//
//}