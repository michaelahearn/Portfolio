#Michael Ahearn - W0202544 - Gap Analysis - Advanced Object Oriented Programming
#A program for learning basic Python concepts, as explored in https://docs.python.org/3/tutorial/ (and other examples as indicated).
#Scroll or CTRL-F to "Main Menu" to see the program structure, and function names.

##################################################
#Math Functions for BV Section
##################################################
def add(num1, num2):
    return num1 + num2

def subtract(num1, num2):
    return num1 - num2

def multiply(num1, num2):
    return num1 * num2

def divide(num1, num2):
    return num1 / num2    

##################################################
#Basic Data Types Category
##################################################

def basicDataTypes():
#Menu string
    bVMenu = """
    Topics:
    Option 1 - Numbers
    Option 2 - Strings
    Option 3 - ListsBasic Data Types
    Option b - Back To Main
    """    

    userChoice = ""#Holds user's menu choice

    #Get user menu choice. Only go back to main if user chooses b.
    while userChoice != 'q':
        print(bVMenu)#Print menu
        userChoice = input('Choose a topic: ')#Get user choice

        #Numbers section. Allows the user to enter two numbers and an operator.
        if userChoice == '1':
            print('Numbers Demonstration')                    
            num1 = float(input('Enter a number:'))#Get first number and cast to float.
            operator = input('Enter an operator:')#Get operator.
            num2 = float(input('Enter a second number:'))#Get second number and cast to float.            
            if(operator == '+'): #Figure out which operator is being used, and apply the corresponding function. 
                num3 = add(num1, num2)
                print('Result: ', num3)
            elif(operator == '-'):
                num3 = subtract(num1, num2)
                print('Result: ', num3)
            elif(operator == '*'):
                num3 = multiply(num1, num2)
                print('Result: ', num3)
            elif(operator =='/'):
                num3 = divide(num1, num2)
                print('Result: ', num3)
            else:
                print('Invalid Operator')#Print the result. 
            
        #Strings section           
        elif userChoice == '2':
            #Demonstrates concatenation by creating two string variables and adding them together. 
            print('Concatenation Test:')
            string1 = "String1"
            string2 = "String2"
            string3 = string1 + string2
            print('String 1:', string1)
            print('String 2:', string2)            
            print('Concatenation:', string3)                     
            print('\nImmutability test.')

            #Demonstrates string immutability by displaying a string's memory address before/after "altering" it. 
            print('Strings in Python are immutable, which means that when you change the "value" of the string, it does not alter the contents in memory at the location of that string. A new string is created, and the old one is eventually removed through garbage collection.\n');             
            print('String1:', string1)
            print('String 1 Memory Address:', (hex(id(string1))))
            string1 += string1
            print('String1 += String1:', string1)
            print('String1 += String1 Memory Address:', (hex(id(string1))))

        #Lists section. Demonstrates creation of and iteration through lists. Uses examples from http://stackoverflow.com/questions/6667201/how-to-define-two-dimensional-array-in-python, http://www.dotnetperls.com/2d-python, http://www.i-programmer.info/programming/python/3942-arrays-in-python.html?start=1, http://www.stev.org/post/2012/02/22/Python-2d-Arrays-dont-work.aspx, http://www.kosbie.net/cmu/fall-11/15-112/handouts/notes-2d-lists.html
        elif userChoice == '3':

            #set array width / height. 
            maxX = 5
            maxY = 5

            #Create static list and print it. 
            print('One Dimensional 5 Item List:')
            listSet = [0, 1, 2, 3, 4]

            for i in range(maxX):
                print(listSet[i])

            print('')

            print('Creating a SubList From First 3 elements: ', listSet[0:3])

            print('')

            #Create dynamic list and print it. 
            print('One Dimensional 5 Element List - Set With For Loop');
            list1D = [];

            for i in range(maxX):
                list1D.append(i)#Append adds an item to the end of a list. 
                
            for i in range(maxX):
                print(list1D[i])

            print('');

            #Create dynamic two-dimensional list and print it. 
            print('Two Dimensional List:')
            list2D = [[0 for i in range(maxX)] for j in range(maxY)] #Uses a comprehension to initialize the list with zeroes.

            #Demonstrates creating a list where each row item has the same value.
            for row in range(0, maxX):
                for column in range(0, maxY):
                    list2D[row][column] = row;            

            for row in list2D:
                for column in row:
                    print(column, end='')
                print(end='\n')

            print('Item at coordinates 0, 4:', list2D[0][4])

            print('')

            #Demonstrates creating a list where each column item has the same value. 
            for row in range(0, maxX):
                for column in range(0, maxY):
                    list2D[row][column] = column

            for row in list2D:
                for column in row:
                    print(column, end='')
                print(end='\n')

            print('Item at coordinates 0, 4:', list2D[0][4])
      
        #Back to main
        elif userChoice == 'b':
            print('Back To Main')
            break

        #Invalid option
        else:
            print('Invalid Option, choose again')


##################################################
#Comparison Functions for Conditionals / Loops
##################################################
#Function that tests if one number is greater than another. 
def greater(num1, num2):
    if num1 > num2:
        return True
    else:
        return False    
#Function that tests if one number is greater than another. 
def lesser(num1, num2):
    if num1 < num2:
        return True
    else:
        return False 
#Function that tests if two numbers are equal.
def equal(num1, num2):
    if num1 == num2:
        return True
    else:
        return False


##################################################
#Conditionals/Loops Category
##################################################

def conditionalsLoops():
#Menu string
    bVMenu = """
    Topics:
    Option 1 - If
    Option 2 - For
    Option 3 - While
    Option 4 - Range
    Option 5 - Break / Continue / Else
    Option b - Back To Main
    """
    

    userChoice = ""#Holds user's menu choice

    #Get user menu choice. Only go back to main if user chooses b.
    while userChoice != 'q':
        print(bVMenu)#Print menu
        userChoice = input('Choose a topic: ')#Get user choice
        print('')
        #If section. 
        if userChoice == '1':                  
            num1 = float(input('Enter a number:'))#Get first number and cast to float.
            operator = input('Enter an operator (>, <, or =) to test:')#Get operator.
            num2 = float(input('Enter a second number:'))#Get second number and cast to float.
            if(operator == '>'):
                result = greater(num1, num2)
                print('Result: ', result)
            elif(operator == '<'):
                result = lesser(num1, num2)
                print('Result: ', result)
            elif(operator == '='):
                result = equal(num1, num2)
                print('Result: ', result)            
            else:
                print('Invalid Operator')
            
        #For section. 
        elif userChoice == '2':
            print('For Loop')
            print('''The standard For is similar to foreach in other languages, in that its purpose is to iterate through items in a sequence rather than through a range with a defined beginning / end. In this case, a list of words is displayed in order: \n''')
            forLoopDemo = ['This', 'is', 'a', 'for', 'loop.']#Create list to iterate through.
            forCounter = 0 #Shows loop iteration
            for word in forLoopDemo: #For all the items in forLoopDemo
                forCounter += 1 #Add 1 to the forCounter.
                print(forCounter, word)#Print the word associated with this iteration

        #While section
        elif userChoice == '3':
            print('While loops run until a condition is met eg. a number of loops')
            whileCounter = 0
            loopNumber = int(input('Enter number of loops: '))
            while whileCounter < loopNumber:
                whileCounter += 1
                print(whileCounter, 'Loop')    

        #Range section
        elif userChoice == '4':
            print('''Range creates a sequence of numbers, given an end point and optional starting point (as in the second example).\n''')                  

            print('for i in range(5)')
            for i in range(5):
                print(i)
            print('for i in range(2, 4)')
            for i in range(2, 4):
                print(i)

        #Break / Continue / Else section
        elif userChoice == '5':
            print('''\nBreak ends the loop that contains it, preventing it from continuing.''')

            print('This loop from 0-4 is set to break if i = 2')
            for i in range(5):                
                if(i == 2):
                    break
                print(i)
            print('''\nContinue can be used to skip a particular loop iteration.''')
            print('This loop from 0-4 is set to continue if i = 2')
            for i in range(5):                
                if(i == 2):
                    continue
                print(i)

            print('''\nElse can be used to handle a condition that didn't occur in a loop.''')
            print('''This loop checks for 5 in range 0-4, and outputs a message that it could not be found.''')
            for i in range(5):
                print(i)
                if(i == 5):                    
                    print('5 Has been found')
            else:
                print('5 Has not been found')
                

        #Back to main
        elif userChoice == 'b':
            print('Back To Main')
            break

        #Invalid option
        else:
            print('Invalid Option, choose again')

##################################################
#Data Structures Category
##################################################
def dataStructures():
#Menu string
    dSMenu = """
    Topics:
    Option 1 - Tuples
    Option 2 - Sets
    Option 3 - Dictionaries
    Option b - Back to Main
    """    

    userChoice = ""#Holds user's menu choice

    #Get user menu choice. Only go back to main if user chooses b.
    while userChoice != 'q':
        print(dSMenu)#Print menu
        userChoice = input('Choose a topic: ')#Get user choice

        #Tuples section. Demonstrates the creation of and iteration through a tuple. Tuples items are immutable, so tuple[0] = 'Test' will throw an error. Tuples can, however, contain mutable objects. 
        if userChoice == '1':
            tuple = 'tupleItem1', 'tupleItem2', 'tupleItem3'

            for item in tuple:
                print(item)

            print('Item At Tuple[0]:', tuple[0])
            
        #Sets section. Sets are unordered collections that don't contain any duplicate elements.            
        elif userChoice == '2':
            set = {'Car', 'Truck', 'Plane', 'Train', 'Train'}
            print('Set (duplicates removed automatically):', set)
            print('');
            print('Is Car in Set?', 'Car' in set)
            print('Is Boat in Set?', 'Boat' in set)

        #Dictionaries section. A dictionary is a set of key-value pairs, basically equal to a hashmap or hash table in other languages. 
        elif userChoice == '3':
            dictionary = {'Bob': '555-5555', 'Jack': '444-4444'}
            print('Dictionary Phone Directory:', dictionary)
            print('Number for Bob:', dictionary['Bob'])
      
        #Back to main
        elif userChoice == 'b':
            print('Back To Main')
            break;

        #Invalid option
        else:
            print('Invalid Option, choose again')

            
##################################################
#File Input / Output Section
##################################################
def fileIO():
#Menu string
    menu = """
    Topics:
    Option 1 - File Output / Input
    Option b - Back To Main
    """    

    userChoice = ""#Holds user's menu choice

    #Get user menu choice. Only go back to main if user chooses b.
    while userChoice != 'q':
        print(menu)#Print menu
        userChoice = input('Choose a topic: ')#Get user choice

        #File Input / Output section. Creates a file, puts some text in it, and then reads it back in order; for the second argument of 'open' 'w' opens a file for (over)writing, 'r' opens it for reading, and 'a' opens it for appending. 
        if userChoice == '1':
            
            fileString = 'File Write Test\n'
            file = open('fileTest.txt', 'w')
            file.write(fileString)        
            file.close()

            file = open('fileTest.txt', 'a')
            file.write('File Append Test\n')
            file.close()

            file = open('fileTest.txt', 'r')
            print(file.read())#This reads the entire file. To get one line, use readline();
            file.close()
 
        #Back to main
        elif userChoice == 'b':
            print('Back To Main')
            break;

        #Invalid option
        else:
            print('Invalid Option, choose again')


##################################################
#Classes Category
##################################################

class Car:

    _type = 'Car' #This variable is shared by all instances of this class. The initial underscore is an accepted convention indicating
                    #that this is a variable that is not to be accessed directly or altered by other programmers. However, it doesn't specifically prevent them from doing so.  

    def __init__(this, pMake, pModel):#These variables are unique to each instance of this class.            
        this.make = pMake
        this.__model = pModel #This variable makes used of "Name mangling". The double underscores tell Python to add the class name to the start of the variable, so that it can't be directly accessed easily. 
        this.wheels = 4
        
    def getModel(this):
        return this.__model
  
    def setWheels(this, pWheels):
        this.wheels = pWheels
    
class SuperClass:
    testStatic = "Shared Member From SuperClass"
    def __init__(this):
        this.tester = 'Unique Member From SuperClass'

class SubClass(SuperClass):
    def getTester(this):
        return this.tester
        
def classes():
#Menu string
    menu = """
    Topics:
    Option 1 - Empty Class
    Option 2 - Actual Class
    Option 3 - Inheritance
    Option b - Back To Main
    """    

    userChoice = ""#Holds user's menu choice

    #Get user menu choice. Only go back to main if user chooses b.
    while userChoice != 'q':
        print(menu)#Print menu
        userChoice = input('Choose a topic: ')#Get user choice

        #Empty class section. Empty classes can be used in a manner similar to structs in other languages, in that they allow you to created a container for data. 
        if userChoice == '1':
            class Customer:
                pass

            customerTest = Customer()
            customerTest.name = 'Frank Thompson'
            customerTest.number = '555-5555'
            customerTest.age = 55

            print('Customer Test Class:', customerTest.name, customerTest.number, customerTest.age)

            
        #Class Section. Demonstrates instantiating a class with arguments, and getting / setting variables.            
        elif userChoice == '2':            
            print('Car Information Class:')            
            carTest = Car('G6', 'Pontiac')
            carTest.setWheels(input('Enter number of wheels:'))
            print('Make:', carTest.make, '\nModel:', carTest.getModel(), '\nWheels:', carTest.wheels)

        #Superclass section. Demonstrates instantiating a subclass and printing a member variable inherited from the superclass. 
        elif userChoice == '3':
            subClassTest = SubClass()
            print(subClassTest.getTester())
            print(subClassTest.testStatic)
      
        #Back to main
        elif userChoice == 'b':
            print('Back To Main')
            break;

        #Invalid option
        else:
            print('Invalid Option, choose again')

##################################################
#Blank Category
##################################################
def blankFunction():
#Menu string
    menu = """
    Topics:
    Option 1 - ""
    Option 2 - ""
    Option 3 - ""
    Option b - Back To Main
    """    

    userChoice = ""#Holds user's menu choice

    #Get user menu choice. Only go back to main if user chooses b.
    while userChoice != 'q':
        print(menu)#Print menu
        userChoice = input('Choose a topic: ')#Get user choice

        #Numbers section. Allows the user to enter two numbers and an operator.
        if userChoice == '1':
            print('Choice1')
            
        #Strings section           
        elif userChoice == '2':
            print('Choice2')

        #Lists section. Demonstrates creation of and iteration through lists. Uses examples from http://stackoverflow.com/questions/6667201/how-to-define-two-dimensional-array-in-python, http://www.dotnetperls.com/2d-python, http://www.i-programmer.info/programming/python/3942-arrays-in-python.html?start=1, http://www.stev.org/post/2012/02/22/Python-2d-Arrays-dont-work.aspx, http://www.kosbie.net/cmu/fall-11/15-112/handouts/notes-2d-lists.html
        elif userChoice == '3':
            print('Choice3')
      
        #Back to main
        elif userChoice == 'b':
            print('Back To Main')
            break

        #Invalid option
        else:
            print('Invalid Option, choose again')


    
##################################################
#Main Menu
##################################################

#String holding main menu selections. Triple quotes create string as-is, displaying it exactly as typed.
#Use CTRL-F (eg. Basic Variables) or scroll up to find code for each section.
topMenu ="""
Welcome to Python Explorer

Option 1 - Basic Data Types
Option 2 - Conditionals/Loops
Option 3 - Data Structures
Option 4 - File Input / Output
Option 5 - Classes
Option 6 - Blank Category
Option 7 - Zen of Python
Option q - Quit
"""

tMChoice = '' #String holding user's menu choice. 

#Get user menu choice. Only exit program if user chooses q.
while tMChoice != 'q':
    print(topMenu) #Print menu.
    tMChoice = input('Please choose a category: ')
    if tMChoice == '1':
        basicDataTypes()
    elif tMChoice == '2':
        conditionalsLoops();
    elif tMChoice == '3':
        dataStructures()
    elif tMChoice == '4':
        fileIO()
    elif tMChoice == '5':
        classes()
    elif tMChoice == '6':
        blankFunction();
    elif tMChoice == '7':
        import this
    elif tMChoice == 'q':
        print('Quitting Program')
    else:
        print('Invalid Option, choose again')

#http://stackoverflow.com/questions/743164/emulate-a-do-while-loop-in-python
        





    
