//Class accepts a CPP file and outputs a converted html file, adding <PRE> and </PRE> tags to the start / end, and replacing "<" with "&lt;" and > with "&gt;".
#ifndef CONVERTER_H
#define CONVERTER_H
#include <iostream>
#include <fstream>
#include <string>
#include <stdexcept>
using namespace std;

class Converter
{
private:
	ifstream fin;//Input file stream. 
	ofstream fout;//Output file stream. 
	string fileInName = "OriginalCPP.cpp";//String holding input file name.
	string fileOutName = "OriginalCPP.html";//String holding output file name. 
	string line;//String holding each line received from input file.

	struct fileOpenException : public exception //Displays message when file doesn't open correctly. Derived from class example. 
	{
		const char * what() const throw ()
		{
			return "File Open Exception";
		}
	};

	struct fileCloseException : public exception//Displays message when file doesn't close properly. Derived from class example. 
	{
		const char * what() const throw ()
		{
			return "File Close Exception";
		}
	};

	struct fileReadWriteException : public exception //Displays message when file doesn't read or write correctly. Derived from class example. 
	{
		const char * what() const throw ()
		{
			return "File Read/Write Exception";
		}
	};

public: 
	Converter(string nameIn, string nameOut)
	{
		fileInName = nameIn;
		fileOutName = nameOut;
	};

	string convert(string lineIn)//Accepts a line string and removes "<" and ">" characters from it, replacing them with "&lt;" and "&gt;". 
	{
		string string1;//Holds everything in input string that occurs before search character. 
		string string2;//Holds everything in input string that occurs after search character
		
		for (int i = 0; i < lineIn.length(); i++)//For the length of the input string. 
		{
				if (lineIn.substr(i, 1) == "<" || lineIn.substr(i, 1) == ">")//If the input string contains either a "<" or a ">". 
				{					
					string1 = lineIn.substr(0, i);//Make string1 equal to everything that occurs before the "<" or ">".
					if (lineIn.substr(i, 1) == "<")//If a "<" is found. 
					{
						string1.append("&lt;");//Add "&lt;" to the end of the first string. 
					}
					else
					{
						string1.append("&gt;");//Add "&gt;" to the end of the first string. 
					}
					string2 = lineIn.substr(i + 1, lineIn.length());//Make string2 equal to everything that occurs after the ">" or "<".
					lineIn = string1 + string2;//Bring string1 and string2 together, replacing lineIn. 
				}
		}
		return lineIn;//Return line string. 
	}

	void fileOperation()
	{	
		//Open input file. Throw exception and display message if file doesn't open properly. 			
		try
		{
			fin.open(fileInName);
			if (fin.fail())
			{
				throw fileOpenException();
			}
		}
		catch (fileOpenException& e)
		{
			std::cout << e.what() << std::endl;
			std::cout << "Input file not opened" << std::endl;
		}

		//Open output file. Throw exception and display message if file doesn't open properly. 		
		try
		{
			fout.open(fileOutName);
			if (fout.fail())
			{
				throw fileOpenException();
			}
		}
		catch (fileOpenException& e)
		{
			std::cout << e.what() << std::endl;
			std::cout << "Output file not opened" << std::endl;
		}
		
	//fin.exceptions(ifstream::failbit | ifstream::badbit); //NOT WORKING. BREAKS IF TURNED ON. CAN'T COMPLETE STANDARD EXCEPTIONS WITHOUT. 
	//fout.exceptions(ofstream::failbit | ofstream::badbit);
	//If both files open successfully. 
	if (fin.is_open() && fout.is_open())
	{		
		fout << "<PRE>" << endl;//Add a <PRE> tag to the start of the output stream.			
		
		while (!fin.eof())//While the file has lines remaining. 
		{
			getline(fin, line);//Get a line and assign it to the line string. 
			fout << convert(line) << endl;//Run each line through the conversion function and output it to the file. 
		}
		
		fout << "</PRE>";//Add a <PRE> tage to the end of the output stream. 
		
		//Close files.
		fin.close();
		fout.close();
	}
	else//If the files don't open successfully. 
	{
		//cout << "Input or output file wasn't open. Conversion cannot proceed" << endl;//Inform the user. 
	}	
	
	}
};
#endif


// ios::exceptions from http://www.cplusplus.com/reference/ios/ios/exceptions/
//#include <iostream>     // std::cerr
//#include <fstream>      // std::ifstream
//
//int main() {
//	std::ifstream file;
//	file.exceptions(std::ifstream::failbit | std::ifstream::badbit);
//	try {
//		file.open("test.txt");
//		while (!file.eof()) file.get();
//		file.close();
//	}
//	catch (std::ifstream::failure e) {
//		std::cerr << "Exception opening/reading/closing file\n";
//	}
//
//	return 0;
//}

