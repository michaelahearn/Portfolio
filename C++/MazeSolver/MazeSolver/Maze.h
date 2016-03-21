#ifndef MAZE_H
#define MAZE_H
#include <iostream>
#include <fstream>
#include "stack.h"
#include "Solver.h"
using namespace std;

class Maze
{

private:
	/*const static int mazeHeight = 51;
	const static int mazeWidth = 51;*/
	int fileSize;
	int fileHeight;
	int fileWidth;


	//Dynamic Maze - delete if there are problems
	int dynMazeWidth;
	int dynMazeHeight;
	char** dynMaze;

	Stack mazeStack;

	/*ifstream mazeFile;*/
	/*char maze[mazeHeight][mazeWidth];*/
	char* mazePointer;
	int currentRow;
	int currentColumn;
	char solverCharacter = '#';
	char breadCrumb = '.';

public:
	
	Maze();
	void ReadMaze();	
	void WriteMaze();
	void LookAround();
	void Solve();

	friend ostream& operator<<(ostream& output, Maze& pMaze);//Display the list. 	

};

#endif