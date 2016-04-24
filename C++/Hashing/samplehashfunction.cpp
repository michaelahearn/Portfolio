// samplehashfunction.cpp : Super simple application to demonstrate a simple
// hashing function. It converts an input string in to an integer index 
// to be used later in a table.
//
//	Author: Hal O'Connell
//	April 2015
//

#include "stdafx.h"

#include <iostream> 
#include <string>

using namespace std;

#define SIZE 47  // Size of the sample hash table - note the use of a prime number

#define DEBUG TRUE;


int hashfn(string keyString)
// Accepts an input string and returns the calculated index (to use later with a hash table)
{
	int i, sum, address;

	sum = 0;
	int len = keyString.length();
	for (i = 0; i < len; i++)
		sum += (int)keyString[i]; // cast each character of the string as int to get ascii value

	// the modulus, or remainder, of integer division gives a result between 0 and SIZE-1, perfect for an index
	address = sum % SIZE;


#ifdef DEBUG
	cout << "DEBUG - Sum: " << sum << "  Address: " << address << "\n\n";
#endif
	return address;
}


int main(int argc, char* argv[])
{
	string keyString;
	int address;

	cout << "\nEnter string to hash: ";
	getline(cin, keyString);
	address = hashfn(keyString);
	cout << "\nHashed Address: " << address << "\n";
	cin.ignore();
	return 0;
}

