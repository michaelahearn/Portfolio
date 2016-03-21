//Class example
#ifndef _STACKNODE_H
#define _STACKNODE_H

class StackNode
{
private:
    int _data;
	int _xCoord;
	int _yCoord;
    StackNode *_next;

public:
    StackNode();
    StackNode( int data, int xCoord, int yCoord, StackNode* next );
    virtual ~StackNode();

    int getData();
    void setData(int data);

	int getXCoord();
	void setXCoord(int xCoord);

	int getYCoord();
	void setYCoord(int yCoord);

    StackNode* getNext();
    void setNext( StackNode *next );
};

#endif
