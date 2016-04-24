#include <iostream> 
#include <string>
#include <fstream>
#include <conio.h>
#include <vector>
#include <algorithm>
#include "Node.h"
#include "LinkedList.h"

using namespace std;

#define SIZE 47  // Size of the sample hash table - note the use of a prime number

#define DEBUG TRUE;

//Class example.
int hashfn(string keyString)
// Accepts an input string and returns the calculated index (to use later with a hash table)
{
	int i, sum, address;

	sum = 0;
	int len = keyString.length();
	for (i = 0; i < len; i++)
	{
		sum += (int)keyString[i]; // Cast each character of the string as int to get ascii value.
	}	

	// The modulus, or remainder, of integer division gives a result between 0 and SIZE-1, perfect for an index
	address = sum % SIZE;


//#ifdef DEBUG
//	cout << "DEBUG - Sum: " << sum << "  Address: " << address << "\n\n";
//#endif
	return address;
}

int main(int argc, char* argv[])
{
	string filePath = "C:\\Users\\Michael\\Desktop\\Hashing\\Hashing\\Debug\\";//Path to the files used by the program.	
	int address;//Holds the hashmap index of the word being inserted or searched for. 
	LinkedList * dictionaryMap[SIZE];//Hashmap index array.	
	bool wordFound;//Used to determine whether or not a word has been found. 
	const int dictionarySize = 64;//Number of words in the dictionary. 
	string dictionaryArray[dictionarySize];//Dictionary array.
	ifstream dictionaryFile(filePath + "dictionary.txt");//Dictionary file stream.
	ifstream checkFile(filePath + "mispelled.txt");//Misspelled words file.
	string checkLine;//String used to hold each line from the file being checked for parsing.
	vector<string> checkFileVector;//Used to hold each word from the file being checked. 
	vector<string> misspelledWords;//Used to hold misspelled words. 

	//Used if dictionary file can't be found.
	if (!dictionaryFile)
	{
		cout << "Couldn't open data file";
		_getch();
		exit(102);//http://stackoverflow.com/questions/19307364/how-to-read-chars-from-a-txt-file-to-a-2d-array-c
	}

	//Get all lines in the dictionary file and insert them into the dictionary array.
	for (int i = 0; i < dictionarySize; i++)
	{
		getline(dictionaryFile, dictionaryArray[i]);		
	}
	
	//Initialize hashmap array
	for (int i = 0; i < SIZE; i++)
	{
		dictionaryMap[i] = new LinkedList;
	}

	//Iterate through dictionary array, hash each word, and insert as a linked list item in hashmap.
	for (int i = 0; i < dictionarySize; i++)
	{		
		address = hashfn(dictionaryArray[i]);		
		dictionaryMap[address]->Add(dictionaryArray[i]);		
	}	
	
	
	//Display Dictionary
	for (int i = 0; i < SIZE; i++)
	{
		cout << "Index: " << i << " ";
		dictionaryMap[i]->DisplayList();
	}

	//Used if file to be spellchecked can't be opened.
	if (!checkFile)
	{
		cout << "Couldn't open data file";
		cin.ignore();
		exit(102);//http://stackoverflow.com/questions/19307364/how-to-read-chars-from-a-txt-file-to-a-2d-array-c
	}
	
	//Get all of the words from the file to be spellchecked.
	while (!checkFile.eof())
	{
		getline(checkFile, checkLine, ' ');//Getline up to the next space character. 
		checkLine.erase(remove_if(checkLine.begin(), checkLine.end(), [](char c) { return !isalpha(c); }), checkLine.end());//Remove all non-alpha characters - From http://stackoverflow.com/questions/28491954/remove-non-alphabet-characters-from-string-c
		if (checkLine != "")//If anything remains in the string, it must be a word. 
		{
			transform(checkLine.begin(), checkLine.end(), checkLine.begin(), ::tolower); //Convert word to lower case - From http://stackoverflow.com/questions/313970/how-to-convert-stdstring-to-lower-case
			checkFileVector.push_back(checkLine);//Push word to the end of the vector;
		}
	}

	//Close the checkfile
	checkFile.close();

	//Search the vector of words from the checkfile against the dictionary hashmap.	
	for (int i = 0; i < checkFileVector.size(); i++)
	{
		address = hashfn(checkFileVector[i]);//Get the dictionary index by hashing the word
		wordFound = dictionaryMap[address]->Search(checkFileVector[i]);//Determine whether or not the word was found in the dictionary. 
		if (wordFound == false)
		{
			misspelledWords.push_back(checkFileVector[i]);//If the word wasn't found, it must be a misspelled word.
		}
		
	}

	//Output list of misspelled words. 
	cout << endl << "Misspelled Words: " << endl;
	for (int i = 0; i < misspelledWords.size(); i++)
	{
		cout << misspelledWords[i] << endl;
	}

	cin.ignore();
	return 0;
}



