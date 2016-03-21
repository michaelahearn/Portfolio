//File input example from class.
#include <iostream>
#include <string>
#include <conio.h>
#include "converter.h"
#include "validator.h"
using namespace std;

int main()
{
	string fileInName;
	string fileOutName;	
	Validator validator;
	bool loop = true;;
	cout << "Enter input file name (filename.extension): ";
	cin >> fileInName;
	while (loop == true)		
		{
			if (validator.Validate(fileInName) == true)
			{
				loop = false;
			}

			else
			{
				loop = true;
				cout << "Invalid file name." << endl;
				cout << "Enter input file name (filename.extension): ";
				cin >> fileInName;
			}
		}

	cout << "Enter output file name (filename.extension): ";
	cin >> fileOutName;
	loop = true;	
	while (loop == true)
	{
		if (validator.Validate(fileOutName) == true)
		{
			loop = false;
		}

		else
		{
			loop = true;
			cout << "Invalid file name." << endl;
			cout << "Enter output file name (filename.extension): ";
			cin >> fileOutName;
		}
	}

	Converter converter(fileInName, fileOutName);
	converter.fileOperation();
	cout << "File conversion completed";
	_getch();	
	return 0;
}

