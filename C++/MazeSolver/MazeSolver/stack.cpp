//#include "stdafx.h"
#include <iostream>
#include "stacknode.h"
#include "stack.h"

using namespace std;


Stack::Stack() : _top(NULL)
{
}

Stack::~Stack()
{
    while( _top != NULL )
    {
        Pop();
    }
}

myerror_code Stack::Push( int data, int xCoord, int yCoord )
{
    _top = new StackNode( data, xCoord, yCoord, _top );
	return(success);
}

int Stack::Peek()
{
    return _top->getData();
}

int Stack::PeekX()
{
	return _top->getXCoord();
}

int Stack::PeekY()
{
	return _top->getYCoord();
}

myerror_code Stack::Pop()
{
	if (_top != NULL) {

		StackNode* node = _top;
		_top = _top->getNext();
		delete node;
		return(success);
	}
	else {
		return(underflow);
	}
}

ostream& operator<<( ostream& output, Stack& stack )
{
    StackNode *node = stack._top;

    while( node != NULL )
    {
        cout << node->getData() << endl;
        node = node->getNext();
    }

    return output;
}

