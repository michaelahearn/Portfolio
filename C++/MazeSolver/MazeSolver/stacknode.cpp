//#include "stdafx.h"
#include <iostream>
#include "stacknode.h"

using namespace std;

StackNode::StackNode() : _data(0), _xCoord(0), _yCoord(0), _next(NULL) 
{
}

StackNode::StackNode( int data, int xCoord, int yCoord, StackNode* next ) : _data(data), _xCoord(xCoord), _yCoord(yCoord), _next(next) 
{
}

StackNode::~StackNode() 
{
}

int StackNode::getData() 
{ 
    return _data; 
}

void StackNode::setData( int data ) 
{ 
    _data = data; 
}

int StackNode::getXCoord()
{
	return _xCoord;
}

void StackNode::setXCoord(int xCoord)
{
	_xCoord = xCoord;
}

int StackNode::getYCoord()
{
	return _yCoord;
}

void StackNode::setYCoord(int yCoord)
{
	_yCoord = yCoord;
}

StackNode* StackNode::getNext() 
{ 
    return _next; 
}

void StackNode::setNext( StackNode *next ) 
{ 
    _next = next; 
}
