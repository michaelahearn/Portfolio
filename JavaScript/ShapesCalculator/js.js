var display = document.getElementById("display");
var shapesArray = []; 
var shapesCounter = 0;
var circleCounter = 0;
var triangleCounter = 0;
var squareCounter = 0;
var rectangleCounter = 0;

function Shape()
{	
	function area()
	{
		display.innerHTML += "Test";
	}
}

function Circle(pRadius)
{		
	Shape.call(this);	
	this.shapeType = "Circle";
	this.radius = pRadius;
	this.displayString = "Circle Radius: " + this.radius + "<br>" + "Circle Area: " + this.area().toFixed(3) + "<br><br>";		
	var countCircle = function()
	{
		var circleCount = 0;
		return function countCircle(){return circleCount += 1};
	}	
}

// Inheritance
Circle.prototype = Object.create(Shape.prototype);

// Override - Is this necessary, since it works when just in the constructor function?
Circle.prototype.area = function() 
{
    var pi = 3.1415926535897932384676;
	return pi * (this.radius * this.radius);	
};

function addCircle(pRadius)
{
	var circle = new Circle(pRadius);
	circleCounter += 1;
	if(shapesArray.length != 10)
	{
		shapesArray.push(circle);
	}
	Display();
}

function Square(pSideLength)
{
	Shape.call(this)
	this.shapeType = "Square";
	this.sideLength = pSideLength;	
	this.displayString = "Square Side Length: " + this.sideLength + "<br>" + "Square Area: " + this.area().toFixed(3) + "<br><br>";		
}

Square.prototype = Object.create(Shape.prototype);
Square.prototype.area = function()
{
	return (this.sideLength * this.sideLength);
}

function addSquare(pSideLength)
{
	var square = new Square(pSideLength);
	squareCounter += 1;
	if(shapesArray.length != 10)
	{
		shapesArray.push(square);
	}
	Display();
}

function Rectangle(pLongSideLength, pShortSideLength)
{
	Shape.call(this);
	this.shapeType = "Rectangle";
	this.longSideLength = pLongSideLength;
	this.shortSideLength = pShortSideLength;	
	this.displayString = "Rectangle Long Side Length: " + this.longSideLength + "<br>" + "Rectangle Short Side Length: " + this.shortSideLength + "<br>" + "Rectangle Area: " + this.area().toFixed(3) + "<br><br>";
}
Rectangle.prototype = Object.create(Shape.prototype);
Rectangle.prototype.area = function()
{
	return(this.longSideLength * this.shortSideLength);
}

function addRectangle(pLongSideLength, pShortSideLength)
{
	var rectangle = new Rectangle(pLongSideLength, pShortSideLength);
	rectangleCounter += 1;
	if(shapesArray.length != 10)
	{
		shapesArray.push(rectangle);
	}
	Display();
}

function Triangle(pBase, pHeight)
{
	Shape.call(this)
	this.shapeType = "Triangle";
	this.base = pBase;
	this.height = pHeight;	
	this.displayString = "Triangle Base: " + this.base + "<br>" + "Triangle Height: " + this.height + "<br>" + "Triangle Area: " + this.area().toFixed(3) + "<br><br>";		
}

Triangle.prototype = Object.create(Shape.prototype);
Triangle.prototype.area = function()
{
	return ((this.base * this.height) / 2);
}

function addTriangle(pBase, pHeight)
{
	var triangle = new Triangle(pBase, pHeight);
	triangleCounter += 1;
	if(shapesArray.length != 10)
	{
		shapesArray.push(triangle);
	}
	Display();
}

function Display()
{	
	display.innerHTML = "";
	if(shapesArray.length !== 0)
	{		
		for(i = 0; i < shapesArray.length; i++)
		{
			display.innerHTML += "Shape " + (i + 1) + ":<br>";
			display.innerHTML += "Shape Type: " + shapesArray[i].shapeType + "<br>";
			display.innerHTML += shapesArray[i].displayString;		
		}	
		display.innerHTML += "-------------------------------------<br><br>";			
		display.innerHTML += "Total Number of Shapes: " + shapesArray.length + "<br>";	
		display.innerHTML += "Total Number of Circles: " + circleCounter + "<br>";	
		display.innerHTML += "Total Number of Squares: " + squareCounter + "<br>";
		display.innerHTML += "Total Number of Rectangles: " + rectangleCounter + "<br>";
		display.innerHTML += "Total Number of Triangles: " + triangleCounter + "<br>";	
			
	}	
}