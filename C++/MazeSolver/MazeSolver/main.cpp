#include <iostream>
#include <fstream>
#include <conio.h>
#include "Maze.h"
#include "Solver.h"
using namespace std;

//ostream& operator<<(ostream& output, Maze& pMaze)
//{
//	cout << endl;
//
//	pMaze[pMaze.currentRow][pMaze.currentColumn] = '#';
//	//Display dynamic array. 
//	for (int rows = 0; rows < pMaze.dynMazeHeight; ++rows)
//	{
//		for (int columns = 0; columns < pMaze.dynMazeWidth; ++columns)
//		{
//			if (pMaze[rows][columns] != '.')
//			{
//				output << pMaze[rows][columns];
//			}
//			else
//			{
//				output << " ";
//			}
//
//		}
//
//		output << "\n";
//	}
//
//	return output;
//
//}

int main()
{
	Maze maze;
	maze.Solve();
	maze.WriteMaze();
	//cout << maze;


	_getch();
	return 0;
}