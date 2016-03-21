#ifndef VALIDATOR_H
#define VALIDATOR_H
#include <iostream>
#include <string>
#include <regex>
using namespace std;
class Validator
{

private:
	string regexFileName = "[a-zA-Z0-9-_ ]+\.[a-zA-Z0-9-_]+"; /*"[A-Za-z0-9_-]+.[A-Za-z0-9_-]+";*///"(\w{1}:(\\[\w\d\s]+)+.[\w\d]+)";//Regex created using http://www.regexr.com and https://regex101.com/. 
	regex regMatcher = regex(regexFileName);

public: 

	bool Validate(string enteredName)
	{
		if (regex_match(enteredName, regMatcher) && enteredName != "")
		{
			/*cout << "Match" << endl;*/
			return true;
		}

		else
		{
			/*cout << "No Match" << endl;*/
			return false;
		}
	}
};
#endif
