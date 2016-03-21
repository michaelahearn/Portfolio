#include <iostream>
#include <conio.h>
#include "Maze.h"
#include "stack.h"
#include "stacknode.h"
#include <string.h>
using namespace std;

Maze::Maze()
{	
	//Set starting coordinates.
	currentRow = 1;
	currentColumn = 0;

	//Open maze file. 
	ifstream mazeFile("mazex.txt");

	//If maze file can't be opened, exit with error. 
	if (!mazeFile)
	{
		cout << "Couldn't open data file";
		_getch();
		exit(102);//http://stackoverflow.com/questions/19307364/how-to-read-chars-from-a-txt-file-to-a-2d-array-c
	}

	


	//Get size of file as per method in http://www.cplusplus.com/reference/istream/istream/seekg/. Seek to end of file to get size. 
	mazeFile.seekg(0, mazeFile.end);
	int fileSize = mazeFile.tellg();
	mazeFile.seekg(0, mazeFile.beg);
	
	//Seek to end of line to get width. Return to beginning.
	char fileSeeker;
	while (true)
	{
		if (mazeFile.peek() != '\n')
		{
			fileSeeker = mazeFile.get();
			fileWidth += 1;
		}
		else
		{
			break;
		}
	}
	mazeFile.seekg(0, mazeFile.beg);

	//Get height by dividing size by width.
	fileHeight = fileSize / fileWidth;

	dynMazeWidth = fileWidth;
	dynMazeHeight = fileHeight;

	//Dynamic Maze Array solution from https://gsamaras.wordpress.com/code/dynamic-2d-array-in-c/ and http://stackoverflow.com/questions/936687/how-do-i-declare-a-2d-array-in-c-using-new
	//Create 2d array of 2d arrays to hold maze, allowing dynamic sizing at runtime. 
	dynMaze = new char*[dynMazeWidth];

	for (int rows = 0; rows < dynMazeHeight; ++rows)
	{
		dynMaze[rows] = new char[dynMazeWidth];
	}

	//Fill array with 0
	for (int rows = 0; rows < dynMazeHeight; ++rows)
	{
		for (int columns = 0; columns < dynMazeWidth; ++columns)
		{
			dynMaze[rows][columns] = '0';
		}
	}

	//Read file stream into maze array one character at a time. 
	for (int rows = 0; rows < dynMazeHeight; rows++)
	{
		for (int columns = 0; columns <= dynMazeWidth; columns++)
		{					
			if (mazeFile.peek() != '\n')
			{
				dynMaze[rows][columns] = mazeFile.get();
			}
			else
			{
				mazeFile.ignore();
			}			
		}
	}	


	//Close maze file.
	mazeFile.close();

}


void Maze::WriteMaze()
{	
	//Set first character in maze. 
	dynMaze[currentRow][currentColumn] = '#';
	//Display dynamic array. 
	for (int rows = 0; rows < dynMazeHeight; ++rows)
	{
		for (int columns = 0; columns < dynMazeWidth; ++columns)
		{
			if (dynMaze[rows][columns] != '.')
			{
				cout << dynMaze[rows][columns]; 
			}
			else
			{
				cout << " ";
			}
			
		}

		cout << "\n";
	}
}

//Solves maze by calling LookAround until current coordinate matches end of maze. 
void Maze::Solve()
{	
	while (currentRow != dynMazeHeight - 1 && currentColumn != dynMazeWidth - 1)
	{
		LookAround();
	}	
}

//"Look" in 4 directions (NESW) from current position, move to valid space if found, mark dead ends with a ".". Store valid path in stack.
void Maze::LookAround()
{	
	//Look North
	if (dynMaze[currentRow - 1][currentColumn] == ' ')
	{
		mazeStack.Push(0, currentRow, currentColumn);
		/*cout << "Moving from: " << mazeStack.PeekX() << " " << mazeStack.PeekY() << " To: " << currentRow - 1 << " " << currentColumn << endl;*/
		dynMaze[currentRow][currentColumn] = '#';		
		currentRow = currentRow - 1;
	}

	//Look East
	else if (dynMaze[currentRow][currentColumn + 1] == ' ')
	{
		mazeStack.Push(0, currentRow, currentColumn);
		/*cout <<"Moving from: " << mazeStack.PeekX() << " " << mazeStack.PeekY() << " To: " << currentRow << " " << currentColumn + 1 << endl;*/
		dynMaze[currentRow][currentColumn] = '#';
		currentColumn = currentColumn + 1;
	}

	//Look South
	else if (dynMaze[currentRow + 1][currentColumn] == ' ')
	{		
		mazeStack.Push(0, currentRow, currentColumn);
		/*cout <<"Moving from: " << mazeStack.PeekX() << " " << mazeStack.PeekY() << " To: " << currentRow + 1 << " " << currentColumn << endl;*/
		dynMaze[currentRow][currentColumn] = '#';
		currentRow = currentRow + 1;
	}

	//Look West
	else if (dynMaze[currentRow][currentColumn - 1] == ' ')
	{
		mazeStack.Push(0, currentRow, currentColumn);
		/*cout <<"Moving from: " << mazeStack.PeekX() << " " << mazeStack.PeekY() << "To: " << currentRow << " " << currentColumn - 1 << endl;*/
		dynMaze[currentRow][currentColumn] = '#';
		currentColumn = currentColumn - 1;
	}

	//Can't find a move
	else
	{
		/*cout << "Can't move, mark location, pop stack." << endl;*/
		dynMaze[currentRow][currentColumn] = '.';
		currentRow = mazeStack.PeekX();
		currentColumn = mazeStack.PeekY();
		mazeStack.Pop();
		/*cout << "New Top of Stack: " << mazeStack.PeekX() << " " << mazeStack.PeekY() << endl;*/			
	}

}



//ostream& operator<<(ostream& output, LinkedList& list)
//{
//	Node *currNode = list.first;
//
//	while (currNode != NULL)
//	{
//		output << "data = " << currNode->data << endl;
//
//		currNode = currNode->next;
//	}
//
//	return output;
//}
