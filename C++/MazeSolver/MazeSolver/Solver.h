#ifndef SOLVER_H
#define SOLVER_H
#include <iostream>
#include <conio.h>
using namespace std;

class Solver
{
private:
	int currentRow;
	int currentColumn;

public:
	Solver();
	void LookAround(char* sMazePointer);
};

#endif
